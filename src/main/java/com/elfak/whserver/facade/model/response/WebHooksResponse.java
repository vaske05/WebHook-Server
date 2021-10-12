package com.elfak.whserver.facade.model.response;

import com.elfak.whserver.enumeration.WebHookType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WebHooksResponse {

    List<WebHookResponse> webHooks;

    @Getter
    @Setter
    public static class WebHookResponse {
        private Long id;
        private String name;
        private WebHookType type;
        private String url;
    }
}
