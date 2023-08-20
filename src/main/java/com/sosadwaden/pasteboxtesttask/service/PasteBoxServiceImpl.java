package com.sosadwaden.pasteboxtesttask.service;

import com.sosadwaden.pasteboxtesttask.api.request.Status;
import com.sosadwaden.pasteboxtesttask.api.response.PasteBoxResponse;
import com.sosadwaden.pasteboxtesttask.api.response.PasteBoxUrlResponse;
import com.sosadwaden.pasteboxtesttask.api.request.PasteBoxRequest;
import com.sosadwaden.pasteboxtesttask.entity.PasteBoxEntity;
import com.sosadwaden.pasteboxtesttask.repository.PasteBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class PasteBoxServiceImpl implements PasteBoxService {

    private String host = "http://abc.ru";
    private int publicListSize = 10;
    private final PasteBoxRepository repository;
    // TODO сделать свой класс
    private AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public PasteBoxResponse getPasteBoxByHash(String hash) {
        PasteBoxEntity entity = repository.getByHash(hash);
        return new PasteBoxResponse(entity.getData(), entity.isPublic());
    }

    @Override
    public List<PasteBoxResponse> getFirstPublicPasteBoxes() {
        List<PasteBoxEntity> list = repository.getListOfPublicAndLive(publicListSize);
        return list.stream()
                .map(entity -> new PasteBoxResponse(entity.getData(), entity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PasteBoxUrlResponse create(PasteBoxRequest request) {

        int hash = generateId();

        PasteBoxEntity entity = new PasteBoxEntity();
        entity.setData(request.getData());
        entity.setId(hash);
        entity.setHash(Integer.toHexString(hash));
        entity.setPublic(request.getPublicStatus() == Status.PUBLIC);
        entity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(entity);

        return new PasteBoxUrlResponse(host + "/" + entity.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
