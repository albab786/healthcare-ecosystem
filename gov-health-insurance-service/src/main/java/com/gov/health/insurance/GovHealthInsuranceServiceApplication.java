package com.gov.health.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GovHealthInsuranceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GovHealthInsuranceServiceApplication.class, args);
	}

}
