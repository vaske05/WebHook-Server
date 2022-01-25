package com.elfak.whserver.service.dto.wh;

import com.elfak.whserver.enumeration.WebHookType;
import com.elfak.whserver.service.dto.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebHookDTO {
    private Long id;
    private String name;
    private WebHookType type;
    private String url;
    private String country;
    private String region;
    private String city;
    private UserDTO user;
}
