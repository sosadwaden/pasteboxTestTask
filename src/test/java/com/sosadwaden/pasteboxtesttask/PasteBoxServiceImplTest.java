package com.sosadwaden.pasteboxtesttask;

import com.sosadwaden.pasteboxtesttask.api.response.PasteBoxResponse;
import com.sosadwaden.pasteboxtesttask.entity.PasteBoxEntity;
import com.sosadwaden.pasteboxtesttask.exception.EntityNotFoundException;
import com.sosadwaden.pasteboxtesttask.repository.PasteBoxRepository;
import com.sosadwaden.pasteboxtesttask.service.PasteBoxService;
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
    private PasteBoxService service;

    @MockBean
    private PasteBoxRepository repository;

    @Test
    public void notExistHash() {
        when(repository.getByHash(anyString())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> service.getPasteBoxByHash("fsdfsg"));
    }

    @Test
    public void getExistHash() {

        PasteBoxEntity entity = new PasteBoxEntity();
        entity.setHash("1");
        entity.setData("11");
        entity.setPublic(true);

        when(repository.getByHash("1")).thenReturn(entity);

        PasteBoxResponse excepted = new PasteBoxResponse("11", true);
        PasteBoxResponse actual = service.getPasteBoxByHash("1");

        assertEquals(excepted, actual);
    }

}
