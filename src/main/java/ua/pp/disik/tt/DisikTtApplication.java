package ua.pp.disik.tt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.pp.disik.tt.repositories.PublicationRepository;
import ua.pp.disik.tt.repositories.UserRepository;

@SpringBootApplication
public class DisikTtApplication {
	public static void main(String[] args) {
		SpringApplication.run(DisikTtApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository,
						   PublicationRepository publicationRepository) {
		return (var1) -> {

		};
	}
}
