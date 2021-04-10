package com.elfak.whserver.repository;

import com.elfak.whserver.IntegrationTestPrototype;
import com.elfak.whserver.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryUnitTest extends IntegrationTestPrototype{

    @Autowired
    UserRepository userRepository;

    @Test
    public void testSaveUser() {
        // Given
        User user = new User();
        String email = "vaske494@gmail.com";
        user.setEmail(email);
        user.setFirstName("Milan");
        user.setLastName("Vasic");
        user.setPassword("Milan994!");
        // When
        userRepository.save(user);
        // Then
        User savedUser = userRepository.findUserByEmail(email).orElseThrow();
        Assert.assertEquals(savedUser.getEmail(), email);

    }
}