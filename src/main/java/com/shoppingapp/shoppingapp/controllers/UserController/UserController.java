package com.shoppingapp.shoppingapp.controllers.UserController;

import com.okta.jwt.Jwt;
import com.okta.jwt.JwtVerificationException;
import com.shoppingapp.shoppingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.shoppingapp.shoppingapp.oktaJwtVerifier.JwtVerifier;
//import java.lang.String;

@RestController
//@RestController(value = "/userController")
@AllArgsConstructor
public class UserController {
    // Okta ref: https://developer.okta.com/docs/reference/api/users/#get-user-with-id
//    @Autowired
    private final UserRepository userRepository;

//    @Value("0oam8mzyjZe2wBERc5d6")
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

    private final RestTemplate restTemplate = new RestTemplate();

    private final JwtVerifier jwtVerifier = new JwtVerifier();

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

    private static class PostUsersBody {
        public String user_id;
        public String firstName;
        public String lastName;
        public String email;
        public String username;
    }

    @PostMapping(value = "/users",
    consumes = "application/json")
    public ResponseEntity createUserWithIdToken(@RequestHeader("Authorization") String authorization,
                                                @RequestBody PostUsersBody body) {
        System.out.println(body.username);
        return new ResponseEntity("creating user", HttpStatus.CREATED);
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity getUserWithAccessToken(
            @PathVariable("user_id") String user_id,
            @RequestHeader("Authorization") String authorization) {
        // retrieve from Okta
        try {
            authorization = authorization.replace("Bearer ", "");
            Jwt jwt = jwtVerifier.accessTokenVerifier.decode(authorization);
            // Check that User.login with User.user_id == user_id equals jwt.getClaims().get("sub")
            // then return that user data
            System.out.println(jwt.getClaims().get("sub"));
//            userRepository.findUserById(user_id);
            return new ResponseEntity("found user", HttpStatus.OK);
        }
        catch (JwtVerificationException exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity("invalid access_token", HttpStatus.UNAUTHORIZED);
        }
        catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
    }

//    // Ref: https://developer.okta.com/docs/reference/api/oidc/#introspect
//    @PostMapping("/token/{token_id}")
//    public ResponseEntity validIdOrToken(
//            @PathVariable("token_id") String token) {
//        // retrieve from Okta
//        return new ResponseEntity("created user", HttpStatus.OK);
//    }

}
