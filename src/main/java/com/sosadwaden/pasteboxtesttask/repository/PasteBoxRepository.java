package com.sosadwaden.pasteboxtesttask.repository;

import com.sosadwaden.pasteboxtesttask.entity.PasteBoxEntity;

import java.util.List;

public interface PasteBoxRepository {

    PasteBoxEntity getByHash(String hash);

    List<PasteBoxEntity> getListOfPublicAndLive(int amount);

    void add(PasteBoxEntity pasteBoxEntity);

}
