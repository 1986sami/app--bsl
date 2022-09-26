package com.grupo1.BSL.controller;

import com.grupo1.BSL.models.Empleado;
import com.grupo1.BSL.models.Empresa;
import com.grupo1.BSL.models.MovimientoDinero;
import com.grupo1.BSL.service.EmpresaService;
import com.grupo1.BSL.service.MovimientoService;
import com.grupo1.BSL.service.UsuarioService;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FrontController {
        private final EmpresaService empresaService;
        private final UsuarioService usuarioService;
        private final MovimientoService movimientoService;

        public FrontController (EmpresaService empresaService,UsuarioService usuarioService,MovimientoService movimientoService){
            this.empresaService =empresaService;
            this.movimientoService= movimientoService;
            this.usuarioService= usuarioService;

        }


    @GetMapping({"/","/VerEmpresas"})
        public String viewEmpresas(Model model, @ModelAttribute("mensaje") String mensaje){
            List<Empresa> listaEmpresas=empresaService.getAll();
            model.addAttribute("emplist",listaEmpresas);
            model.addAttribute("mensaje",mensaje);
            return "verEmpresas"; //Llamamos al HTML
        }

        @GetMapping("/AgregarEmpresa")
        public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
            Empresa emp= new Empresa();
            model.addAttribute("emp",emp);
            model.addAttribute("mensaje",mensaje);
            return "agregarEmpresa";
        }

        @PostMapping("/GuardarEmpresa")
        public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes){
            empresaService.save(emp);
            return  "redirect:/VerEmpresas";

        }



        @GetMapping ("/VerEmpleados")
        public String viewEmpleados(Model model, @ModelAttribute("mensaje") String mensaje){
            List<Empleado> listaEmpleados=  usuarioService.getAll();
            model.addAttribute("emplelist",listaEmpleados);
            model.addAttribute("mensaje",mensaje);
            return "verEmpleados"; //Llamamos al HTML
        }
        @GetMapping("/AgregarEmpleado")
        public String nuevoEmpleado(Model model, @ModelAttribute("mensaje") String mensaje){
            Empleado empl= new Empleado();
            model.addAttribute("empl",empl);
            model.addAttribute("mensaje",mensaje);
            List<Empresa> listaEmpresas= empresaService.getAll();
            model.addAttribute("emprelist",listaEmpresas);
            return "agregarEmpleado"; //Llamar HTML
        }

        @PostMapping("/GuardarEmpleado")
        public String guardarEmpleado(Empleado empl, RedirectAttributes redirectAttributes) {
            String passEncriptada=passwordEncoder().encode(empl.getPassword());
            empl.setPassword(passEncriptada);
            usuarioService.save(empl);
            return "redirect:/AgregarEmpleado";
        }


        @GetMapping("/EliminarEmpleado/{id}")
        public String eliminarEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes){
            return "redirect:/VerEmpleados";
        }

        @GetMapping ("/VerMovimientos/{idEmpresa}")
        public String viewMovements(Model model, @ModelAttribute("mensaje") String mensaje,@PathVariable("idEmpresa") int idEmpresa){
            List<MovimientoDinero> listMovimientos = movimientoService.getAllByEnterprise(idEmpresa);
            model.addAttribute("movlist",listMovimientos);
            model.addAttribute("mensaje",mensaje);
           model.addAttribute("idEmpresa",idEmpresa);
            return "verMovimientos"; //Llamamos al HTML
        }

        @GetMapping("/AgregarMovimiento/{idEmpresa}")
        public String addMovimiento(Model model, @ModelAttribute("mensaje") String mensaje, @PathVariable("idEmpresa") int idEmpresa){
            MovimientoDinero movimiento = new MovimientoDinero();
            List<Empleado> listEmpleado =  usuarioService.getByEmpresa(idEmpresa);

            model.addAttribute("movimiento", movimiento);
            model.addAttribute("listEmpleados", listEmpleado);
            model.addAttribute("mensaje", mensaje);
            return "agregarMovimiento";
        }

        @PostMapping("/GuardarMovimiento")
        public String saveMovimiento(MovimientoDinero movimiento, RedirectAttributes redirectAttributes){

            MovimientoDinero movimientoSaved = movimientoService.saveByEnterprise(movimiento, 1);

            if(movimientoSaved != null)
            {
                redirectAttributes.addFlashAttribute("mensaje","saveOK");
                return "redirect:/VerMovimientos/" + movimientoSaved.getUsuario().getEmpresa().getId();
            }
            redirectAttributes.addFlashAttribute("mensaje", "saveError");
            return "redirect:/VerMovimientos/" + movimiento.getUsuario().getEmpresa().getId();
        }

        @PostMapping("/ActualizarMovimiento")
        public String updateMovimiento(@ModelAttribute("mov") MovimientoDinero mov, RedirectAttributes redirectAttributes){
            return "redirect:/EditarMovimiemto/"+mov.getId();
        }

        @GetMapping("/EliminarMovimiento/{id}")
        public String eliminarMovimiento(@PathVariable("id") int id, RedirectAttributes redirectAttributes){

            int empresa = movimientoService.getById(id).getUsuario().getEmpresa().getId();

            if(movimientoService.delete(id)){
                redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
                return "redirect:/VerMovimientos/"+empresa;
            }
            redirectAttributes.addFlashAttribute("mensaje", "deleteError");
            return "redirect:/VerMovimientos/"+empresa;
        }

    @RequestMapping(value="/Denegado")
    public String accesoDenegado(){

            return "accessDenied";
    }

    /*@GetMapping("/VerMovimientos")
    public String accesoMovimientos(){

        return "verMovimientos";
    }*/

    //Metodo para encriptar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
