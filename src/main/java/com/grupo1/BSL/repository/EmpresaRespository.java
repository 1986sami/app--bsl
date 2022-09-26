package com.grupo1.BSL.repository;

import com.grupo1.BSL.models.Empresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRespository extends CrudRepository<Empresa, Integer> {
}
