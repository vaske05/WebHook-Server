package com.elfak.whserver.service.impl;

import com.elfak.whserver.model.User;
import com.elfak.whserver.model.WebHook;
import com.elfak.whserver.repository.UserRepository;
import com.elfak.whserver.repository.WebHookRepository;
import com.elfak.whserver.service.WebHookService;
import com.elfak.whserver.service.dto.WebHookCreateRequestDto;
import com.elfak.whserver.service.dto.WebHookCreateResponseDto;
import com.elfak.whserver.service.dto.WebHookDTO;
import com.elfak.whserver.service.dto.WebHooksResponseDTO;
import com.elfak.whserver.service.mapper.WebHookServiceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WebHookServiceImpl implements WebHookService {

    private final WebHookRepository webHookRepository;
    private final UserRepository userRepository;
    private final WebHookServiceMapper mapper;


    @Override
    @Transactional
    public WebHookCreateResponseDto saveOrUpdate(WebHookCreateRequestDto webHookCreateRequestDto, String email) {

        WebHook webHook;

        if (webHookCreateRequestDto.getId() != null) {
            // update
            webHook = webHookRepository.findById(webHookCreateRequestDto.getId()).orElseThrow(); // TODO: Add Web hook not found exception
            webHook.setType(webHookCreateRequestDto.getType());
            webHook.setName(webHookCreateRequestDto.getName());
            webHook.setUrl(webHookCreateRequestDto.getUrl());
        } else {
            webHook = mapper.webHookCreateRequestDtoToWebHook(webHookCreateRequestDto);
            User user = userRepository.findUserByEmail(email).orElseThrow(); // TODO: user not found exception
            webHook.setUser(user);
        }

        return mapper.webHookToWebHookCreateResponseDto(webHookRepository.save(webHook));
    }

    @Override
    @Transactional
    public WebHooksResponseDTO findAllUserWebHooks(String email) {

        List<WebHook> webHooks = userRepository.findUserByEmail(email).orElseThrow().getWebHooks(); // TODO: user not found exception

        WebHooksResponseDTO webHooksResponseDTO = new WebHooksResponseDTO();
        webHooksResponseDTO.setWebHooksDto(mapper.webHooksToWebHooksResponseDTO(webHooks));

        return webHooksResponseDTO;
    }

    @Override
    @Transactional
    public WebHookDTO findByUrl(String url) {
        return mapper.webHookToWebHookDTO(webHookRepository.findWebHookByUrl(url).orElseThrow()); // TODO: WH not found exc
    }

    @Override
    @Transactional
    public Optional<WebHookDTO> findById(Long id) {
        Optional<WebHook> webHook = webHookRepository.findById(id);
        return webHook.map(mapper::webHookToWebHookDTO);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        webHookRepository.deleteById(id);
    }
}
