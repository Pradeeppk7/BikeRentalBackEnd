package com.api.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * RouteValidator is a component responsible for validating routes to determine if they require security/authentication.
 * It defines a list of open API endpoints that do not require authentication.
 * It provides a predicate to check if a given request is secured based on its URI path.
 */
@Component
public class RouteValidator {
	
	private static final Logger logger = LoggerFactory.getLogger(RouteValidator.class);

    // List of open API endpoints that do not require authentication
    public static final List<String> openApiEndpoints = List.of(
////            "/user/register",
            "/vehicles/types",
            "/vehicles/brandtypes",
            "/vehicles/fueltypes",
            "/vehicles/all",
//            "/vehicles/all-available-vehicles",
            "/vehicles/vehicle-by-id",
            "/user/get-by-id",
            "/bookings/get-by-confirmation-code",
    		"/user/login",
    		"/user/register",
    		"/user/validate",
    		"/user/registerwithotp",
    		"/user/sendotp"
    		
    );
    
    private static final List<Pattern> openApiPatterns = openApiEndpoints.stream()
            .map(uri -> Pattern.compile(uri + "(\\/.*)?")) // Add regex to handle wildcards
            .toList();
   

        // Predicate to check if a request is secured
        public Predicate<ServerHttpRequest> isSecured = request -> {
            String path = request.getURI().getPath();
            boolean isSecured = openApiPatterns.stream().noneMatch(pattern -> pattern.matcher(path).matches());
            if (!isSecured) {
                logger.info("Unsecured access: {}", path);
            }
            return isSecured;
        };
        // Predicate to check if a request is secured
        
//    // Predicate to check if a request is secured
//    public Predicate<ServerHttpRequest> isSecured =
//    		request -> {
//                String path = request.getURI().getPath();
//                boolean isSecured = openApiEndpoints.stream().noneMatch(uri -> path.contains(uri));
//                if (!isSecured) {
//                    logger.info("Unsecured access: {}", path);
//                }
//                return isSecured;
//            };

}

