package br.com.cabeleireiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjetoCabeleireiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoCabeleireiroApplication.class, args);
	
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
		System.out.println("-*************************");
		System.err.println(bCryptPasswordEncoder.matches("130297", "$2a$10$tT6p0Q6kQ7FKi2.fkhrVCucSLxF.41zEa/HHi791l9hry6Z6WskT."));
	
	}

	
	
	
	
}
