package com.prasad.apigateway.filter;


import com.prasad.apigateway.util.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtService jwtService;

    Logger logger= LoggerFactory.getLogger(JwtService.class);

    public AuthFilter()
    {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        logger.info("Entry into GatewayFilter method");
        return ((exchange,chain)->{
            if(routeValidator.isSecured.test(exchange.getRequest()))
            {
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                {
                    logger.error("Missing Authorization header");
                    throw new RuntimeException("Missing Authorization header");
                }

                        String authorization=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                        String token=null;

                        if(authorization!=null && authorization.startsWith("Bearer "))
                        {
                             token=authorization.substring(7);
                        }
                    try
                        {
                            boolean status=jwtService.validateToken(token);
                            System.out.println(status);
                            if(!status)
                            {
                                logger.error("Invalid access");
                                throw  new RuntimeException("Invalid access");
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("Invalid access");
                            throw  new RuntimeException("Invalid access");
                        }
            }

            return chain.filter(exchange);
        });
    }

    public static class Config
    {

    }


}
