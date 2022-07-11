package br.com.ifmg.formiga.atividadescomplementares.model.dto;

import br.com.ifmg.formiga.atividadescomplementares.model.Atividade;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

public class AtividadeDTO {

    @NotNull
    private Long idUsuario;

    @NotNull
    private Long idTipoAtividade;

    @Positive
    private Long qtdHoras;

    public AtividadeDTO(Atividade atividade) {
        this.idUsuario = atividade.getUsuario().getId();
        this.idTipoAtividade = atividade.getUsuario().getId();
        this.qtdHoras = atividade.getQtdHoras();
    }

    @Deprecated
    public AtividadeDTO() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdTipoAtividade() {
        return idTipoAtividade;
    }

    public Long getQtdHoras() {
        return qtdHoras;
    }

    public static List<AtividadeDTO> toDto(List<Atividade> atividadeList) {
        return atividadeList.stream().map(AtividadeDTO::new).collect(Collectors.toList());
    }
}
