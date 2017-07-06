package com.losek.regression.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegressionController {

    @RequestMapping("/")
    public String index() {
        return "Hello regression!";
    }

}
