package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.exceptions.DatabaseException;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;



    public EventDTO update(Long id, EventDTO body) {
        try{
            Event entity = repository.findById(id).get();
            entity.setName(body.getName());
            entity.setDate(body.getDate());
            entity.setUrl(body.getUrl());
            entity.setCity(new City(body.getCityId(), null));
            EventDTO eventDTO = new EventDTO(entity);
            return eventDTO;
        }
       catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Id not found");
       }
    }



}
