package ua.pp.disik.tt;

import com.mongodb.MongoClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.pp.disik.tt.entities.Publication;
import ua.pp.disik.tt.entities.Topic;
import ua.pp.disik.tt.entities.User;
import ua.pp.disik.tt.repositories.PublicationRepository;
import ua.pp.disik.tt.repositories.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BillboardTtApplication {
	public static void main(String[] args) {
		SpringApplication.run(BillboardTtApplication.class, args);
	}

	@Bean
	CommandLineRunner init(MongoClient mongoClient,
						   Environment environment,
						   UserRepository userRepository,
						   PublicationRepository publicationRepository,
						   PasswordEncoder passwordEncoder) {
		return (args) -> {
			mongoClient.dropDatabase(environment.getProperty("spring.data.mongodb.database"));

			List<User> users = new ArrayList<>();
			for (int i = 1; i < 4; i++) {
				String index = String.valueOf(i);
				String email = "user" + index + "@test.test";
				String password = passwordEncoder.encode(email);

				users.add(new User(email, email, password));
			}

			users = userRepository.save(users);

			List<Publication> publications = new ArrayList<>();
			long currentTimestamp = Instant.now().getEpochSecond();
			for (User user : users) {
                for (int i = 1; i < 10; i++, currentTimestamp++) {
                    String index = String.valueOf(i);
                    String body = "";
                    for (int j = 0; j < 50; j++) {
                        body += ("Body" + index + " ");
                    }

                    Publication publication = new Publication(
                            user,
                            "Title" + index,
                            body,
                            Topic.getRandomTopic(),
                            currentTimestamp);
                    publications.add(publication);
                }
			}

			publications = publicationRepository.save(publications);
		};
	}
}
