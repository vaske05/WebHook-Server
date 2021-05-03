package com.elfak.whserver.service;

import com.elfak.whserver.model.WebHook;

public interface WebHookService {

    WebHook save(WebHook webHook);

    WebHook findByUrl(String url);

    WebHook findById(Long id);
}
