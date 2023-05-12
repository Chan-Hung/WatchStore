package com.hungdc.watchstore;

import com.hungdc.watchstore.repositories.UserRepository;
import com.hungdc.watchstore.utils.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class WatchStoreApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(WatchStoreApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count()==0){
            com.hungdc.watchstore.entities.User user = new com.hungdc.watchstore.entities.User("hungdc","20110072@student.hcmute.edu.vn","123456789",
                    Arrays.asList(EnumRole.ROLE_ADMIN.name()));
            this.userRepository.save(user);
        }
    }
}
