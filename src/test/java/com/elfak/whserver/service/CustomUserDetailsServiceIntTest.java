package com.elfak.whserver.service;

import com.elfak.whserver.IntegrationTestPrototype;
import com.elfak.whserver.exceptions.UserNotFoundException;
import com.elfak.whserver.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.jdbc.Sql;

public class CustomUserDetailsServiceIntTest extends IntegrationTestPrototype {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testLoadUserByUsername() {
        // Given
        String email = "vaske@gmail.com";
        // When
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        // Then
        Assert.assertNotNull(userDetails.getUsername());
        Assert.assertEquals(email, userDetails.getUsername());
    }

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testLoadUserByUsernameException() {
        // Given
        String email = "no_email@gmail.com";
        // When, Then
        Assert.assertThrows(UserNotFoundException.class, () -> customUserDetailsService.loadUserByUsername(email));
    }

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testLoadUserById() {
        // Given
        Long id = 0L;
        // When
        User user = customUserDetailsService.loadUserById(id);
        // Then
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId(), id);
    }

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testLoadUserByIdException() {
        // Given
        Long id = 111L;
        // When, Then
        Assert.assertThrows(UserNotFoundException.class, () -> customUserDetailsService.loadUserById(id));
    }
}
