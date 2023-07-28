package com.eryk.os.services;

import com.eryk.os.domain.Cliente;
import com.eryk.os.domain.OS;
import com.eryk.os.domain.Tecnico;
import com.eryk.os.domain.enuns.Priority;
import com.eryk.os.domain.enuns.Status;
import com.eryk.os.repositories.ClienteRepository;
import com.eryk.os.repositories.OSRepository;
import com.eryk.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OSRepository osRepository;

    public void instanciaDB(){
        Tecnico t1 = new Tecnico(null, "Eryk", "304.019.190-05", "(88)8888-8888");
        Tecnico t2 = new Tecnico(null, "Felipe", "632.653.970-68", "(88)8888-9999");
        Cliente c1 = new Cliente(null, "Camila", "654.936.720-49", "(88)8888-7777");
        OS os1 = new OS(null, Priority.HIGH, "Teste create OS", Status.OPEN, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1, t2));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }

}
