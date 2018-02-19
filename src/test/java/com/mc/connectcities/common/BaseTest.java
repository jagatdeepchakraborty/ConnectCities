package com.mc.connectcities.common;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTest {

    public String loadResource(String fileName) throws Exception {
        try {
            URI uri = BaseTest.class.getClassLoader().getResource(fileName).toURI();
            String fileContents = new String(Files.readAllBytes(Paths.get(uri)));
            return fileContents;
        } catch (Exception e) {
            throw new Exception(fileName + " doesn't exist in the classpath !!!!!");
        }
    }
}
