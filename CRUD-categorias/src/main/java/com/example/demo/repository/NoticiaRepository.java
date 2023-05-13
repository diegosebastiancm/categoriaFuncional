package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Noticias;

public interface NoticiaRepository extends JpaRepository<Noticias, Integer>{

}
