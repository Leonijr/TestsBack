package br.com.apiservicos.apiservicos.controller;

import antlr.TokenStreamHiddenTokenFilter;
import br.com.apiservicos.apiservicos.controller.dto.LoginDTO;
import br.com.apiservicos.apiservicos.controller.dto.TokenDTO;
import br.com.apiservicos.apiservicos.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("autenticar")
    public ResponseEntity<TokenDTO> stored(@RequestBody @Valid LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha());

        authenticationManager.authenticate(login).isAuthenticated();


        TokenDTO tokenDTO = new TokenDTO("Bearer", autenticacaoService.getToken(login));

        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("validar")
    public ResponseEntity<Boolean> isValid(@RequestBody TokenDTO token){
        return ResponseEntity.ok(autenticacaoService.isTokenValid(token.getToken()));
    }

}