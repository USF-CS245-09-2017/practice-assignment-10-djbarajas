import java.util.LinkedList;
import java.util.List;

public class GraphImplementation implements Graph {
    //values I will need
    int vertices;
    int[][] adjmatrix;
    /*
    * graphimplementation()
    * constructor, initializes nim of vertices
    * and creates adjacency matrix
     */
    public GraphImplementation(int verticies){
        this.vertices=verticies;
        adjmatrix = new int[verticies][verticies];
    }
    /*
    * addEdge()
    * adds a directed edge from vertex 1 to vertex 2
     */
    @Override
    public void addEdge(int v1, int v2) {
        adjmatrix[v1][v2] = 1;
    }
    /*
    * topological sort()
    * creates an incident array and initializes all values to 0
    * then adds up all numbers in adjacency matrix column-wise
    * then creates a list to put the vertices in topological order
    * and loops through the incident array to find a 0 index
    * Once the 0 is found it gets the neighbors and subtracts 1 from the neighbors
    * the index is then added to the list of topological ordered vertices
    * and the 0 is set to -1 to not mess with the ordering of the other vertices
    * finally, the list is returned in topological order
     */
    @Override
    public List<Integer> topologicalSort() {
        int[] incident = new int[vertices];
        for(int i = 0; i < vertices; i++){
            incident[i] = 0;

        }
        //update incident array w/ adj matrix values column-wise
        for(int j = 0; j < adjmatrix.length;j++){
            for(int k = 0; k < adjmatrix.length;k++){
                incident[k] += adjmatrix[j][k];
            }
        }
        List<Integer> scedule = new LinkedList<>();
        for(int j = 0 ; j < vertices; j++){
            for(int h = 0; h < incident.length; h++){
                /*
                 * 1. find an index w/ 0 count
                 * 2. update incident array by count
                 * 3. return index of item with 0 count, set to -1
                 */
                if(incident[h] == 0){
                    int[] neighbors = this.neighbors(h);
                    for(int i = 0; i < neighbors.length;i++){
                        incident[neighbors[i]] -= 1;
                    }
                    scedule.add(h);
                    incident[h] = -1;
                }
            }


        }
        return scedule;
    }
    /*
    * neighbors()
    * goes through the row of a given vertex to add up the size
    * of the neighbors array then creates the array with that size
    * then loops through the row again to find the indexes of the
    * neighboring nodes then adds the indexes to the array and returns
    * that array of neighbors
     */
    @Override
    public int[] neighbors(int vertex) {
        int count = 0;
        for(int i = 0; i < vertices; i++){
            if(adjmatrix[vertex][i] > 0){
                count++;
            }
        }
        int[] neigh = new int[count];
        for(int i = 0, j = 0; i < vertices;i++){
            if(adjmatrix[vertex][i] > 0){
                neigh[j++] = i;
            }
        }
        return neigh;
    }
}
