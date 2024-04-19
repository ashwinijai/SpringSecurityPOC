package com.example.configuration;

import com.example.exception.CustomAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class ThirdPartyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException{
        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
try {
    ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:7001/validate/"+auth.getPrincipal()+"/"
            +auth.getCredentials(), HttpMethod.GET, entity, String.class);
    if (responseEntity.getBody().equals("Success")) {
        System.out.println("Authentication Successful");
        var user = User.withUsername(auth.getPrincipal().toString()).password(auth.getCredentials().toString()).authorities(new ArrayList<>()).build();
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());

    }

    System.out.println("Authentication UnSuccessful");
    throw new CustomAuthenticationException("Invalid credentials");
}catch(Exception e){
    System.out.println("Authentication UnSuccessful");
    throw new CustomAuthenticationException("Invalid credentials");
}
    }

    @Override
    public boolean supports(Class<?> authCls) {
        return UsernamePasswordAuthenticationToken.class.equals(authCls);
    }
}
