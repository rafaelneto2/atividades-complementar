package br.com.ifmg.formiga.atividadescomplementares.config.security.user;

import br.com.ifmg.formiga.atividadescomplementares.model.Usuario;
import br.com.ifmg.formiga.atividadescomplementares.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

public class UsuarioLogado extends User {

    private static final long serialVersionUID = 1L;

    private final Usuario usuario;

    public UsuarioLogado(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getRegistroAcademico(), usuario.getSenha(), authorities);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
