package com.example.demo.Controller;

import com.example.demo.Dto.PessoaDTO;
import com.example.demo.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO pessoaCriada = pessoaService.criarPessoa(pessoaDTO);
        if (pessoaCriada != null) {
            return ResponseEntity.created(URI.create("/pessoas/" + pessoaCriada.getId())).body(pessoaCriada);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> editarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO pessoaEditada = pessoaService.editarPessoa(id, pessoaDTO);
        if (pessoaEditada != null) {
            return ResponseEntity.ok(pessoaEditada);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> consultarPessoa(@PathVariable Long id) {
        PessoaDTO pessoaDTO = pessoaService.consultarPessoa(id);
        if (pessoaDTO != null) {
            return ResponseEntity.ok(pessoaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listarPessoas() {
        List<PessoaDTO> pessoas = pessoaService.listarPessoas();
        return ResponseEntity.ok(pessoas);
    }
}
