package com.elfak.whserver.repository;

import com.elfak.whserver.IntegrationTestPrototype;
import com.elfak.whserver.model.WebHook;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

public class WebHookRepositoryUnitTest extends IntegrationTestPrototype {

    @Autowired
    WebHookRepository webHookRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testSaveWebHook() {
        // Given
        WebHook webHook = new WebHook();
        webHook.setUser(userRepository.findById(0L).orElseThrow());
        webHook.setUrl("localhost:8082/api/receive");
        // When
        webHookRepository.save(webHook);
        // Then
        WebHook savedWebHook = webHookRepository.findWebHookByUrl(webHook.getUrl()).orElseThrow();
        Assert.assertEquals(savedWebHook.getUrl(), webHook.getUrl());
    }

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testFindWebHookByUrl() {
        // Given
        String url = "localhost:8082/api/receive-air-data";
        // When
        WebHook webHook = webHookRepository.findWebHookByUrl(url).orElseThrow();
        // Then
        Assert.assertEquals(webHook.getUrl(), url);
    }
}
