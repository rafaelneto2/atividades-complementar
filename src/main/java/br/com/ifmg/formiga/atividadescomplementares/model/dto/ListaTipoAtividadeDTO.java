package br.com.ifmg.formiga.atividadescomplementares.model.dto;

import br.com.ifmg.formiga.atividadescomplementares.model.TipoAtividade;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

public class ListaTipoAtividadeDTO {

    @NotNull
    private Long id;
    @NotBlank
    private String nome;

    private String descricao;

    @Positive
    private Long limiteHoras;

    public ListaTipoAtividadeDTO(TipoAtividade tipoAtividade) {
        this.id = tipoAtividade.getId();
        this.nome = tipoAtividade.getNome();
        this.descricao = tipoAtividade.getDescricao();
        this.limiteHoras = tipoAtividade.getLimiteHoras();
    }

    @Deprecated
    public ListaTipoAtividadeDTO() {
    }

    public Long getId() {
        return id;
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

    public static List<ListaTipoAtividadeDTO> toDto(List<TipoAtividade> tipoAtividadeList) {
        return tipoAtividadeList.stream().map(ListaTipoAtividadeDTO::new).collect(Collectors.toList());
    }
}
