package cf.chercheur_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChercheurServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChercheurServiceApplication.class, args);
    }

}
