import java.util.ArrayList;
import java.util.Scanner;
//Given an undirected graph and two distinct vertices u and v, check if there is a path between u and v
public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        boolean[] visited = new boolean[adj.length];
        return checkPath(adj,visited,x,y);
    	
    	
        //return 0;
    }


    private static int checkPath(ArrayList<Integer>[] adj, boolean[] visited, int x, int y) {
		visited[x] = true;
		for(int v : adj[x]) {
			if(v  == y)
				return 1;
			if(!visited[v]) {
				if(checkPath(adj,visited, v,y)==1)
					return 1;
			}
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}

