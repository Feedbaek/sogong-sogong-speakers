package org.mybatis.jpetstore.mapper;

import java.util.List;

import org.mybatis.jpetstore.domain.PetManager;

public interface PetManagerMapper {

    List<PetManager> getCatDogManagerList();
    List<PetManager> getRepFishManagerList();
    List<PetManager> getBirdManagerList();
    List<PetManager> getPetMangerList();
    PetManager getPetMangerByID(String id);
    void insertPetManager(PetManager petManager);
    void editPetManager(PetManager petManager);
    void deletePetManager(String petManagerId);
}
