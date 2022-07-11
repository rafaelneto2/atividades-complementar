package br.com.ifmg.formiga.atividadescomplementares.controller;

import br.com.ifmg.formiga.atividadescomplementares.config.security.user.UsuarioLogado;
import br.com.ifmg.formiga.atividadescomplementares.model.Atividade;
import br.com.ifmg.formiga.atividadescomplementares.model.TipoAtividade;
import br.com.ifmg.formiga.atividadescomplementares.model.Usuario;
import br.com.ifmg.formiga.atividadescomplementares.model.dto.AtividadeDTO;
import br.com.ifmg.formiga.atividadescomplementares.model.dto.ExibeAtividadeDTO;
import br.com.ifmg.formiga.atividadescomplementares.repository.AtividadeRepository;
import br.com.ifmg.formiga.atividadescomplementares.repository.TipoAtividadeRepository;
import br.com.ifmg.formiga.atividadescomplementares.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/atividade")
public class AtividadeController {

    private final AtividadeRepository atividadeRepository;
    private final TipoAtividadeRepository tipoAtividadeRepository;

    private final UsuarioRepository usuarioRepository;

    public AtividadeController(AtividadeRepository atividadeRepository, TipoAtividadeRepository tipoAtividadeRepository, UsuarioRepository usuarioRepository) {
        this.atividadeRepository = atividadeRepository;
        this.tipoAtividadeRepository = tipoAtividadeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@RequestBody @Valid AtividadeDTO dto) {
        Optional<TipoAtividade> possivelTipoAtividade = tipoAtividadeRepository.findById(dto.getIdTipoAtividade());
        Optional<Usuario> possivelUsuario = usuarioRepository.findById(dto.getIdUsuario());
        if (possivelTipoAtividade.isPresent() && possivelUsuario.isPresent()) {
            Atividade atividade = new Atividade(dto, possivelTipoAtividade.get(), possivelUsuario.get());
            atividadeRepository.save(atividade);
            possivelUsuario.get().atualizaStatus(atividadeRepository);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Tipo de Atividade não encontrado");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualiza(@PathVariable Long id, @RequestBody @Valid AtividadeDTO dto, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Optional<Atividade> atividadeSalva = atividadeRepository.findById(id);
        if (atividadeSalva.isPresent()) {
            Optional<TipoAtividade> possivelTipoAtividade = tipoAtividadeRepository.findById(dto.getIdTipoAtividade());
            Optional<Usuario> possivelUsuario = usuarioRepository.findById(dto.getIdUsuario());
            if (possivelTipoAtividade.isPresent() && possivelUsuario.isPresent()) {
                Atividade atividadeAtualizada = new Atividade(dto, possivelTipoAtividade.get(), possivelUsuario.get());
                atividadeAtualizada.setId(id);
                atividadeRepository.save(atividadeAtualizada);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ExibeAtividadeDTO>> listarPorUsuario(@PathVariable Long id, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            List<Atividade> atividadeList = atividadeRepository.findByUsuario(usuario.get());
            return ResponseEntity.ok(ExibeAtividadeDTO.toDto(atividadeList));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AtividadeDTO>> listar() {
        List<Atividade> atividadeList = atividadeRepository.findAll();
        return ResponseEntity.ok(AtividadeDTO.toDto(atividadeList));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        atividadeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
