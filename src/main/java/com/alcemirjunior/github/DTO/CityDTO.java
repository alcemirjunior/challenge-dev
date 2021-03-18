package com.alcemirjunior.github.DTO;

import com.alcemirjunior.github.entities.City;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CityDTO {

    @Size(min=3, max=30)
    @NotBlank
    private String name;
    private Long population;
    private StateDTO state;

    public CityDTO() {
    }

    public CityDTO(String name, Long population) {
        this.name = name;
        this.population = population;
    }

    public CityDTO(City entity) {
        name = entity.getName();
        population = entity.getPopulation();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public StateDTO getState() {
        return state;
    }

    public void setState(StateDTO state) {
        this.state = state;
    }

}
