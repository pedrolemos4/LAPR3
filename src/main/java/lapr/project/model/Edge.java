package lapr.project.model;

import java.lang.reflect.Array;

public class Edge<V,E> implements Comparable {

    private E element;           // Edge information
    private double weight;       // Edge weight
    private Vertex<V,E> vOrig;  // vertex origin
    private Vertex<V,E> vDest;  // vertex destination

    /**
     * Cria um construtor vazio
     */
    public Edge() {
        element = null; weight= 0.0; vOrig=null; vDest=null;
    }

    /**
     * Cria uma edge com os elemento, o peso e a informação
     * @param eInf
     * @param ew
     * @param vo
     * @param vd 
     */
    public Edge(E eInf, double ew, Vertex<V,E> vo, Vertex<V,E> vd) {
        element = eInf; weight= ew; vOrig=vo; vDest=vd;
    }

    /**
     * Devolve o elemento
     * @return 
     */
    public E getElement() {
        return element;
    }

    /**
     * Modifica o elemento
     * @param eInf 
     */
    public void setElement(E eInf) {
        element = eInf;
    }

    /**
     * Devolve o peso
     * @return 
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Modifica o peso
     * @param ew 
     */
    public void setWeight(double ew) {
        weight= ew;
    }

    /**
     * Devolve a origem
     * @return 
     */
    public V getVOrig() {
        if (this.vOrig != null)
            return vOrig.getElement();
        return null;
    }

    /**
     * Modifica a origem
     * @param vo 
     */
    public void setVOrig(Vertex<V,E> vo) {
        vOrig= vo;
    }

    /**
     * Devolve o destino
     * @return 
     */
    public V getVDest() {
        if (this.vDest != null)
            return vDest.getElement();
        return null;
    }

    /**
     * Modifica o destino
     * @param vd 
     */
    public void setVDest(Vertex<V,E> vd) {
        vDest= vd;
    }

    /**
     * Retorna os vértices na extremidade do ramo
     * @return 
     */
    public V[] getEndpoints() {

        V oElem=null;
        V dElem=null;
        V typeElem=null;

        if (this.vOrig != null)
            oElem = vOrig.getElement();

        if (this.vDest != null)
            dElem = vDest.getElement();

        if (oElem == null && dElem == null)
            return null;

        if (oElem != null)         
            typeElem = oElem;

        if (dElem != null)
            typeElem = dElem;

        V[] endverts = (V [])Array.newInstance(typeElem.getClass(), 2);

        endverts[0]= oElem;
        endverts[1]= dElem;

        return endverts;
    }

    /**
     * Compara o objeto Edge com outro objeto Edge
     * @param otherObj
     * @return 
     */
    @Override
    public boolean equals(Object otherObj) {

        if (this == otherObj){
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass()){
            return false;
        }

        Edge<V,E> otherEdge = (Edge<V,E>) otherObj;

        // if endpoints vertices are not equal
        if ((this.vOrig == null && otherEdge.vOrig != null) ||
                (this.vOrig != null && otherEdge.vOrig == null))
            return false;

        if ((this.vDest == null && otherEdge.vDest!=null) ||
                (this.vDest != null && otherEdge.vDest == null))
            return false;

        if (this.vOrig != null && otherEdge.vOrig != null &&
                !this.vOrig.equals(otherEdge.vOrig))
            return false;

        if (this.vDest != null && otherEdge.vDest!=null &&
                !this.vDest.equals(otherEdge.vDest))
            return false;

        if (this.weight != otherEdge.weight)
            return false;

        if (this.element != null && otherEdge.element != null)
            return this.element.equals(otherEdge.element);

        return true;
    }

    /**
     * Compara 2 Edges
     * @param otherObject
     * @return 
     */
    @Override
    public int compareTo(Object otherObject) {

        Edge<V,E> other = (Edge<V,E>) otherObject ;
        if (this.weight < other.weight)  return -1;
        if (this.weight == other.weight) return 0;
        return 1;
    }

    /**
     * Devolve a descrição textual da Edge
     * @return 
     */
    @Override
    public String toString() {
        String st="";
        if (element != null)
            st= "      (" + element + ") - ";
        else
            st= "\t ";

        if (weight != 0)
            st += weight +" - " +vDest.getElement()+ "\n";
        else
            st += vDest.getElement()+ "\n";

        return st;
    }

}

