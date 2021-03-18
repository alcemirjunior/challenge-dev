package com.alcemirjunior.github.services;


import com.alcemirjunior.github.DTO.CityDTO;
import com.alcemirjunior.github.entities.City;
import com.alcemirjunior.github.entities.State;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CityService {

    public List<CityDTO> findAllPaged(Page pageRequest){
        List<CityDTO> citiesDTO = new ArrayList<>();
        List<City> cities = City.findAll().page(pageRequest).list();
        cities.forEach((x)-> citiesDTO.add(new CityDTO(x)));
        return citiesDTO;
    }

    public List<CityDTO> findByState(Long stateId ,Page pageRequest){
        List<CityDTO> cityDTO = new ArrayList<>();
        Optional<State> stateOptional = State.findByIdOptional(stateId);
        if (stateOptional.isPresent()) {
            List<City> cities = City.find("stateId", stateId).page(pageRequest).list();
            cities.forEach((x) -> cityDTO.add(new CityDTO(x)));
            return cityDTO;
        }else{
            throw new NotFoundException();
        }
    }

    public List<CityDTO> findByCityName(String cityName, Page pageRequest) {
        List<CityDTO> citiesDTO = new ArrayList<>();
        List<City> cities = City.find("LOWER(name)", cityName.toLowerCase()).page(pageRequest).list();
        cities.forEach((x) -> citiesDTO.add(new CityDTO(x)));
        return citiesDTO;
    }

    public Long registers(){
        return City.count();
    }

    @Transactional
    public CityDTO insert (CityDTO dto){
        Optional<State> stateOptional = State.findByIdOptional(dto.getState().getId());
        if (stateOptional.isPresent()) {
            State state = stateOptional.get();
            City city = new City();

            dtoToEntity(dto, city);
            city.setStateId(state.getId());

            city.setState(state);
            state.getCities().add(city);
            state.increasePopulation(dto.getPopulation());

            state.persist();
            city.persist();
            return dto;
        }else{
            throw new NotFoundException();
        }
    }

    @Transactional
    public CityDTO update (Long id, CityDTO dto){
        Optional<City> cityOptional = City.findByIdOptional(id);
        if(cityOptional.isPresent()){
            City city = cityOptional.get();
            dtoToEntity(dto, city);
            city.persist();
            return dto;
        }else{
            throw new NotFoundException();
        }
    }

    @Transactional
    public void delete (Long id){
        Optional<City> cityOptional = City.findByIdOptional(id);
        if(cityOptional.isPresent()){
            City.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }

    public void dtoToEntity(CityDTO dto, City entity){
        entity.setName(dto.getName());
        entity.setPopulation(dto.getPopulation());
    }
}