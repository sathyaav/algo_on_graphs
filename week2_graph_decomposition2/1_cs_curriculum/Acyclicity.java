import java.util.ArrayList;
import java.util.Scanner;

//Check whether a given directed graph with n vertices and m edges contains a cycle.
public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
    	int result = 0;
    	int len = adj.length;
        boolean[] visited = new boolean[len];
        for(int i=0; i<len;i++) {
        	if(!visited[i]) {
        		if(findCycle(adj, visited, new boolean[len],  i, result))
        			return 1;
        		visited[i] = true;
        		
        	}
        }
        
        return 0;
    }
    
    private static boolean findCycle(ArrayList<Integer>[] adj, boolean[] visited,boolean[] trackVisited, int vertex, int cycle) {
    	if(trackVisited[vertex]) // cycle
    		return true; 
    	if(visited[vertex])
    		return false;
    	
    	trackVisited[vertex] = true;
    	visited[vertex] = true;
		for(int v : adj[vertex]) {
			if(findCycle(adj,visited,trackVisited, v, cycle)) {
				return true;
			}
		}
		trackVisited[vertex] = false;
    	
    	
		return false;
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
        }
        scanner.close();
        System.out.println(acyclic(adj));
    }
}

