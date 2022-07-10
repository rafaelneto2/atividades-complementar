package br.com.ifmg.formiga.atividadescomplementares.repository;

import br.com.ifmg.formiga.atividadescomplementares.model.TipoAtividade;
import br.com.ifmg.formiga.atividadescomplementares.model.dto.TipoAtividadeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoAtividadeRepository extends JpaRepository<TipoAtividade, Long> {
    List<TipoAtividade> findAllByOrderByNomeAsc();
}
