package com.elfak.whserver.service;

import com.elfak.whserver.model.WebHook;
import com.elfak.whserver.repository.WebHookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class WebHookServiceImpl implements WebHookService {

    private final WebHookRepository webHookRepository;

    @Override
    @Transactional
    public WebHook save(WebHook webHook) {
        return webHookRepository.save(webHook);
    }

    @Override
    @Transactional
    public WebHook findByUrl(String url) {
        return webHookRepository.findWebHookByUrl(url).orElseThrow();
    }

    @Override
    @Transactional
    public WebHook findById(Long id) {
        return webHookRepository.findById(id).orElseThrow();
    }
}
