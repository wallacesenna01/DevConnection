package com.br.wallaceartur.DevConnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.br.wallaceartur.DevConnection.model")
public class DevConnectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevConnectionApplication.class, args);
	}

}
