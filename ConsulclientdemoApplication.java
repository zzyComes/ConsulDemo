package com.example.consulclientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
public class ConsulclientdemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConsulclientdemoApplication.class, args);
		ConsulInter.serviceRegister();
	}
}

