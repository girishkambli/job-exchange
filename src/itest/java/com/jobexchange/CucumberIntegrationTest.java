package com.jobexchange;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/itest/resources",
    format = {"pretty", "json:target/cucumber.json", "lv.ctco.cukes.core.formatter.CukesJsonFormatter:target/cucumber2.json"},
    glue = {"lv.ctco.cukes", "src/itest/resources"},
    tags = {"~@ignore", "~@debug"})
public class CucumberIntegrationTest {

    @BeforeClass
    public static void setup() {
        JobExchangeApplication.main(new String[0]);
    }

}