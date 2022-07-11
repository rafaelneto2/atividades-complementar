package br.com.ifmg.formiga.atividadescomplementares.model.dto;

import br.com.ifmg.formiga.atividadescomplementares.model.Atividade;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

public class ExibeAtividadeDTO {

    @NotNull
    private Long id;

    @NotNull
    private ListaTipoAtividadeDTO tipoAtividade;

    @Positive
    private Long qtdHoras;

    public ExibeAtividadeDTO(Atividade atividade) {
        this.id = atividade.getId();
        this.tipoAtividade = new ListaTipoAtividadeDTO(atividade.getTipoAtividade());
        this.qtdHoras = atividade.getQtdHoras();
    }

    @Deprecated
    public ExibeAtividadeDTO() {
    }

    public Long getId() {
        return id;
    }

    public ListaTipoAtividadeDTO getTipoAtividade() {
        return tipoAtividade;
    }

    public Long getQtdHoras() {
        return qtdHoras;
    }

    public static List<ExibeAtividadeDTO> toDto(List<Atividade> atividadeList) {
        return atividadeList.stream().map(ExibeAtividadeDTO::new).collect(Collectors.toList());
    }
}
