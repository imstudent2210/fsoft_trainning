package com.example.library.service.impl;

import com.example.library.model.Manufacturer;
import com.example.library.repository.ManufactureRepository;
import com.example.library.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufactureRepository manufactureRepository;
    @Override
    public List<Manufacturer> findAll() {
        return manufactureRepository.findAll();
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        Manufacturer manufacturerSave = new Manufacturer();
        manufacturerSave.getName();
        manufacturerSave.getAddress();
        manufacturerSave.getEmail();
        manufacturerSave.setActivated(true);
        manufacturerSave.setDeleted(false);
        return manufactureRepository.save(manufacturer);
    }

    @Override
    public Manufacturer findById(Long id) {
        return manufactureRepository.findById(id).get();
    }

//    @Override
//    public void deleteById(Long id) {
//        manufactureRepository.deleteById(id);
//    }
//

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        Manufacturer manufacturerUpdate = null;
        try {
            manufacturerUpdate= manufactureRepository.findById(manufacturer.getId()).get();
            manufacturerUpdate.setName(manufacturer.getName());
            manufacturerUpdate.setAddress(manufacturer.getAddress());
            manufacturerUpdate.setEmail(manufacturer.getEmail());
            manufacturerUpdate.setActivated(manufacturer.isActivated());
            manufacturerUpdate.setDeleted(manufacturer.isDeleted());
        }catch (Exception e){
            e.printStackTrace();
        }
        return manufactureRepository.save(manufacturerUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Manufacturer manufacturer = manufactureRepository.getById(id);
        manufacturer.setDeleted(true);
        manufacturer.setActivated(false);
        manufactureRepository.save(manufacturer);
    }

    @Override
    public void enabledById(Long id) {
        Manufacturer manufacturer = manufactureRepository.getById(id);
        manufacturer.setActivated(true);
        manufacturer.setDeleted(false);
        manufactureRepository.save(manufacturer);
    }

    @Override
    public List<Manufacturer> findAllByActivated() {
        return manufactureRepository.findAllByActivated();
    }

}
