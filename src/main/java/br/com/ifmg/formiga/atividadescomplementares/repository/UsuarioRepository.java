package br.com.ifmg.formiga.atividadescomplementares.repository;

import br.com.ifmg.formiga.atividadescomplementares.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByRegistroAcademico(String registroAcademico);
}
