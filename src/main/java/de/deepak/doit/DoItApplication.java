package de.deepak.doit;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicates;

import de.deepak.doit.controller.NotesController;
import de.deepak.doit.controller.ToDoItemController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
@ComponentScan(basePackageClasses = {ToDoItemController.class, NotesController.class, DoItApplication.class})
public class DoItApplication {
    @RequestMapping("/")
	public String home() {
    	InetAddress ipAddress;
		try {
			ipAddress = InetAddress.getLocalHost();
			return ipAddress.getHostAddress();
		} catch (UnknownHostException e) {
			return "Hello Docker World";
		}
	}

    public static void main(String[] args) {
        SpringApplication.run(DoItApplication.class, args);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .build()
                .pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("doIt Notes API")
                .description("RESTful APIs for doIt Notes")
                .version("1.0")
                .build();
    }
}
