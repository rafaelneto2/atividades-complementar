package br.com.ifmg.formiga.atividadescomplementares.model.dto;

import javax.validation.constraints.NotBlank;

public class TipoAtividadeDTO {

    @NotBlank
    private String nome;

    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
