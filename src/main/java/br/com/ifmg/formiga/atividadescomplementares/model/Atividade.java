package br.com.ifmg.formiga.atividadescomplementares.model;

import br.com.ifmg.formiga.atividadescomplementares.model.dto.AtividadeDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @ManyToOne
    private TipoAtividade tipoAtividade;

    @NotNull
    @Positive
    private Long qtdHoras;

    public Atividade(AtividadeDTO dto, TipoAtividade tipoAtividade, Usuario user) {
        this.usuario = user;
        this.tipoAtividade = tipoAtividade;
        this.qtdHoras = dto.getQtdHoras();
    }

    @Deprecated
    public Atividade() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public Long getQtdHoras() {
        return qtdHoras;
    }
}
