package com.elfak.whserver.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.elfak.whserver.IntegrationTestPrototype;
import com.elfak.whserver.exceptions.EmailUniqueException;
import com.elfak.whserver.model.User;
import com.elfak.whserver.service.dto.UserRegistrationRequestDTO;

public class UserServiceIntTest extends IntegrationTestPrototype {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        // Given
        UserRegistrationRequestDTO userRegistrationRequestDTO = new UserRegistrationRequestDTO();
        String email = "vaske494@gmail.com";
        userRegistrationRequestDTO.setEmail(email);
        userRegistrationRequestDTO.setFullName("Milan Vasic");
        userRegistrationRequestDTO.setPassword("Milan994!");
        // When
        userService.createUser(userRegistrationRequestDTO);
        // Then
        User savedUser = userService.findByEmail(email);
        Assert.assertEquals(savedUser.getEmail(), email);
        Assert.assertNotNull(savedUser.getSecretKey());
    }

    @Test
    @Sql({"/sql/insert-users.sql"})
    public void testEmailUniqueException() {
        // Given
        UserRegistrationRequestDTO userRegistrationRequestDTO = new UserRegistrationRequestDTO();
        String email = "vaske@gmail.com";
        userRegistrationRequestDTO.setEmail(email);
        userRegistrationRequestDTO.setFullName("Milan Vasic");
        userRegistrationRequestDTO.setPassword("Milan994!");
        // When, Then
        Assert.assertThrows(EmailUniqueException.class, () -> userService.createUser(userRegistrationRequestDTO));
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
