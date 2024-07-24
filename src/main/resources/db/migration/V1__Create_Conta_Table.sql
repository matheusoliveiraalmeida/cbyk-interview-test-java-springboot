CREATE TABLE contas (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor DECIMAL NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    situacao VARCHAR(50) NOT NULL
);
