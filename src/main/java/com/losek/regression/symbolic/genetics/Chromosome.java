package com.losek.regression.symbolic.genetics;

import com.losek.regression.symbolic.expression.Node;

public class Chromosome {

    private Node treeRoot;

    public Chromosome(Node treeRoot) {
        this.treeRoot = treeRoot;
    }

    public Node getTreeRoot() {
        return treeRoot;
    }
}
