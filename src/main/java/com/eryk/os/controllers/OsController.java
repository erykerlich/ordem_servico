package com.eryk.os.controllers;

import com.eryk.os.domain.OS;
import com.eryk.os.dtos.OsDTO;
import com.eryk.os.services.OsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
