package br.com.ifmg.formiga.atividadescomplementares.model;

import br.com.ifmg.formiga.atividadescomplementares.model.dto.TipoAtividadeDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
public class TipoAtividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String descricao;

    @Positive
    private Long limiteHoras;

    public TipoAtividade(TipoAtividadeDTO dto) {
        this.nome = dto.getNome();
        this.descricao = dto.getDescricao();
        this.limiteHoras = dto.getLimiteHoras();
    }

    @Deprecated
    public TipoAtividade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getLimiteHoras() {
        return limiteHoras;
    }

}
