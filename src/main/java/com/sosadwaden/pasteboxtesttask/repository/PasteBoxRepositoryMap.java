package com.sosadwaden.pasteboxtesttask.repository;

import com.sosadwaden.pasteboxtesttask.entity.PasteBoxEntity;
import com.sosadwaden.pasteboxtesttask.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PasteBoxRepositoryMap implements PasteBoxRepository {

    private final Map<String, PasteBoxEntity> vault = new HashMap<>();

    @Override
    public PasteBoxEntity getByHash(String hash) {

        PasteBoxEntity entity = vault.get(hash);

        if (entity == null) {
            throw new EntityNotFoundException("PasteBox not found with hash = " + hash);
        }

        return entity;
    }

    @Override
    public List<PasteBoxEntity> getListOfPublicAndLive(int amount) {

        LocalDateTime now = LocalDateTime.now();

        return vault.values().stream()
                .filter(PasteBoxEntity::isPublic)
                .filter(entity -> entity.getLifetime().isAfter(now))
                .sorted(Comparator.comparing(PasteBoxEntity::getId).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(PasteBoxEntity pasteBoxEntity) {
        vault.put(pasteBoxEntity.getHash(), pasteBoxEntity);
    }
}
