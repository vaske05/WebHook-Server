package com.elfak.whserver.service.dto;

import com.elfak.whserver.enumeration.WebHookType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebHookDTO {
    private Long id;
    private String name;
    private WebHookType type;
    private String url;
    private UserDTO user;
}
