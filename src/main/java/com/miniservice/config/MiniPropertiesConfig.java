package com.miniservice.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

/**
 * 系统配置
 * 当前处于写死状态
 * 如果做poc需要配置到配置中心
 */
@Configuration
public class MiniPropertiesConfig {


    private String swaggerDescription = "mini app backend service";

    private String swaggerContactName = "demoUser";


    private String swaggerContactEmail = "user@demo.com";

    private String swaggerHost = "localhost:9999";


    private String swaggerContactUrl = StringUtils.EMPTY;

    private String basePackages = "com.miniservice.controller";

    private String swaggerUrl = "/mini/api";

    private String swaggerTitle = "Demo Mini App Backend Service";

    public String getSwaggerTitle() {
        return swaggerTitle;
    }

    public void setSwaggerTitle(String swaggerTitle) {
        this.swaggerTitle = swaggerTitle;
    }

    public String getSwaggerUrl() {
        return swaggerUrl;
    }

    public void setSwaggerUrl(String swaggerUrl) {
        this.swaggerUrl = swaggerUrl;
    }

    public String getSwaggerDescription() {
        return swaggerDescription;
    }

    public void setSwaggerDescription(String swaggerDescription) {
        this.swaggerDescription = swaggerDescription;
    }

    public String getSwaggerContactName() {
        return swaggerContactName;
    }

    public void setSwaggerContactName(String swaggerContactName) {
        this.swaggerContactName = swaggerContactName;
    }

    public String getSwaggerContactEmail() {
        return swaggerContactEmail;
    }

    public void setSwaggerContactEmail(String swaggerContactEmail) {
        this.swaggerContactEmail = swaggerContactEmail;
    }

    public String getSwaggerContactUrl() {
        return swaggerContactUrl;
    }

    public void setSwaggerContactUrl(String swaggerContactUrl) {
        this.swaggerContactUrl = swaggerContactUrl;
    }

    public String getBasePackages() {
        return basePackages;
    }

    public void setBasePackages(String basePackages) {
        this.basePackages = basePackages;
    }

    public String getSwaggerHost() {
        return swaggerHost;
    }

    public void setSwaggerHost(String swaggerHost) {
        this.swaggerHost = swaggerHost;
    }
}
