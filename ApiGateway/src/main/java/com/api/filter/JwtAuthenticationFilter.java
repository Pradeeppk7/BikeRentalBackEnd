package com.api.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import com.api.util.Jwtutil;

import org.springframework.http.HttpMethod;

@Component
public class JwtAuthenticationFilter extends  AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config>  {

    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    
    @Autowired
    private Jwtutil jwtUtil;

    @Autowired
    private RouteValidator validator;
    
   
        public JwtAuthenticationFilter() {
            super(Config.class);
        }
        /**
         * apply method overrides the apply method of AbstractGatewayFilterFactory.
         * It returns a GatewayFilter that performs the authentication and authorization logic.
         *
         * @param config Configuration object for the filter (not used in this implementation)
         * @return GatewayFilter for handling authentication and authorization
         */
        @Override
        public GatewayFilter apply(Config config) {
        	   return ((exchange, chain) -> {
                   if (validator.isSecured.test(exchange.getRequest())) {
                       if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                           logger.error("Missing Authorization header");
                           throw new RuntimeException("Missing Authorization header");
                       }
                       String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                       HttpMethod method = exchange.getRequest().getMethod();
                       if (authHeader != null && authHeader.startsWith("Bearer ")) {
                           authHeader = authHeader.substring(7);
                       }
                       try {
                           jwtUtil.validateToken(authHeader);
                           String role = jwtUtil.extractRole(authHeader);
                           String path = exchange.getRequest().getURI().getPath();
                           if (!checkRoleAccess(role, path,method)) {
                               logger.error("Unauthorized access: Role '{}' does not have access to path '{}'", role, path);
                               throw new RuntimeException("Unauthorized access");
                           }
                       } catch (Exception e) {
                           logger.error("Invalid access: {}", e.getMessage());
                           throw new RuntimeException("Unauthorized access to the application");
                       }
                   }
                   return chain.filter(exchange);
               });
        }
        /**
         * checkRoleAccess method checks if a role has access to a specific path based on predefined rules.
         * 
         * @param role Role extracted from the JWT token
         * @param path Requested path from the URI
         * @return true if the role has access to the path, false otherwise
         */
        private boolean checkRoleAccess(String role, String path,HttpMethod method) {
        	 if (role.equals("ADMIN")) {
                 return true; // Admin can access everything
             } else if (role.equals("USER")) {
                 
                 return 
                		path.startsWith("/bookings/book-vehicle/") ||
                		path.startsWith("/bookings/") ||
                		path.startsWith("/bookings/booking-by-userid/") ||
                		path.startsWith("/vehicles/") ||
                		path.startsWith("/user/");
//                		path.startsWith("/vehicles/vehicle-by-id/") ||
//                		path.startsWith("/user/get-by-id/") ;
                 
                 
                		
             }
                 
//                		(path.startsWith("/flight") && method.equals(HttpMethod.GET));
//                 return path.startsWith("/booking/bookFlight") || 
//                 		path.startsWith("/api/user/getUser") ||
//                 		path.startsWith("/checkIn") ||
//                 		path.startsWith("/payment") ||
//                  		(path.startsWith("/flight") && method.equals(HttpMethod.GET));
                     
             
             return false; // Default: deny access
        }

        /**
         * Config is a static inner class for configuring the AuthenticationFilter.
         * It is currently empty as there are no specific configurations needed for this filter.
         */
        public static class Config {

        }
 }