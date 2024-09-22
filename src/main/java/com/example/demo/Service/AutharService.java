package com.example.demo.Service;

import com.example.demo.Entity.AutharEntity;
import com.example.demo.Repository.AutharRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutharService {
    @Autowired
    AutharRepository autharRepository;
    public boolean existsById(int id) {
        return autharRepository.existsById(id);
    }
    public AutharEntity saveData(AutharEntity autharEntity)
    {
        return autharRepository.save(autharEntity);
    }

    public Optional<AutharEntity> findById(int id)
    {
        return autharRepository.findById(id);
    }

    public List<AutharEntity> findAll()
    {
        return autharRepository.findAll();
    }

    public void deleteById(int id)
    {
        autharRepository.deleteById(id);
    }
}
