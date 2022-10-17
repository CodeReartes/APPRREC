package com.egg.AppRECC.controladores;


import com.egg.AppRECC.entidades.Posteo;
import com.egg.AppRECC.excepciones.MiException;
import com.egg.AppRECC.servicios.PosteoServicio;
import org.springframework.stereotype.Controller;
import com.egg.AppRECC.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.egg.AppRECC.servicios.UsuarioServicio;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio servicio;

    @GetMapping({"/usuarios", "/"})
    public String listarUsuario(Model modelo) {
        modelo.addAttribute("usuarios", servicio.listarTodosLosUsuarios());
        return "usuarios";
    }

    @GetMapping("usuarios/register")
    public String crearUsuarioFormulario(Model modelo) {
        Usuario usuario = new Usuario();
        modelo.addAttribute("usuario", usuario);
        return "register";
    }

    @GetMapping({"/usuarios/login"})
    public String ingresarUsuario(Model modelo) {
        return "login";
    }

    @PostMapping("/usuarios")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        servicio.guardarUsuario(usuario);
        return "redirect:/usuarios";

    }

    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("usuario", servicio.obtenerUsuarioPorId(id));
        return "editar";
    }

    @PostMapping("/usuarios/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute("usuario") Usuario usuario, Model modelo) {
        Usuario usuarioExistente = servicio.obtenerUsuarioPorId(id);
        usuarioExistente.setId(id);
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setEmail(usuario.getEmail());

        servicio.actualizarUsuario(usuarioExistente);

        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        servicio.eliminarUsuario(id);
        return "redirect:/usuarios";
    }


    @Autowired
    private PosteoServicio posteoServicio;

    @GetMapping("/inicio")
    public String index(ModelMap modelo) { //localhost:8080/

        List<Posteo> posteos = posteoServicio.listarPosteos();
        modelo.addAttribute("posteos", posteos);

        return "index.html";
    }

    @PostMapping("/posteo")
    public String Index(@RequestParam("titulo") String titulo,
                        @RequestParam("cuerpo") String cuerpo, @RequestParam("file") MultipartFile imagen, ModelMap modelo) {
        try {
            posteoServicio.crearPosteo(titulo, cuerpo, imagen);

            modelo.put("exito", "la actividad se cargo correctamente");

            List<Posteo> posteos = posteoServicio.listarPosteos();

            modelo.addAttribute("posteos", posteos);

            return "index.html";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "posteos_form.html";
        }
    }
}
