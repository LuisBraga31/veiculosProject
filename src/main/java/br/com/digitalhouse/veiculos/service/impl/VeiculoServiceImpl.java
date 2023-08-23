package br.com.digitalhouse.veiculos.service.impl;

import br.com.digitalhouse.veiculos.dao.IDao;
import br.com.digitalhouse.veiculos.model.Veiculo;
import br.com.digitalhouse.veiculos.service.VeiculoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    private final IDao<Veiculo> veiculoIDao;

    public VeiculoServiceImpl(IDao<Veiculo> veiculoIDao) {
        this.veiculoIDao = veiculoIDao;
    }

    @Override
    public Veiculo criarVeiculo(Veiculo veiculo) {
        return veiculoIDao.criar(veiculo);
    }

    @Override
    public Veiculo buscarVeiculoPorId(Integer id) {
        return veiculoIDao.buscarPorId(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public List<Veiculo> buscarPorTodosVeiculos() {
        return veiculoIDao.buscarTodos();
    }

    @Override
    public Veiculo atualizarVeiculo(Veiculo veiculo) {
        return veiculoIDao.atualizar(veiculo);
    }

    @Override
    public void excluirVeiculo(Integer id) {
        veiculoIDao.excluir(id);
    }
}
