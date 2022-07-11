package br.com.ifmg.formiga.atividadescomplementares.repository;

import br.com.ifmg.formiga.atividadescomplementares.model.Usuario;
import br.com.ifmg.formiga.atividadescomplementares.model.enumeration.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByRegistroAcademico(String registroAcademico);
    List<Usuario> findByTipoUsuario(TipoUsuario valueOf);

    @Query(" SELECT user " +
            " FROM Usuario user " +
            " WHERE user.tipoUsuario = :role " +
            " AND user.nome " +
            " LIKE %:str%" )
    public List<Usuario> filtroUsuario(@Param("role") TipoUsuario role, @Param("str") String name);
}
