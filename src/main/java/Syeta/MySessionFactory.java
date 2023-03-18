package Syeta;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Driver;
import java.sql.DriverManager;

public abstract class MySessionFactory {
    private static SessionFactory factory;

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            try {
                Driver driver = (Driver) Class.forName("org.postgresql.Driver").newInstance();
                DriverManager.registerDriver(driver);
                Configuration configuration = new Configuration();
                configuration
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                        .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                        .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/Lab6")
                        .setProperty("hibernate.connection.username", "postgres")
                        .setProperty("hibernate.connection.password", "1234")
                        .setProperty("hibernate.show_sql", "true")
                        .setProperty("hibernate.hbm2dll.auto", "update");
                configuration.addAnnotatedClass(People.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                factory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return factory;
    }
}