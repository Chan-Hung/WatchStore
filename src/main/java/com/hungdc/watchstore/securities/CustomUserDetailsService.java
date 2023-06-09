package com.hungdc.watchstore.securities;

import com.hungdc.watchstore.entities.User;
import com.hungdc.watchstore.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public JwtUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.getUserByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Tài khoản có email %s không tồn tại", email)));
        return getUserDetails(user);
    }

    private JwtUserDetails getUserDetails(User user) {
        return new JwtUserDetails(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
                user.isState()
        );
    }
}
