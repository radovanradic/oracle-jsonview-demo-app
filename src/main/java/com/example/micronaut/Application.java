package com.example.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.utility.DockerImageName;

public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        OracleContainer oracleContainer = new OracleContainer(DockerImageName.parse("gvenzl/oracle-free:latest-faststart")
                .asCompatibleSubstituteFor("gvenzl/oracle-xe"))
                .withDatabaseName("test");
        try {
            oracleContainer.start();
            initApp(oracleContainer);
            LOG.info("Application is ready");
        } catch (Exception e) {
            LOG.error("An error occurred", e);
            System.exit(1);
        }
    }

    /**
     * Initializes application context using datasource parameters from the underlying test container.
     *
     * @param oracleContainer the Oracle test container started before app init
     * @return the application context
     */
    private static ApplicationContext initApp(OracleContainer oracleContainer) {
        String username = oracleContainer.getUsername();
        String password = oracleContainer.getPassword();
        String url = oracleContainer.getJdbcUrl();
        ApplicationContext applicationContext = Micronaut.run(Application.class, "-jdbc-url=" + url,
                "-jdbc-username=" + username, "-jdbc-password=" + password);
        return applicationContext;
    }
}
