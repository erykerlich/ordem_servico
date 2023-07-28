package com.eryk.os.repositories;

import com.eryk.os.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    @Query("SELECT obj FROM PESSOAS obj WHERE obj.cpf =:cpf")
    Pessoa findByCPF(@Param("cpf") String cpf);

}
