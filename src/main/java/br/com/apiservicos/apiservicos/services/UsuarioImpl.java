package br.com.apiservicos.apiservicos.services;

import br.com.apiservicos.apiservicos.domain.ResetPasswordToken;
import br.com.apiservicos.apiservicos.domain.Usuario;
import br.com.apiservicos.apiservicos.exceptions.RegistroNaoEncontradoException;
import br.com.apiservicos.apiservicos.exceptions.RegraDeNegocioException;
import br.com.apiservicos.apiservicos.repositories.UsuarioRepository;
import br.com.apiservicos.apiservicos.utils.Enum.PerfilUsuarioEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioImpl implements UsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    ResetPasswordTokenService resetPasswordService;

    @Override
    public Usuario save(Usuario usuario) {

        if(usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new RegraDeNegocioException("usuario.jaExists");
        }

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario getByID(Long id) {
        System.out.println("teste");
        return usuarioRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Usuário não localizado!"));
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RegistroNaoEncontradoException("usuario.naoEncontrado"));
    }

    @Override
    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("usuario.naoEncontrado"));
    }

    public ResetPasswordToken generateResetPasswordToken(Usuario obj) {
        Usuario user = this.buscar(obj.getId());
        System.out.println("alefe");
        ResetPasswordToken resetToken = new ResetPasswordToken();

        if(user != null) {
            resetToken.setUser(user);
            Date createdDate = new Date();
            resetToken.setToken(UUID.randomUUID().toString());
            resetToken.setCreatedDate(createdDate);
            resetPasswordService.insert(resetToken);
        }
        return resetToken;
    }

    public void updatePassword(Usuario usuario){
        Usuario usuarioFind = buscar(usuario.getId());
        usuarioFind.setSenha(encoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuarioFind);
    }

    @Override
    public List<Usuario> buscaPorPerfil(PerfilUsuarioEnum perfilUsuarioEnum) {
        return  usuarioRepository.findByPerfil(perfilUsuarioEnum);
    }


}
