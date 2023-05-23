package es.codeurjc.books;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import es.codeurjc.books.repositories.UserRepository;
import es.codeurjc.books.restclients.UserClient;
import es.codeurjc.books.restclients.UserClientMicroservice;
import es.codeurjc.books.restclients.UserClientMicroserviceImpl;
import es.codeurjc.books.restclients.UserClientRepository;

@SpringBootApplication
@EnableFeignClients
public class Application {
    
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper(Arrays.asList("dozer_mapping.xml"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    
    @Bean
    @ConditionalOnProperty(value = "USERS", havingValue = "USERS_IN_MICROSERVICE")
    public UserClient userClientService(UserClientMicroservice userClientMicroService) {
        logger.info("USERS_IN_MICROSERVICE");
        return new UserClientMicroserviceImpl(userClientMicroService);
    }
    

    @Bean
    @ConditionalOnProperty(value = "USERS", havingValue = "USERS_IN_MONOLITH")
    public UserClient userClientMonolith(UserRepository userRepository) {
        logger.info("USERS_IN_MONOLITH");
        return new UserClientRepository(userRepository);
    }
    
}
