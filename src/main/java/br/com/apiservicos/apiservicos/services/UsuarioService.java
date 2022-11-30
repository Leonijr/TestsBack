package br.com.apiservicos.apiservicos.services;

import br.com.apiservicos.apiservicos.domain.Usuario;
import br.com.apiservicos.apiservicos.utils.Enum.PerfilUsuarioEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsuarioService {

    Usuario save(Usuario usuario);

    Usuario getByID(Long id);

    Usuario buscarPorEmail(String email);

    Usuario buscar(Long id);

    List<Usuario> buscaPorPerfil(PerfilUsuarioEnum perfilUsuarioEnum);
}
