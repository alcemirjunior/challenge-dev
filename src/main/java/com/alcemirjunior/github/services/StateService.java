package com.alcemirjunior.github.services;

import com.alcemirjunior.github.DTO.StateDTO;
import com.alcemirjunior.github.entities.State;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class StateService {

    public List<StateDTO> findAllPaged (Page pageRequest){
        List<StateDTO> statesDto = new ArrayList<>();
        List<State> states = State.findAll().page(pageRequest).list();
        states.forEach((x)-> statesDto.add(new StateDTO(x)));
        return statesDto;
    }

    public Long registers(){
        return State.count();
    }

    @Transactional
    public StateDTO insert (StateDTO dto){
        State state = new State();
        dtoToEntity(dto, state);
        state.persist();
        return dto;
    }

    @Transactional
    public StateDTO update (Long id, StateDTO dto){
        Optional<State> stateOptional = State.findByIdOptional(id);
        if (stateOptional.isPresent()){
            State state = stateOptional.get();
            dtoToEntity(dto,state);
            state.persist();
            return dto;
        }else{
            throw new NotFoundException();
        }
    }

    @Transactional
    public void delete(Long id){
        Optional<State> stateOptional = State.findByIdOptional(id);
        if(stateOptional.isPresent()){
            State.deleteById(id);
        }else{
            throw new NotFoundException();
        }
    }


    public void dtoToEntity(StateDTO dto, State entity){
        entity.setName(dto.getName());
    }
}
