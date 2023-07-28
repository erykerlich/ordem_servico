package com.eryk.os.services;

import com.eryk.os.domain.Tecnico;
import com.eryk.os.dtos.TecnicoDTO;
import com.eryk.os.repositories.TecnicoRepository;
import com.eryk.os.services.exceptions.DataIntegratyViolationException;
import com.eryk.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    public Tecnico findById(Integer id) {
       Optional<Tecnico> obj = tecnicoRepository.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException
               ("Objeto não encontrado ! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
       return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDto) {
        if(findByCPF(objDto) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados !");
        }
        Tecnico newObj = new Tecnico(null, objDto.getName(), objDto.getCpf(), objDto.getCellphone());
        return tecnicoRepository.save(newObj);
    }
    private Tecnico findByCPF(TecnicoDTO objDto) {
        Tecnico obj = tecnicoRepository.findByCPF(objDto.getCpf());
        if(obj != null) {
            return obj;
        }
        return null;
    }
}
