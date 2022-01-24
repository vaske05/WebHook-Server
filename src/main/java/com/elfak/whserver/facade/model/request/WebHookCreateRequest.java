package com.elfak.whserver.facade.model.request;

import com.elfak.whserver.enumeration.WebHookType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WebHookCreateRequest {
    Long id;
    @NotBlank(message = "Name field cannot be blank")
    String name;
    @NotNull(message = "Please choose a type")
    WebHookType type;
    @NotBlank(message = "Url field cannot be blank")
    String url;
    @NotBlank(message = "Please choose country")
    String country;
    String region;
    String city;
}
