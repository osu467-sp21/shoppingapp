//package com.shoppingapp.shoppingapp.controllers.UserController;
//
//import com.shoppingapp.shoppingapp.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.nio.charset.StandardCharsets;
//import java.time.ZonedDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//@RestController
//@AllArgsConstructor
//public class UserController {
//    // Okta ref: https://developer.okta.com/docs/reference/api/users/#get-user-with-id
//    private UserRepository userRepository;
//
//    @Value("${okta.api.client.id}")
//    private final String oktaApiClientId;
//
//    @Value("${okta.api.client.secret}")
//    private final String oktaApiClientSecret;
//
//    @Value("${okta.api.domain}")
//    private final String oktaApiClientDomain;
//
//    @Value("${okta.api.token}")
//    private final String oktaApiClientToken;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @PostMapping("/users")
//    public ResponseEntity createUserWithPassword(@RequestParam("userName") String userName,
//                                                 @RequestParam("password") String password) {
//        String url = "https://"+oktaApiClientDomain + "/api/v1/users?activate=true";
//
//        ArrayList acceptHeader = new ArrayList();
//        acceptHeader.add(MediaType.APPLICATION_JSON);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(acceptHeader);
//        headers.set("Authorization", oktaApiClientToken);
//        HttpEntity <String> entity = new HttpEntity<String>(headers);
//
////        restTemplate.postForEntity(url)
//        return new ResponseEntity("created user", HttpStatus.OK);
//    }
//
//    @GetMapping("/users/{user_token}")
//    public ResponseEntity getUserWithPassword(
//            @PathVariable("user_token") String user_token) {
//        // retrieve from Okta
//        return new ResponseEntity("created user", HttpStatus.OK);
//    }
//
//    // Ref: https://developer.okta.com/docs/reference/api/oidc/#introspect
//    @PostMapping("/token/{token_id}")
//    public ResponseEntity validIdOrToken(
//            @PathVariable("token_id") String token) {
//        // retrieve from Okta
//        return new ResponseEntity("created user", HttpStatus.OK);
//    }
//
//}
