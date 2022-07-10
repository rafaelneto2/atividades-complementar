package br.com.ifmg.formiga.atividadescomplementares.model.dto;

import br.com.ifmg.formiga.atividadescomplementares.model.Usuario;
import br.com.ifmg.formiga.atividadescomplementares.model.enumeration.TipoUsuario;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String registroAcademico;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public String getNome() {
        return nome;
    }

    public String getRegistroAcademico() {
        return registroAcademico;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

}
