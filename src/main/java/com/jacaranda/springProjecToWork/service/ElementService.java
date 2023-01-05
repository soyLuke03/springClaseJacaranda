package com.jacaranda.springProjecToWork.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.springProjecToWork.model.Element;
import com.jacaranda.springProjecToWork.repository.ElementRepository;

@Service
public class ElementService {
	@Autowired
	private ElementRepository repository;
	
	public Element add(Element s) {
		return repository.save(s);
	}

	public List<Element> findAll() {
		return repository.findAll();
	}

	public Element findById(int id) {
		return repository.findById(id).orElse(null);
	}

	public Element save(Element e) {
		return repository.save(e);
	}

	public void delete(Element e) {
		repository.delete(e);
	}
}
