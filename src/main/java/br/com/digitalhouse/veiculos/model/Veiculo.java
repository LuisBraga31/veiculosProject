package br.com.digitalhouse.veiculos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Veiculo implements Serializable {

    private Integer id;
    private String marca;
    private String cor;

}

