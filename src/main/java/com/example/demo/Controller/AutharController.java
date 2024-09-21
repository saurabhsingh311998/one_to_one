package com.example.demo.Controller;

import com.example.demo.Entity.AutharEntity;
import com.example.demo.Service.AutharService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authar")
public class AutharController {
    @Autowired
    AutharService autharService;

    @PostMapping("/save")
    public AutharEntity saveData(@RequestBody AutharEntity autharEntity)
    {
        return autharService.saveData(autharEntity);
    }

    @GetMapping("/{id}")
    public Optional<AutharEntity> findById(@PathVariable int id)
    {
        return autharService.findById(id);
    }

    @GetMapping("/getAll")
    public List<AutharEntity> findAll()
    {
        return autharService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id)
    {
        autharService.deleteById(id);
    }
}
