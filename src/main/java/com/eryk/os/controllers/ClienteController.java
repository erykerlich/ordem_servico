package com.eryk.os.controllers;

import com.eryk.os.domain.Cliente;
import com.eryk.os.dtos.ClienteDTO;
import com.eryk.os.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService tecnicoService;
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Cliente obj = tecnicoService.findById(id);
        ClienteDTO objDTO = new ClienteDTO(obj);
        return ResponseEntity.ok().body(objDTO);
    }
    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> listDto = tecnicoService.findAll().stream().map(obj -> new ClienteDTO(obj)).
                collect(Collectors.toList());
     /*List<Cliente> list = tecnicoService.findAll();
     List<ClienteDTO> listDto = new ArrayList<>();
        for (Cliente obj: list) {
            listDto.add(new ClienteDTO(obj));
        }
        list.forEach(obj -> listDto.add(new ClienteDTO(obj)));*/
        return ResponseEntity.ok().body(listDto);

    }
    @PostMapping()
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDto) {
        Cliente newObj = tecnicoService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO objDto) {
        Cliente newObj = tecnicoService.update(id, objDto);
        return ResponseEntity.ok().body(new ClienteDTO(newObj));
        //Poderia ser feito assim tambem
        // ClienteDTO newObj = new ClienteDTO(tecnicoService.update(id, objDto));
        //        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
