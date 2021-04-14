package com.elfak.whserver.service;

import com.elfak.whserver.IntegrationTestPrototype;
import com.elfak.whserver.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceIntTest extends IntegrationTestPrototype  {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() {
        // Given
        User user = new User();
        String email = "vaske494@gmail.com";
        user.setEmail(email);
        user.setFullName("Milan Vasic");
        user.setPassword("Milan994!");
        // Then
        userService.saveUser(user);
        //When
        User savedUser = userService.findUserByEmail(email);
        Assert.assertEquals(savedUser.getEmail(), email);
    }

}
