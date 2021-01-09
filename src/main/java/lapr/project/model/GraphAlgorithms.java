package lapr.project.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author beatr
 */

public class GraphAlgorithms {

    /**
     * Performs breadth-first search of a Graph starting in a Vertex
     * @param g Graph instance
     * @param vert information of the Vertex that will be the source of the search
     * @return qbfs a queue with the vertices of breadth-first search
     */
    public static<V,E> LinkedList<V> BreadthFirstSearch(Graph<V,E> g, V vert){
        if(!g.validVertex(vert)){
            return null;
        }
        LinkedList<V> qbfs = new LinkedList<>();
        LinkedList<V> qaux = new LinkedList<>();

        qaux.add(vert);
        qbfs.add(vert);

        while(!qaux.isEmpty()){
            vert=qaux.remove();
            for (V vAdj : g.adjVertices(vert)){
                if(!qbfs.contains(vAdj)){
                    qbfs.add(vAdj);
                    qaux.add(vAdj);
                }
            }
        }
        return qbfs;
    }

    /**
     * Performs depth-first search starting in a Vertex
     * @param g Graph instance
     * @param vOrig Vertex of graph g that will be the source of the search
     * @param visited set of discovered vertices
     * @param qdfs queue with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        visited[g.getKey(vOrig)] = true;
        qdfs.add(vOrig);
        for (V adjVertice : g.adjVertices(vOrig)) {
            if (!visited[g.getKey(adjVertice)]) {
                DepthFirstSearch(g, adjVertice, visited, qdfs);
            }
        }
    }

    /**
     * @param g Graph instance
     * @param vert information of the Vertex that will be the source of the search
     * @return qdfs a queue with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {
        LinkedList<V> qdfs = new LinkedList<>();
        boolean visited[] = new boolean[g.numVertices()];
        if (!g.validVertex(vert)) {
            return null;
        }
        DepthFirstSearch(g, vert, visited, qdfs);
        return qdfs;
    }

    /**
     * Returns all paths from vOrig to vDest
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vDest Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path stack with vertices of the current path (the path is in reverse order)
     * @param paths ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
                                        LinkedList<V> path, ArrayList<LinkedList<V>> paths) {
        visited[g.getKey(vOrig)] = true;
        path.add(vOrig);
        for (Edge<V,E> edg : g.outgoingEdges(vOrig)) {
            if (edg.getVDest().equals(vDest)) {
                path.add(vDest);
                paths.add(path);
                path.removeLast();
            } else {
                if (!visited[g.getKey(edg.getVDest())]) {
                    allPaths(g, edg.getVDest(), vDest, visited, path, paths);
                }
            }

        }
        visited[g.getKey(vOrig)] = false;
        path.removeLast();
    }

    /**
     * @param g Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from voInf to vdInf
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {
        LinkedList<V> path = new LinkedList<>();
        ArrayList<LinkedList<V>> paths = new ArrayList<>();
        if (g.validVertex(vDest) && g.validVertex(vOrig)) {
            boolean[] visited = new boolean[g.numVertices()];
            allPaths(g, vOrig, vDest, visited, path, paths);
            return paths;
        }
        return null;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights
     * This implementation uses Dijkstra's algorithm
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param visited set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist minimum distances
     */
    protected static<V,E> void shortestPathLength(Graph<V,E> g, V vOrig,
                                                  boolean[] visited, V[] pathKeys, double[] dist){

        int vKey = g.getKey(vOrig);
        dist[vKey] = 0;
        pathKeys[vKey] = vOrig;
        while(vOrig != null){
            vKey = g.getKey(vOrig);
            visited[vKey] = true;
            for (V vAdj : g.adjVertices(vOrig)){
                int vKeyAdj = g.getKey(vAdj);
                Edge<V,E> edge = g.getEdge(vOrig,vAdj);
                if (!visited[vKeyAdj] && dist[vKeyAdj] > dist[vKey]+ edge.getWeight()){
                    dist[vKeyAdj] = dist[vKey] + edge.getWeight();
                    pathKeys[vKeyAdj] = vOrig;
                }
            }
            double minDist = Double.MAX_VALUE;
            vOrig = null;
            for(V vert : g.vertices()){
                int i = g.getKey(vert);
                if(!visited[i] && dist[i] < minDist){
                    minDist = dist[i];
                    vOrig = vert;
                }
            }
        }
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     * @param g Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */
    protected static<V,E> void getPath(Graph<V,E> g, V vOrig, V vDest, V[] pathKeys, LinkedList<V> path){

        if(vOrig.equals(vDest))
            path.push(vOrig);
        else{
            path.push(vDest);
            int vKey = g.getKey(vDest);
            vDest= pathKeys[vKey];
            getPath(g,vOrig,vDest,pathKeys,path);
        }
    }

    //shortest-path between vOrig and vDest
    public static<V,E> double shortestPath(Graph<V,E> g, V vOrig, V vDest, LinkedList<V> shortPath){

        if(!g.validVertex(vOrig) || !g.validVertex(vDest)){
            return 0;
        }
        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts];
        V[] pathKeys = (V [])Array.newInstance(vOrig.getClass(),nverts);
        double[] dist = new double[nverts];
        for (int i = 0; i < nverts; i++){
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = null;
        }

        shortestPathLength(g,vOrig,visited,pathKeys,dist);

        double lengthPath = dist[g.getKey(vDest)];
        if(lengthPath != Double.MAX_VALUE){
            getPath(g,vOrig,vDest,pathKeys,shortPath);
            return lengthPath;
        }
        return 0;
    }

    //shortest-path between voInf and all other
    public static<V,E> boolean shortestPaths(Graph<V,E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList<Double> dists){

        if(!g.validVertex(vOrig)) return false;

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts];
        V[] pathKeys = (V [])Array.newInstance(vOrig.getClass(),nverts);
        double[] dist = new double[nverts];

        for(int i = 0; i < nverts ; i++){
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = null;
        }

        shortestPathLength(g,vOrig,visited,pathKeys,dist);

        dists.clear(); paths.clear();
        for (int i = 0;i < nverts; i++){
            paths.add(null);
            dists.add(Double.MAX_VALUE);
        }

        for (V vDst : g.vertices()){
            int i = g.getKey(vDst);
            if (dist[i] != Double.MAX_VALUE){
                LinkedList<V> shortPath = new LinkedList<>();
                getPath(g,vOrig,vDst,pathKeys,shortPath);
                paths.set(i,shortPath);
                dists.set(i,dist[i]);
            }
        }
        return true;
    }


    /**
     * Reverses the path
     * @param path stack with path
     */
    private static<V,E> LinkedList<V> revPath(LinkedList<V> path){

        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty())
            pathrev.push(pathcopy.pop());

        return pathrev ;
    }
}
