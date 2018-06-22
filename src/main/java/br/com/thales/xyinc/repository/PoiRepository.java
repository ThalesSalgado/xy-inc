package br.com.thales.xyinc.repository;

import br.com.thales.xyinc.model.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PoiRepository extends JpaRepository<Poi, Integer> {

    @Transactional(readOnly=true)
    Poi findByNome(String nome);

}
