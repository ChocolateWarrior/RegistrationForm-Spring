package de.springboot;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void saveAdmin(){
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }


}