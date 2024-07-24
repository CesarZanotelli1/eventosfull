package br.com.czar.eventosfull.containers;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.atomic.AtomicBoolean;

public class PostgresTestContainer implements BeforeAllCallback {

    private static AtomicBoolean containerStarted = new AtomicBoolean(false);

    @Container
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
            .withDatabaseName("test")
            .withUsername("postgres")
            .withPassword("postgres");

    @Override
    public void beforeAll(ExtensionContext context) {
        if (!containerStarted.get()) {
            postgres.start();

            System.setProperty("DB_URL", postgres.getJdbcUrl());
            System.setProperty("DB_USERNAME", postgres.getUsername());
            System.setProperty("DB_PASSWORD", postgres.getPassword());

            containerStarted.set(true);
        }
    }
}