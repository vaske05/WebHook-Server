package com.elfak.whserver.service.dto;

import com.elfak.whserver.enumeration.WebHookType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebHookCreateRequestDto {
    Long id;
    String name;
    WebHookType type;
    String url;
}
