package br.com.thales.xyinc.service;

import br.com.thales.xyinc.model.Poi;
import br.com.thales.xyinc.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private PoiRepository poiRepository;

    public void instantiateBatabase() {
        Poi p1 = new Poi(null, "Lanchonete", 27, 12);
        Poi p2 = new Poi(null, "Posto", 31, 18);
        Poi p3 = new Poi(null, "Joalheria", 15, 12);
        Poi p4 = new Poi(null, "Floricultura", 19, 21);
        Poi p5 = new Poi(null, "Pub", 12, 8);
        Poi p6 = new Poi(null, "Supermercado", 23, 6);
        Poi p7 = new Poi(null, "Churrascaria", 28, 2);

        poiRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
    }

}
