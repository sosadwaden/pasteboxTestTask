package com.sosadwaden.pasteboxtesttask.repository;

import com.sosadwaden.pasteboxtesttask.entity.PastebinEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PastebinRepository extends JpaRepository<PastebinEntity, Integer> {

    //@Query("SELECT PastebinEntity FROM PastebinEntity p WHERE p.hash = ?1")
    PastebinEntity findByHash(String hash);

    @Query("FROM PastebinEntity p WHERE p.lifetime >= now() AND p.isPublic = true ORDER BY p.id ASC")
    List<PastebinEntity> findListOfPublicAndLive(PageRequest pageRequest);

}
