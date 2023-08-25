package com.sosadwaden.pasteboxtesttask.service;

import com.sosadwaden.pasteboxtesttask.api.response.PastebinResponse;
import com.sosadwaden.pasteboxtesttask.entity.PastebinEntity;
import com.sosadwaden.pasteboxtesttask.exception.EntityNotFoundException;
import com.sosadwaden.pasteboxtesttask.repository.PastebinRepository;
import com.sosadwaden.pasteboxtesttask.service.PastebinService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PasteBoxServiceImplTest {

    @Autowired
    private PastebinService service;

    @MockBean
    private PastebinRepository repository;

    @Test
    public void notExistHash() {
        when(repository.findByHash(anyString())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> service.findPasteByHash("fsdfsg"));
    }

    @Test
    public void getExistHash() {

        PastebinEntity entity = new PastebinEntity();
        entity.setHash("1");
        entity.setData("11");
        entity.setPublic(true);

        when(repository.findByHash("1")).thenReturn(entity);

        PastebinResponse excepted = new PastebinResponse("11", true);
        PastebinResponse actual = service.findPasteByHash("1");

        assertEquals(excepted, actual);
    }

}
