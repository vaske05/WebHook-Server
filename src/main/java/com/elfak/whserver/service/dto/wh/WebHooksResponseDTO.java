package com.elfak.whserver.service.dto.wh;

import com.elfak.whserver.enumeration.WebHookType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WebHooksResponseDTO {

    List<WebHookResponseDTO> webHooksDto;

    @Getter
    @Setter
    public static class WebHookResponseDTO {
        private Long id;
        private String name;
        private WebHookType type;
        private String url;
        private String country;
        private String region;
        private String city;
    }
}
