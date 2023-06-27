package io.cloudtype.Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.program.email.EmailView;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping(value="/login")
	public String loginView() {
		return "login";
	}
	
	@GetMapping("/mv.do")
	public void mailView() throws Exception {
		EmailView ev = new EmailView();
		ev.mailTest();
	}

}
