package br.com.ifmg.formiga.atividadescomplementares.repository;

import br.com.ifmg.formiga.atividadescomplementares.model.Atividade;
import br.com.ifmg.formiga.atividadescomplementares.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    List<Atividade> findByUsuario(Usuario usuario);
}
