package one.terenin;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class MusUserServiceApplication {

	@Bean
	public Server server(){
		return ServerBuilder
				.forPort(8080)
		//		.addService()
				.build();
	}

	@Bean
	public RestTemplate restTemplate(){
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		return restTemplateBuilder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MusUserServiceApplication.class, args);
	}

}
