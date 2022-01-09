package com.example.broker.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("tasklist-queries")
@PropertySource(value = "classpath:server-details.properties")
public class TaskListQueryController {

    private static final Logger logger = LoggerFactory.getLogger(TaskListQueryController.class);
    private static final String APPLICATION_JSON="applcation/json";


    @Value("${server.domain.url}")
    private String DOMAIN_URL;

    private final RestTemplate restTemplate;

    public TaskListQueryController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("{query_name}")
    public ResponseEntity<?> getTasklistQueriesByQueryName(HttpServletRequest request) {
        logger.info("Client Requested for resource at path : {}",request.getRequestURI());
        StringBuilder requestURL = new StringBuilder(DOMAIN_URL).append(request.getRequestURI());
        HttpHeaders headers=new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION,request.getHeader(HttpHeaders.AUTHORIZATION));
        headers.add(HttpHeaders.ACCEPT,APPLICATION_JSON);
        HttpEntity<String> requestEntity=new HttpEntity<>(headers);

       try {
           logger.info("Requesting the server with url : {}", requestURL);
           ResponseEntity<String> response = restTemplate.exchange(requestURL.toString(), HttpMethod.GET, requestEntity, String.class);
           logger.info("Server Returned Status Code : {}", response.getStatusCode());
           return response;
       }
       catch(Exception e)
       {
           logger.error("Unable to process the request due to error : {}",e.getMessage());
       }
       return new ResponseEntity<String>("Error Occurred while processing the request. Please try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
