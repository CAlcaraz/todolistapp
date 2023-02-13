package pod.meanmachine.todolistapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String TASK_TAG = "Task";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pod.meanmachine.todolistapp.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .tags(new Tag(TASK_TAG, "CRUD operations for Task entity"))
                ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "TO-DO List API",
                "Tech Meet Sample TODO List App",
                "1.0",
                null,
                new Contact("Mean Machine", null, null),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }

}
