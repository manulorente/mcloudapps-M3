package es.codeurjc.mca.events;

import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.codeurjc.mca.events.event.Event;
import es.codeurjc.mca.events.event.EventRepository;
import es.codeurjc.mca.events.user.User;
import es.codeurjc.mca.events.user.UserRepository;

@Component
public class DatabaseInitializer {

    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @PostConstruct
	public void init() {

        if (!this.userRepository.findById(1L).isPresent()) {
            log.info("--> EMPTY DATABASE, CREATING DATA");

            // CREATE ADMIN
            User u1 = new User("admin", "admin@urjc.es", "pass", User.ROLE_ADMIN);
            userRepository.save(u1);
            
            // CREATE ORGANIZER
            User u2 = new User("Patxi", "francisco.gortazar@urjc.es", "pass", User.ROLE_ORGANIZER);
            userRepository.save(u2);

            // CREATE CUSTOMER
            User u3 = new User("Michel", "michel.maes@urjc.es", "pass", User.ROLE_CUSTOMER);
            userRepository.save(u3);

            // CREATE AN EVENT
            Calendar c1 = Calendar.getInstance(); 
            c1.set(2021, Calendar.MAY, 2, 18, 30);
            Event e1 = new Event("Concierto municipal de Móstoles", "Concierto ofrecido por ...", c1.getTime(), 19.99, 50);
            e1.setCreator(u2);
            eventRepository.save(e1);

            // ONLY FOR TESTING PURPOSES
            // IntStream.range(0,100).forEach(i -> {
            //     User u = new User("user_"+i, "user_"+i+"@urjc.es", "pass", User.ROLE_CUSTOMER);
            //     userRepository.save(u);
            // });

        } else {
            log.info("--> DATABASE WITH DATA");
        }
    }

}
