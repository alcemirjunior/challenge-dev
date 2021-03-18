package com.alcemirjunior.github.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;


@Entity
@Table(name = "city")
public class City extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long population;

    @ManyToOne
    private State state;

    private Long stateId;

    public City() {
    }

    public City(Long id, String name, Long population, State state) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
}
