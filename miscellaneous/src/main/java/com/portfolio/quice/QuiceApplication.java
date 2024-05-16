package com.portfolio.quice;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class QuiceApplication {

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(QuiceApplication.class, args);
	}

}

@Component("uuid")
class UuidService {
	public String buildUuid() {
		return UUID.randomUUID().toString();
	}
}

@Component
class Bar {
	private final Foo foo;
	private final Log log = LogFactory.getLog(getClass());

	Bar(Foo foo, @Value("#{ uuid.buildUuid() }") String uuid, @Value("#{ 2>1 }") boolean proceed) {
		this.foo = foo;
		this.log.info("UUID: " + uuid);
		this.log.info("proceed: " + proceed);
	}
}

@Component
class Foo {
}

class LoggingUuidService extends UuidService {
	@Override
	public String buildUuid() {
		LogFactory.getLog(getClass()).info("before...");
		String result = super.buildUuid();
		LogFactory.getLog(getClass()).info("after...");

		return result;
	}
}
