package com.elfak.whserver.repository;

import com.elfak.whserver.enumeration.WebHookType;
import com.elfak.whserver.model.WebHook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface WebHookRepository extends JpaRepository<WebHook, Long> {

    Optional<WebHook> findWebHookByUrl(String url);

    void deleteById(@NotNull Long id);

    List<WebHook> findWebHooksByType(WebHookType webHookType); // TODO: Use pageable interface
}
