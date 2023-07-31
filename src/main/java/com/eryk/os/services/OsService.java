package com.eryk.os.services;

import com.eryk.os.domain.OS;
import com.eryk.os.dtos.OsDTO;
import com.eryk.os.repositories.OSRepository;
import com.eryk.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository osRepository;

    public OS findById(Integer id) {
        Optional<OS> obj = osRepository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! Id: " + id
    + ", Tipo: " + OS.class.getName()));
    }

    public List<OS> findAll() {
        return osRepository.findAll();
    }
}
