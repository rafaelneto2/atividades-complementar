package br.com.ifmg.formiga.atividadescomplementares.config.security.user;

import br.com.ifmg.formiga.atividadescomplementares.model.Usuario;
import br.com.ifmg.formiga.atividadescomplementares.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String registroAcademico) throws UsernameNotFoundException {
        Optional<Usuario> possivelUsuario = usuarioRepository.findByRegistroAcademico(registroAcademico);
        Usuario usuario = possivelUsuario.orElseThrow(() -> new UsernameNotFoundException("Não foi possível encontrar usuário com registro academico: " + registroAcademico));
        return new UsuarioLogado(usuario, getPermissoes(usuario));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE"));
        return authorities;
    }

}
