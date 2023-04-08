package com.mediclick.services;

import com.mediclick.entities.Citas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//Methods implementation
public interface CitasService {

    public List<Citas> findAll();

    public Page<Citas> findAll(Pageable pageable);

    public void save(Citas citas);

    public Citas findOne(Integer idcitas);

    public void delete(Integer idcitas);
}
