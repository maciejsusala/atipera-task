package pl.maciejsusala.atiperatask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AtiperaTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtiperaTaskApplication.class, args);
    }

}