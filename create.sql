DROP TABLE IF EXISTS Veiculo;

CREATE TABLE Veiculo
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    marca            VARCHAR(255) NOT NULL,
    cor             VARCHAR(80)  NOT NULL
);

INSERT INTO Veiculo(marca, cor)
VALUES ('Ferrari', 'Amarela');
INSERT INTO Veiculo(marca, cor)
VALUES ('Chevrolet', 'Branco');
INSERT INTO Veiculo(marca, cor)
VALUES ('Ford', 'Preto');
INSERT INTO Veiculo(marca, cor)
VALUES ('Honda', 'Verde');
