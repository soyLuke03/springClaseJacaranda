package com.jacaranda.springProjecToWork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.springProjecToWork.model.Element;

public interface ElementRepository extends JpaRepository<Element, Integer> {

}
