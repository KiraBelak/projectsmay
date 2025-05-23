package com.alldata.jproject.controller;

import com.alldata.jproject.model.Orden;
import com.alldata.jproject.model.Usuario;
import com.alldata.jproject.service.OrdenService;
import com.alldata.jproject.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {this.usuarioService = usuarioService;}

    @Autowired
    private OrdenService ordenService;

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);


    @Autowired
    BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();

    @GetMapping("/registro")
    public String create(){
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario){
        logger.info("Usuario registro: {}", usuario);
        usuario.setTipo("USER");
        usuario.setPassword(passEncode.encode(usuario.getPassword()));
        usuarioService.save(usuario);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }

    @GetMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session){
        logger.info("Accesos : {}", usuario);

        Optional<Usuario> user = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));
        //logger.info("Usuario obtenido: {}",user.get());

        if(user.isPresent()){
            session.setAttribute("idusuario",user.get().getId());
            if(user.get().getTipo().equals("ADMIN")){
                return "redirect:/administrador";
            }else {
                return "redirect:/";
            }
        }else {
            logger.info("Usuario no existe");
        }

        return "redirect:/";
    }


    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session){
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        Usuario usuario= usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        List<Orden> ordenes = ordenService.findByUsuario(usuario);

        model.addAttribute("ordenes", ordenes);

        return "usuario/compras";
    }

    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model){
        logger.info("Id del de la orden: {}",id);

        Optional<Orden> orden = ordenService.findById(id);

        model.addAttribute("detalles", orden.get().getDetalle());

        //session
        model.addAttribute("sesion",session.getAttribute("idusuario"));

        return "usuario/detallecompra";
    }

    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session){

        session.removeAttribute("idusuario");

        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {usuarioService.delete(id);}

  /*      @PutMapping
    public Usuario update(@RequestBody Usuario usuario){
    Usuario usuarioDB= usuarioService.findById(usuario.getId());
        usuarioDB.setDireccion(usuario.getDireccion());
        usuarioDB.setNombre(usuario.getNombre());
        usuarioDB.setEmail(usuario.getEmail());
        usuarioDB.setPassword(usuario.getPassword());
        usuarioDB.setUsername(usuario.getUsername());
        usuarioDB.setTelefono(usuario.getTelefono());

        return usuarioService.update(usuarioDB);
    }*/
}
