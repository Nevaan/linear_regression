package com.losek.regression.symbolic.web;

import com.losek.regression.symbolic.genetics.Population;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class RegressionController {

    @RequestMapping(path = "index")
    public String index() {
        return "Hello regression!";
    }

    @RequestMapping(method = RequestMethod.POST, path = "regression", consumes = "application/json", produces = "application/json")
    public Map<Double, Double> countSymbolicRegression(@RequestBody Map<Double, Double> dataset) {

        Population population = new Population();

        return dataset;
    }

}
