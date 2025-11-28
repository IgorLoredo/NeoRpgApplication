package com.neo.game.infrastructure.web;

import org.springframework.beans.factory.annotation.Autowired;
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
    public SwaggerIndexController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @GetMapping("/index")
    public List<RouteInfo> index() {
        List<RouteInfo> routes = new ArrayList<>();

        Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> e : map.entrySet()) {
            RequestMappingInfo info = e.getKey();
            HandlerMethod hm = e.getValue();

            Set<String> patterns = info.getPatternValues();
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();

            String controller = hm.getBeanType().getSimpleName();
            String handlerMethod = hm.getMethod().getName();

            for (String pattern : patterns) {
                // Skip framework and self endpoints we don't usually want in the index
                if (pattern.startsWith("/actuator") || pattern.startsWith("/error") || pattern.startsWith("/v3/api-docs") || pattern.startsWith("/swagger")) {
                    continue;
                }

                String httpMethods = methods.isEmpty() ? "ALL" : methods.stream().map(Enum::name).collect(Collectors.joining(","));
                routes.add(new RouteInfo(httpMethods, pattern, controller, handlerMethod));
            }
        }

        routes.sort(Comparator.comparing(r -> r.path));
        return routes;
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
