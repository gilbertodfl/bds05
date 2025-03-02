package com.devsuperior.movieflix.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;


@RestController
@RequestMapping(value = "/movie")

public class MovieController {

		@Autowired
		private MovieService service;
		
		@GetMapping
		public ResponseEntity<Page<MovieDTO>> findAll(Pageable pageable) {
			
			PageRequest pageRequest = PageRequest.of( pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name"));
			Page<MovieDTO> list = service.findAll(pageRequest);		
			return ResponseEntity.ok().body(list);
		}
		
		@GetMapping(value = "/{id}")
		public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
			MovieDTO dto = service.findById(id);
			return ResponseEntity.ok().body(dto);
		}
		
		@PostMapping
		public ResponseEntity<MovieDTO> insert(@Valid @RequestBody MovieDTO dto) {
			dto = service.insert(dto);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(dto.getId()).toUri();
			return ResponseEntity.created(uri).body(dto);
		}

		@PutMapping(value = "/{id}")
		public ResponseEntity<MovieDTO> update( @PathVariable Long id,  @Valid @RequestBody MovieDTO dto) {
			dto = service.update(id, dto);
			return ResponseEntity.ok().body(dto);
		}

		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}	
}
