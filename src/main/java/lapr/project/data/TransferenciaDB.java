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
        System.out.println("1");
        addTransferencia(fOrig.getNIF(), fDest.getNIF(), produto.getId(), quantidade, 1);
        System.out.println("2");
        enviarStock(fOrig, fDest, produto, quantidade);
        System.out.println("3");

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
                System.out.println("exec before");
                callStmt.execute();
                System.out.println("exec after");
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
        System.out.println("ENTRASTE?");
        if (stockFarmOrig.containsKey(produto) && stockFarmOrig.get(produto) >= quantidade) {
            System.out.println("4");
            if (!stockFarmDest.containsKey(produto)) {
                System.out.println("5");
                stockFarmDest.put(produto, 0);
                stockFarmDest.put(produto, quantidade);
                pdb.addProdutoStock(fDest.getNIF(), produto.getId(), stockFarmDest.get(produto));
            } else {
                System.out.println("6");
                stockFarmDest.replace(produto, stockFarmDest.get(produto) + quantidade);
                pdb.atualizarStock(fDest.getNIF(), produto.getId(), stockFarmDest.get(produto));
            }
            System.out.println("7");
            stockFarmOrig.replace(produto, stockFarmOrig.get(produto) - quantidade);
            pdb.atualizarStock(fOrig.getNIF(), produto.getId(), stockFarmOrig.get(produto));
            System.out.println("8");
            return true;

        }

        if (stockFarmOrig.containsKey(produto) && stockFarmOrig.get(produto) < quantidade) {
            if (!stockFarmDest.containsKey(produto)) {
                System.out.println("9");
                stockFarmDest.put(produto, 0);
                stockFarmDest.put(produto, stockFarmOrig.get(produto));
                pdb.addProdutoStock(fDest.getNIF(), produto.getId(), stockFarmDest.get(produto));
            } else {
                System.out.println("10");
                stockFarmDest.replace(produto, stockFarmDest.get(produto) + stockFarmOrig.get(produto));
                pdb.atualizarStock(fDest.getNIF(), produto.getId(), stockFarmDest.get(produto));
            }
            stockFarmOrig.replace(produto, stockFarmOrig.get(produto) - stockFarmDest.get(produto));
            pdb.atualizarStock(fOrig.getNIF(), produto.getId(), stockFarmOrig.get(produto));
        }

        return false;
    }
}
