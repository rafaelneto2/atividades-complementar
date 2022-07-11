package br.com.ifmg.formiga.atividadescomplementares.model;

import br.com.ifmg.formiga.atividadescomplementares.model.dto.UsuarioDTO;
import br.com.ifmg.formiga.atividadescomplementares.model.enumeration.StatusUsuario;
import br.com.ifmg.formiga.atividadescomplementares.model.enumeration.TipoUsuario;
import br.com.ifmg.formiga.atividadescomplementares.repository.AtividadeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(unique = true)
    private String registroAcademico;

    @NotBlank
    @Column(unique = true)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Enumerated(EnumType.STRING)
    private StatusUsuario statusUsuario;

    public Usuario(UsuarioDTO dto) {
        this.nome = dto.getNome();
        this.registroAcademico = dto.getRegistroAcademico();
        this.email = dto.getEmail();
        this.senha = new BCryptPasswordEncoder().encode(dto.getSenha());
        this.tipoUsuario = dto.getTipoUsuario();
        this.statusUsuario = dto.getStatusUsuario();
    }

    public Usuario() {
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

    public String getSenha() {
        return senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public StatusUsuario getStatusUsuario() {
        return statusUsuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatusUsuario(StatusUsuario statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    public void atualizaStatus(AtividadeRepository atividadeRepository) {
        AtomicReference<Long> soma = new AtomicReference<>(0L);
        List<Atividade> atividadeList = atividadeRepository.findByUsuario(this);

        atividadeList.forEach(i -> {
            soma.updateAndGet(v -> v + i.getQtdHoras());
        });

        if (soma.get() >= 360L) {
            this.setStatusUsuario(StatusUsuario.APROVADO);
        } else {
            this.setStatusUsuario(StatusUsuario.PENDENTE);
        }

    }
}
