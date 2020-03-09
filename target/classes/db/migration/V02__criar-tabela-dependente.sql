CREATE TABLE `dependente` (
`id` BIGINT(11) NOT NULL AUTO_INCREMENT,
`nome` VARCHAR(18) NOT NULL,
`cpf` BIGINT(11) NOT NULL,
`email` VARCHAR(200) NOT NULL,
`cep` BIGINT(8) NOT NULL,
`rua` VARCHAR(150),
`bairro` VARCHAR(50),
`cidade` VARCHAR(30),
`uf` VARCHAR(2),
`data_registro` DATE NOT NULL,
`data_atualizacao` DATE,
`cliente_id` BIGINT(11) NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (cliente_id) REFERENCES cliente(id)
)