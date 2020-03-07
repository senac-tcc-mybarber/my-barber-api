package com.senac.feature;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", glue = "com.senac.feature", features = "./src/feature")
public class MybarberApiApplicationTests {
    
}
