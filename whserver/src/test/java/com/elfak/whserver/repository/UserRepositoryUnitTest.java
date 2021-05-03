package com.elfak.whserver.repository;

import com.elfak.whserver.IntegrationTestPrototype;
import com.elfak.whserver.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

public class UserRepositoryUnitTest extends IntegrationTestPrototype {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testSaveUser() {
        // Given
        User user = new User();
        String email = "vaske494@gmail.com";
        user.setEmail(email);
        user.setFullName("Milan Vasic");
        user.setPassword("Milan994!");
        // When
        userRepository.save(user);
        // Then
        User savedUser = userRepository.findUserByEmail(email).orElseThrow();
        Assert.assertEquals(savedUser.getEmail(), email);
    }

    @Test
    @Sql({"/sql/insert-users.sql"})
    public void testFindUserByEmail() {
        // Given
        String email = "vaske@gmail.com";
        // When
        User user = userRepository.findUserByEmail(email).orElseThrow();
        // Then
        Assert.assertEquals(user.getEmail(), email);
    }

}
