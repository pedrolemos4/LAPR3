package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParqueTest {

    @Test
    void ParqueEmptyConstructorTest(){
        Parque instance = new Parque();

        assertEquals(instance,instance);
    }

    @Test
    void ParqueConstructorTest(){
        Parque instance = new Parque(20);

        assertEquals(instance,instance);
    }

    @Test
    void getNumeroMaximo() {
        System.out.println("getNumeroMaximo");
        Parque instance = new Parque(20);
        int expResult = 20;
        int result = instance.getNumeroMaximo();
        assertEquals(expResult, result);
    }

    @Test
    void setNumeroMaximo() {
        System.out.println("setNumeroMaximo");
        int numeroMaximo = 15;
        Parque instance = new Parque(20);
        instance.setNumeroMaximo(15);
        assertEquals(numeroMaximo, instance.getNumeroMaximo());
    }
}