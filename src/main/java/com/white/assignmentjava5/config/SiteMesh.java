package com.white.assignmentjava5.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SiteMesh extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder
                .setDecoratorPrefix("/WEB-INF/decorators/")
                .addDecoratorPath("/*", "web.jsp")
                .addExcludedPath("/login")
                .addExcludedPath("/register")
                .addExcludedPath("/assets/**");
    }
}
