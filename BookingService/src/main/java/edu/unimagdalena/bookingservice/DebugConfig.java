package edu.unimagdalena.bookingservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DebugConfig implements CommandLineRunner {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String url;

    @Override
    public void run(String... args) {
        System.out.println("🔍 Config actual:");
        System.out.println("URL: " + url);
        System.out.println("User: " + username);
        System.out.println("Pass: " + password);
    }
}

