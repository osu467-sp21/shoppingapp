package com.shoppingapp.shoppingapp.controllers.UserController;

import com.okta.jwt.Jwt;
import com.okta.jwt.JwtVerificationException;
import com.shoppingapp.shoppingapp.repository.UserRepository;
import com.shoppingapp.shoppingapp.model.User;
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

    private final RestTemplate restTemplate = new RestTemplate();
    private final JwtVerifier jwtVerifier = new JwtVerifier();

    private class HttpException extends Exception {
        private HttpStatus status;

        public HttpException (String message, HttpStatus status) {
            super(message);
            this.setStatus(status);
        }

        public HttpStatus getStatus() {
            return this.status;
        }

        public void setStatus(HttpStatus status) {
            this.status = status;
        }

    }

    @PostMapping(value = "/users",
    consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestHeader("Authorization") String authorization,
                                                @RequestBody User user) {
        try {
//            System.out.println(user);
            user.setMaster_shopper_level(0);
            User newUser = userRepository.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("error creating user", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<?> getUserWithAccessToken(
            @PathVariable("user_id") String user_id,
            @RequestHeader("Authorization") String authorization) {
        // retrieve from Okta
        try {
            authorization = jwtVerifier.stripBearer(authorization);
            Jwt jwt = jwtVerifier.accessTokenVerifier.decode(authorization);
            String jwt_uid = jwt.getClaims().get("uid").toString();
            if (user_id.equals(jwt_uid)) {
                User user = userRepository.findUserById(user_id);
                if (user == null)
                    throw new HttpException("could not find user", HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            else {
                throw new HttpException("access_token does not match param user_id", HttpStatus.FORBIDDEN);
            }
        // then return that user data
        }
        catch (JwtVerificationException exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("invalid access_token", HttpStatus.UNAUTHORIZED);
        }
        catch (HttpException exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
        catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
