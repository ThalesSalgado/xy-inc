package br.com.thales.xyinc;

import br.com.thales.xyinc.dto.PoiDTO;
import br.com.thales.xyinc.model.Poi;
import br.com.thales.xyinc.repository.PoiRepository;
import br.com.thales.xyinc.service.PoiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PoiServiceTest {

    @TestConfiguration
    static class PoiServiceConfiguration {
        @Bean
        public PoiService poiService() {
            return new PoiService();
        }
    }

    @Autowired
    private PoiService poiService;

    @MockBean
    private PoiRepository poiRepository;

    @Before
    public void setUp() {
        Poi p1 = new Poi(1, "Lanchonete", 27, 12);
        Poi p2 = new Poi(2, "Posto", 31, 18);
        Poi p3 = new Poi(3, "Joalheria", 15, 12);
        Poi p4 = new Poi(4, "Floricultura", 19, 21);
        Poi p5 = new Poi(5, "Pub", 12, 8);
        Poi p6 = new Poi(6, "Supermercado", 23, 6);
        Poi p7 = new Poi(7, "Churrascaria", 28, 2);

        Mockito.when(poiRepository.findById(1)).thenReturn(Optional.of(p1));
        Mockito.when(poiRepository.findAll()).thenReturn(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
    }

    @Test
    public void whenValidId_thenPoiShouldBeFound() {
        Integer id1 = 1;
        Poi found = poiService.findOne(1);

        assertThat(found.getId()).isEqualTo(id1);
    }

    @Test
    public void getAllPoi() {
        List<Poi> listFound = poiService.findAll();
        Poi p1 = new Poi(1, "Lanchonete", 27, 12);
        Poi p2 = new Poi(2, "Posto", 31, 18);
        Poi p3 = new Poi(3, "Joalheria", 15, 12);
        Poi p4 = new Poi(4, "Floricultura", 19, 21);
        Poi p5 = new Poi(5, "Pub", 12, 8);
        Poi p6 = new Poi(6, "Supermercado", 23, 6);
        Poi p7 = new Poi(7, "Churrascaria", 28, 2);
        List<Poi> list = Arrays.asList(p1, p2, p3, p4, p5, p6, p7);

        assertThat(listFound).isEqualTo(list);
    }

    @Test
    public void getNearPois() {
        Integer x = 20; Integer y = 10; Integer d = 10;
        List<Poi> listFound = poiService.findAll();
        Poi p1 = new Poi(1, "Lanchonete", 27, 12);
        Poi p3 = new Poi(3, "Joalheria", 15, 12);
        Poi p5 = new Poi(5, "Pub", 12, 8);
        Poi p6 = new Poi(6, "Supermercado", 23, 6);

        List<Poi> listProximos = new ArrayList<Poi>();
        for (Poi obj : listFound) {
            if ( Math.sqrt((obj.getX() - x)*(obj.getX() - x) + (obj.getY() - y)*(obj.getY() - y)) <= d ) {
                listProximos.add(obj);
            }
        }

        assertThat(listProximos).isEqualTo(Arrays.asList(p1, p3, p5, p6));
    }

}
