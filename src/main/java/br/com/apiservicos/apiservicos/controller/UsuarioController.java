package br.com.apiservicos.apiservicos.controller;

import br.com.apiservicos.apiservicos.domain.Usuario;
import br.com.apiservicos.apiservicos.services.UsuarioService;
import br.com.apiservicos.apiservicos.utils.Enum.PerfilUsuarioEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("salvar")
    ResponseEntity<Usuario> save(@RequestBody @Valid Usuario usuario){
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @GetMapping("/{id}")
    ResponseEntity<Usuario> getById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.getByID(id));
    }

    @GetMapping("/listar-por-tipo/{tipo}")
    ResponseEntity <List<Usuario>> findByTipo(@PathVariable(name = "tipo") PerfilUsuarioEnum perfilUsuarioEnum){
        return ResponseEntity.ok(usuarioService.buscaPorPerfil(perfilUsuarioEnum));
    }

}
