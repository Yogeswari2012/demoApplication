package org.demo.demoapplication;

import com.amazonaws.Response;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import lombok.extern.slf4j.Slf4j;
import org.demo.demoapplication.service.MyUserDetailsService;
import org.demo.demoapplication.webToken.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.interceptor.AroundInvoke;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private PublicRepository rep;

    @Autowired
    private DynamoDBMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    public enum UserRole {
        Admin, SuperAdmin, Staff, User,
    }

    private final BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder(12);
    @PostMapping("/register")
    public ResponseEntity<Object> userRegister(@RequestBody User user) {
        String pass = encoder.encode(user.getPassword());
        user.setPassword(pass);
        log.info(user.getEmail());
        try {
          mapper.save(user);
        }catch(Exception e ) {
            log.error(e.toString());
        }

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping("/login")
    public String authenticate(@RequestBody User user) throws Exception {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(), user.getPassword()
                )
        );

        if(authenticate.isAuthenticated()) {
            return jwtService.generateJwtToken(userDetailsService.loadUserByUsername(user.getEmail()));
        }else {
            throw new UsernameNotFoundException("Provide Valid credentials");
        }
     }
     @GetMapping("/demo")
    public String demo() {
        return "Success";
    }

}
