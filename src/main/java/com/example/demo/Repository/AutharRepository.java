package com.example.demo.Repository;

import com.example.demo.Entity.AutharEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutharRepository extends JpaRepository<AutharEntity,Integer> {
}
