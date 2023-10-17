package com.example.demo.Controller;

import com.example.demo.Dto.EnderecoDTO;
import com.example.demo.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/{id}/enderecos")
    public ResponseEntity<EnderecoDTO> criarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
        EnderecoDTO enderecoCriado = enderecoService.criarEndereco(id, enderecoDTO);
        if (enderecoCriado != null) {
            return ResponseEntity.created(URI.create("/pessoas/" + id + "/enderecos/" + enderecoCriado.getId())).body(enderecoCriado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/enderecos")
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos(@PathVariable Long id) {
        List<EnderecoDTO> enderecos = enderecoService.listarEnderecos(id);
        return ResponseEntity.ok(enderecos);
    }

    @PutMapping("/{id}/enderecos/{enderecoId}")
    public ResponseEntity<EnderecoDTO> editarEndereco(@PathVariable Long id, @PathVariable Long enderecoId, @RequestBody EnderecoDTO enderecoDTO) {
        EnderecoDTO enderecoEditado = enderecoService.editarEndereco(id, enderecoId, enderecoDTO);
        if (enderecoEditado != null) {
            return ResponseEntity.ok(enderecoEditado);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/endereco/{enderecoId}")
    public ResponseEntity<EnderecoDTO> definirEnderecoPrincipal(@PathVariable Long id, @PathVariable Long enderecoId) {
        EnderecoDTO enderecoDefinido = enderecoService.definirEnderecoPrincipal(id, enderecoId);
        if (enderecoDefinido != null) {
            return ResponseEntity.ok(enderecoDefinido);
        }
        return ResponseEntity.notFound().build();
    }
}
