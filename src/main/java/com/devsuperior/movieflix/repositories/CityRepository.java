package com.devsuperior.movieflix.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.movieflix.entities.City;



//repare que aqui é uma INTERFACE 
// e quem precisa importar o import org.springframework.data.jpa.repository.JpaRepository;
public interface CityRepository extends JpaRepository <City, Long>{

	City findByName(String city);
	
}
