package com.eryk.os.controllers;

import com.eryk.os.domain.OS;
import com.eryk.os.dtos.OsDTO;
import com.eryk.os.services.OsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/os")
public class OsController {

    @Autowired
    private OsService osService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OsDTO> findById(@PathVariable Integer id){
        //pode ser feito assim tbm
        //osdto obj = new osdto(osservice.findbyid(id);
        OS obj = osService.findById(id);
        OsDTO objDto = new OsDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping
    public ResponseEntity<List<OsDTO>> findAll() {
        List<OS> list = osService.findAll();
        List<OsDTO> listDto = new ArrayList<>();
        for (OS obj : list) {
            listDto.add(new OsDTO(obj));
        }
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<OsDTO> create(@Valid @RequestBody OsDTO objDto) {
        objDto = new OsDTO(osService.create(objDto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(objDto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
