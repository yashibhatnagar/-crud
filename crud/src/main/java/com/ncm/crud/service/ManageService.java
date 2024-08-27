package com.ncm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncm.crud.entity.Manage;
import com.ncm.crud.repo.ManageRepo;

@Service
public class ManageService {

    @Autowired
    private ManageRepo manageRepo;

    public Manage save(Manage manage) {
        return manageRepo.save(manage);
    }

    public List<Manage> getAllItems() {
        return manageRepo.findAll();
    }

    public void deleteItem(Long id) {
        manageRepo.deleteById(id);
    }

    public void saveAll(List<Manage> manages) {
        manageRepo.saveAll(manages);
    }
}