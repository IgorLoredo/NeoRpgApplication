package com.neo.game.infrastructure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Exposes a simple index of registered request mappings. Useful as a programmatic Swagger index
 * in addition to the OpenAPI/Swagger UI.
 */
@RestController
@RequestMapping("/swagger")
public class SwaggerIndexController {

    private final RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public SwaggerIndexController(@Qualifier("requestMappingHandlerMapping") RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    public static class RouteInfo {
        public String methods;
        public String path;
        public String controller;
        public String handler;

        public RouteInfo(String methods, String path, String controller, String handler) {
            this.methods = methods;
            this.path = path;
            this.controller = controller;
            this.handler = handler;
        }
    }
}
