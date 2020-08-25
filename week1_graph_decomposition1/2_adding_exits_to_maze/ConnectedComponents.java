import java.util.ArrayList;
import java.util.Scanner;

//Given an undirected graph with n vertices and m edges, compute the number of connected components in it.
public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        boolean[] visited = new boolean[adj.length];
        for(int i=0; i<adj.length;i++) {
        	if(!visited[i]) {
        		result++;
        		
        		findConnectedComponents(adj,visited,  i);
        		//System.out.println();
        	}
        }
        
        return result;
    }

    private static int findConnectedComponents(ArrayList<Integer>[] adj, boolean[] visited, int vertex) {
    	visited[vertex] = true;
    	//System.out.print(" "+vertex);
		for(int v : adj[vertex]) {
			if(!visited[v])
				findConnectedComponents(adj,visited, v);
		}
		
    	
    	
		return 0;
	}

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        scanner.close();
        System.out.println(numberOfComponents(adj));
    }
}

