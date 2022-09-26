package com.grupo1.BSL.repository;

import com.grupo1.BSL.models.MovimientoDinero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository  extends CrudRepository<MovimientoDinero, Integer> {
    @Query(value = "select * from movimiento_dinero where usuario_id in (select id from empleado where empresa_id = ?1) ", nativeQuery = true)
    List<MovimientoDinero> findByEmpresa(int idEmpresa);

    @Query(value = "select * from movimiento_dinero where id=?1 and usuario_id in (select id from empleado where empresa_id = ?2) ", nativeQuery = true)
    Optional<MovimientoDinero> findByIdAndEmpresa(int id, int idEnterprise);


}
