import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Dijkstra {
    private static long distance(ArrayList<Edge>[] adj,  int s, int t, ArrayList<Edge> edges) {
        
    	
    	//long ans1 = findDistanceBellman(adj, s, t, edges);
    	
    	//return ans1;
    	return findDistanceDijsktra(adj, s, t);
    }
    
    private static Long findDistanceBellman(ArrayList<Edge>[] adj,  int s, int t, ArrayList<Edge> edges) {
    	Long[] distance = new Long[adj.length];
    	Arrays.fill(distance, Long.MAX_VALUE);
    	
    	//Edge start = new Edge(s,s,0l);
    	distance[s] = 0l;
    	
    	    	
    	for(int i=0; i<adj.length;i++) {
    		for(Edge edge : edges) {
    			 Relax(edge.from, edge.to, edge.cost,distance);
    		}
    	}
    	
    	return (distance[t]==Long.MAX_VALUE)?-1:distance[t];
    		
    }
    
    
   	private static boolean Relax(int from, Integer to, Long cost, Long[] distance) {
		boolean flag = false;
		
		if(distance[from]==Long.MAX_VALUE)
			return false;
		
   		if(distance[to] > distance[from]+cost) {
   			flag = true;
			distance[to] = distance[from]+cost;
		}
   		return flag;
		
	}

	private static Long findDistanceDijsktra(ArrayList<Edge>[] adj, int s, int t) {
   		
		boolean visited[] = new boolean[adj.length];
   		PriorityQueue< Edge> pQueue = new PriorityQueue<Edge>();
   		Queue<Edge> queue = new  LinkedList<Edge>();
   		
   		Long[] distance = new Long[adj.length];
    	
    	Edge start = new Edge(s,s,0l);
    	distance[s] = 0l;
    	
    	queue.add(start);
   		while(!queue.isEmpty()) {
    		Edge e = queue.poll();
    		int v = e.to;
    		if(visited[v])
    			 continue;
    		visited[v] = true;
    		
    		for(Edge adjEdge: adj[v]) {
    			pQueue.add(adjEdge);
    		}
    		
    		while(!pQueue.isEmpty()) {
    			Edge adjV= pQueue.poll();
    			int adjvertex = adjV.to;
    			
    			if( distance[adjvertex]==null || distance[adjvertex] > distance[v] + adjV.cost) {
					distance[adjvertex] = distance[v] + adjV.cost;
					queue.add(adjV);
					visited[adjvertex] = false;
	    		}
    		}    		
    	}
   		return (distance[t]==null)?-1:distance[t];
		
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
		int test=0;
		if(test==1) {
		  stressTest();
		}
		else {
		
		Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Edge>[] adj = (ArrayList<Edge>[])new ArrayList[n];
       // ArrayList<Edge> edges = new ArrayList<Edge>(m);
        //ArrayList<Edge>[] cost = (ArrayList<Edge>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Edge>();
            //cost[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            Long w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextLong();
            
            
            if(x>n || y >n) {
            	 System.out.println("-1");
            	 return;
            }
            	
            
            Edge e = new Edge(x-1,y-1,w);
           // edges.add(e);
            adj[x - 1].add(e);
            //cost[x - 1].add(e);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        scanner.close();
        //System.out.println(distance(adj, x, y,edges));
        System.out.println(findDistanceDijsktra(adj, x, y));
		}
        //*/
    }
	
	
	static boolean stressTest() {
		Random r = new Random();
		while(true) {
			int tv = (r.nextInt(20) & Integer.MAX_VALUE )+1;
			int te = (r.nextInt(20) & Integer.MAX_VALUE)+1;
			 ArrayList<Edge>[] adj = (ArrayList<Edge>[])new ArrayList[tv];
		     ArrayList<Edge> edges = new ArrayList<Edge>();
		     for (int i = 0; i < tv; i++) {
		            adj[i] = new ArrayList<Edge>();
		            //cost[i] = new ArrayList<Edge>();
		    }
		     System.out.println(tv+" : "+te);
			for(int i=0 ; i<te;i++) {
				int from = r.nextInt(tv) & Integer.MAX_VALUE;
				int to = r.nextInt(tv) & Integer.MAX_VALUE;
				long cost = r.nextLong() & 10 ;
				System.out.println(""+(from+1)+" "+(to+1)+" "+cost);
				Edge e = new Edge(from,to,cost);
				edges.add(e);
				adj[from].add(e);
				
			}
			int start = r.nextInt(tv)  & Integer.MAX_VALUE;
			int end = r.nextInt(tv)  & Integer.MAX_VALUE;
			System.out.println((start+1)+" "+(end+1));
			Long ans1 = findDistanceBellman(adj, start, end, edges);
			Long ans2 = findDistanceDijsktra(adj, start, end);
			System.out.println("ans: "+ ans1+" -- "+ans2);
			System.out.println("**************");
			if(!ans1.equals(ans2)) {
				System.out.println(" s: "+start+" e: "+end+" "+ ans1+" : "+ ans2);
				return false;
			}
		}
		//return true;
	}


}

