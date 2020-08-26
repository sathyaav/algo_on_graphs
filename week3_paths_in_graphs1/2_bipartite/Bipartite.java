import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        Queue<Integer> queue = new LinkedList<Integer>();
    	Integer[] values = new Integer[adj.length];
    	
    	
    	
    	for(int i=0; i<adj.length; i++) {
    		if(values[i]==null) {
    			queue.add(i);
            	values[i] = 1;
        		
            	while(!queue.isEmpty()) {
            		int v = queue.poll();
            		for(int u: adj[v]) {
            			if(values[u]==null) {
            				queue.add(u);
            				values[u] = (values[v]==0)?1:0;
            			}else if(values[u]== values[v]) {
            				return 0;
            			}
            		}
            	}
    		}
    	}
    	
        
        
    	
        return 1;
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
        System.out.println(bipartite(adj));
    }
}

