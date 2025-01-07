package org.demo.demoapplication.service;


import lombok.extern.slf4j.Slf4j;
import org.demo.demoapplication.PublicRepository;
import org.demo.demoapplication.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    private PublicRepository rep;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> user = Optional.ofNullable(rep.findByProperty(new User(username)));
        log.info(user.toString());
        if (user.isPresent()) {
            User u = user.get();
            return org.springframework.security.core.userdetails.User.builder().
                    username(u.getEmail())
                    .password(u.getPassword())
                    .roles(getRoles(u))
                    .build();
        } else {
            throw new UsernameNotFoundException("Please Provide Valid User name and Password");
        }

    }


    public String[] getRoles(User u) {
        if(u.getRole() ==  null) {
            return new String[]{"User"};
        }

        return u.getRole().split(",");
    }
}
