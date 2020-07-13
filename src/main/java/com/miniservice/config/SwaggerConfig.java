package com.miniservice.config;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnClass({ApiInfo.class})
@EnableSwagger2
public class SwaggerConfig {

    private MiniPropertiesConfig miniPropertiesConfig;

    public SwaggerConfig(MiniPropertiesConfig miniPropertiesConfig) {
        this.miniPropertiesConfig = miniPropertiesConfig;
    }

    @Bean
    public Docket createRestApi(ServletContext servletContext) {
        ParameterBuilder tokenPar2 = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar2.name("AUTH_TOKEN").description("auth-token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        pars.add(tokenPar2.build());
        Predicate<RequestHandler> requestHandlerPredicate = RequestHandlerSelectors.basePackage(miniPropertiesConfig.getBasePackages());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(requestHandlerPredicate)
                .paths(PathSelectors.any())
                .build()
                .host(miniPropertiesConfig.getSwaggerHost())

                .pathProvider(new BasePathAwareRelativePathProvider(miniPropertiesConfig.getSwaggerUrl()))
                .globalOperationParameters(pars);
    }

    class BasePathAwareRelativePathProvider extends AbstractPathProvider {
        private String basePath;

        BasePathAwareRelativePathProvider(String basePath) {
            this.basePath = basePath;
        }

        @Override
        protected String applicationPath() {
            return basePath;
        }

        @Override
        protected String getDocumentationPath() {
            return "/";
        }

        @Override
        public String getOperationPath(String operationPath) {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
            return Paths.removeAdjacentForwardSlashes(
                    uriComponentsBuilder.path(operationPath.replaceFirst(basePath, "")).build().toString());
        }
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title(miniPropertiesConfig.getSwaggerTitle())
                .description(miniPropertiesConfig.getSwaggerDescription())
                .contact(new Contact(miniPropertiesConfig.getSwaggerContactName(), miniPropertiesConfig.getSwaggerContactUrl(), miniPropertiesConfig.getSwaggerContactEmail()))
                .termsOfServiceUrl("http://demo.com/")
                .version("1.0.0")
                .build();
    }

}
