package br.com.thales.xyinc.controller;

import br.com.thales.xyinc.dto.PoiDTO;
import br.com.thales.xyinc.model.Poi;
import br.com.thales.xyinc.repository.PoiRepository;
import br.com.thales.xyinc.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pontos")
public class PoiController {

    @Autowired
    private PoiService service;

    @GetMapping
    public ResponseEntity<List<Poi>> findAll() {
        List<Poi> list = service.findAll();
        //List<PoiDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody PoiDTO objDTO) {
        Poi obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/proximos")
    public ResponseEntity<List<Poi>> findPontosProximos(@RequestParam(value = "x", defaultValue = "0") Integer x,
                                                        @RequestParam(value = "y", defaultValue = "0") Integer y,
                                                        @RequestParam(value = "d", defaultValue = "0") Integer d) {
        List<Poi> list = service.findPontosProximos(x, y, d);

        return ResponseEntity.ok().body(list);
    }

}
