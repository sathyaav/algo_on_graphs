import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//Given an undirected graph with n vertices and m edges and two vertices u and v, compute the length
//of a shortest path between u and v (that is, the minimum number of edges in a path from u to v).
public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
       Queue<Integer> queue = new LinkedList<Integer>();
       int[] distance = new int[adj.length];
		//Arrays.fill(distance, -1);
		//distance[s]=0;
		queue.add(s);
	   	while(!queue.isEmpty()) {
	   		int v = queue.poll();
	   		for(int u : adj[v]) {
	   			if(distance[u]==0) {
	   				queue.add(u);
	   				distance[u] = distance[v]+1;
	   				if(u==t)
	   					return distance[t];
	   			}
	   		}
	   	}
	   	return -1;
    	
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
        scanner.close();
        System.out.println(distance(adj, x, y));
    }
}

