package com.itrex.java.lab.basemapping.service;

import org.flywaydb.core.Flyway;

import static com.itrex.java.lab.basemapping.properties.Properties.*;

public class FlywayService {

    private Flyway flyway;

    public FlywayService() {
        inti();
    }

    public void migrate() {
        flyway.migrate();
    }

    public void clean() {
        flyway.clean();
    }

    private void inti() {
        flyway = Flyway.configure()
          .dataSource(H2_URL, H2_USER, H2_PASSWORD)
          .locations(MIGRATIONS_LOCATION)
          .load();
    }

}
