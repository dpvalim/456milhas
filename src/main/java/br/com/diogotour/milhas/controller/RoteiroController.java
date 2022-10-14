package br.com.diogotour.milhas.controller;

import br.com.diogotour.milhas.domain.TodosRoteiros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/roteiros")
public class RoteiroController {

    @Autowired
    private TodosRoteiros roteiros;

    @GetMapping
    public ResponseEntity listarRoteiros() {
        return ResponseEntity.ok(roteiros.com(null, null, null, null, null));
    }
}
