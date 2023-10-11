package az.baxtiyargil.employeemanagementws.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private final Swagger swagger = new Swagger();

    public Swagger getSwagger() {
        return swagger;
    }

    public static class Swagger {
        private String basePackage;
        private String paths;

        public String getBasePackage() {
            return basePackage;
        }

        public void setBasePackage(String basePackage) {
            this.basePackage = basePackage;
        }

        public String getPaths() {
            return paths;
        }

        public void setPaths(String paths) {
            this.paths = paths;
        }

    }
}
