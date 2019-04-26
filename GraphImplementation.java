import java.util.LinkedList;
import java.util.List;

public class GraphImplementation implements Graph {
    int vertices;
    int[][] adjmatrix;
    public GraphImplementation(int verticies){
        this.vertices=verticies;
        adjmatrix = new int[verticies][verticies];
    }
    @Override
    public void addEdge(int v1, int v2) {
        adjmatrix[v1][v2] = 1;
    }

    @Override
    public List<Integer> topologicalSort() {
        int[] incident = new int[vertices];
        for(int i = 0; i < vertices; i++){ //Arrays.fill(incident,0); this works instead of loop
            incident[i] = 0;

        }
        //update incident array w/ adj matrix values column-wise
        for(int j = 0; j < adjmatrix.length;j++){
            for(int k = 0; k < adjmatrix.length;k++){
                incident[j] += adjmatrix[j][k];
            }
        }
        List<Integer> scedule = new LinkedList<>();
        for(int j = 0 ; j < vertices; j++){
            /*
             * 1. find an index w/ 0 count
             * 2. update incident array by count
             * 3. return index of item with 0 count, set to -1
             */
            scedule.add(j);
        }
        return scedule;
    }

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
