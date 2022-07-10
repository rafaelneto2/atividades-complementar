package br.com.ifmg.formiga.atividadescomplementares.controller;

import br.com.ifmg.formiga.atividadescomplementares.config.security.user.UsuarioLogado;
import br.com.ifmg.formiga.atividadescomplementares.model.Usuario;
import br.com.ifmg.formiga.atividadescomplementares.model.dto.UsuarioDTO;
import br.com.ifmg.formiga.atividadescomplementares.model.enumeration.TipoUsuario;
import br.com.ifmg.formiga.atividadescomplementares.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository repository) {
        this.usuarioRepository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@RequestBody @Valid UsuarioDTO dto) {
        if (dto.getTipoUsuario().equals(TipoUsuario.PROFESSOR)) {
            return ResponseEntity.badRequest().body("Acesso a criação de Usuário do tipo professor negado! Por favor, solicite que um outro Professor crie para você.");
        }
        Usuario usuario = new Usuario(dto);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualiza(@PathVariable Long id, @RequestBody @Valid UsuarioDTO dto, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
        if (usuarioSalvo.isPresent()) {
            Usuario usuarioAtualizado = new Usuario();
            usuarioRepository.save(usuarioAtualizado);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
