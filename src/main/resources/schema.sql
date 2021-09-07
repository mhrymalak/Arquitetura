DROP TABLE corredores IF EXISTS;
DROP TABLE eventos IF EXISTS;

CREATE TABLE corredores(
    cpf VARCHAR(255), 
    nome VARCHAR(255), 
    genero VARCHAR(255),
    data_corredor Date,
    CONSTRAINT corredores_id PRIMARY KEY(cpf));
    
CREATE TABLE eventos(
    id int IDENTITY NOT NULL, 
    nome VARCHAR(255), 
    data_evento Date,
    tempo Time,
    distancia int,
    CONSTRAINT eventos_id PRIMARY KEY(id))