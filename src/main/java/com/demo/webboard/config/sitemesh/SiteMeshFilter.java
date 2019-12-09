package com.demo.webboard.config.sitemesh;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.tagrules.html.Sm2TagRuleBundle;

public class SiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        final String emptyDeco = "/WEB-INF/decorators/emptyDecorator.jsp";

        builder
            .addTagRuleBundle(new Sm2TagRuleBundle())
            .addDecoratorPath("/user*", emptyDeco)
            .addDecoratorPath("/admin*", emptyDeco)
            .addDecoratorPath("/*", "/WEB-INF/decorators/decorator.jsp")
                .addExcludedPath("/error*")
                .addExcludedPath("/login*")
                .addExcludedPath("/pw*")
                .addExcludedPath("/**/*List")
                .addExcludedPath("/**/list/**")
                .addExcludedPath("/favicon.ico");
    }

}