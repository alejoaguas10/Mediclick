package com.mediclick.services;

import com.mediclick.entities.Historiales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//Implementacion de metodos
public interface HistorialesService {

    public List<Historiales> findAll();

    public Page<Historiales> findAll(Pageable pageable);

    public void save(Historiales historiales);

    public Historiales findOne(Integer idhistoriales);

    public void delete(Integer idhistoriales);
}
