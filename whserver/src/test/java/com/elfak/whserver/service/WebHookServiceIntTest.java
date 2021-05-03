package com.elfak.whserver.service;

import com.elfak.whserver.IntegrationTestPrototype;
import com.elfak.whserver.model.WebHook;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

public class WebHookServiceIntTest extends IntegrationTestPrototype {

    @Autowired
    private WebHookService webHookService;
    @Autowired
    private UserService userService;

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testSaveWebHook() {
        // Given
        WebHook webHook = new WebHook();
        webHook.setUser(userService.findById(0L));
        webHook.setUrl("localhost:8082/api/receive");
        // When
        webHookService.save(webHook);
        // Then
        WebHook savedWebHook = webHookService.findByUrl(webHook.getUrl());
        Assert.assertEquals(savedWebHook.getUrl(), webHook.getUrl());
    }

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testFindWebHookByUrl() {
        // Given
        String url = "localhost:8082/api/receive-air-data";
        // When
        WebHook webHook = webHookService.findByUrl(url);
        // Then
        Assert.assertEquals(webHook.getUrl(), url);
    }

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testFindWebHookById() {
        // Given
        Long id = 0L;
        // When
        WebHook webHook = webHookService.findById(id);
        // Then
        Assert.assertEquals(webHook.getId(), id);
    }

}
