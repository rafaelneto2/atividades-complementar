package br.com.ifmg.formiga.atividadescomplementares.controller;

import br.com.ifmg.formiga.atividadescomplementares.config.security.user.UsuarioLogado;
import br.com.ifmg.formiga.atividadescomplementares.model.TipoAtividade;
import br.com.ifmg.formiga.atividadescomplementares.model.Usuario;
import br.com.ifmg.formiga.atividadescomplementares.model.dto.TipoAtividadeDTO;
import br.com.ifmg.formiga.atividadescomplementares.model.dto.UsuarioDTO;
import br.com.ifmg.formiga.atividadescomplementares.model.enumeration.TipoUsuario;
import br.com.ifmg.formiga.atividadescomplementares.repository.TipoAtividadeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/tipoatividade")
public class TipoAtividadeController {

    private TipoAtividadeRepository tipoAtividadeRepository;

    public TipoAtividadeController(TipoAtividadeRepository tipoAtividadeRepository) {
        this.tipoAtividadeRepository = tipoAtividadeRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@RequestBody @Valid TipoAtividadeDTO dto, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        if (usuarioLogado.getUsuario().getTipoUsuario() != TipoUsuario.PROFESSOR) {
            return ResponseEntity.badRequest().body("Acesso a criação de Tipo de Atividade NEGADO! Por favor, solicite a criação a um professor.");
        }
        TipoAtividade atividade = new TipoAtividade(dto);
        tipoAtividadeRepository.save(atividade);
        return ResponseEntity.ok().build();
    }
}
