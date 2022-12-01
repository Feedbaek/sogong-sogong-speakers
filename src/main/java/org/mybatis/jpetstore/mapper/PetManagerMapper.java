package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.PetManager;

import java.util.List;

public interface PetManagerMapper {

    List<PetManager> getCatDogManagerList();
    List<PetManager> getRepFishManagerList();
    List<PetManager> getBirdManagerList();
    List<PetManager> getPetMangerList();
    void insertPetManager(PetManager petManager);
}
