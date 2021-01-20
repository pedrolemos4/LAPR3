drop table "LAPR3_G23".farmacia CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".cliente CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".administrador CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".veiculo CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".drone CASCADE CONSTRAINTS PURGE;
drop table "LAPR3_G23".scooter CASCADE CONSTRAINTS PURGE;
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
drop table "LAPR3_G23".caminho CASCADE CONSTRAINTS PURGE;


CREATE TABLE "LAPR3_G23".Farmacia 
(           NIF number(10),
            email varchar(255) NOT NULL,
            Morada varchar(255) NOT NULL,
            PRIMARY KEY (NIF)
);
/

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
/

CREATE TABLE "LAPR3_G23".veiculo 
(           idveiculo number(10) GENERATED AS IDENTITY, 
            descricao varchar(255) NOT NULL UNIQUE,
            tipo varchar(255) NOT NULL,
            capacidade number(6,2) NOT NULL,
            percentagemBateria number(5,2) NOT NULL,
            pesoMaximo number(5,2) NOT NULL, 
            pesoveiculo number(5,2) NOT NULL,
            potencia number(5,2) NOT NULL,
            areaFrontal number(5,2) NOT NULL,
            Estadoveiculoid number(10) NOT NULL, 
            PRIMARY KEY (idveiculo)
);
/

CREATE TABLE "LAPR3_G23".drone
(           idDrone number(10),
            powerPro number(5,2),
            PRIMARY KEY (idDrone)
);
/

CREATE TABLE "LAPR3_G23".scooter
(           idScooter number(10),
            areaFrontal number(5,2),
            PRIMARY KEY (idScooter)
);
/

CREATE TABLE "LAPR3_G23".Estafeta 
(           UtilizadorNIF number(10) NOT NULL,
            EstadoEstafetaid number(10) NOT NULL, 
            pesoEstafeta number(10) NOT NULL, 
            PRIMARY KEY (UtilizadorNIF)
);
/

CREATE TABLE "LAPR3_G23".Produto 
(           idProduto number(10) GENERATED AS IDENTITY, 
            designacao varchar(255) NOT NULL, 
            peso number(10,2) NOT NULL, 
            precoBase number(10,2) NOT NULL, 
            PRIMARY KEY (idProduto)
);
/

CREATE TABLE "LAPR3_G23".Encomenda 
(           idEncomenda number(10) GENERATED AS IDENTITY, 
            dataPedida timestamp NOT NULL,
            preco number(10,2) NOT NULL, 
            pesoEncomenda number(10) NOT NULL, 
            taxa number(10,2) NOT NULL,
            EstadoEncomendaidEstadoEncomenda number(10) NOT NULL, 
            ClienteUtilizadorNIF number(10) NOT NULL, 
            PRIMARY KEY (idEncomenda)
);
/

CREATE TABLE "LAPR3_G23".Entrega 
(           idEntrega number(10) GENERATED AS IDENTITY, 
            EstafetaUtilizadorNIF number(10), 
            veiculoid number(10) NOT NULL, 
            dataInicio timestamp NOT NULL, 
            dataFim timestamp NOT NULL, 
            PRIMARY KEY (idEntrega)
);
/

CREATE TABLE "LAPR3_G23".Parque 
(           idParque number(10) GENERATED AS IDENTITY, 
            FarmaciaNIF number(10) NOT NULL,
            numeroMaximo number(10) NOT NULL,
            tipo varchar(255) NOT NULL,
            maxCap number(10) NOT NULL,
            PRIMARY KEY (idParque)
);


CREATE TABLE "LAPR3_G23".Endereco 
(           morada varchar(255) NOT NULL,
            latitude number(10) NOT NULL, 
            longitude number(10) NOT NULL, 
            altitude number(10) NOT NULL, 
            PRIMARY KEY (morada)
);
/

CREATE TABLE "LAPR3_G23".Utilizador 
(           NIF number(10), 
            nome varchar(255) NOT NULL, 
            email varchar(255) NOT NULL UNIQUE, 
            numeroSegurancaSocial number(10) NOT NULL UNIQUE, 
            password varchar(255) NOT NULL, 
            PRIMARY KEY (NIF)
 );
/

CREATE TABLE "LAPR3_G23".Estadoveiculo 
(           idEstadoveiculo number(10), 
            designacao varchar(255), 
            PRIMARY KEY (idEstadoveiculo)
);
/

CREATE TABLE "LAPR3_G23".EstadoEncomenda 
(           idEstadoEncomenda number(10), 
            designacao varchar(255) NOT NULL, 
            PRIMARY KEY (idEstadoEncomenda)
);
/

CREATE TABLE "LAPR3_G23".EstadoEstafeta 
(           id number(10) , 
            designacao varchar(255), 
            PRIMARY KEY (id)
);
/

CREATE TABLE "LAPR3_G23".StockFarmacia 
(           FarmaciaNIF number(10) NOT NULL, 
            ProdutoidProduto number(10) NOT NULL,
            stock number(10) NOT NULL,
            PRIMARY KEY (FarmaciaNIF,ProdutoidProduto)
);


CREATE TABLE "LAPR3_G23".EncomendaProduto 
(           EncomendaidEncomenda number(10) NOT NULL, 
            ProdutoidProduto number(10) NOT NULL, 
            stock number(10) NOT NULL, 
            PRIMARY KEY (EncomendaidEncomenda, ProdutoidProduto)
);
/

CREATE TABLE "LAPR3_G23".Estacionamento 
(           numeroLote number(10) NOT NULL, 
            carregador number(1) NOT NULL, 
            idParque number(10) NOT NULL, 
            PRIMARY KEY (idParque, numeroLote)
);
/

CREATE TABLE "LAPR3_G23".Estacionamentoveiculo 
(           idEstacionamentoveiculo number(10) GENERATED AS IDENTITY, 
            veiculoidveiculo number(10) NOT NULL, 
            EstacionamentonumeroLote number(10) NOT NULL, 
            EstacionamentoIdParque number(10) NOT NULL,
            datainicio timestamp NOT NULL, 
            datafim timestamp, 
            PRIMARY KEY (idEstacionamentoveiculo)
);
/

CREATE TABLE "LAPR3_G23".Recibo 
(           idRecibo number(10) GENERATED AS IDENTITY, 
            dataRecibo timestamp NOT NULL, 
            preco number(10) NOT NULL,
            ClienteUtilizadorNIF number(10) NOT NULL, 
            EncomendaidEncomenda number(10) NOT NULL,
            PRIMARY KEY (idRecibo)
);
/

CREATE TABLE "LAPR3_G23".LinhaRecibo 
(           ReciboidRecibo number(10) NOT NULL, 
            ProdutoidProduto number(10) NOT NULL, 
            quantidade number (10) NOT NULL,
            PRIMARY KEY (ReciboidRecibo, ProdutoidProduto)
);
/

CREATE TABLE "LAPR3_G23".Pagamento 
(           idPagamento number(10) GENERATED AS IDENTITY, 
            precoTotal number(10) NOT NULL,
            EncomendaidEncomenda number(10) NOT NULL, 
            PRIMARY KEY (idPagamento)
);
/

CREATE TABLE "LAPR3_G23".Cartao 
(           numeroCartaoCredito number(10),
            dataDeValidade date NOT NULL, 
            CCV number(10) NOT NULL, 
            PRIMARY KEY (numeroCartaoCredito)
);
/

CREATE TABLE "LAPR3_G23".EncomendaEntrega 
(           EntregaidEntrega number(10) NOT NULL, 
            EncomendaidEncomenda number(10) NOT NULL, 
            PRIMARY KEY (EntregaidEntrega, EncomendaidEncomenda)
);
/

CREATE TABLE "LAPR3_G23".TransferenciaProduto
(           id_Transferencia INTEGER GENERATED AS IDENTITY CONSTRAINT pkIdTransferencia PRIMARY KEY,
            id_Fornecedor    INTEGER CONSTRAINT nnFornecedorTransferencia NOT NULL,
            id_Recetor       INTEGER CONSTRAINT nnRecetorTransferencia NOT NULL,
            id_Produto       INTEGER CONSTRAINT nnProdutoTransferencia NOT NULL,
            quantidade       INTEGER CONSTRAINT nnQuantidadeTransferencia NOT NULL, CONSTRAINT ckQuantidadeTransferencia check (quantidade >0),
            id_Estado        INTEGER CONSTRAINT nnEstadoTransferencia NOT NULL
);            
/

CREATE TABLE "LAPR3_G23".EstadoTransferencia 
(           id number(10), 
            designacao varchar(255), 
            PRIMARY KEY (id)
);
/

CREATE TABLE "LAPR3_G23".Caminho 
(           morada1 varchar(255), 
            morada2 varchar(255), 
            roadResistanceCoefficient number(5,2) check(roadResistanceCoefficient > 0),
            velocidadeVento number(5,2) check(velocidadeVento > 0),
            direcaoVento number(5,2) check(direcaoVento BETWEEN 0 AND 360),
            PRIMARY KEY (morada1, morada2)
);
/

ALTER TABLE "LAPR3_G23".Cliente ADD CONSTRAINT FKCliente_Utilizador FOREIGN KEY (UtilizadorNIF) REFERENCES "LAPR3_G23".Utilizador (NIF);
ALTER TABLE "LAPR3_G23".Estafeta ADD CONSTRAINT FKEstafeta_Utilizador FOREIGN KEY (UtilizadorNIF) REFERENCES "LAPR3_G23".Utilizador (NIF);
ALTER TABLE "LAPR3_G23".Cliente ADD CONSTRAINT FKCliente_Endereco FOREIGN KEY (Enderecomorada) REFERENCES "LAPR3_G23".Endereco (morada);
ALTER TABLE "LAPR3_G23".Parque ADD CONSTRAINT FKParque_Farmacia FOREIGN KEY (FarmaciaNIF) REFERENCES "LAPR3_G23".Farmacia (NIF);
ALTER TABLE "LAPR3_G23".Farmacia ADD CONSTRAINT FKFarmacia_Endereco FOREIGN KEY (morada) REFERENCES "LAPR3_G23".Endereco (morada);
ALTER TABLE "LAPR3_G23".veiculo ADD CONSTRAINT FKveiculo_Estadoveiculo FOREIGN KEY (Estadoveiculoid) REFERENCES "LAPR3_G23".Estadoveiculo (idEstadoveiculo);
ALTER TABLE "LAPR3_G23".drone ADD CONSTRAINT FKdrone_Veiculo FOREIGN KEY (idDrone) REFERENCES "LAPR3_G23".veiculo (idVeiculo);
ALTER TABLE "LAPR3_G23".scooter ADD CONSTRAINT FKscooter_Veiculo FOREIGN KEY (idScooter) REFERENCES "LAPR3_G23".veiculo (idVeiculo);
ALTER TABLE "LAPR3_G23".Entrega ADD CONSTRAINT FKEntrega_Estafeta FOREIGN KEY (EstafetaUtilizadorNIF) REFERENCES "LAPR3_G23".Estafeta (UtilizadorNIF);
ALTER TABLE "LAPR3_G23".Entrega ADD CONSTRAINT FKEntrega_veiculo FOREIGN KEY (veiculoid) REFERENCES "LAPR3_G23".veiculo (idveiculo);
ALTER TABLE "LAPR3_G23".Encomenda ADD CONSTRAINT FKEncomenda_EstadoEncomenda FOREIGN KEY (EstadoEncomendaidEstadoEncomenda) REFERENCES "LAPR3_G23".EstadoEncomenda (idEstadoEncomenda);
ALTER TABLE "LAPR3_G23".Estafeta ADD CONSTRAINT FKEstafeta_EstadoEstafeta FOREIGN KEY (EstadoEstafetaid) REFERENCES "LAPR3_G23".EstadoEstafeta (id);
ALTER TABLE "LAPR3_G23".StockFarmacia ADD CONSTRAINT FKStock_Farmacia FOREIGN KEY (FarmaciaNIF) REFERENCES "LAPR3_G23".Farmacia (NIF);
ALTER TABLE "LAPR3_G23".StockFarmacia ADD CONSTRAINT FKStock_Produto FOREIGN KEY (ProdutoidProduto) REFERENCES "LAPR3_G23".Produto (idProduto);
ALTER TABLE "LAPR3_G23".Encomenda ADD CONSTRAINT FKEncomenda_Cliente FOREIGN KEY (ClienteUtilizadorNIF) REFERENCES "LAPR3_G23".Cliente (UtilizadorNIF);
ALTER TABLE "LAPR3_G23".Administrador ADD CONSTRAINT FKAdministrador_Utilizador FOREIGN KEY (UtilizadorNIF) REFERENCES "LAPR3_G23".Utilizador (NIF);
ALTER TABLE "LAPR3_G23".EncomendaProduto ADD CONSTRAINT FKEncomendaProduto_Encomenda FOREIGN KEY (EncomendaidEncomenda) REFERENCES "LAPR3_G23".Encomenda (idEncomenda);
ALTER TABLE "LAPR3_G23".EncomendaProduto ADD CONSTRAINT FKEncomendaProduto_Produto FOREIGN KEY (ProdutoidProduto) REFERENCES "LAPR3_G23".Produto (idProduto);
ALTER TABLE "LAPR3_G23".Estacionamento ADD CONSTRAINT FKEstacionamento_Parque FOREIGN KEY (idParque) REFERENCES "LAPR3_G23".Parque (idParque);
ALTER TABLE "LAPR3_G23".Estacionamentoveiculo ADD CONSTRAINT FKEstacionamentoveiculo_veiculo FOREIGN KEY (veiculoidveiculo) REFERENCES "LAPR3_G23".veiculo (idveiculo);
ALTER TABLE "LAPR3_G23".Estacionamentoveiculo ADD CONSTRAINT FKEstacionamentoveiculo_Estacionamento FOREIGN KEY (EstacionamentonumeroLote,EstacionamentoIdParque) REFERENCES "LAPR3_G23".Estacionamento (numeroLote,idParque);
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
ALTER TABLE "LAPR3_G23".Caminho ADD CONSTRAINT FKCaminho_Morada1 FOREIGN KEY (morada1) REFERENCES "LAPR3_G23".Endereco (morada);
ALTER TABLE "LAPR3_G23".Caminho ADD CONSTRAINT FKCaminho_Morada2 FOREIGN KEY (morada2) REFERENCES "LAPR3_G23".Endereco (morada);
------------------------------------------------------------------------------------

INSERT INTO "LAPR3_G23".estadoveiculo VALUES (1,'Disponivel');
INSERT INTO "LAPR3_G23".estadoveiculo VALUES (2,'Indisponivel');

INSERT INTO "LAPR3_G23".estadoEncomenda VALUES (1,'Encomendado');
INSERT INTO "LAPR3_G23".estadoEncomenda VALUES (2,'Entregando');
INSERT INTO "LAPR3_G23".estadoEncomenda VALUES (3,'Entregue');

INSERT INTO "LAPR3_G23".estadoEstafeta VALUES(1,'Disponivel');
INSERT INTO "LAPR3_G23".estadoEstafeta VALUES(2,'Indisponivel');

INSERT INTO "LAPR3_G23".EstadoTransferencia VALUES(1,'Pendente');
INSERT INTO "LAPR3_G23".EstadoTransferencia VALUES(2,'A transferir');
INSERT INTO "LAPR3_G23".EstadoTransferencia VALUES(3,'Transferido');

CREATE OR REPLACE PROCEDURE addFarmacia(p_NIF "LAPR3_G23".farmacia.nif%type,p_email "LAPR3_G23".farmacia.email%type,
morada "LAPR3_G23".farmacia.morada%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".farmacia VALUES(p_NIF,p_email, morada);   
END;    
/

CREATE OR REPLACE PROCEDURE addCliente(utilizadorNif "LAPR3_G23".cliente.utilizadorNif%type, creditos "LAPR3_G23".cliente.creditos%type,
enderecoMorada "LAPR3_G23".cliente.enderecoMorada%type, numeroCartaoCredito "LAPR3_G23".cliente.cartaonumeroCartaoCredito%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".cliente VALUES(utilizadorNif, creditos, enderecoMorada, numeroCartaoCredito);   
END;
/

CREATE OR REPLACE PROCEDURE atualizarCliente(utilizadorNif "LAPR3_G23".cliente.utilizadorNif%type, creditos "LAPR3_G23".cliente.creditos%type,
enderecoMorada "LAPR3_G23".cliente.enderecoMorada%type, numeroCartaoCredito "LAPR3_G23".cliente.cartaonumeroCartaoCredito%type) 
AS
BEGIN
  UPDATE "LAPR3_G23".cliente SET "LAPR3_G23".cliente.UtilizadorNIF = utilizadorNif, "LAPR3_G23".cliente.creditos = creditos, 
  "LAPR3_G23".cliente.Enderecomorada = enderecoMorada, "LAPR3_G23".cliente.CartaonumeroCartaoCredito = numeroCartaoCredito
    WHERE "LAPR3_G23".cliente.UtilizadorNIF = utilizadorNif;
END;
/

CREATE OR REPLACE PROCEDURE addAdministrador(utilizadorNif "LAPR3_G23".administrador.utilizadorNif%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".administrador VALUES(utilizadorNif);
END;
/

CREATE OR REPLACE PROCEDURE addEstafeta(UtilizadorNIF "LAPR3_G23".estafeta.utilizadorNif%type, EstadoEstafetaid "LAPR3_G23".estafeta.estadoestafetaid%type,
pesoEstafeta "LAPR3_G23".estafeta.pesoestafeta%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".estafeta VALUES(UtilizadorNIF, EstadoEstafetaid, pesoEstafeta);
END;
/

CREATE OR REPLACE PROCEDURE atualizarEstafeta(UtilizadorNIF "LAPR3_G23".estafeta.utilizadorNif%type, EstadoEstafetaid "LAPR3_G23".estafeta.estadoestafetaid%type,
pesoEstafeta "LAPR3_G23".estafeta.pesoestafeta%type) 
AS
BEGIN
  UPDATE "LAPR3_G23".estafeta SET "LAPR3_G23".estafeta.EstadoEstafetaid = EstadoEstafetaid, "LAPR3_G23".estafeta.pesoEstafeta = pesoEstafeta
  WHERE "LAPR3_G23".estafeta.UtilizadorNif = UtilizadorNIF;
END;
/

CREATE OR REPLACE FUNCTION addProduto(p_designacao "LAPR3_G23".produto.designacao%type, p_peso "LAPR3_G23".produto.peso%type, 
p_precoBase "LAPR3_G23".produto.precoBase%type) RETURN INTEGER
IS
v_idproduto INTEGER;
contador int;

BEGIN
    select count(*) into contador 
    from "LAPR3_G23".produto 
    where designacao = p_designacao and "LAPR3_G23".produto.precobase = p_precoBase;
    
    if(contador=0)then
        INSERT INTO "LAPR3_G23".produto(designacao,peso,precobase) VALUES(p_designacao, p_peso, p_precoBase);
    END IF;
  SELECT "LAPR3_G23".produto.idproduto INTO v_idProduto
  FROM "LAPR3_G23".produto
  WHERE "LAPR3_G23".produto.designacao = p_designacao
  AND "LAPR3_G23".produto.precobase = p_precoBase;
  return v_idProduto;
END;
/

create or replace PROCEDURE addProdutoStock(p_nif "LAPR3_G23".stockfarmacia.farmacianif%type,
p_idproduto "LAPR3_G23".stockFarmacia.produtoIdproduto%type, p_stock "LAPR3_G23".stockFarmacia.stock%type)
AS

contador int;

BEGIN
    select count(*) into contador 
    from "LAPR3_G23".stockfarmacia 
    where produtoIdproduto = p_idproduto AND farmacianif = p_nif;

    if(contador > 0) then
    UPDATE "LAPR3_G23".stockFarmacia SET "LAPR3_G23".stockFarmacia.stock = "LAPR3_G23".stockFarmacia.stock + p_stock
        WHERE "LAPR3_G23".stockFarmacia.produtoIdproduto = p_idProduto AND "LAPR3_G23".stockfarmacia.farmacianif = p_nif;
    else
        INSERT INTO "LAPR3_G23".stockFarmacia VALUES(p_nif,p_idproduto,p_stock);
    end if;

END;
/

CREATE OR REPLACE PROCEDURE atualizaProduto(idProd "LAPR3_G23".produto.idProduto%type,designacao "LAPR3_G23".produto.designacao%type,
peso "LAPR3_G23".produto.peso%type, precoBase "LAPR3_G23".produto.precoBase%type)
AS
BEGIN
  UPDATE "LAPR3_G23".produto SET "LAPR3_G23".produto.designacao = designacao, "LAPR3_G23".produto.peso = peso, 
  "LAPR3_G23".produto.precoBase = precoBase 
  WHERE "LAPR3_G23".produto.idProduto = idProd;
END;
/

CREATE OR REPLACE FUNCTION addEncomenda(p_dataPedida "LAPR3_G23".encomenda.dataPedida%type,
p_preco "LAPR3_G23".encomenda.preco%type, p_pesoEncomenda "LAPR3_G23".encomenda.pesoEncomenda%type,p_taxa "LAPR3_G23".encomenda.taxa%type, 
p_EstadoEncomendaidEstadoEncomenda "LAPR3_G23".encomenda.estadoEncomendaIdEstadoEncomenda%type,
p_ClienteUtilizadorNIF "LAPR3_G23".encomenda.clienteutilizadornif%type) 
RETURN INTEGER
IS
v_idEncomenda INTEGER;
BEGIN
  INSERT INTO "LAPR3_G23".encomenda(dataPedida,preco,pesoEncomenda,taxa,EstadoEncomendaidEstadoEncomenda,ClienteUtilizadorNIF)
  VALUES(p_dataPedida ,p_preco,p_pesoEncomenda,p_taxa,p_EstadoEncomendaidEstadoEncomenda,p_ClienteUtilizadorNIF);
  SELECT "LAPR3_G23".encomenda.idEncomenda INTO v_idEncomenda
  FROM "LAPR3_G23".encomenda
  WHERE "LAPR3_G23".encomenda.dataPedida = p_dataPedida
  AND "LAPR3_G23".encomenda.preco = p_preco
  AND "LAPR3_G23".encomenda.pesoEncomenda = p_pesoEncomenda
  AND "LAPR3_G23".encomenda.taxa = p_taxa
  AND "LAPR3_G23".encomenda.EstadoEncomendaidEstadoEncomenda = p_EstadoEncomendaidEstadoEncomenda
  AND "LAPR3_G23".encomenda.clienteUtilizadorNif=p_clienteUtilizadorNIF;
  return v_idEncomenda;
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


CREATE OR REPLACE FUNCTION addParque(p_FarmaciaNIF "LAPR3_G23".parque.farmacianif%type, p_numeroMaximo "LAPR3_G23".parque.numeromaximo%type,
p_tipo "LAPR3_G23".parque.tipo%type, p_maxCap "LAPR3_G23".parque.maxCap%type) 
RETURN INTEGER
IS
v_idParque INTEGER;
BEGIN
    INSERT INTO "LAPR3_G23".parque (FarmaciaNIF,numeroMaximo,tipo,maxCap)
    VALUES(p_FarmaciaNIF, p_numeroMaximo, p_tipo, p_maxCap);
    SELECT "LAPR3_G23".parque.idParque INTO v_idParque
    FROM "LAPR3_G23".parque
    WHERE "LAPR3_G23".parque.FarmaciaNIF = p_FarmaciaNIF;
RETURN v_idParque;
END;
/

CREATE OR REPLACE PROCEDURE addEndereco(morada "LAPR3_G23".endereco.morada%type, latitude "LAPR3_G23".endereco.latitude%type, 
longitude "LAPR3_G23".endereco.longitude%type, altitude "LAPR3_G23".endereco.altitude%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".endereco VALUES(morada, latitude, longitude , altitude);
END;
/

CREATE OR REPLACE PROCEDURE addUtilizador(NIF "LAPR3_G23".utilizador.nif%type, nome "LAPR3_G23".utilizador.nome%type, 
email "LAPR3_G23".utilizador.email%type, numeroSegurancaSocial "LAPR3_G23".utilizador.numeroSegurancaSocial%type,
password "LAPR3_G23".utilizador.password%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".utilizador VALUES(NIF, nome , email, numeroSegurancaSocial, password);
END;
/

CREATE OR REPLACE PROCEDURE atualizarUtilizador(NIF "LAPR3_G23".utilizador.nif%type, nome "LAPR3_G23".utilizador.nome%type, 
email "LAPR3_G23".utilizador.email%type, numeroSegurancaSocial "LAPR3_G23".utilizador.numeroSegurancaSocial%type,
password "LAPR3_G23".utilizador.password%type) 
AS
BEGIN
  UPDATE "LAPR3_G23".utilizador SET "LAPR3_G23".utilizador.nome = nome, "LAPR3_G23".utilizador.email = email, 
    "LAPR3_G23".utilizador.numeroSegurancaSocial = numeroSegurancaSocial, "LAPR3_G23".utilizador.password = password
    WHERE "LAPR3_G23".utilizador.NIF = NIF;
END;
/
------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE addEstadoveiculo(idEstadoveiculo "LAPR3_G23".estadoveiculo.idestadoveiculo%type, 
designacao "LAPR3_G23".estadoveiculo.designacao%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".estadoveiculo VALUES(idEstadoveiculo,designacao);   
END;
/

CREATE OR REPLACE PROCEDURE addEstadoEncomenda(idEstadoEncomenda "LAPR3_G23".estadoencomenda.idEstadoEncomenda%type,
designacao "LAPR3_G23".estadoEncomenda.designacao%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".estadoencomenda VALUES(idEstadoEncomenda,designacao);   
END;
/

CREATE OR REPLACE PROCEDURE addEstadoEstafeta(id "LAPR3_G23".estadoestafeta.id%type, designacao "LAPR3_G23".estadoestafeta.designacao%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".estadoestafeta VALUES(id,designacao);   
END;
/

CREATE OR REPLACE PROCEDURE addStockFarmacia(FarmaciaNIF "LAPR3_G23".stockfarmacia.farmacianif%type, ProdutoidProduto "LAPR3_G23".stockfarmacia.produtoidproduto%type, 
stock "LAPR3_G23".stockfarmacia.stock%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".stockfarmacia VALUES(FarmaciaNIF,ProdutoidProduto,stock);   
END;
/

CREATE OR REPLACE PROCEDURE addEncomendaProduto(EncomendaidEncomenda "LAPR3_G23".encomendaproduto.encomendaidencomenda%type, 
ProdutoidProduto "LAPR3_G23".encomendaproduto.produtoidproduto%type, stock "LAPR3_G23".encomendaproduto.stock%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".encomendaproduto VALUES(EncomendaidEncomenda,ProdutoidProduto,stock);   
END;
/

CREATE OR REPLACE PROCEDURE addEstacionamento(numeroLote "LAPR3_G23".estacionamento.numerolote%type, carregador "LAPR3_G23".estacionamento.carregador%type,
idParque "LAPR3_G23".estacionamento.idParque%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".estacionamento VALUES(numeroLote,carregador,idParque);   
END;
/

CREATE OR REPLACE PROCEDURE addEstacionamentoveiculo(veiculoidveiculo "LAPR3_G23".estacionamentoveiculo.veiculoidveiculo%type, 
EstacionamentonumeroLote "LAPR3_G23".estacionamentoveiculo.estacionamentonumerolote%type, datainicio "LAPR3_G23".estacionamentoveiculo.dataInicio%type,
datafim "LAPR3_G23".estacionamentoveiculo.dataFim%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".estacionamentoveiculo(veiculoidveiculo,estacionamentonumerolote,datainicio,datafim) VALUES(veiculoidveiculo,EstacionamentonumeroLote, datainicio, datafim);   
END;
/

CREATE OR REPLACE FUNCTION addRecibo(v_dataRecibo "LAPR3_G23".recibo.datarecibo%type, v_preco "LAPR3_G23".recibo.preco%type,
v_ClienteUtilizadorNIF "LAPR3_G23".recibo.clienteutilizadornif%type, v_EncomendaidEncomenda "LAPR3_G23".recibo.encomendaidencomenda%type) 
RETURN INTEGER
IS
v_idrecibo INTEGER;
BEGIN
  INSERT INTO "LAPR3_G23".recibo(dataRecibo,preco,ClienteUtilizadorNif,EncomendaidEncomenda)
  VALUES(v_dataRecibo,v_preco,v_ClienteUtilizadorNIF, v_EncomendaidEncomenda);   
  SELECT "LAPR3_G23".recibo.idrecibo into v_idrecibo
  FROM "LAPR3_G23".recibo
  WHERE "LAPR3_G23".recibo.clienteutilizadornif = v_ClienteUtilizadorNIF
  AND "LAPR3_G23".recibo.encomendaidencomenda = v_EncomendaidEncomenda;
  return v_idrecibo;
END;
/

CREATE OR REPLACE PROCEDURE addLinhaRecibo(ReciboidRecibo "LAPR3_G23".linharecibo.reciboidrecibo%type,
ProdutoidProduto "LAPR3_G23".linharecibo.produtoidproduto%type, quantidade "LAPR3_G23".linharecibo.quantidade%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".linharecibo VALUES(ReciboidRecibo,ProdutoidProduto,quantidade);   
END;
/

CREATE OR REPLACE PROCEDURE addPagamento(idPagamento "LAPR3_G23".pagamento.idpagamento%type, precoTotal "LAPR3_G23".pagamento.precoTotal%type,
EncomendaidEncomenda "LAPR3_G23".pagamento.encomendaidencomenda%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".pagamento VALUES(idPagamento,precoTotal,EncomendaidEncomenda);   
END;
/

CREATE OR REPLACE PROCEDURE addCartao(numeroCartaoCredito "LAPR3_G23".cartao.numerocartaocredito%type, dataDeValidade "LAPR3_G23".cartao.datadevalidade%type,
CCV "LAPR3_G23".cartao.ccv%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".cartao VALUES(numeroCartaoCredito,dataDeValidade,CCV);   
END;
/

CREATE OR REPLACE PROCEDURE addEncomendaEntrega(EntregaidEntrega "LAPR3_G23".entrega.identrega%type,
EncomendaidEncomenda "LAPR3_G23".encomenda.idencomenda%type)
AS
BEGIN
  INSERT INTO "LAPR3_G23".encomendaEntrega VALUES(EntregaidEntrega,EncomendaidEncomenda);
END;
/

CREATE OR REPLACE Function addveiculo(p_descricao "LAPR3_G23".veiculo.descricao%type, p_tipo "LAPR3_G23".veiculo.tipo%type,
p_capacidade "LAPR3_G23".veiculo.capacidade%type,percentagemBateria "LAPR3_G23".veiculo.percentagemBateria%type, 
pesoMaximo "LAPR3_G23".veiculo.pesoMaximo%type, pesoveiculo "LAPR3_G23".veiculo.pesoveiculo%type,
potencia "LAPR3_G23".veiculo.potencia%type, areaFrontal "LAPR3_G23".veiculo.areaFrontal%type,
estadoveiculoId "LAPR3_G23".veiculo.estadoveiculoId%type)
RETURN INTEGER
IS
v_idveiculo INTEGER;
    BEGIN
        INSERT INTO "LAPR3_G23".veiculo(descricao,tipo, capacidade,percentagemBateria,pesoMaximo,pesoveiculo,potencia,
        areaFrontal,Estadoveiculoid)
        VALUES(p_descricao,p_tipo,p_capacidade,percentagemBateria, pesoMaximo, pesoveiculo, potencia, areaFrontal, estadoveiculoId);
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


create or replace PROCEDURE procRemoverCreditos(p_nif "LAPR3_G23".cliente.utilizadornif%type, p_creditos "LAPR3_G23".cliente.creditos%type) IS
BEGIN
    
    UPDATE "LAPR3_G23".cliente SET "LAPR3_G23".cliente.creditos = "LAPR3_G23".cliente.creditos - p_creditos 
    WHERE "LAPR3_G23".cliente.Utilizadornif = p_nif;
END;
/


create or replace PROCEDURE procAtualizarStock(p_nif "LAPR3_G23".stockfarmacia.farmacianif%type,
p_idProduto "LAPR3_G23".stockfarmacia.ProdutoidProduto%type, p_quantidade "LAPR3_G23".stockfarmacia.stock%type) IS
BEGIN
    
    UPDATE "LAPR3_G23".stockfarmacia SET  "LAPR3_G23".stockfarmacia.stock = p_quantidade
    WHERE "LAPR3_G23".stockfarmacia.farmacianif = p_nif AND "LAPR3_G23".stockfarmacia.ProdutoidProduto  = p_idProduto;
END;
/


CREATE OR REPLACE PROCEDURE updateveiculo(p_id "LAPR3_G23".veiculo.idveiculo%type,p_descricao "LAPR3_G23".veiculo.descricao%type,
p_tipo "LAPR3_G23".veiculo.tipo%type,p_capacidade "LAPR3_G23".veiculo.capacidade%type,p_pb "LAPR3_G23".veiculo.percentagemBateria%type,
p_pm "LAPR3_G23".veiculo.pesoMaximo%type, p_ps "LAPR3_G23".veiculo.pesoveiculo%type,p_pot "LAPR3_G23".veiculo.potencia%type,
p_af "LAPR3_G23".veiculo.areafrontal%type,p_eS "LAPR3_G23".veiculo.estadoveiculoid%type) 
AS
BEGIN
  UPDATE "LAPR3_G23".veiculo SET "LAPR3_G23".veiculo.descricao = p_descricao,"LAPR3_G23".veiculo.tipo = p_tipo,
  "LAPR3_G23".veiculo.capacidade = p_capacidade, "LAPR3_G23".veiculo.percentagemBateria=p_pm,"LAPR3_G23".veiculo.pesoMaximo = p_pm,
  "LAPR3_G23".veiculo.pesoveiculo = p_ps, "LAPR3_G23".veiculo.potencia = p_pot,"LAPR3_G23".veiculo.areafrontal = p_af, 
  "LAPR3_G23".veiculo.estadoveiculoId = p_eS
    WHERE "LAPR3_G23".veiculo.idveiculo = p_id;
END;
/

CREATE OR REPLACE PROCEDURE addCreditosCliente(p_nif "LAPR3_G23".cliente.UtilizadorNIF%type, p_creds "LAPR3_G23".cliente.creditos%type) 
AS
BEGIN
  UPDATE "LAPR3_G23".cliente SET "LAPR3_G23".cliente.creditos = "LAPR3_G23".cliente.creditos + p_creds 
  WHERE "LAPR3_G23".cliente.UtilizadorNIF = p_nif;
END;
/

CREATE OR REPLACE PROCEDURE updateEntrega(p_idEntrega "LAPR3_G23".entrega.idEntrega%type, p_EstafetaUtilizadorNIF "LAPR3_G23".entrega.EstafetaUtilizadorNIF%type,
p_veiculoid "LAPR3_G23".entrega.veiculoid%type, p_dataInicio "LAPR3_G23".entrega.dataInicio%type, p_datafim "LAPR3_G23".entrega.dataFim%type)
AS
BEGIN
  UPDATE "LAPR3_G23".entrega SET "LAPR3_G23".entrega.EstafetaUtilizadorNIF = p_EstafetaUtilizadorNIF,
  "LAPR3_G23".entrega.veiculoid = p_veiculoid, "LAPR3_G23".entrega.dataInicio= p_dataInicio,"LAPR3_G23".entrega.dataFim = p_datafim
    WHERE "LAPR3_G23".entrega.idEntrega = p_idEntrega;
END;
/

CREATE OR REPLACE PROCEDURE updateEncomenda(p_idEncomenda "LAPR3_G23".encomenda.idEncomenda%type,
p_EstadoEncomendaidEstadoEncomenda "LAPR3_G23".encomenda.estadoEncomendaIdEstadoEncomenda%type)
AS
BEGIN
  UPDATE "LAPR3_G23".encomenda SET 
  "LAPR3_G23".encomenda.estadoEncomendaIdEstadoEncomenda = p_EstadoEncomendaidEstadoEncomenda
    WHERE "LAPR3_G23".encomenda.idEncomenda = p_idEncomenda;
END;
/

CREATE OR REPLACE PROCEDURE addTransferencia(p_fornecedor "LAPR3_G23".TransferenciaProduto.id_fornecedor%type, p_recetor "LAPR3_G23".TransferenciaProduto.id_recetor%type,
p_produto "LAPR3_G23".TransferenciaProduto.id_produto%type, p_quantidade "LAPR3_G23".TransferenciaProduto.quantidade%type, p_estado "LAPR3_G23".TransferenciaProduto.id_estado%type)
AS
BEGIN
  INSERT INTO "LAPR3_G23".TransferenciaProduto(id_fornecedor,id_recetor,id_produto,quantidade,id_estado)
  VALUES(p_fornecedor,p_recetor,p_produto,p_quantidade,p_estado);
END;
/


CREATE OR REPLACE PROCEDURE addCaminho(morada1 "LAPR3_G23".caminho.morada1%type, morada2 "LAPR3_G23".caminho.morada2%type,
roadResistanceCoefficient "LAPR3_G23".caminho.roadResistanceCoefficient%type, velocidadeVento "LAPR3_G23".caminho.velocidadeVento%type,
direcaoVento "LAPR3_G23".caminho.direcaoVento%type) 
AS
BEGIN
  INSERT INTO "LAPR3_G23".caminho VALUES(morada1, morada2, roadResistanceCoefficient, velocidadeVento, direcaoVento);
END;
/

CREATE OR REPLACE PROCEDURE addScooter (idScooter "LAPR3_G23".scooter.idScooter%type, areaFrontal "LAPR3_G23".scooter.areaFrontal%type)
AS
BEGIN
  INSERT INTO "LAPR3_G23".scooter VALUES(idScooter,areaFrontal);
END;
/

CREATE OR REPLACE PROCEDURE addDrone (idDrone "LAPR3_G23".drone.idDrone%type, powerPro "LAPR3_G23".drone.powerPro%type)
AS
BEGIN
  INSERT INTO "LAPR3_G23".drone VALUES (idDrone,powerPro);
END;
/
--------------------------------------------------------------------------------------------------------------------------------


select * from endereco;
INSERT INTO "LAPR3_G23".cartao VALUES(123123456,3,3);   
select * from cartao;
select c.utilizadornif, c.creditos, c.enderecomorada, c.cartaonumerocartaocredito
from cliente c, utilizador u
where c.utilizadornif = u.nif;

SELECT * 
FROM produto p 
INNER JOIN StockFarmacia s ON s.ProdutoidProduto = p.idProduto 
AND s.FarmaciaNIF = 555666555;

select * from farmacia;
select * from utilizador;
select * from encomenda;
SELECT * FROM cliente e INNER JOIN utilizador u ON e.UtilizadorNIF = u.NIF WHERE u.email= 'boomer@gmail.com';

SELECT e.idEncomenda,e.datapedida,e.preco, e.pesoEncomenda, e.taxa, e.estadoencomendaidestadoencomenda, e.clienteutilizadornif FROM encomenda e INNER JOIN cliente c ON e.ClienteUtilizadorNIF = c.UtilizadorNIF WHERE c.Enderecomorada = 'rua we';

SELECT c.utilizadornif, c.creditos, c.enderecomorada, c.cartaonumerocartaocredito 
FROM cliente c 
INNER JOIN utilizador u ON c.UtilizadorNIF = u.NIF 
WHERE u.NIF =123455678;

SELECT * from encomenda;
INSERT INTO  "LAPR3_G23".encomenda(dataPedida,preco,pesoEncomenda,taxa,EstadoEncomendaidEstadoEncomenda,ClienteUtilizadorNIF)
  VALUES(TO_TIMESTAMP(sysdate) ,66,44,0.6,1,123456789);

SELECT "LAPR3_G23".encomenda.idEncomenda --INTO v_idEncomenda
  FROM "LAPR3_G23".encomenda
  WHERE --"LAPR3_G23".encomenda.dataPedida = p_dataPedida
 "LAPR3_G23".encomenda.preco = 66
  AND "LAPR3_G23".encomenda.pesoEncomenda = 44
  AND "LAPR3_G23".encomenda.taxa = 0.6
  AND "LAPR3_G23".encomenda.EstadoEncomendaidEstadoEncomenda = 1
  AND "LAPR3_G23".encomenda.clienteUtilizadorNif=123456789;
  
  
  
  INSERT INTO "LAPR3_G23".recibo(dataRecibo,preco,ClienteUtilizadorNif,EncomendaidEncomenda)
  VALUES(LOCALTIMESTAMP,10,111222333, 181);   
  
  SELECT *--"LAPR3_G23".recibo.idrecibo
  FROM recibo
  WHERE --"LAPR3_G23".Recibo.dataRecibo=localTIMESTAMP AND
   "LAPR3_G23".recibo.clienteutilizadornif = 111222333
  AND "LAPR3_G23".recibo.encomendaidencomenda = 181;
  
rollback;
  
  SELECT * FROM produto;
  
  select * from cliente;
  
  select * from entrega;
  select * from encomenda;
  
  SELECT c.utilizadorNIF, c.creditos, c.EnderecoMorada, c.cartaonumerocartaocredito FROM cliente c INNER JOIN endereco e ON c.EnderecoMorada = e.morada WHERE c.EnderecoMorada= 'adsfsf';

SELECT c.utilizadorNIF, c.creditos, c.enderecomorada, c.cartaonumerocartaocredito FROM cliente c INNER JOIN endereco e ON c.EnderecoMorada = e.morada WHERE c.EnderecoMorada = 'adsfsf';

  select * from produto inner join stockfarmacia on produto.idproduto = stockfarmacia.produtoidproduto;
  
  update stockfarmacia set stock = 2 where produtoidproduto = 21;
  
  select * from farmacia ;
  select * from utilizador;
  
  select * from linhaRecibo;
  
  SELECT * FROM endereco e INNER JOIN farmacia f ON e.morada = f.morada WHERE f.NIF = 333666999;
  
  UPDATE "LAPR3_G23".stockfarmacia SET  "LAPR3_G23".stockfarmacia.stock = 2
    WHERE "LAPR3_G23".stockfarmacia.farmacianif = 555666555 AND "LAPR3_G23".stockfarmacia.ProdutoidProduto  = 1;