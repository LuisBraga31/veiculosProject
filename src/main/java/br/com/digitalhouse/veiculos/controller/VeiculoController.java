package br.com.digitalhouse.veiculos.controller;

import br.com.digitalhouse.veiculos.model.Veiculo;
import br.com.digitalhouse.veiculos.service.VeiculoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("listaveiculos")
public class VeiculoController {

    private VeiculoService veiculoService;

    @Autowired

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public Veiculo criarVeiculo(@RequestBody Veiculo veiculo) {
        log.info("chamando criar veiculo");
        return veiculoService.criarVeiculo(veiculo);
    }

    @GetMapping("{id}")
    public ResponseEntity<Veiculo> buscarVeiculoPorId(@PathVariable Integer id) {
        log.info("chamando buscar veiculo por id: " + id);
        try {
            return ResponseEntity.ok(veiculoService.buscarVeiculoPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public List<Veiculo> buscarListaDeVeiculos() {
        log.info("chamando todos veiculos");
        return veiculoService.buscarPorTodosVeiculos();
    }

    @PutMapping("{id}")
    public Veiculo atualizarVeiculo(@PathVariable Integer id, @RequestBody Veiculo veiculo) {
        log.info("atualizando o veiculo");
        veiculo.setId(id);
        return veiculoService.atualizarVeiculo(veiculo);
    }

    @DeleteMapping("{id}")
    public void excluirVeiculo(@PathVariable Integer id) {
        log.info("excluindo veiculo");
        veiculoService.excluirVeiculo(id);
    }


}
