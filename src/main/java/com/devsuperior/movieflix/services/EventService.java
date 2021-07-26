package com.devsuperior.movieflix.services;

//import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.EventDTO;
import com.devsuperior.movieflix.entities.City;
import com.devsuperior.movieflix.entities.Event;
import com.devsuperior.movieflix.repositories.EventRepository;
import com.devsuperior.movieflix.services.exceptions.DatabaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;



@Service
public class EventService {
	// Já instância : org.springframework.beans.factory.annotation.Autowired;
	@Autowired
	private EventRepository repository;
/*	public List<EventDTO> findAll(){
		List<Event> list = repository.findAll(Sort.by("name"));
		// o comando abaixo faz um "FOR" 
		return list.stream().map( x -> new EventDTO(x)).collect(Collectors.toList());
		
	}
*/	
	@Transactional(readOnly = true)
	public Page<EventDTO> findAll(Pageable pageable) {
		Page<Event> list = repository.findAll(pageable);
		return list.map(x -> new EventDTO(x));
	}

	@Transactional(readOnly = true)
	public EventDTO findById(Long id) {
		Optional<Event> obj = repository.findById(id);
		Event entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new EventDTO(entity);
	}

	@Transactional 
	public EventDTO insert(EventDTO dto) {
		Event entity = new Event();
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
		entity.setCity ( new City ( dto.getCityId(), null ));
		entity = repository.save(entity);
		return new EventDTO(entity);
	
	}

	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event entity = repository.getOne(id);
		//	Event entity = new Event();
			entity.setName(dto.getName());
			entity.setDate(dto.getDate());
			entity.setUrl(dto.getUrl());
			entity.setCity ( new City ( dto.getCityId(), null ));
			entity = repository.save(entity);
			return new EventDTO(entity);

		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}		
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}	
	
}
