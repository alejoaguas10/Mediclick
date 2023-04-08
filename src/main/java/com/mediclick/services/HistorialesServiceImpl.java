package com.mediclick.services;

import com.mediclick.entities.Historiales;
import com.mediclick.entities.HistorialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistorialesServiceImpl implements HistorialesService {

    @Autowired
    private HistorialesRepository historialesRepository;

    @Override
    @Transactional(readOnly = true) // Anotacion para optimizar memoria en la recuperacion de registros
    public List<Historiales> findAll() {
        return (List<Historiales>) historialesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true) // Anotacion para optimizar memoria en la recuperacion de registros
    public Page<Historiales> findAll(Pageable pageable) {
        return historialesRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(Historiales historiales) {
        historialesRepository.save(historiales);

    }

    @Override
    @Transactional(readOnly = true)
    public Historiales findOne(Integer idhistoriales) {
        return historialesRepository.findById(idhistoriales).orElse(null);
    }

    @Override
    public void delete(Integer idhistoriales) {
        historialesRepository.deleteById(idhistoriales);
    }
}
