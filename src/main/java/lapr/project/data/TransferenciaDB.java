package lapr.project.data;

import lapr.project.model.Farmacia;
import lapr.project.model.Produto;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Map;

public class TransferenciaDB extends DataHandler {

    ProdutosDB pdb = new ProdutosDB();
    EmailDB edb = new EmailDB();

    public boolean realizaPedido(Farmacia fOrig, Farmacia fDest, Produto produto, int quantidade) {
        addTransferencia(fOrig.getNIF(), fDest.getNIF(), produto.getId(), quantidade, 1);
        enviarStock(fOrig, fDest, produto, quantidade);

        return true;
    }

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

    public boolean enviarStock(Farmacia fOrig, Farmacia fDest, Produto produto, int quantidade) {
        Map<Produto, Integer> stockFarmOrig = pdb.getLista(fOrig.getNIF());
        Map<Produto, Integer> stockFarmDest = pdb.getLista(fDest.getNIF());

        if (stockFarmOrig.containsKey(produto) && stockFarmOrig.get(produto) >= quantidade) {
            System.out.println("Quantidade antes: "+quantidade);
            System.out.println("Dest Antes: "+(stockFarmDest.get(produto)));
            stockFarmDest.replace(produto, stockFarmDest.get(produto) + quantidade);
            System.out.println("Dest: "+(stockFarmDest.get(produto)));
            pdb.atualizarStock(fDest.getNIF(), produto.getId(), stockFarmDest.get(produto));
            System.out.println("Orig Antes: "+stockFarmOrig.get(produto));
            System.out.println("Quantidade: "+quantidade);
            stockFarmOrig.replace(produto, stockFarmOrig.get(produto) - quantidade);
            System.out.println("Orig: "+(stockFarmOrig.get(produto)));
            pdb.atualizarStock(fOrig.getNIF(), produto.getId(), stockFarmOrig.get(produto));
            return true;
        }

        return false;
    }
}
