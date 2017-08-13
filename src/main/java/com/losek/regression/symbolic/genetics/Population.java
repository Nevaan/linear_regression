package com.losek.regression.symbolic.genetics;

import com.losek.regression.symbolic.expression.ExpressionGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Population {

    private Object lock = new Object();
    private Set<Chromosome> chromosomes = new HashSet<>();

    public Population() {

        Runnable generateSingleChromosome = () -> {
            // TODO: pass proper maxDepth value here instead hardcode
            Chromosome chromosome = new Chromosome(ExpressionGenerator.generateNode(5));
            synchronized (lock) {
                chromosomes.add(chromosome);
            }
        };

        // TODO: extract threadPool to be configurable
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Runnable> tasks = new ArrayList<>();

        for(int i = 0; i < 100; i++) {
           tasks.add(generateSingleChromosome);
        }

        CompletableFuture<?>[] futures = tasks.stream()
                                              .map(task -> CompletableFuture.runAsync(task, executorService))
                                              .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
    }

    public Set<Chromosome> getChromosomes() {
        return chromosomes;
    }
}

