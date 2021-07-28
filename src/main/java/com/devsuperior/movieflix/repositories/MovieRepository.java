package com.devsuperior.movieflix.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.movieflix.entities.Movie;

//repare que aqui é uma INTERFACE 
// e quem precisa importar o import org.springframework.data.jpa.repository.JpaRepository;
public interface MovieRepository extends JpaRepository <Movie, Long>{
	//Movie findById(Long id);

}
