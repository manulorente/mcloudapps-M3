package es.codeurjc.users;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableFeignClients
public class Application {
    
    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();    
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
