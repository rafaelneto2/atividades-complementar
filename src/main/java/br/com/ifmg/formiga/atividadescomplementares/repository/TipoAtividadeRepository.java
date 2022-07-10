package br.com.ifmg.formiga.atividadescomplementares.repository;

import br.com.ifmg.formiga.atividadescomplementares.model.TipoAtividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAtividadeRepository extends JpaRepository<TipoAtividade, Long> {
}
