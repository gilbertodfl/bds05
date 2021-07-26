package com.devsuperior.movieflix.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.devsuperior.movieflix.dto.CityDTO;
import com.devsuperior.movieflix.services.CityService;


@RestController
@RequestMapping(value = "/cities")

public class CityController {

		@Autowired
		private CityService service;
	
		
		@GetMapping
		public ResponseEntity<List<CityDTO>> findAll() {
			List<CityDTO> list = service.findAll();		
			return ResponseEntity.ok().body(list);
		}
		
/*		
 * Embora paginado seja melhor, o exercício pede que seja uma lista simples. 
		@GetMapping
		public ResponseEntity<Page<CityDTO>> findAll(Pageable pageable) {
			PageRequest pageRequest = PageRequest.of( pageable.getPageNumber(), pageable.getPageSize() , Sort.by("name") );
			Page<CityDTO> list = service.findAllPaged(pageRequest);
			return ResponseEntity.ok().body(list); 
		}
*/		
		@GetMapping(value = "/{id}")
		public ResponseEntity<CityDTO> findById(@PathVariable Long id) {
			CityDTO dto = service.findById(id);
			return ResponseEntity.ok().body(dto);
		}
		
		@PostMapping
		public ResponseEntity<CityDTO> insert(@Valid @RequestBody CityDTO dto) {
			// o parametro @Valid é o que ativa as validações. Sem ele nada do que foi construído será executado. 
			dto = service.insert(dto);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(dto.getId()).toUri();
			return ResponseEntity.created(uri).body(dto);
		}

		@PutMapping(value = "/{id}")
		public ResponseEntity<CityDTO> update(@Valid @PathVariable Long id, @RequestBody CityDTO dto) {
			dto = service.update(id, dto);
			return ResponseEntity.ok().body(dto);
		}

		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}		
}
