package br.com.ifmg.formiga.atividadescomplementares.controller;

import br.com.ifmg.formiga.atividadescomplementares.config.security.user.UsuarioLogado;
import br.com.ifmg.formiga.atividadescomplementares.model.TipoAtividade;
import br.com.ifmg.formiga.atividadescomplementares.model.dto.ListaTipoAtividadeDTO;
import br.com.ifmg.formiga.atividadescomplementares.model.dto.TipoAtividadeDTO;
import br.com.ifmg.formiga.atividadescomplementares.repository.TipoAtividadeRepository;
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
@RequestMapping("/api/tipoatividade")
public class TipoAtividadeController {

    private final TipoAtividadeRepository tipoAtividadeRepository;

    public TipoAtividadeController(TipoAtividadeRepository tipoAtividadeRepository) {
        this.tipoAtividadeRepository = tipoAtividadeRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@RequestBody @Valid TipoAtividadeDTO dto) {
        TipoAtividade atividade = new TipoAtividade(dto);
        tipoAtividadeRepository.save(atividade);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ListaTipoAtividadeDTO>> listar() {
        List<TipoAtividade> tipoAtividadeList = tipoAtividadeRepository.findAll();
        return ResponseEntity.ok(ListaTipoAtividadeDTO.toDto(tipoAtividadeList));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualiza(@PathVariable Long id, @RequestBody @Valid TipoAtividadeDTO dto) {
        Optional<TipoAtividade> atividadeSalva = tipoAtividadeRepository.findById(id);
        if (atividadeSalva.isPresent()) {
            TipoAtividade atividadeAtualizada = new TipoAtividade(dto);
            atividadeAtualizada.setId(id);
            tipoAtividadeRepository.save(atividadeAtualizada);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        tipoAtividadeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
