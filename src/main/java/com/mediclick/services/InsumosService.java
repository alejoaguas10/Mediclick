package com.mediclick.services;

import com.mediclick.entities.Insumos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//Methods implementation
public interface InsumosService {

    public List<Insumos> findAll();

    public Page<Insumos> findAll(Pageable pageable);

    public void save(Insumos insumos);

    public Insumos findOne(Integer idinsumos);

    public void delete(Integer idinsumos);
}
