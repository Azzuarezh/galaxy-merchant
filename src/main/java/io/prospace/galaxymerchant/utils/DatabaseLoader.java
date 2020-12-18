package io.prospace.galaxymerchant.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.prospace.galaxymerchant.repository.material.Material;
import io.prospace.galaxymerchant.repository.material.MaterialRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final MaterialRepository repository;

	@Autowired
	public DatabaseLoader(MaterialRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		this.repository.save(
				new Material("Silver", "A soft, white, lustrous transition metal, it exhibits the highest electrical conductivity, thermal conductivity, and reflectivity of any metal.",
						17.0));
		this.repository.save(
				new Material("Gold", "A relatively rare element, gold is a precious metal that has been used for coinage, jewelry, and other arts throughout recorded history.",
						14450.0));
		this.repository.save(
				new Material("Iron", "it is by mass the most common element on Earth, right in front of oxygen.",
						195.5));

	}

}
