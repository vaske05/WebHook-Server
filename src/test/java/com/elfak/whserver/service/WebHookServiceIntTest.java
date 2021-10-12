package com.elfak.whserver.service;

import com.elfak.whserver.IntegrationTestPrototype;
import com.elfak.whserver.enumeration.WebHookType;
import com.elfak.whserver.model.WebHook;
import com.elfak.whserver.service.dto.WebHookCreateRequestDto;
import com.elfak.whserver.service.dto.WebHooksResponseDTO;
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
        WebHookCreateRequestDto webHookCreateDto = new WebHookCreateRequestDto();
        webHookCreateDto.setName("Covid data wh");
        webHookCreateDto.setUrl("localhost:8082/api/receive");
        webHookCreateDto.setType(WebHookType.COVID_DATA);
        String email = "vaske@gmail.com";
        // When
        webHookService.saveOrUpdate(webHookCreateDto, email);
        // Then
        WebHook savedWebHook = webHookService.findByUrl(webHookCreateDto.getUrl());
        Assert.assertEquals(savedWebHook.getUrl(), webHookCreateDto.getUrl());
        Assert.assertEquals(savedWebHook.getType(), WebHookType.COVID_DATA);
        Assert.assertEquals(savedWebHook.getName(), webHookCreateDto.getName());
        Assert.assertEquals(savedWebHook.getUser().getEmail(), email);
        Assert.assertEquals(savedWebHook.getUser().getWebHooks().size(), 3);
    }

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testUpdateWebHook() {
        // Given
        WebHookCreateRequestDto webHookCreateDto = new WebHookCreateRequestDto();
        webHookCreateDto.setId(0L);
        webHookCreateDto.setName("Covid data wh");
        webHookCreateDto.setUrl("localhost:8082/api/receive");
        webHookCreateDto.setType(WebHookType.COVID_DATA);
        String email = "vaske@gmail.com";
        // When
        webHookService.saveOrUpdate(webHookCreateDto, email);
        // Then
        WebHook savedWebHook = webHookService.findByUrl(webHookCreateDto.getUrl());
        Assert.assertEquals(savedWebHook.getUrl(), webHookCreateDto.getUrl());
        Assert.assertEquals(savedWebHook.getType(), WebHookType.COVID_DATA);
        Assert.assertEquals(savedWebHook.getName(), webHookCreateDto.getName());
        Assert.assertEquals(savedWebHook.getUser().getEmail(), email);
        Assert.assertEquals(savedWebHook.getUser().getWebHooks().size(), 2);
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
        Assert.assertEquals(webHook.getType(), WebHookType.AIR_DATA);
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
        Assert.assertEquals(webHook.getType(), WebHookType.AIR_DATA);
    }

    @Test
    @Sql({"/sql/insert-users.sql", "/sql/insert-web-hooks.sql"})
    public void testFindWebHookByUserEmail() {
        // Given
        String email = "vaske@gmail.com";
        // When
        WebHooksResponseDTO webHooksResponseDTO = webHookService.findAllUserWebHooks(email);
        // Then
        Assert.assertEquals(webHooksResponseDTO.getWebHooksDto().size(), 2);
        Assert.assertNotNull(webHooksResponseDTO.getWebHooksDto().get(0).getId());
        Assert.assertNotNull(webHooksResponseDTO.getWebHooksDto().get(0).getType());
        Assert.assertNotEquals(webHooksResponseDTO.getWebHooksDto().get(0).getName(), "");
        Assert.assertNotEquals(webHooksResponseDTO.getWebHooksDto().get(0).getUrl(), "");
    }

}
