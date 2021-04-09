package com.elfak.whserver.repository;

import com.elfak.whserver.model.WebHook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebHookRepository extends JpaRepository<WebHook, Long> {
}
