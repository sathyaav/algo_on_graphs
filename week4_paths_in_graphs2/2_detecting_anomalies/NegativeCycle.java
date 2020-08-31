import java.util.ArrayList;
import java.util.Scanner;

// Given an directed graph with possibly negative edge weights and with n vertices and m edges, check
// whether it contains a cycle of negative weight.
public class NegativeCycle {
    private static int negativeCycle(ArrayList<Edge>[] adj, ArrayList<Edge> edges) {
        // write your code here
        return findNegativeCycleBellman(adj,edges);
    }
    
    private static int findNegativeCycleBellman(ArrayList<Edge>[] adj, ArrayList<Edge> edges) {
    	Long[] distance = new Long[adj.length];
    	distance[0] = 0l;
    	
    	
    	for(int i=0; i<adj.length;i++) {
    		boolean flag= false;
    		for(Edge edge : edges) {
    			if(Relax(edge.from, edge.to, edge.cost,distance))
    				 flag=true;
    		}
    		if(!flag) // if no update in the vertices then there is no negative cycle
    			return 0;
    	}
    	
    	for(int i=0; i<adj.length;i++) {
    		for(Edge edge : edges) {
    			 if(Relax(edge.from, edge.to, edge.cost,distance))
    				 return 1;
    		}
    	}
    	return 0;
    		
    }
    
    
   	private static boolean Relax(int from, Integer to, Long cost, Long[] distance) {
		boolean flag = false;
		
		if(distance[from]==null)
			distance[from]=0l;
		if(distance[to]==null)
			distance[to]=0l;
		
   		if(distance[to] > distance[from]+cost) {
   			flag = true;
			distance[to] = distance[from]+cost;
		}
   		return flag;
		
	}
   	
   	public static class Edge implements Comparable<Edge>{
   		Integer from;
   		Integer to;
   		Long cost;
   		public Edge(Integer from,Integer to, Long cost) {
   			this.from = from;
   			this.to = to;
   			this.cost = cost;
   			
   		}
		@Override
		public int compareTo(Edge o) {
			Long diff = this.cost - o.cost;
			if(diff < 0) return -1;
			else if(diff >=0) return 1;
			else return 0;
			//return this.cost - o.cost;
		}
   		
   		
   	}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Edge>[] adj = (ArrayList<Edge>[])new ArrayList[n];
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Edge>();
           // cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            Long w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextLong();
            
            Edge e = new Edge(x-1,y-1,w);
            edges.add(e);
            adj[x - 1].add(e);
           // adj[x - 1].add(y - 1);
            //cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, edges));
    }
}

