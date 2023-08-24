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
    public PastebinResponse getPasteBoxByHash(String hash) {
        PastebinEntity entity = repository.findByHash(hash);

        if (entity == null) {
            throw new EntityNotFoundException("PasteBox not found with hash = " + hash);
        }

        return new PastebinResponse(entity.getData(), entity.isPublic());
    }

    @Override
    public List<PastebinResponse> getFirstPublicPasteBoxes() {

        PageRequest pageRequest = PageRequest.of(0, publicListSize);

        List<PastebinEntity> list = repository.findListOfPublicAndLive(pageRequest);
        return list.stream()
                .map(entity -> new PastebinResponse(entity.getData(), entity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PastebinUrlResponse create(PastebinRequest request) {

        int hashInt = Base62.decode(request.getData());
        String hashHex = Integer.toHexString(hashInt);
        String data = request.getData();
        boolean isPublic = request.getStatus() == Status.PUBLIC;
        System.out.println(request.getStatus());
        System.out.println(isPublic);
        LocalDateTime lifetime = LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds());

        PastebinEntity entity = new PastebinEntity();
        entity.setData(data);
        entity.setId(hashInt);
        entity.setHash(hashHex);
        entity.setPublic(isPublic);
        entity.setLifetime(lifetime);

        repository.save(entity);

        return new PastebinUrlResponse(host + "/" + entity.getHash());
    }

}
