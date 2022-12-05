package org.mybatis.jpetstore.service;

import java.util.List;

import org.mybatis.jpetstore.domain.PetManager;
import org.mybatis.jpetstore.mapper.PetManagerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetManagerService {

    private final PetManagerMapper petManagerMapper;

    public PetManagerService(PetManagerMapper petManagerMapper) { this.petManagerMapper = petManagerMapper; }

    public List<PetManager> getCatDogManagerList() { return petManagerMapper.getCatDogManagerList(); }

    public List<PetManager> getRepFishManagerList() { return petManagerMapper.getRepFishManagerList(); }

    public List<PetManager> getBirdManagerList() { return petManagerMapper.getBirdManagerList(); }

    public List<PetManager> getPetMangerList() { return petManagerMapper.getPetMangerList(); }

    public PetManager getPetMangerByID(String id) { return petManagerMapper.getPetMangerByID(id); }

    @Transactional
    public void insertPetManager(PetManager petManager) { petManagerMapper.insertPetManager(petManager);}

    @Transactional
    public void editPetManager(PetManager petManager) { petManagerMapper.editPetManager(petManager);}

    @Transactional
    public void deletePetManager(String petManagerId) { petManagerMapper.deletePetManager(petManagerId); }
}
