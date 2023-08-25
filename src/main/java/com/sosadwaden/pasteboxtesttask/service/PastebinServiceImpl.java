package com.sosadwaden.pasteboxtesttask.service;

import com.sosadwaden.pasteboxtesttask.api.request.Status;
import com.sosadwaden.pasteboxtesttask.api.response.PastebinResponse;
import com.sosadwaden.pasteboxtesttask.api.response.PastebinUrlResponse;
import com.sosadwaden.pasteboxtesttask.api.request.PastebinRequest;
import com.sosadwaden.pasteboxtesttask.entity.PastebinEntity;
import com.sosadwaden.pasteboxtesttask.exception.EntityNotFoundException;
import com.sosadwaden.pasteboxtesttask.repository.PastebinRepository;
import com.sosadwaden.pasteboxtesttask.utils.Base62;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Setter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class PastebinServiceImpl implements PastebinService {

    private int publicListSize;
    private String host;
    private final PastebinRepository repository;

    @Override
    public PastebinResponse findPasteByHash(String hash) {
        PastebinEntity entity = repository.findByHash(hash);

        if (entity == null) {
            throw new EntityNotFoundException("Paste not found with hash = " + hash);
        }

        return new PastebinResponse(entity.getData(), entity.isPublic());
    }

    @Override
    public List<PastebinResponse> findFirstPublicPastes() {

        PageRequest pageRequest = PageRequest.of(0, publicListSize);

        List<PastebinEntity> list = repository.findListOfPublicAndLive(pageRequest);
        return list.stream()
                .map(entity -> new PastebinResponse(entity.getData(), entity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PastebinUrlResponse create(PastebinRequest request) {

        PastebinEntity entity = new PastebinEntity();

        String data = request.getData();
        boolean isPublic = request.getStatus() == Status.PUBLIC;
        LocalDateTime lifetime = LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds());

        entity.setData(data);
        entity.setPublic(isPublic);
        entity.setLifetime(lifetime);

        repository.save(entity);
        int id = entity.getId();

        String hash = Base62.encode(id);
        entity.setHash(hash);

        repository.save(entity);

        return new PastebinUrlResponse(host + "/" + entity.getHash());
    }

}
