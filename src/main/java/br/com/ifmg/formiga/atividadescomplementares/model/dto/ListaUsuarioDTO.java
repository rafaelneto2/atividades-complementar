package br.com.ifmg.formiga.atividadescomplementares.model.dto;

import br.com.ifmg.formiga.atividadescomplementares.model.Usuario;
import br.com.ifmg.formiga.atividadescomplementares.model.enumeration.StatusUsuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class ListaUsuarioDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String registroAcademico;

    @NotBlank
    @Email
    private String email;

    private StatusUsuario statusUsuario;

    public ListaUsuarioDTO(Usuario user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.registroAcademico = user.getRegistroAcademico();
        this.email = user.getEmail();
        this.statusUsuario = user.getStatusUsuario();
    }

    @Deprecated
    public ListaUsuarioDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRegistroAcademico() {
        return registroAcademico;
    }

    public String getEmail() {
        return email;
    }

    public StatusUsuario getStatusUsuario() {
        return statusUsuario;
    }

    public static List<ListaUsuarioDTO> toDto(List<Usuario> usuarioList) {
        return usuarioList.stream().map(ListaUsuarioDTO::new).collect(Collectors.toList());
    }
}
