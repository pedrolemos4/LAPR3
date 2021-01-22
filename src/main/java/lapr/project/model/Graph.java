package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author beatr
 * @param <V>
 * @param <E>
 */

public class Graph<V,E> implements GraphInterface<V,E> {

    private int numVert;
    private int numEdge;
    private final boolean isDirected;
    private final Map<V,Vertex<V,E>> vertices;  

    /**
     * Constroi uma instância grafo onde indica se é direcionado ou não
     * @param directed 
     */
    public Graph(boolean directed) {
        numVert=0;
        numEdge=0;
        isDirected=directed;
        vertices = new LinkedHashMap<>();
    }

    /**
     * Retorna o  numero de vertices
     * @return 
     */
    public int numVertices(){
        return numVert;
    }

    /**
     * Devolve os vertices todos
     * @return 
     */
    public Iterable<V> vertices() {
        return vertices.keySet();
    }

    /**
     * Valida um vertice enviado por parametro
     * @param vert
     * @return 
     */
    public boolean validVertex(V vert) {

        if (vertices.get(vert) == null)
            return false;

        return true;
    }

    /**
     * Devolve a chave referente ao vertice
     * @param vert
     * @return 
     */
    public int getKey(V vert) {
        return vertices.get(vert).getKey();
    }

    /**
     * Retorna os vertives adjacentes ao enviado por parametro
     * @param vert
     * @return 
     */
    public Iterable<V> adjVertices(V vert){

        if (!validVertex(vert))
            return null;

        Vertex<V,E> vertex = vertices.get(vert);

        return vertex.getAllAdjVerts();
    }


    /**
     * Retorna o numero de arestas
     * @return 
     */
    public int numEdges(){
        return numEdge;
    }

    /**
     * Retorna todas as arestas
     * @return 
     */
    public Iterable<Edge<V,E>> edges() {
        ArrayList<Edge<V,E>> listEdges = new ArrayList<>();

        for (Vertex<V,E> vert : vertices.values()) {
            for(Edge<V,E> edge : vert.getAllOutEdges()) {
                listEdges.add(edge);
            }
        }
        return listEdges;
    }

    /**
     * Devolve a aresta entre o vOrig e vDest
     * @param vOrig
     * @param vDest
     * @return 
     */
    public Edge<V,E> getEdge(V vOrig, V vDest){

        if (!validVertex(vOrig) || !validVertex(vDest))
            return null;

        Vertex<V,E> vorig = vertices.get(vOrig);

        return vorig.getEdge(vDest);
    }

    /**
     * Devolve os vertices da aresta enviada por parametro
     * @param edge
     * @return 
     */
    public V[] endVertices(Edge<V,E> edge){

        if (edge == null)
            return null;

        if (!validVertex(edge.getVOrig()) || !validVertex(edge.getVDest()))
            return null;

        Vertex<V,E> vorig = vertices.get(edge.getVOrig());

        if (!edge.equals(vorig.getEdge(edge.getVDest())))
            return null;

        return edge.getEndpoints();
    }

    /**
     * Devolve o vertice que está do outro lado da aresta
     * @param vert
     * @param edge
     * @return 
     */
    public V opposite(V vert, Edge<V,E> edge){

        if (!validVertex(vert))
            return null;

        Vertex<V,E> vertex = vertices.get(vert);

        return vertex.getAdjVert(edge);
    }

    /**
     * Devolve o numero de vertices adjacentes
     * @param vert
     * @return 
     */
    public int outDegree(V vert){

        if (!validVertex(vert))
            return -1;

        Vertex<V,E> vertex = vertices.get(vert);

        return vertex.numAdjVerts();
    }

    /**
     * Devolve o numero de arestas do vertices
     * @param vert
     * @return 
     */
    public int inDegree(V vert){

        if (!validVertex(vert))
            return -1;

        int degree=0;
        for (V otherVert : vertices.keySet())
            if (getEdge(otherVert,vert) != null)
                degree++;

        return degree;
    }

    /**
     * Devolve todas as arestas que tem como vertice de origem o vertice vert
     * @param vert
     * @return 
     */
    public Iterable<Edge<V,E>> outgoingEdges(V vert){

        if (!validVertex(vert))
            return null;

        Vertex<V,E> vertex = vertices.get(vert);

        return vertex.getAllOutEdges();
    }

    /**
     * Devolve todas as arestas que tem como vertice de destino o vertice vert
     * @param vert
     * @return 
     */
    public Iterable<Edge<V,E>> incomingEdges(V vert){
        ArrayList <Edge<V,E>> listIncomingEdges = new ArrayList<>();
        for(Vertex<V,E> verti :  vertices.values()) {
            for (Edge<V,E> edge : verti.getAllOutEdges()) {
                if(edge.getVDest().equals(vert)) {
                    listIncomingEdges.add(edge);
                }
            }
        }
        return listIncomingEdges;
    }

    /**
     * Insere o vertice vert
     * @param vert
     * @return 
     */
    public boolean insertVertex(V vert){

        if (validVertex(vert))
            return false;

        Vertex<V,E> vertex = new Vertex<>(numVert,vert);
        vertices.put(vert,vertex);
        numVert++;

        return true;
    }

    /**
     * Insere a aresta com a informação eInf, peso eWeight, vertice origem vOrig e vertice destino vDest
     * @param vOrig
     * @param vDest
     * @param eInf
     * @param eWeight
     * @return 
     */
    public boolean insertEdge(V vOrig, V vDest, E eInf, double eWeight){

        if (getEdge(vOrig,vDest) != null)
            return false;

        if (!validVertex(vOrig))
            insertVertex(vOrig);

        if (!validVertex(vDest))
            insertVertex(vDest);

        Vertex<V,E> vorig = vertices.get(vOrig);
        Vertex<V,E> vdest = vertices.get(vDest);

        Edge<V,E> newEdge = new Edge<>(eInf,eWeight,vorig,vdest);
        vorig.addAdjVert(vDest,newEdge);
        numEdge++;

        if (!isDirected)
            if (getEdge(vDest,vOrig) == null){
                Edge<V,E> otherEdge = new Edge<>(eInf,eWeight,vdest,vorig);
                vdest.addAdjVert(vOrig,otherEdge);
                numEdge++;
            }

        return true ;
    }

    /**
     * Remove o vertice
     * @param vert
     * @return 
     */
    public boolean removeVertex(V vert){

        if (!validVertex(vert))
            return false;

        //remove all edges that point to vert
        for (Edge<V,E> edge : incomingEdges(vert)){
            V vadj = edge.getVOrig();
            removeEdge(vadj,vert);
        }

        Vertex<V,E> vertex = vertices.get(vert);

        //update the keys of subsequent vertices in the map
        for (Vertex<V,E> v : vertices.values()){
            int keyVert = v.getKey();
            if ( keyVert > vertex.getKey()){
                keyVert = keyVert-1;
                v.setKey(keyVert);
            }
        }
        //The edges that live from vert are removed with the vertex
        vertices.remove(vert);

        numVert--;

        return true;
    }

    /**
     * Remove a aresta
     * @param vOrig
     * @param vDest
     * @return 
     */
    public boolean removeEdge(V vOrig, V vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest))
            return false;

        Edge<V,E> edge = getEdge(vOrig,vDest);

        if (edge == null)
            return false;

        Vertex<V,E> vorig = vertices.get(vOrig);

        vorig.remAdjVert(vDest);
        numEdge--;

        if (!isDirected){
            edge = getEdge(vDest,vOrig);
            if (edge != null){
                Vertex<V,E> vdest = vertices.get(vDest);
                vdest.remAdjVert(vOrig);
                numEdge--;
            }
        }
        return true;
    }


    /**
     * Retorna uma copia do grafo
     * @return 
     */
    public Graph<V,E> clone() {

        Graph<V,E> newObject = new Graph<>(this.isDirected);

        for (V vert : vertices.keySet())
            newObject.insertVertex(vert);

        for (V vert1 : vertices.keySet())
            for (Edge<V,E> e : this.outgoingEdges(vert1))
                if (e != null){
                    V vert2=this.opposite(vert1,e);
                    newObject.insertEdge(vert1, vert2, e.getElement(), e.getWeight());
                }

        return newObject;
    }

    /* equals implementation
     * @param the other graph to test for equality
     * @return true if both objects represent the same graph
     */
    public boolean equals(Object otherObj) {

        if (this == otherObj)
            return true;

        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;

        Graph<V,E> otherGraph = (Graph<V,E>) otherObj;

        if (numVert != otherGraph.numVertices() || numEdge != otherGraph.numEdges())
            return false;

        boolean eqvertex;
        for (V v1 : this.vertices()){
            eqvertex=false;
            for (V v2 : otherGraph.vertices())
                if (v1.equals(v2))
                    eqvertex=true;

            if (!eqvertex)
                return false;
        }
        return true;
    }

    /**
     * Devolve descrição do grafo
     * @return 
     */
    @Override
    public String toString() {
        String s="" ;
        if (numVert == 0) {
            s = "\nGraph not defined!!";
        }
        else {
            s = "Graph: "+ numVert + " vertices, " + numEdge + " edges\n";
            for (Vertex<V,E> vert : vertices.values())
                s += vert + "\n" ;
        }
        return s ;
    }
}