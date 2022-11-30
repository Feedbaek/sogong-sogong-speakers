package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.PetManager;
import org.mybatis.jpetstore.mapper.PetManagerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetManagerService {

    private final PetManagerMapper petManagerMapper;

    public PetManagerService(PetManagerMapper petManagerMapper) { this.petManagerMapper = petManagerMapper; }

    public List<PetManager> getCatDogManagerList() { return petManagerMapper.getCatDogManagerList(); }

    public List<PetManager> getRepFishManagerList() { return petManagerMapper.getRepFishManagerList(); }

    public List<PetManager> getBirdManagerList() { return petManagerMapper.getBirdManagerList(); }

}
