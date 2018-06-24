package br.com.thales.xyinc;

import br.com.thales.xyinc.controller.PoiController;
import br.com.thales.xyinc.model.Poi;
import br.com.thales.xyinc.service.PoiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PoiController.class)
public class PoiRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PoiService service;

    @Test
    public void whenGetPois_thenReturnJsonArray() throws Exception {
        Poi p1 = new Poi(1, "Lanchonete", 27, 12);
        Poi p2 = new Poi(2, "Posto", 31, 18);
        List<Poi> allPois = Arrays.asList(p1, p2);

        given(service.findAll()).willReturn(allPois);

        mvc.perform(get("/pontos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void whenGetCoordinates_thenReturnJsonArray() throws Exception {
        Integer x = 20; Integer y = 10; Integer d = 10;
        Poi p1 = new Poi(1, "Lanchonete", 27, 12);
        Poi p3 = new Poi(3, "Joalheria", 15, 12);
        Poi p5 = new Poi(5, "Pub", 12, 8);
        Poi p6 = new Poi(6, "Supermercado", 23, 6);
        List<Poi> proximityPois = Arrays.asList(p1, p3, p5, p6);

        given(service.findPontosProximos(x, y, d)).willReturn(proximityPois);

        mvc.perform(get("/pontos/proximos?x=20&y=10&d=10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void whenGetId_thenReturnJsonArray() throws Exception {
        Integer id1 = 1;
        Poi p1 = new Poi(1, "Lanchonete", 27, 12);

        given(service.findOne(id1)).willReturn(p1);

        mvc.perform(get("/pontos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1));
    }

}
