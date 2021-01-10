package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReciboTest {

    @Test
    void ReciboEmptyConstructorTest(){
        Recibo instance = new Recibo();

        assertEquals(instance,instance);
    }

    @Test
    void ProdutoConstructorTest(){
        Recibo instance = new Recibo(123,"data");

        assertEquals(instance,instance);
    }

    @Test
    void getNif() {
        Recibo instance = new Recibo(123,"data");
        assertEquals(123,instance.getNif());
    }

    @Test
    void getId() {
        Recibo instance = new Recibo(123,"data");
        assertEquals(0,instance.getId());
    }

    @Test
    void getData() {
        Recibo instance = new Recibo(123,"data");
        assertEquals("data",instance.getData());
    }

    @Test
    void getLst() {
        Recibo instance = new Recibo(123,"data");
        assertEquals(new ArrayList<>(),instance.getLst());
    }

    @Test
    void setNif() {
        Recibo instance = new Recibo(123,"data");
        instance.setNif(2);
        assertEquals(2,instance.getNif());
    }

    @Test
    void setId() {
        Recibo instance = new Recibo(123,"data");
        instance.setId(2);
        assertEquals(2,instance.getId());
    }

    @Test
    void setData() {
        Recibo instance = new Recibo(123,"data");
        instance.setData("dataExpec");
        assertEquals("dataExpec",instance.getData());
    }

    @Test
    void setLst() {
        Recibo instance = new Recibo(123,"data");
        List<Produto> expcLst = new ArrayList<>();
        instance.setLst(expcLst);
        assertEquals(expcLst,instance.getLst());
    }
}