package com.maskeddev.libraryapp.config;

import com.maskeddev.libraryapp.entities.Book;
import com.maskeddev.libraryapp.entities.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private String theAllowedOrigins = "http://localhost:3000/";
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors){
        HttpMethod[] unSupportedOpertaions = {
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.PUT,
                HttpMethod.DELETE
        };

        config.exposeIdsFor(Book.class);

        config.exposeIdsFor(Review.class);

        disableHttpMethods(Book.class, config, unSupportedOpertaions);
        disableHttpMethods(Review.class, config, unSupportedOpertaions);

//        Configure CORS Mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);

    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] unSupportedOpertaions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(unSupportedOpertaions)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unSupportedOpertaions));
    }
}
