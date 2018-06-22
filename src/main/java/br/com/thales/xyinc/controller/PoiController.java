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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pontos")
public class PoiController {

    @Autowired
    private PoiService service;

    @GetMapping
    public ResponseEntity<List<PoiDTO>> findAll() {
        List<Poi> list = service.findAll();
        List<PoiDTO> listDTO = list.stream().map(obj -> new PoiDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
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

    @GetMapping("/{id}")
    public ResponseEntity<PoiDTO> findOne(@PathVariable Integer id) {
        PoiDTO objDTO = new PoiDTO(service.findOne(id));
        return ResponseEntity.ok().body(objDTO);
    }

}
