package br.com.apiservicos.apiservicos.config;


import br.com.apiservicos.apiservicos.domain.Usuario;
import br.com.apiservicos.apiservicos.services.AutenticacaoService;
import br.com.apiservicos.apiservicos.services.UsuarioService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoFilter extends OncePerRequestFilter {

    private AutenticacaoService autenticacaoService;
    private UsuarioService usuarioService;

    public AutenticacaoFilter(AutenticacaoService autenticacaoService, UsuarioService usuarioService) {
        super();
        this.autenticacaoService = autenticacaoService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = recuperartoken(request);
        boolean isTokenValid = autenticacaoService.isTokenValid(token);

        if (isTokenValid) {
            autenticarUsuario(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarUsuario(String token) {
        Long idUsuario = autenticacaoService.GetIdUser(token);
        Usuario usuario = usuarioService.buscar(idUsuario);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperartoken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (isAuthorizationValid(token)) {
            return token.substring(7);
        }

        return null;
    }

    private boolean isAuthorizationValid(String token) {
        return token != null
                && token.startsWith("Bearer ")
                && token.length() > 7;
    }


}