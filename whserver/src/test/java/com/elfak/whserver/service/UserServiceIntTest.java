package com.elfak.whserver.service;

import com.elfak.whserver.IntegrationTestPrototype;
import com.elfak.whserver.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

public class UserServiceIntTest extends IntegrationTestPrototype {

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
        // When
        userService.save(user);
        // Then
        User savedUser = userService.findByEmail(email);
        Assert.assertEquals(savedUser.getEmail(), email);
        Assert.assertNotNull(savedUser.getSecretKey());

    }

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testUpdateUser() {
        // Given
        User user = userService.findById(0L);
        user.setFullName("Nikola Savic");
        // When
        User updatedUser = userService.update(user);
        // Then
        Assert.assertNotNull(updatedUser.getId());
        Assert.assertEquals("Nikola Savic", updatedUser.getFullName());
        Assert.assertEquals(user.getDecodedSecretKey(), "fc965557-e2b7-4fa3-82de-584b85e2f283");
        Assert.assertFalse(updatedUser.getFullName().isEmpty());
        Assert.assertFalse(updatedUser.getPassword().isEmpty());
    }

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testFindUserByEmail() {
        // Given
        String email = "vaske@gmail.com";
        // When
        User user = userService.findByEmail(email);
        // Then
        Assert.assertEquals(user.getEmail(), email);
        Assert.assertEquals(user.getDecodedSecretKey(), "fc965557-e2b7-4fa3-82de-584b85e2f283");
    }

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testFindUserById() {
        // Given
        Long id = 0L;
        // When
        User user = userService.findById(id);
        // Then
        Assert.assertEquals(user.getId(), id);
    }

}