package com.mediclick.services;

import com.mediclick.entities.Citas;
import com.mediclick.entities.CitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CitasServiceImpl implements CitasService {

    @Autowired
    private CitasRepository citasRepository;

    @Override
    @Transactional(readOnly = true) // Annotation for optimizing memory usage
    public List<Citas> findAll() {
        return (List<Citas>) citasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true) // Annotation for optimizing memory usage
    public Page<Citas> findAll(Pageable pageable) {
        return citasRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(Citas citas) {
        citasRepository.save(citas);

    }

    @Override
    @Transactional(readOnly = true)
    public Citas findOne(Integer idcitas) {
        return citasRepository.findById(idcitas).orElse(null);
    }

    @Override
    public void delete(Integer idcitas) {
        citasRepository.deleteById(idcitas);
    }
}
