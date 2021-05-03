package com.elfak.whserver.web.rest.controller;

import com.elfak.whserver.web.WebConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = WebConstant.USER_BASE_URL, produces = APPLICATION_JSON_VALUE)
public class UserController {
}
