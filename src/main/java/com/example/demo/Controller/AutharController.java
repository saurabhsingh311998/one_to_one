package com.example.demo.Controller;

import com.example.demo.Entity.AutharEntity;
import com.example.demo.Entity.ResponseEntity;
import com.example.demo.Service.AutharService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authar")
public class AutharController {
    @Autowired
    AutharService autharService;

    @PostMapping("/save")
    public ResponseEntity<AutharEntity> saveData(@RequestBody AutharEntity autharEntity)
    {
        AutharEntity savedEntity = autharService.saveData(autharEntity);
        return new ResponseEntity<>(false, HttpStatus.OK.value(), savedEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AutharEntity>> findById(@PathVariable int id)
    {
        Optional<AutharEntity> entity = autharService.findById(id);
        if (entity.isPresent()) {
            return new ResponseEntity<>(false, HttpStatus.OK.value(), entity); // Wrap in ResponseEntity
        } else {
            return new ResponseEntity<>(true, HttpStatus.NOT_FOUND.value(), null); // Wrap in ResponseEntity
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AutharEntity>> findAll()
    {
        List<AutharEntity> entities = autharService.findAll();
        return new ResponseEntity<>(false, HttpStatus.OK.value(), entities); // Wrap in ResponseEntity
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id)
    {
        autharService.deleteById(id);
        return new ResponseEntity<>(false, HttpStatus.OK.value(), null); // Successfully deleted
    }
}
