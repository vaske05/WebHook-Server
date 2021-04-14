package com.elfak.whserver.repository;

import com.elfak.whserver.model.WebHook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface WebHookRepository extends JpaRepository<WebHook, Long> {
    @Transactional
    Optional<WebHook> findWebHookByUrl(String url);

}
