package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferenciaProdutoTest {

    @Test
    void ProdutoEmptyConstructorTest(){
        TransferenciaProduto instance = new TransferenciaProduto();

        assertEquals(instance,instance);
    }

    @Test
    void ProdutoConstructorTest(){
        TransferenciaProduto instance = new TransferenciaProduto(1,1,2,1,1);

        assertEquals(instance,instance);
    }

    @Test
    void getIdTrans() {
        System.out.println("getIdTrans");
        TransferenciaProduto instance = new TransferenciaProduto(1,1,2,1,1);
        int expResult = 1;
        int result = instance.getIdTrans();
        assertEquals(expResult, result);
    }

    @Test
    void getIdRemetente() {
        System.out.println("getIdRemetente");
        TransferenciaProduto instance = new TransferenciaProduto(1,1,2,1,1);
        int expResult = 1;
        int result = instance.getIdRemetente();
        assertEquals(expResult, result);
    }

    @Test
    void getIdDestinatario() {
        System.out.println("getIdDestinatario");
        TransferenciaProduto instance = new TransferenciaProduto(1,1,2,1,1);
        int expResult = 2;
        int result = instance.getIdDestinatario();
        assertEquals(expResult, result);
    }

    @Test
    void getIdProduto() {
        System.out.println("getIdProduto");
        TransferenciaProduto instance = new TransferenciaProduto(1,1,2,1,1);
        int expResult = 1;
        int result = instance.getIdProduto();
        assertEquals(expResult, result);
    }

    @Test
    void getQuantidade() {
        System.out.println("getQuantidade");
        TransferenciaProduto instance = new TransferenciaProduto(1,1,2,1,1);
        int expResult = 1;
        int result = instance.getQuantidade();
        assertEquals(expResult, result);
    }
}