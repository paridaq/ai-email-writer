package ai_backend.backend.controller;


import ai_backend.backend.model.User;
import ai_backend.backend.repository.UserRepository;
import ai_backend.backend.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {


    @Autowired
    public UserRepository userRepository;

//    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler
    @Autowired
        private OAuth2AuthorizedClientService authorizedClientService;


//    @PostMapping("/register")
//     public ResponseEntity<AuthResponse> registerUser(@RequestBody User user){
//        String email = user.getEmail();
//        String name = user.getName();
//        User newUser = new User();
//        newUser.setEmail(email);
//        newUser.setName(name);
//        userRepository.save(newUser);
//        AuthResponse authResponse = new AuthResponse();
//        authResponse.setName(name);
//        authResponse.setMessage("registered succesfully");
//    return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
//
//    }

@GetMapping("/public")
    public String publicApi(){
        return "this is public api";
}
@GetMapping("/private")
    public String privateApi(OAuth2AuthenticationToken authentication) {

    // registrationId = the provider (example: "google")
    String registrationId = authentication.getAuthorizedClientRegistrationId();
    String principalName = authentication.getName();

    // Load authorized client (contains both tokens)
    OAuth2AuthorizedClient client =
            authorizedClientService.loadAuthorizedClient(registrationId, principalName);



    Map<String, Object> attributes = authentication.getPrincipal().getAttributes();

    String name = (String) attributes.get("name");
    String email = (String) attributes.get("email");
    System.out.println(name);
    System.out.println(email);
    if(client!=null){
    System.out.println(client);
        System.out.println(client.getAccessToken());
  }


    if (client == null) {
        System.out.println("⚠️ No authorized client found for " + registrationId);
        return "No authorized client found!";
    }

    // Print tokens in console
    if (client.getAccessToken() != null) {
        System.out.println("✅ ACCESS TOKEN: " + client.getAccessToken().getTokenValue());
        System.out.println("Access token expires at: " + client.getAccessToken().getExpiresAt());
    } else {
        System.out.println("⚠️ No access token found.");
    }

    if (client.getRefreshToken() != null) {
        System.out.println("🔄 REFRESH TOKEN: " + client.getRefreshToken().getTokenValue());
        System.out.println("Refresh token issued at: " + client.getRefreshToken().getIssuedAt());
    } else {
        System.out.println("⚠️ No refresh token found.");
    }
    



    return "private";
}
   }
