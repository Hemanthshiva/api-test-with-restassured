package com.tenx.test.apisupport;

import org.junit.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.jayway.restassured.RestAssured.authentication;
import static com.jayway.restassured.RestAssured.preemptive;

/**
 * Created by hemanth.shivashankrappa on May, 2018
 */

public class RestBaseClass {

    private static Properties props = new Properties();
    private static InputStream input = null;

    @BeforeClass
    public static void endPointConfig() throws IOException {
        String filename = System.getProperty("user.dir") + "/src/test/resources/config/test_config.properties";
        input = new FileInputStream(filename);
        props.load(input);
//        String env = props.getProperty("env");

//        baseURI = props.getProperty("baseUrl");
//        basePath = props.getProperty("basePath");
        authentication = preemptive().basic("admin", "password123");
    }

}
