package br.com.apiservicos.apiservicos.domain;

import br.com.apiservicos.apiservicos.utils.Enum.PerfilUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty(message = "O campo nome deve ser preenchido!")
    @NotNull
    private String nomeCompleto;

    @NotEmpty(message = "O campo e-mail deve ser preenchido!")
    @NotNull
    private String email;

    private String telefone;

    @NotEmpty(message = "O campo celular deve ser preenchido!")
    @NotNull
    private String celular;

    @NotEmpty(message = "O campo endereco deve ser preenchido!")
    @NotNull
    private String endereco;

    @NotEmpty(message = "O campo senha deve ser preenchido!")
    @NotNull
    private String senha;

    private PerfilUsuarioEnum perfil;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
