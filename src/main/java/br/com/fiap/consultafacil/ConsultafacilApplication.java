package br.com.fiap.consultafacil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class ConsultafacilApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultafacilApplication.class, args);
	}

	@GetMapping
	public String home() {
		return "index";
	}

}
