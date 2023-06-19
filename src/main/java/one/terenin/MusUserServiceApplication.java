package one.terenin;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusUserServiceApplication {

	@Bean
	public Server server(){
		return ServerBuilder
				.forPort(8080)
		//		.addService()
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MusUserServiceApplication.class, args);
	}

}
