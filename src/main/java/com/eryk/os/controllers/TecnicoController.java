package com.eryk.os.controllers;

import com.eryk.os.domain.Tecnico;
import com.eryk.os.dtos.TecnicoDTO;
import com.eryk.os.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico obj = tecnicoService.findById(id);
        TecnicoDTO objDTO = new TecnicoDTO(obj);
        return ResponseEntity.ok().body(objDTO);
    }
    @GetMapping()
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<TecnicoDTO> listDto = tecnicoService.findAll().stream().map(obj -> new TecnicoDTO(obj)).
                collect(Collectors.toList());
     /*List<Tecnico> list = tecnicoService.findAll();
     List<TecnicoDTO> listDto = new ArrayList<>();
        for (Tecnico obj: list) {
            listDto.add(new TecnicoDTO(obj));
        }
        list.forEach(obj -> listDto.add(new TecnicoDTO(obj)));*/
        return ResponseEntity.ok().body(listDto);

    }
    @PostMapping()
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDto) {
        Tecnico newObj = tecnicoService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,@Valid @RequestBody TecnicoDTO objDto) {
        Tecnico newObj = tecnicoService.update(id, objDto);
        return ResponseEntity.ok().body(new TecnicoDTO(newObj));
        //Poderia ser feito assim tambem
        // TecnicoDTO newObj = new TecnicoDTO(tecnicoService.update(id, objDto));
        //        return ResponseEntity.ok().body(newObj);
    }

}
