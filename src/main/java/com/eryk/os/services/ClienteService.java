package com.eryk.os.services;

import com.eryk.os.domain.Pessoa;
import com.eryk.os.domain.Cliente;
import com.eryk.os.dtos.ClienteDTO;
import com.eryk.os.repositories.PessoaRepository;
import com.eryk.os.repositories.ClienteRepository;
import com.eryk.os.services.exceptions.DataIntegratyViolationException;
import com.eryk.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
       Optional<Cliente> obj = tecnicoRepository.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException
               ("Objeto não encontrado ! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
       return tecnicoRepository.findAll();
    }

    private Pessoa findByCPF(ClienteDTO objDto) {
        Pessoa obj = pessoaRepository.findByCPF(objDto.getCpf());
        if(obj != null) {
            return obj;
        }
        return null;
    }

    public Cliente create(ClienteDTO objDto) {
        if(findByCPF(objDto) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados !");
        }
        Cliente newObj = new Cliente(null, objDto.getName(), objDto.getCpf(), objDto.getCellphone());
        return tecnicoRepository.save(newObj);
    }

    public Cliente update(Integer id, ClienteDTO objDto) {
        Cliente oldObj = findById(id);
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
        Cliente obj = findById(id);
        //Verefica se tem ordens de serviço
        if(obj.getList().size() > 0) {
            throw new DataIntegratyViolationException("Pessoa possui ordens de serviço ! Não pode ser deletado.");
        }
        tecnicoRepository.deleteById(id);
    }
}
