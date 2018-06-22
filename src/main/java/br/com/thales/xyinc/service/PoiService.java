package br.com.thales.xyinc.service;

import br.com.thales.xyinc.dto.PoiDTO;
import br.com.thales.xyinc.model.Poi;
import br.com.thales.xyinc.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoiService {

    @Autowired
    private PoiRepository repo;

    public List<Poi> findAll() {
        return repo.findAll();
    }

    public Poi insert(Poi obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Poi fromDTO(PoiDTO objDTO) {
        return new Poi(objDTO.getId(), objDTO.getNome(), objDTO.getX(), objDTO.getY());
    }

    public List<Poi> findPontosProximos(Integer x, Integer y, Integer d) {
        List<Poi> list = findAll();
        List<Poi> listProximos = new ArrayList<Poi>();
        for (Poi obj : list) {
            if ( Math.sqrt((obj.getX() - x)*(obj.getX() - x) + (obj.getY() - y)*(obj.getY() - y)) <= d ) {
                listProximos.add(obj);
            }
        }

        return listProximos;
    }

}
