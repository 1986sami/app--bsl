package com.grupo1.BSL;

import com.grupo1.BSL.models.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// este es un comentario


@RestController
@SpringBootApplication
public class BslApplication {
	@GetMapping("/Empresa/Test")
	public String testEmpresa(){
		Empresa emp = new Empresa();
		emp.setNombre("Business Software of Latin America");
		return emp.getNombre();
	}
	public static void main(String[] args) {
		SpringApplication.run(BslApplication.class, args);
	}

}
