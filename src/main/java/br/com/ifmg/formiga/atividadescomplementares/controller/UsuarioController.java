package br.com.ifmg.formiga.atividadescomplementares.controller;

import br.com.ifmg.formiga.atividadescomplementares.config.security.user.UsuarioLogado;
import br.com.ifmg.formiga.atividadescomplementares.model.Usuario;
import br.com.ifmg.formiga.atividadescomplementares.model.dto.ListaUsuarioDTO;
import br.com.ifmg.formiga.atividadescomplementares.model.dto.UsuarioDTO;
import br.com.ifmg.formiga.atividadescomplementares.model.enumeration.StatusUsuario;
import br.com.ifmg.formiga.atividadescomplementares.model.enumeration.TipoUsuario;
import br.com.ifmg.formiga.atividadescomplementares.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
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
        usuario.setStatusUsuario(StatusUsuario.PENDENTE);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualiza(@PathVariable Long id, @RequestBody @Valid UsuarioDTO dto) {
        Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
        if (usuarioSalvo.isPresent()) {
            Usuario usuarioAtualizado = new Usuario(dto);
            usuarioAtualizado.setId(id);
            usuarioRepository.save(usuarioAtualizado);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/list/{role}")
    public ResponseEntity<List<ListaUsuarioDTO>> listarPorRole(@PathVariable String role) {
        List<Usuario> usuarioList = usuarioRepository.findByTipoUsuario(TipoUsuario.valueOf(role));
        return ResponseEntity.ok(ListaUsuarioDTO.toDto(usuarioList));
    }

    @GetMapping("/filtrar/{role}/{str}")
    public ResponseEntity<List<ListaUsuarioDTO>> filtrar(@PathVariable TipoUsuario role, @PathVariable String str) {
        List<Usuario> usuarioList = usuarioRepository.filtroUsuario(role, str);
        return ResponseEntity.ok(ListaUsuarioDTO.toDto(usuarioList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long id, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(value -> ResponseEntity.ok(new UsuarioDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
