package br.com.digitalhouse.veiculos.dao.impl;

import br.com.digitalhouse.veiculos.dao.ConfiguracaoJdbc;
import br.com.digitalhouse.veiculos.dao.IDao;
import br.com.digitalhouse.veiculos.model.Veiculo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class VeiculoH2Dao implements IDao<Veiculo> {

    private final ConfiguracaoJdbc configuracaoJdbc = new ConfiguracaoJdbc();
    private static final String SQL_CRIACAO_VEICULO = """
            INSERT INTO Veiculo(marca, cor) VALUES(?, ?);
            """;
    private static final String SQL_BUSCA_VEICULO_POR_ID = """
            SELECT *
            FROM Veiculo v WHERE v.id = ?
            """;
    private static final String SQL_BUSCAR_TODOS = """
            SELECT * FROM Veiculo
            """;
    private static final String SQL_EXCLUIR_VEICULO_POR_ID = "DELETE FROM Veiculo WHERE id = ?";
    private static final String SQL_ATUALIZAR_VEICULO = """
            UPDATE Veiculo SET marca = ?, cor = ? WHERE id = ?;
            """;


    @Override
    public Veiculo criar(Veiculo entidade) {

        log.info("[veiculo_h2]: Criando um novo veículo!");

        Connection connection = configuracaoJdbc.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_CRIACAO_VEICULO, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entidade.getMarca());
            statement.setString(2, entidade.getCor());
            log.info("[veiculo_h2]: veiculo criado: " + entidade);
            statement.execute();
            ResultSet resultado = statement.getGeneratedKeys();
            while (resultado.next()) {
                entidade.setId(resultado.getInt(1));
            }
            log.info("[veiculo_h2]: id do veiculo gerado: " + entidade.getId());
            return entidade;
        } catch (Exception e) {
            log.error("Um erro aconteceu, veja: ", e);
            return null;
        }
    }

    @Override
    public Optional<Veiculo> buscarPorId(Integer id) {
        log.info("[veiculo_h2]: buscando veiculo por id: " + id);
        Connection connection = configuracaoJdbc.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_BUSCA_VEICULO_POR_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Veiculo veiculo = null;
            while (resultSet.next()) {
                veiculo = this.getVeiculoByResultSet(resultSet);
                log.info("veiculo encontrado: " + veiculo);
            }
            return Optional.ofNullable(veiculo);
        } catch (Exception e) {
            log.error("Um erro aconteceu, veja: ", e);
            return Optional.empty();
        }
    }

    @Override
    public List<Veiculo> buscarTodos() {
        log.info("[veiculo_h2]: buscando todos os veiculos do banco");
        Connection connection = configuracaoJdbc.getConnection();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_BUSCAR_TODOS);

            List<Veiculo> veiculos = new ArrayList<>();
            while (resultSet.next()) {
                Veiculo veiculo = getVeiculoByResultSet(resultSet);
                log.info("veiculo encontrado com o id: " + veiculo.getId() + " foi encontrado");
                veiculos.add(veiculo);
            }
            return veiculos;
        } catch (Exception e) {
            log.error("Um erro aconteceu, veja: ", e);
            return Collections.emptyList();
        }
    }

    @Override
    public Veiculo atualizar(Veiculo entidade) {
        log.info("[veiculo_h2]: atualizando dados do veiculo");
        Connection connection = configuracaoJdbc.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_ATUALIZAR_VEICULO)) {
            statement.setString(1, entidade.getMarca());
            statement.setString(2, entidade.getCor());
            statement.setInt(3, entidade.getId());
            log.info("[veiculo_h2]: executando atualização do veiculo");
            statement.executeUpdate();
            return entidade;
        } catch (Exception e) {
            log.error("Um erro aconteceu, veja: ", e);
            return null;
        }
    }

    @Override
    public void excluir(Integer id) {
        Connection connection = configuracaoJdbc.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_EXCLUIR_VEICULO_POR_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            log.error("Um erro aconteceu, veja: ", e);
        }
    }

    private Veiculo getVeiculoByResultSet(ResultSet resultSet) throws SQLException {
        Veiculo veiculo;
        Integer id = resultSet.getInt(1);
        String marca = resultSet.getString(2);
        String cor = resultSet.getString(3);
        veiculo = new Veiculo(id, marca, cor);
        return veiculo;
    }

}
