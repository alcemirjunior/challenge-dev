package com.alcemirjunior.github.resources;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

public class DatabaseLifecycle implements QuarkusTestResourceLifecycleManager {

    private static PostgreSQLContainer<?> POSTEGRESQL = new PostgreSQLContainer<>("postgres:13.2");

    @Override
    public Map<String, String> start() {
        POSTEGRESQL.start();
        Map<String, String> properties = new HashMap<>();
        properties.put("quarkus.datasource.jdbc.url", POSTEGRESQL.getJdbcUrl());
        properties.put("quarkus.datasource.username", POSTEGRESQL.getUsername());
        properties.put("quarkus.datasource.password", POSTEGRESQL.getPassword());
        return properties;
    }

    @Override
    public void stop() {
        if(POSTEGRESQL != null){
            POSTEGRESQL.stop();
        }
    }
}