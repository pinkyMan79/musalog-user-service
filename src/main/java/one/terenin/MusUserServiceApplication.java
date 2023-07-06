package one.terenin;

import com.technokratos.springbootadministrationpanel.configuration.GeneratorConfiguration;
import com.technokratos.springbootadministrationpanel.generator.GeneratorApi;
import com.technokratos.springbootadministrationpanel.generator.impl.GeneratorApiImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.net.URI;

@SpringBootApplication
@EnableDiscoveryClient
@DependsOn("generatorApi")
public class MusUserServiceApplication {

	private static GeneratorApiImpl generatorApi;

	public MusUserServiceApplication(GeneratorApi generatorApi) {
		MusUserServiceApplication.generatorApi = (GeneratorApiImpl) generatorApi;
	}

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
		ConfigurableApplicationContext run = SpringApplication.run(MusUserServiceApplication.class, args);
	}

	public static void isPackageModule(String moduleName, String packageName) {
		ModuleFinder finder = ModuleFinder.ofSystem();
		finder.findAll().forEach(e -> System.out.println(e.location().orElse(URI.create("none"))));
	}


}
