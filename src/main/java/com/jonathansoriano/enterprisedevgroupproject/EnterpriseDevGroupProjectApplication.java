package com.jonathansoriano.enterprisedevgroupproject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnterpriseDevGroupProjectApplication implements CommandLineRunner {

    @Value("${spring.profiles.active:default}")
    private String activeProfile;
    public static void main(String[] args) {
        SpringApplication.run(EnterpriseDevGroupProjectApplication.class, args);
    }

    /**
     * Executes the logic after the application starts. Prints the active Spring profile
     * to the console. The active profile is determined by the `spring.profiles.active`
     * property.
     *
     * @param args command-line arguments passed to the application at startup
     * @throws Exception if an error occurs during method execution
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Active Profile: " + activeProfile);
    }

}
