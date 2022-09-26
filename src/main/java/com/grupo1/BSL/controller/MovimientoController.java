package com.grupo1.BSL.controller;

import com.grupo1.BSL.models.MovimientoDinero;
import com.grupo1.BSL.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("/enterprises/{id}/movements")
    public List<MovimientoDinero> getAllByEnterprise(@PathVariable("id") int idEnterprise){
        return movimientoService.getAllByEnterprise(idEnterprise);
    }

    @PostMapping("/enterprises/{id}/movements")
    public MovimientoDinero saveByEnterprise(@PathVariable("id") int idEnterprise, @RequestBody MovimientoDinero movimiento){
        return movimientoService.saveByEnterprise(movimiento, idEnterprise);
    }

    @PatchMapping("/enterprises/{id}/movements")
    public MovimientoDinero updateByEnterprise(@PathVariable("id") int idEnterprise,  @RequestBody MovimientoDinero movimiento){
        return movimientoService.updateByEnterprise(movimiento, idEnterprise);
    }

    @DeleteMapping("/enterprises/{id}/movements/{idMovements}")
    public boolean deleteByIdAndEnterprise(@PathVariable("idMovements") int id, @PathVariable("id") int idEnterprise){
        return  movimientoService.deleteByIdAndEnterprise(id, idEnterprise);
    }
}
