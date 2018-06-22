package br.com.thales.xyinc.repository;

import br.com.thales.xyinc.model.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoiRepository extends JpaRepository<Poi, Integer> {



}