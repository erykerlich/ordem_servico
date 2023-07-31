package com.eryk.os.services;

import com.eryk.os.domain.Cliente;
import com.eryk.os.domain.OS;
import com.eryk.os.domain.Tecnico;
import com.eryk.os.domain.enuns.Priority;
import com.eryk.os.domain.enuns.Status;
import com.eryk.os.dtos.OsDTO;
import com.eryk.os.repositories.OSRepository;
import com.eryk.os.repositories.TecnicoRepository;
import com.eryk.os.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository osRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public OS findById(Integer id) {
        Optional<OS> obj = osRepository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! Id: " + id
    + ", Tipo: " + OS.class.getName()));
    }

    public List<OS> findAll() {
        return osRepository.findAll();
    }

    public OS create(@Valid OsDTO objDto) {
        return fromDto(objDto);
    }

    public OS update(@Valid OsDTO obj) {
        findById(obj.getId());
        return fromDto(obj);
    }
    private OS fromDto(OsDTO objDto) {
        OS newObj = new OS();
        newObj.setId(objDto.getId());
        newObj.setObservacoes(objDto.getObservacoes());
        newObj.setPriority(Priority.toEnum(objDto.getPriority()));
        newObj.setStatus(Status.toEnum(objDto.getStatus()));

        Tecnico tec = tecnicoService.findById(objDto.getTecnico());
        Cliente cli = clienteService.findById(objDto.getCliente());

        newObj.setTecnico(tec);
        newObj.setCliente(cli);

        if(newObj.getStatus().getCod().equals(2)) {
            newObj.setDataFinished(LocalDateTime.now());
        }

        return osRepository.save(newObj);
    }


}
