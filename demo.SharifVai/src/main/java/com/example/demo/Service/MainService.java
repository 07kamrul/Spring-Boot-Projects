package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Product;
import com.example.demo.Repository.MainRepo;


@Service
public class MainService {

	@Autowired
	private MainRepo mRepo;

	public List<Product> listAll() {
		return (List<Product>) mRepo.findAll();
	}

	public void save(Product product) {
		mRepo.save(product);
	}

	public Product get(int id) {
		return mRepo.findById(id).get();
	}

	public void delete(int id) {
		mRepo.deleteById(id);
	}
}
