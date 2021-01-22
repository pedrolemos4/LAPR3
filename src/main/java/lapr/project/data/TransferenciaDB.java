package lapr.project.data;

import lapr.project.model.Farmacia;
import lapr.project.model.Produto;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Map;

public class TransferenciaDB extends DataHandler {

    ProdutosDB pdb = new ProdutosDB();

    /**
     * Adiciona a transferência feita na base de dados e atualiza os stocks das
     * farmácias
     *
     * @param fOrig farmácia de origem
     * @param fDest farmácia de destino
     * @param produto produto a transferir
     * @param quantidade quantidade a ser transferida
     * @return
     */
    public boolean realizaPedido(Farmacia fOrig, Farmacia fDest, Produto produto, int quantidade) {
        addTransferencia(fOrig.getNIF(), fDest.getNIF(), produto.getId(), quantidade, 1);
        enviarStock(fOrig, fDest, produto, quantidade);

        return true;
    }

    /**
     * Adiciona a transferência feita na base de dados
     *
     * @param idRem farmácia de origem
     * @param idDes farmácia de destino
     * @param idProd produto a transferir
     * @param qtd quantidade a ser transferida
     * @param estado estado da transferência
     */
    public void addTransferencia(int idRem, int idDes, int idProd, int qtd, int estado) {
        try {
            openConnection();

            try (CallableStatement callStmt = getConnection().prepareCall("{ call addTransferencia(?,?,?,?,?) }")) {

                callStmt.setInt(1, idRem);
                callStmt.setInt(2, idDes);
                callStmt.setInt(3, idProd);
                callStmt.setInt(4, qtd);
                callStmt.setInt(5, estado);

                callStmt.execute();
            }
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza os stocks das farmácias
     *
     * @param fOrig farmácia de origem
     * @param fDest farmácia de destino
     * @param produto produto a transferir
     * @param quantidade quantidade a ser transferida
     * @return true se os stocks forem atualizados com sucesso, false se não
     */
    public boolean enviarStock(Farmacia fOrig, Farmacia fDest, Produto produto, int quantidade) {
        Map<Produto, Integer> stockFarmOrig = pdb.getLista(fOrig.getNIF());
        Map<Produto, Integer> stockFarmDest = pdb.getLista(fDest.getNIF());

        if (stockFarmOrig.containsKey(produto) && stockFarmOrig.get(produto) >= quantidade) {
            if (!stockFarmDest.containsKey(produto)) {
                stockFarmDest.put(produto, quantidade);
            } else {
                stockFarmDest.replace(produto, stockFarmDest.get(produto) + quantidade);
            }
            pdb.atualizarStock(fDest.getNIF(), produto.getId(), stockFarmDest.get(produto));
            stockFarmOrig.replace(produto, stockFarmOrig.get(produto) - quantidade);
            pdb.atualizarStock(fOrig.getNIF(), produto.getId(), stockFarmOrig.get(produto));
            return true;

        }
        return false;
    }
}
