package az.baxtiyargil.employeemanagementws.config;

import az.baxtiyargil.employeemanagementws.config.properties.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ApplicationProperties.Swagger properties;

    public SwaggerConfig(ApplicationProperties properties) {
        this.properties = properties.getSwagger();
    }

    @Bean
    public Docket api() {

        Parameter authHeader = new ParameterBuilder()
                .parameterType("header")
                .name("Authorization")
                .modelRef(new ModelRef("string"))
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(regex(properties.getPaths()))
                .build()
                .globalOperationParameters(Collections.singletonList(authHeader));
    }
}
