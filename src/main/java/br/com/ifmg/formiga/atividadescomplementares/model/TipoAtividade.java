package br.com.ifmg.formiga.atividadescomplementares.model;

import br.com.ifmg.formiga.atividadescomplementares.model.dto.TipoAtividadeDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class TipoAtividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public TipoAtividade(TipoAtividadeDTO dto) {
        this.nome = dto.getNome();
        this.descricao = dto.getDescricao();
    }

    @Deprecated
    public TipoAtividade() {
    }
}
