package br.com.apiservicos.apiservicos.repositories;

import br.com.apiservicos.apiservicos.domain.Usuario;
import br.com.apiservicos.apiservicos.utils.Enum.PerfilUsuarioEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    List<Usuario> findByPerfil(PerfilUsuarioEnum perfil);

    Optional<Usuario> findByEmail(String email);
}
