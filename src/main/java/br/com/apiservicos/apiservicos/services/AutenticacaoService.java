package br.com.apiservicos.apiservicos.services;

import br.com.apiservicos.apiservicos.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Value("${encrypted.secretJwt}")
    private String secret;

    private UsuarioService usuarioService;
    @Value("${jwt.expiration}")
    private String expiration;

    @Autowired
    public AutenticacaoService(UsuarioService usuarioService) {
        super();
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioService.buscarPorEmail(username);
    }

    public String getToken(UsernamePasswordAuthenticationToken login) {

        Usuario usuario = usuarioService.buscarPorEmail(login.getPrincipal().toString());

        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("Alefe")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(hoje)
                .claim("nome", usuario.getEmail())
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        if(token != null) {
            try {
                Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
                return true;
            }catch (Exception e) {
                return false;
            }

        }
        return false;
    }

    public Long GetIdUser (String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

}