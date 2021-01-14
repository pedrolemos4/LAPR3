drop table "LAPR3_G23".farmacia CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".cliente CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".administrador CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".veiculo CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".estafeta CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".produto CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".encomenda CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".entrega CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".parque CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".endereco CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".utilizador CASCADE CONSTRAINTS PURGE;
-----------------------------------------------------------------------------------
drop table "LAPR3_G23".estadoveiculo CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".estadoencomenda CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".estadoestafeta CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".stockfarmacia CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".encomendaproduto CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".estacionamento CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".estacionamentoveiculo CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".recibo CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".linharecibo CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".pagamento CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".cartao CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".encomendaEntrega CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".transferenciaproduto CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".estadoTransferencia CASCADE CONSTRAINTS PURGE;

CREATE TABLE "LAPR3_G23".Farmacia 
(           NIF number(10) GENERATED AS IDENTITY,
            Morada varchar(255) NOT NULL,
            PRIMARY KEY (NIF)
);


CREATE TABLE "LAPR3_G23".Cliente 
(           UtilizadorNIF number(10) NOT NULL, 
            creditos number(10), 
            Enderecomorada varchar(255) NOT NULL,
            CartaonumeroCartaoCredito number(10) NOT NULL, 
            PRIMARY KEY (UtilizadorNIF)
);


CREATE TABLE "LAPR3_G23".Administrador 
(           UtilizadorNIF number(10) NOT NULL,
            PRIMARY KEY (UtilizadorNIF)
);


CREATE TABLE "LAPR3_G23".veiculo 
(           idveiculo number(10) GENERATED AS IDENTITY, 
            descricao varchar(255) NOT NULL UNIQUE, 
            percentagemBateria number(5,2) NOT NULL,
            pesoMaximo number(5,2) NOT NULL, 
            pesoveiculo number(5,2) NOT NULL,
            potencia number(5,2) NOT NULL,
            areaFrontal number(5,2) NOT NULL,
            Estadoveiculoid number(10) NOT NULL, 
            PRIMARY KEY (idveiculo)
);


CREATE TABLE "LAPR3_G23".Estafeta 
(           UtilizadorNIF number(10) NOT NULL,
            EstadoEstafetaid number(10) NOT NULL, 
            pesoEstafeta number(10) NOT NULL, 
            PRIMARY KEY (UtilizadorNIF)
);


CREATE TABLE "LAPR3_G23".Produto 
(           idProduto number(10) GENERATED AS IDENTITY, 
            designacao varchar(255) NOT NULL, 
            peso number(10,2) NOT NULL, 
            precoBase number(10,2) NOT NULL, 
            PRIMARY KEY (idProduto)
);


CREATE TABLE "LAPR3_G23".Encomenda 
(           idEncomenda number(10) GENERATED AS IDENTITY, 
            dataPedida timestamp NOT NULL,
            preco number(10,2) NOT NULL, 
            pesoEncomenda number(10) NOT NULL, 
            taxa number(10,2) NOT NULL, 
            Entregaid_entrega number(10) NOT NULL, 
            EstadoEncomendaidEstadoEncomenda number(10) NOT NULL, 
            ClienteUtilizadorNIF number(10) NOT NULL, 
            PRIMARY KEY (idEncomenda)
);


CREATE TABLE "LAPR3_G23".Entrega 
(           idEntrega number(10) GENERATED AS IDENTITY, 
            EstafetaUtilizadorNIF number(10) NOT NULL, 
            veiculoid number(10) NOT NULL, 
            dataInicio timestamp NOT NULL, 
            dataFim timestamp NOT NULL, 
            PRIMARY KEY (idEntrega)
);

CREATE TABLE "LAPR3_G23".Parque 
(           FarmaciaNIF number(10) NOT NULL,
            numeroMaximo number(10) NOT NULL,
            tipo varchar(255) NOT NULL, 
            PRIMARY KEY (FarmaciaNIF)
);


CREATE TABLE "LAPR3_G23".Endereco 
(           morada varchar(255) NOT NULL,
            latitude number(10) NOT NULL, 
            longitude number(10) NOT NULL, 
            altitude number(10) NOT NULL, 
            PRIMARY KEY (morada)
);


CREATE TABLE "LAPR3_G23".Utilizador 
(           NIF number(10) GENERATED AS IDENTITY, 
            nome varchar(255) NOT NULL, 
            email varchar(255) NOT NULL UNIQUE, 
            numeroSegurancaSocial number(10) NOT NULL, 
            password varchar(255) NOT NULL, 
            PRIMARY KEY (NIF)
 );


CREATE TABLE "LAPR3_G23".Estadoveiculo 
(           idEstadoveiculo number(10), 
            designacao varchar(255), 
            PRIMARY KEY (idEstadoveiculo)
);


CREATE TABLE "LAPR3_G23".EstadoEncomenda 
(           idEstadoEncomenda number(10), 
            designacao varchar(255) NOT NULL, 
            PRIMARY KEY (idEstadoEncomenda)
);


CREATE TABLE "LAPR3_G23".EstadoEstafeta 
(           id number(10) , 
            designacao varchar(255), 
            PRIMARY KEY (id)
);


CREATE TABLE "LAPR3_G23".StockFarmacia 
(           FarmaciaNIF number(10) NOT NULL, 
            ProdutoidProduto number(10) NOT NULL,
            stock number(10) NOT NULL,
            PRIMARY KEY (FarmaciaNIF)
);


CREATE TABLE "LAPR3_G23".EncomendaProduto 
(           EncomendaidEncomenda number(10) NOT NULL, 
            ProdutoidProduto number(10) NOT NULL, 
            stock number(10) NOT NULL, 
            PRIMARY KEY (EncomendaidEncomenda, ProdutoidProduto)
);


CREATE TABLE "LAPR3_G23".Estacionamento 
(           numeroLote number(10) GENERATED AS IDENTITY, 
            carregador number(1) NOT NULL, 
            ParqueFarmaciaNIF number(10) NOT NULL, 
            PRIMARY KEY (numeroLote)
);


CREATE TABLE "LAPR3_G23".Estacionamentoveiculo 
(           idEstacionamentoveiculo number(10) GENERATED AS IDENTITY, 
            veiculoidveiculo number(10) NOT NULL, 
            EstacionamentonumeroLote number(10) NOT NULL, 
            datainicio timestamp NOT NULL, 
            datafim timestamp, 
            PRIMARY KEY (idEstacionamentoveiculo)
);


CREATE TABLE "LAPR3_G23".Recibo 
(           idRecibo number(10) GENERATED AS IDENTITY, 
            dataRecibo timestamp NOT NULL, 
            ClienteUtilizadorNIF number(10) NOT NULL, 
            EncomendaidEncomenda number(10) NOT NULL,
            PRIMARY KEY (idRecibo)
);


CREATE TABLE "LAPR3_G23".LinhaRecibo 
(           linha number(10) NOT NULL, 
            ReciboidRecibo number(10) NOT NULL, 
            ProdutoidProduto number(10) NOT NULL, 
            PRIMARY KEY (Linha, ReciboidRecibo)
);


CREATE TABLE "LAPR3_G23".Pagamento 
(           idPagamento number(10) GENERATED AS IDENTITY, 
            precoTotal number(10) NOT NULL,
            EncomendaidEncomenda number(10) NOT NULL, 
            PRIMARY KEY (idPagamento)
);


CREATE TABLE "LAPR3_G23".Cartao 
(           numeroCartaoCredito number(10) GENERATED AS IDENTITY,
            dataDeValidade timestamp NOT NULL, 
            CCV number(10) NOT NULL, 
            PRIMARY KEY (numeroCartaoCredito)
);


CREATE TABLE "LAPR3_G23".EncomendaEntrega 
(           EntregaidEntrega number(10) NOT NULL, 
            EncomendaidEncomenda number(10) NOT NULL, 
            PRIMARY KEY (EntregaidEntrega, EncomendaidEncomenda)
);

CREATE TABLE "LAPR3_G23".TransferenciaProduto
(           id_Transferencia INTEGER GENERATED AS IDENTITY CONSTRAINT pkIdTransferencia PRIMARY KEY,
            id_Fornecedor    INTEGER CONSTRAINT nnFornecedorTransferencia NOT NULL,
            id_Recetor       INTEGER CONSTRAINT nnRecetorTransferencia NOT NULL,
            id_Produto       INTEGER CONSTRAINT nnProdutoTransferencia NOT NULL,
            quantidade       INTEGER CONSTRAINT nnQuantidadeTransferencia NOT NULL, CONSTRAINT ckQuantidadeTransferencia check (quantidade >0),
            id_Estado        INTEGER CONSTRAINT nnEstadoTransferencia NOT NULL
);            

CREATE TABLE "LAPR3_G23".EstadoTransferencia 
(           id number(10), 
            designacao varchar(255), 
            PRIMARY KEY (id)
);

ALTER TABLE "LAPR3_G23".Cliente ADD CONSTRAINT FKCliente_Utilizador FOREIGN KEY (UtilizadorNIF) REFERENCES "LAPR3_G23".Utilizador (NIF);
ALTER TABLE "LAPR3_G23".Estafeta ADD CONSTRAINT FKEstafeta_Utilizador FOREIGN KEY (UtilizadorNIF) REFERENCES "LAPR3_G23".Utilizador (NIF);
ALTER TABLE "LAPR3_G23".Cliente ADD CONSTRAINT FKCliente_Endereco FOREIGN KEY (Enderecomorada) REFERENCES "LAPR3_G23".Endereco (morada);
ALTER TABLE "LAPR3_G23".Parque ADD CONSTRAINT FKParque_Farmacia FOREIGN KEY (FarmaciaNIF) REFERENCES "LAPR3_G23".Farmacia (NIF);
ALTER TABLE "LAPR3_G23".Farmacia ADD CONSTRAINT FKFarmacia_Endereco FOREIGN KEY (morada) REFERENCES "LAPR3_G23".Endereco (morada);
ALTER TABLE "LAPR3_G23".veiculo ADD CONSTRAINT FKveiculo_Estadoveiculo FOREIGN KEY (Estadoveiculoid) REFERENCES "LAPR3_G23".Estadoveiculo (idEstadoveiculo);
ALTER TABLE "LAPR3_G23".Entrega ADD CONSTRAINT FKEntrega_Estafeta FOREIGN KEY (EstafetaUtilizadorNIF) REFERENCES "LAPR3_G23".Estafeta (UtilizadorNIF);
ALTER TABLE "LAPR3_G23".Entrega ADD CONSTRAINT FKEntrega_veiculo FOREIGN KEY (veiculoid) REFERENCES "LAPR3_G23".veiculo (idveiculo);
ALTER TABLE "LAPR3_G23".Encomenda ADD CONSTRAINT FKEncomenda_Entrega FOREIGN KEY (Entregaid_entrega) REFERENCES "LAPR3_G23".Entrega (idEntrega);
ALTER TABLE "LAPR3_G23".Encomenda ADD CONSTRAINT FKEncomenda_EstadoEncomenda FOREIGN KEY (EstadoEncomendaidEstadoEncomenda) REFERENCES "LAPR3_G23".EstadoEncomenda (idEstadoEncomenda);
ALTER TABLE "LAPR3_G23".Estafeta ADD CONSTRAINT FKEstafeta_EstadoEstafeta FOREIGN KEY (EstadoEstafetaid) REFERENCES "LAPR3_G23".EstadoEstafeta (id);
ALTER TABLE "LAPR3_G23".StockFarmacia ADD CONSTRAINT FKStock_Farmacia FOREIGN KEY (FarmaciaNIF) REFERENCES "LAPR3_G23".Farmacia (NIF);
ALTER TABLE "LAPR3_G23".StockFarmacia ADD CONSTRAINT FKStock_Produto FOREIGN KEY (ProdutoidProduto) REFERENCES "LAPR3_G23".Produto (idProduto);
ALTER TABLE "LAPR3_G23".Encomenda ADD CONSTRAINT FKEncomenda_Cliente FOREIGN KEY (ClienteUtilizadorNIF) REFERENCES "LAPR3_G23".Cliente (UtilizadorNIF);
ALTER TABLE "LAPR3_G23".Administrador ADD CONSTRAINT FKAdministrador_Utilizador FOREIGN KEY (UtilizadorNIF) REFERENCES "LAPR3_G23".Utilizador (NIF);
ALTER TABLE "LAPR3_G23".EncomendaProduto ADD CONSTRAINT FKEncomendaProduto_Encomenda FOREIGN KEY (EncomendaidEncomenda) REFERENCES "LAPR3_G23".Encomenda (idEncomenda);
ALTER TABLE "LAPR3_G23".EncomendaProduto ADD CONSTRAINT FKEncomendaProduto_Produto FOREIGN KEY (ProdutoidProduto) REFERENCES "LAPR3_G23".Produto (idProduto);
ALTER TABLE "LAPR3_G23".Estacionamento ADD CONSTRAINT FKEstacionamento_Parque FOREIGN KEY (ParqueFarmaciaNIF) REFERENCES "LAPR3_G23".Parque (FarmaciaNIF);
ALTER TABLE "LAPR3_G23".Estacionamentoveiculo ADD CONSTRAINT FKEstacionamentoveiculo_veiculo FOREIGN KEY (veiculoidveiculo) REFERENCES "LAPR3_G23".veiculo (idveiculo);
ALTER TABLE "LAPR3_G23".Estacionamentoveiculo ADD CONSTRAINT FKEstacionamentoveiculo_Estacionamento FOREIGN KEY (EstacionamentonumeroLote) REFERENCES "LAPR3_G23".Estacionamento (numeroLote);
ALTER TABLE "LAPR3_G23".Recibo ADD CONSTRAINT FKRecibo_Cliente FOREIGN KEY (ClienteUtilizadorNIF) REFERENCES "LAPR3_G23".Cliente (UtilizadorNIF);
ALTER TABLE "LAPR3_G23".Recibo ADD CONSTRAINT FKRecibo_Encomenda FOREIGN KEY (EncomendaidEncomenda) REFERENCES "LAPR3_G23".Encomenda (idEncomenda);
ALTER TABLE "LAPR3_G23".LinhaRecibo ADD CONSTRAINT FKLinhaRecibo_Recibo FOREIGN KEY (ReciboidRecibo) REFERENCES "LAPR3_G23".Recibo (idRecibo);
ALTER TABLE "LAPR3_G23".LinhaRecibo ADD CONSTRAINT FKLinhaRecibo_Produto FOREIGN KEY (ProdutoidProduto) REFERENCES "LAPR3_G23".Produto (idProduto);
ALTER TABLE "LAPR3_G23".Pagamento ADD CONSTRAINT FKPagamento_Encomenda FOREIGN KEY (EncomendaidEncomenda) REFERENCES "LAPR3_G23".Encomenda (idEncomenda);
ALTER TABLE "LAPR3_G23".Cliente ADD CONSTRAINT FKCliente_Cartao FOREIGN KEY (CartaonumeroCartaoCredito) REFERENCES "LAPR3_G23".Cartao (numeroCartaoCredito);
ALTER TABLE "LAPR3_G23".EncomendaEntrega ADD CONSTRAINT FKEncomendaEntrega_Entrega FOREIGN KEY (EntregaidEntrega) REFERENCES "LAPR3_G23".Entrega (idEntrega);
ALTER TABLE "LAPR3_G23".EncomendaEntrega ADD CONSTRAINT FKEncomendaEntrega_Encomenda     FOREIGN KEY (EncomendaidEncomenda) REFERENCES "LAPR3_G23".Encomenda (idEncomenda);
ALTER TABLE "LAPR3_G23".TransferenciaProduto ADD CONSTRAINT FKTransferenciaProduto_Fornecedor FOREIGN KEY (id_Fornecedor) REFERENCES "LAPR3_G23".Farmacia (NIF);
ALTER TABLE "LAPR3_G23".TransferenciaProduto ADD CONSTRAINT FKTransferenciaProduto_Recetor FOREIGN KEY (id_Recetor) REFERENCES "LAPR3_G23".Farmacia (NIF);
ALTER TABLE "LAPR3_G23".TransferenciaProduto ADD CONSTRAINT FKTransferenciaProduto_Produto FOREIGN KEY (id_Produto) REFERENCES "LAPR3_G23".Produto (idProduto);
ALTER TABLE "LAPR3_G23".TransferenciaProduto ADD CONSTRAINT FKTransferenciaProduto_Estado FOREIGN KEY (id_Estado) REFERENCES "LAPR3_G23".EstadoTransferencia (id);
------------------------------------------------------------------------------------

INSERT INTO "LAPR3_G23".estadoveiculo VALUES (1,'Disponível');
INSERT INTO "LAPR3_G23".estadoveiculo VALUES (2,'Indisponível');

INSERT INTO "LAPR3_G23".estadoEncomenda VALUES (1,'Encomendado');
INSERT INTO "LAPR3_G23".estadoEncomenda VALUES (2,'Entregando');
INSERT INTO "LAPR3_G23".estadoEncomenda VALUES (3,'Entregue');

INSERT INTO "LAPR3_G23".estadoEstafeta VALUES(1,'Disponível');
INSERT INTO "LAPR3_G23".estadoEstafeta VALUES(2,'Indisponível');

INSERT INTO "LAPR3_G23".EstadoTransferencia VALUES(1,'Pendente');
INSERT INTO "LAPR3_G23".EstadoTransferencia VALUES(2,'A transferir');
INSERT INTO "LAPR3_G23".EstadoTransferencia VALUES(3,'Transferido');

CREATE OR REPLACE PROCEDURE addFarmacia(NIF NUMBER, morada VARCHAR) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".farmacia VALUES(NIF, morada);   
END;
/

CREATE OR REPLACE PROCEDURE addCliente(utilizadorNif NUMBER, creditos NUMBER, enderecoMorada VARCHAR, numeroCartaoCredito NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".cliente VALUES(utilizadorNif, creditos, enderecoMorada, numeroCartaoCredito);   
END;
/

CREATE OR REPLACE PROCEDURE atualizarCliente(utilizadorNif NUMBER, creditos NUMBER, enderecoMorada VARCHAR, numeroCartaoCredito NUMBER) 
AS
BEGIN
  UPDATE "LAPR3_G23".cliente SET "LAPR3_G23".cliente.UtilizadorNIF = utilizadorNif, "LAPR3_G23".cliente.creditos = creditos, 
  "LAPR3_G23".cliente.Enderecomorada = enderecoMorada, "LAPR3_G23".cliente.CartaonumeroCartaoCredito = numeroCartaoCredito
    WHERE "LAPR3_G23".cliente.UtilizadorNIF = utilizadorNif;
END;
/

CREATE OR REPLACE PROCEDURE addAdministrador(utilizadorNif NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".administrador VALUES(utilizadorNif);
END;
/

CREATE OR REPLACE PROCEDURE addEstafeta(UtilizadorNIF NUMBER, EstadoEstafetaid NUMBER, pesoEstafeta NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".estafeta VALUES(UtilizadorNIF, EstadoEstafetaid, pesoEstafeta);
END;
/

CREATE OR REPLACE PROCEDURE atualizarEstafeta(UtilizadorNIF NUMBER, EstadoEstafetaid NUMBER, pesoEstafeta NUMBER) 
AS
BEGIN
  UPDATE "LAPR3_G23".estafeta SET "LAPR3_G23".estafeta.EstadoEstafetaid = EstadoEstafetaid, "LAPR3_G23".estafeta.pesoEstafeta = pesoEstafeta
  WHERE "LAPR3_G23".estafeta.UtilizadorNif = UtilizadorNIF;
END;
/

CREATE OR REPLACE PROCEDURE addProduto(designacao VARCHAR, peso NUMBER, pesoBase NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".produto(designacao,peso,precobase) VALUES(designacao, peso, pesoBase);
END;
/

CREATE OR REPLACE PROCEDURE atualizaProduto(designacao VARCHAR, peso NUMBER, precoBase NUMBER, idProd NUMBER) 
AS
BEGIN
  UPDATE "LAPR3_G23".produto SET "LAPR3_G23".produto.designacao = designacao, "LAPR3_G23".produto.peso = peso, "LAPR3_G23".produto.precoBase = precoBase WHERE "LAPR3_G23".produto.idProduto = idProd;
END;
/

CREATE OR REPLACE PROCEDURE addEncomenda(idEncomenda NUMBER, dataPedida TIMESTAMP, preco NUMBER, peso NUMBER, pesoEncomenda NUMBER, taxa NUMBER, EstadoEncomendaidEstadoEncomenda NUMBER, ClienteUtilizadorNIF NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".encomenda VALUES(idEncomenda, dataPedida , preco, peso, pesoEncomenda, taxa, EstadoEncomendaidEstadoEncomenda, ClienteUtilizadorNIF);
END;
/

CREATE OR REPLACE FUNCTION addEntrega(p_EstafetaUtilizadorNIF "LAPR3_G23".entrega.EstafetaUtilizadorNIF%type,
p_veiculoid "LAPR3_G23".entrega.veiculoid%type, p_dataInicio "LAPR3_G23".entrega.dataInicio%type, p_datafim "LAPR3_G23".entrega.dataFim%type)
RETURN INTEGER
IS
v_idEntrega INTEGER;
    BEGIN
        INSERT INTO "LAPR3_G23".entrega(EstafetaUtilizadorNIF,veiculoid,dataInicio,dataFim)
        VALUES(p_EstafetaUtilizadorNIF,p_veiculoid,p_dataInicio,p_datafim);
        SELECT "LAPR3_G23".entrega.idEntrega INTO v_idEntrega
        FROM "LAPR3_G23".entrega
        WHERE "LAPR3_G23".entrega.EstafetaUtilizadorNIF = p_EstafetaUtilizadorNIF;
RETURN v_idEntrega;
END;
/

CREATE OR REPLACE PROCEDURE addParque(FarmaciaNIF NUMBER, numeroMaximo NUMBER, tipo VARCHAR) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".parque VALUES(FarmaciaNIF, numeroMaximo, tipo);
END;
/

CREATE OR REPLACE PROCEDURE addEndereco(morada VARCHAR, latitude NUMBER, longitude NUMBER, altitude NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".endereco VALUES(morada, latitude, longitude , altitude);
END;
/

CREATE OR REPLACE PROCEDURE addUtilizador(NIF NUMBER, nome VARCHAR, email VARCHAR, numeroSegurancaSocial NUMBER, password VARCHAR) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".utilizador VALUES(NIF, nome , email, numeroSegurancaSocial, password);
END;
/

CREATE OR REPLACE PROCEDURE atualizarUtilizador(NIF NUMBER, nome VARCHAR, email VARCHAR, numeroSegurancaSocial NUMBER, password VARCHAR) 
AS
BEGIN
  UPDATE "LAPR3_G23".utilizador SET "LAPR3_G23".utilizador.nome = nome, "LAPR3_G23".utilizador.email = email, 
    "LAPR3_G23".utilizador.numeroSegurancaSocial = numeroSegurancaSocial, "LAPR3_G23".utilizador.password = password
    WHERE "LAPR3_G23".utilizador.NIF = NIF;
END;
/
------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE addEstadoveiculo(idEstadoveiculo NUMBER, designacao VARCHAR) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".estadoveiculo VALUES(idEstadoveiculo,designacao);   
END;
/

CREATE OR REPLACE PROCEDURE addEstadoEncomenda(idEstadoEncomenda NUMBER, designacao VARCHAR) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".estadoencomenda VALUES(idEstadoEncomenda,designacao);   
END;
/

CREATE OR REPLACE PROCEDURE addEstadoEstafeta(id NUMBER, designacao VARCHAR) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".estadoestafeta VALUES(id,designacao);   
END;
/

CREATE OR REPLACE PROCEDURE addStockFarmacia(FarmaciaNIF NUMBER, ProdutoidProduto NUMBER, stock NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".stockfarmacia VALUES(FarmaciaNIF,ProdutoidProduto,stock);   
END;
/

CREATE OR REPLACE PROCEDURE addEncomendaProduto(EncomendaidEncomenda NUMBER, ProdutoidProduto NUMBER, stock NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".encomendaproduto VALUES(EncomendaidEncomenda,ProdutoidProduto,stock);   
END;
/

CREATE OR REPLACE PROCEDURE addEstacionamento(numeroLote NUMBER, carregador NUMBER, ParqueFarmaciaNIF NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".estacionamento VALUES(numeroLote,carregador,ParqueFarmaciaNIF);   
END;
/

CREATE OR REPLACE PROCEDURE addEstacionamentoveiculo(veiculoidveiculo NUMBER, EstacionamentonumeroLote NUMBER, datainicio TIMESTAMP, datafim TIMESTAMP) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".estacionamentoveiculo(veiculoidveiculo,estacionamentonumerolote,datainicio,datafim) VALUES(veiculoidveiculo,EstacionamentonumeroLote, datainicio, datafim);   
END;
/

CREATE OR REPLACE PROCEDURE addRecibo(idRecibo NUMBER, dataRecibo TIMESTAMP, ClienteUtilizadorNIF NUMBER, EncomendaidEncomenda NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".recibo VALUES(idRecibo,dataRecibo,ClienteUtilizadorNIF, EncomendaidEncomenda);   
END;
/

CREATE OR REPLACE PROCEDURE addLinhaRecibo(linha NUMBER, ReciboidRecibo NUMBER, ProdutoidProduto NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".linharecibo VALUES(linha,ReciboidRecibo,ProdutoidProduto);   
END;
/

CREATE OR REPLACE PROCEDURE addPagamento(idPagamento NUMBER, precoTotal NUMBER, EncomendaidEncomenda NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".pagamento VALUES(idPagamento,precoTotal,EncomendaidEncomenda);   
END;
/

CREATE OR REPLACE PROCEDURE addCartao(numeroCartaoCredito NUMBER, dataDeValidade TIMESTAMP, CCV NUMBER) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".cartao VALUES(numeroCartaoCredito,dataDeValidade,CCV);   
END;
/

CREATE OR REPLACE PROCEDURE addEncomendaEntrega(EntregaidEntrega NUMBER, EncomendaidEncomenda NUMBER)
AS
BEGIN
  INSERT INTO "LAPR3_G23".encomendaEntrega VALUES(EntregaidEntrega,EncomendaidEncomenda);
END;
/

CREATE OR REPLACE Function addveiculo(p_descricao "LAPR3_G23".veiculo.descricao%type,
percentagemBateria "LAPR3_G23".veiculo.percentagemBateria%type, 
pesoMaximo "LAPR3_G23".veiculo.pesoMaximo%type, pesoveiculo "LAPR3_G23".veiculo.pesoveiculo%type,
potencia "LAPR3_G23".veiculo.potencia%type, areaFrontal "LAPR3_G23".veiculo.areaFrontal%type,
estadoveiculoId "LAPR3_G23".veiculo.estadoveiculoId%type)
RETURN INTEGER
IS
v_idveiculo INTEGER;
    BEGIN
        INSERT INTO "LAPR3_G23".veiculo(descricao,percentagemBateria,pesoMaximo,pesoveiculo,potencia,areaFrontal,Estadoveiculoid)
        VALUES(p_descricao,percentagemBateria, pesoMaximo, pesoveiculo, potencia, areaFrontal, estadoveiculoId);
        SELECT "LAPR3_G23".veiculo.idveiculo INTO v_idveiculo
        FROM "LAPR3_G23".veiculo
        WHERE "LAPR3_G23".veiculo.descricao = p_descricao;
RETURN v_idveiculo;
END;
/

CREATE OR REPLACE Function getEntregaAtiva(estafeta_email "LAPR3_G23".Utilizador.email%type)
return integer
is
v_entrega_id integer;
v_estafeta_nif integer;
begin
    select "LAPR3_G23".utilizador.nif into v_estafeta_nif from "LAPR3_G23".utilizador where "LAPR3_G23".utilizador.email = estafeta_email;
    select "LAPR3_G23".entrega.identrega into v_entrega_id from "LAPR3_G23".entrega where "LAPR3_G23".entrega.estafetautilizadornif = v_estafeta_nif and datafim is null;
return v_entrega_id;
end;
/

create or replace PROCEDURE procRemoveveiculo(p_id_veiculo "LAPR3_G23".veiculo.idveiculo%type) IS
BEGIN
    Update "LAPR3_G23".veiculo  Set Estadoveiculoid = 0 where idveiculo = p_id_veiculo;
END;
/

create or replace FUNCTION funcValidateLogin(p_email "LAPR3_G23".utilizador.email%type, p_pwd "LAPR3_G23".utilizador.password%type) 
    RETURN INTEGER IS 
    
    v_nif integer;
    v_email "LAPR3_G23".utilizador.email%type;
    v_pwd   "LAPR3_G23".utilizador.password%type;


BEGIN

    SELECT nif into v_nif
    FROM "LAPR3_G23".utilizador
    WHERE email LIKE p_email and password LIKE p_pwd;

    RETURN v_nif;
    
    EXCEPTION WHEN NO_DATA_FOUND  THEN
        RETURN 0;
END;
/


create or replace PROCEDURE procRemoverProduto(p_des_produto "LAPR3_G23".produto.designacao%type) IS
BEGIN
    delete from "LAPR3_G23".Produto where "LAPR3_G23".Produto.designacao = p_des_produto;
END;
/


create or replace PROCEDURE procRemoverCreditos(p_email "LAPR3_G23".utilizador.email%type, p_creditos "LAPR3_G23".cliente.creditos%type) IS
BEGIN
    
    UPDATE "LAPR3_G23".cliente SET "LAPR3_G23".cliente.creditos = "LAPR3_G23".cliente.creditos - p_creditos 
    WHERE "LAPR3_G23".cliente.Utilizadornif in (SELECT "LAPR3_G23".cliente.Utilizadornif 
                                        FROM "LAPR3_G23".cliente e 
										INNER JOIN "LAPR3_G23".utilizador u ON e.UtilizadorNIF = u.NIF 
										WHERE u.email = p_email);
END;
/

CREATE OR REPLACE PROCEDURE updateveiculo(p_id "LAPR3_G23".veiculo.idveiculo%type,p_descricao "LAPR3_G23".veiculo.descricao%type,
p_pb "LAPR3_G23".veiculo.percentagemBateria%type,p_pm "LAPR3_G23".veiculo.pesoMaximo%type, p_ps "LAPR3_G23".veiculo.pesoveiculo%type,
p_pot "LAPR3_G23".veiculo.potencia%type,p_af "LAPR3_G23".veiculo.areafrontal%type,p_eS "LAPR3_G23".veiculo.estadoveiculoid%type) 
AS
BEGIN
  UPDATE "LAPR3_G23".veiculo SET "LAPR3_G23".veiculo.descricao = p_descricao, "LAPR3_G23".veiculo.percentagemBateria=p_pm,
  "LAPR3_G23".veiculo.pesoMaximo = p_pm,"LAPR3_G23".veiculo.pesoveiculo = p_ps, "LAPR3_G23".veiculo.potencia = p_pot,
  "LAPR3_G23".veiculo.areafrontal = p_af, "LAPR3_G23".veiculo.estadoveiculoId = p_eS
    WHERE "LAPR3_G23".veiculo.idveiculo = p_id;
END;
/
