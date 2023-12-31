package com.eryk.os.services;

import com.eryk.os.domain.Pessoa;
import com.eryk.os.domain.Tecnico;
import com.eryk.os.dtos.TecnicoDTO;
import com.eryk.os.repositories.PessoaRepository;
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

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
       Optional<Tecnico> obj = tecnicoRepository.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException
               ("Objeto não encontrado ! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
       return tecnicoRepository.findAll();
    }

    private Pessoa findByCPF(TecnicoDTO objDto) {
        Pessoa obj = pessoaRepository.findByCPF(objDto.getCpf());
        if(obj != null) {
            return obj;
        }
        return null;
    }

    public Tecnico create(TecnicoDTO objDto) {
        if(findByCPF(objDto) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados !");
        }
        Tecnico newObj = new Tecnico(null, objDto.getName(), objDto.getCpf(), objDto.getCellphone());
        return tecnicoRepository.save(newObj);
    }

    public Tecnico update(Integer id, TecnicoDTO objDto) {
        Tecnico oldObj = findById(id);
        //Verifica se o cpf existe e se bate com o id passado
        if(findByCPF(objDto) != null && findByCPF(objDto).getId() != id) {
            throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados !");
        }
        oldObj.setName(objDto.getName());
        oldObj.setCpf(objDto.getCpf());
        oldObj.setCellphone(objDto.getCellphone());
        return tecnicoRepository.save(oldObj);
    }


    public void delete(Integer id) {
        //se existir vai receber como tecnico com o id de parametro
        Tecnico obj = findById(id);
        //Verefica se tem ordens de serviço
        if(obj.getList().size() > 0) {
            throw new DataIntegratyViolationException("Técnico possui ordens de serviço ! Não pode ser deletado.");
        }
        tecnicoRepository.deleteById(id);
    }
}
