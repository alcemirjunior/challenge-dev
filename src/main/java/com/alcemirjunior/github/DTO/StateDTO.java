package com.alcemirjunior.github.DTO;

import com.alcemirjunior.github.entities.State;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StateDTO {

    private Long id;
    @Size(min=2, max=30)
    @NotBlank
    private String name;
    private Long population;

    public StateDTO(State entity){
        name = entity.getName();
        population = entity.getPopulation();
    }

    public StateDTO() {
    }

    public StateDTO(String name) {
        this.name = name;
    }

    public StateDTO(String name, Long population, Long id) {
        this.name = name;
        this.population = population;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
