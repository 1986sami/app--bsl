package com.grupo1.BSL.service;

import com.grupo1.BSL.models.MovimientoDinero;
import com.grupo1.BSL.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<MovimientoDinero> getAllByEnterprise(int idEmpresa){
        return (List<MovimientoDinero>) movimientoRepository.findByEmpresa(idEmpresa);
    }

    public MovimientoDinero saveByEnterprise(MovimientoDinero movimientoDinero, int idEnterprise){
        return movimientoRepository.save(movimientoDinero);
    }

    public MovimientoDinero updateByEnterprise(MovimientoDinero movimientoMod, int idEnterprise){
        return  movimientoRepository.save(movimientoMod);
    }

    public boolean deleteByIdAndEnterprise(int id, int idEnterprise){
        if(movimientoRepository.findByIdAndEmpresa(id, idEnterprise).isPresent()){
            movimientoRepository.deleteById(id);

            if(movimientoRepository.findById(id).isPresent()){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean delete(int id){

        movimientoRepository.deleteById(id);

        if(movimientoRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

    public MovimientoDinero getById(int id){
        return movimientoRepository.findById(id).get();
    }

}
