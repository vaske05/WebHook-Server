package com.elfak.whserver.facade.model.response;

import com.elfak.whserver.enumeration.WebHookType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebHookCreateResponse {
    Long id;
    String name;
    WebHookType type;
    String url;
}
