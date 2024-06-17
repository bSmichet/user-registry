package fr.airfrance.userregistry;

import org.springframework.boot.SpringApplication;

public class TestUserRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.from(UserRegistryApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
