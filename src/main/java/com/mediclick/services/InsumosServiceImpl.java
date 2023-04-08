package com.mediclick.services;

import com.mediclick.entities.Insumos;
import com.mediclick.entities.InsumosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class InsumosServiceImpl implements InsumosService {

    @Autowired
    private InsumosRepository insumosRepository;

    @Override
    @Transactional(readOnly = true) // Annotation for optimizing memory usage
    public List<Insumos> findAll() {
        return (List<Insumos>) insumosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true) // Annotation for optimizing memory usage
    public Page<Insumos> findAll(Pageable pageable) {
        return insumosRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(Insumos insumos) {
        insumosRepository.save(insumos);

    }

    @Override
    @Transactional(readOnly = true)
    public Insumos findOne(Integer idinsumos) {
        return insumosRepository.findById(idinsumos).orElse(null);
    }

    @Override
    public void delete(Integer idproduct) {
        insumosRepository.deleteById(idproduct);
    }
}
