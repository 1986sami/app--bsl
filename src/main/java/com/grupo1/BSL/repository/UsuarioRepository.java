package com.grupo1.BSL.repository;


import com.grupo1.BSL.models.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends CrudRepository<Empleado, Integer> {
    @Query(value="select * from Empleado e where e.empresa_id = ?1", nativeQuery = true)
    public  abstract ArrayList<Empleado> findByEmpresa(Integer id);
}
