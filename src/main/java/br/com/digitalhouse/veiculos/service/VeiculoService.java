package br.com.digitalhouse.veiculos.service;

import br.com.digitalhouse.veiculos.model.Veiculo;

import java.util.List;

public interface VeiculoService {

    Veiculo criarVeiculo(Veiculo veiculo);
    Veiculo buscarVeiculoPorId(Integer id);
    List<Veiculo> buscarPorTodosVeiculos();
    Veiculo atualizarVeiculo(Veiculo veiculo);
    void excluirVeiculo(Integer id);

}
