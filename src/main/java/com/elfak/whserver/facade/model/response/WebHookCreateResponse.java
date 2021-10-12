package com.elfak.whserver.facade.model.response;

import com.elfak.whserver.enumeration.WebHookType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebHookCreateResponse {
    private Long id;
    private String name;
    private WebHookType type;
    private String url;
}
