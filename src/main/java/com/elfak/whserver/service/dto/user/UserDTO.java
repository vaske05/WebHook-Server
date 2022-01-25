package com.elfak.whserver.service.dto.user;

import com.elfak.whserver.enumeration.WebHookType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String id;
    private String fullName;
    private String email;
    private String secretKey;
    List<WebHookDTONoUser> webHooks;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class WebHookDTONoUser {
        private Long id;
        private String name;
        private WebHookType type;
        private String url;
    }
}
