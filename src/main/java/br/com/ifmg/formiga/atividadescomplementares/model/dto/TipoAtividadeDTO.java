package br.com.ifmg.formiga.atividadescomplementares.model.dto;

import br.com.ifmg.formiga.atividadescomplementares.model.TipoAtividade;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

public class TipoAtividadeDTO {

    @NotBlank
    private final String nome;

    private final String descricao;

    public TipoAtividadeDTO(TipoAtividade tipoAtividade) {
        this.nome = tipoAtividade.getNome();
        this.descricao = tipoAtividade.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<TipoAtividadeDTO> toDto(List<TipoAtividade> tipoAtividadeList) {
        return tipoAtividadeList.stream().map(TipoAtividadeDTO::new).collect(Collectors.toList());
    }
}
