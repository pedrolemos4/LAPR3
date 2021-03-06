/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author beatr
 * @param <V>
 * @param <E>
 */

public class Vertex<V, E> {

    private int key ;                     
    private V  element ;                 
    private final Map<V, Edge<V,E>> outVerts; 

    /**
     * Controi um vertice vazio
     */
    public Vertex () {
        key = -1; element = null; outVerts = new LinkedHashMap<>();
    }

    /**
     * Constroi uma instância com k e vInf
     * @param k
     * @param vInf 
     */
    public Vertex (int k, V vInf) {
        key = k; element = vInf; outVerts = new LinkedHashMap<>();
    }

    /**
     * Controi uma instância com o vertice
     * @param v 
     */
    public Vertex (Vertex<V,E> v) {
        key = v.getKey(); element = v.getElement();
        outVerts = new LinkedHashMap<>();
        for (V vert : v.outVerts.keySet()){
            Edge<V,E> edge = v.outVerts.get(vert);
            outVerts.put(vert, edge);
        }
    }

    /**
     * Devolve a chave
     * @return 
     */
    public int getKey() {
        return key;
    }

    /**
     * Modifica a chave
     * @param k 
     */
    public void setKey(int k) {
        key = k;
    }

    /**
     * Devolve o elemento
     * @return 
     */
    public V getElement() {
        return element;
    }

    /**
     * Modifica o elemento
     * @param vInf 
     */
    public void setElement(V vInf) {
        element = vInf;
    }

    /**
     * Adiciona um vertice ajdacente vAdj a partir da aresta edge
     * @param vAdj
     * @param edge 
     */
    public void addAdjVert(V vAdj, Edge<V,E> edge){
        outVerts.put(vAdj, edge);
    }

    /**
     * Devolve o vertice adjacente
     * @param edge
     * @return 
     */
    public V getAdjVert(Edge<V,E> edge){

        for (V vert : outVerts.keySet())
            if (edge.equals(outVerts.get(vert)))
                return vert;

        return null;
    }

    /**
     * Remove o vertice adjacente vAdj
     * @param vAdj 
     */
    public void remAdjVert(V vAdj){
        outVerts.remove(vAdj);
    }

    /**
     * Devolve a aresta onde 1 dos vertices é vAdj
     * @param vAdj
     * @return 
     */
    public Edge<V,E> getEdge(V vAdj){
        return outVerts.get(vAdj);
    }

    /**
     * Devolve o numero de vertices adjacentes
     * @return 
     */
    public int numAdjVerts() {
        return outVerts.size();
    }

    /**
     * Devolve todos os vertices adjacentes
     * @return 
     */
    public Iterable<V> getAllAdjVerts() {
        return outVerts.keySet();
    }
    
    /**
     * Devolve o valor de todas as arestas
     * @return 
     */
    public Iterable<Edge<V,E>> getAllOutEdges() {
        return outVerts.values();
    }

    /**
     * Verifica se otherObj é igual ao vertice
     * @param otherObj
     * @return 
     */
    @Override
    public boolean equals(Object otherObj) {

        if (this == otherObj){
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;

        Vertex<V,E> otherVertex = (Vertex<V,E>) otherObj;

        if (this.key != otherVertex.key)
            return false;

        if (this.element != null && otherVertex.element != null &&
                !this.element.equals(otherVertex.element))
            return false;

        if (this.numAdjVerts() != otherVertex.numAdjVerts())
            return false;

        Iterator<Edge<V,E>> it1 = this.getAllOutEdges().iterator();
        while (it1.hasNext()){
            Iterator<Edge<V,E>> it2 = otherVertex.getAllOutEdges().iterator();
            boolean exists=false;
            while (it2.hasNext()){
                if (it1.next().equals(it2.next()))
                    exists=true;
            }
            if (!exists)
                return false;
        }
        return true;
    }

    /**
     * Devolve um clone do vertice
     * @return 
     */
    @Override
    public Vertex<V,E> clone() {

        Vertex<V,E> newVertex = new Vertex<>();

        newVertex.setKey(key);
        newVertex.setElement(element);

        for (V vert : outVerts.keySet())
            newVertex.addAdjVert(vert, this.getEdge(vert));

        return newVertex;
    }

    /**
     * Devolve a descrição do vertice
     * @return 
     */
    @Override
    public String toString() {
        String st="";
        if (element != null)
            st= element + " (" + key + "): \n";
        if (!outVerts.isEmpty())
            for (V vert : outVerts.keySet())
                st += outVerts.get(vert);

        return st;
    }

}


