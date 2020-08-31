import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Dijkstra {
    /* for stress testing
    private static Long findDistanceBellman(ArrayList<Edge>[] adj,  int s, int t, ArrayList<Edge> edges) {
    	Long[] distance = new Long[adj.length];
    	Arrays.fill(distance, Long.MAX_VALUE);
    	
    	distance[s] = 0l;
    	
    	    	
    	for(int i=0; i<adj.length;i++) {
    		for(Edge edge : edges) {
    			// Relax(edge.from, edge.to, edge.cost,distance);
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
    */
	
   	private static Long findDistanceDijsktra(HashMap<Integer, Vertex> graph, int s, int t) {
   		PriorityQueue<Vertex> pQueue = new PriorityQueue<Vertex>();
   		
   		graph.get(s).setDistance(0l);
   		pQueue.add(graph.get(s));
   		
   		while(!pQueue.isEmpty()) {
   			
   			Vertex v = pQueue.poll();
   			if(v.isVisited())
   				continue;
   			
   			v.setVisited(true);
   			
   			for(Edge adjEdge: v.getEdges()) {
   				if(adjEdge.to.getDistance() > adjEdge.from.getDistance()+adjEdge.cost) {
   					adjEdge.to.setDistance(adjEdge.from.getDistance()+adjEdge.cost);
   					pQueue.add(adjEdge.to);
   				}
   			}
   			
   		}
   		return (graph.get(t).distance == Long.MAX_VALUE)?-1:graph.get(t).distance;
   	}
   	
	public static class Vertex implements Comparable<Vertex>{
		private int id;
		private Long distance;
		private boolean visited; 
		private ArrayList<Edge> edges;
		public Vertex(int id) {
			this.id = id;
			this.distance = Long.MAX_VALUE;
			this.visited = false;
			this.edges = new ArrayList<Dijkstra.Edge>();
		}
		
		public int getId() {
			return this.id;
		}
		
		public boolean isVisited() {
			return this.visited;
		}
		
		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		
		public void setDistance(Long d) {
			this.distance = d;
		}
		
		public Long getDistance() {
			return this.distance;
		}
		
		public void addEdge(Edge e) {
			this.edges.add(e);
		}
		
		public ArrayList<Edge> getEdges(){
			return this.edges;
		}
		
		@Override
		public int compareTo(Vertex o) {
			Long diff = this.distance - o.distance;
			if(diff < 0) return -1;
			else if(diff >=0) return 1;
			else return 0;
		}
		
		
		
	}
   	
	public static class Edge {
   		Vertex from;
   		Vertex to;
   		Long cost;
   		public Edge(Vertex from,Vertex to, Long cost) {
   			this.from = from;
   			this.to = to;
   			this.cost = cost;
   			
   		}   		
   	}


	public static void main(String[] args) {
		int test=0;
		if(test==1) {
		 // stressTest();
		}
		else {
		
		Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        HashMap<Integer, Vertex> graph = new HashMap<Integer, Vertex>(n);
        
        for (int i = 0; i < n; i++) {
        	Vertex v = new Vertex(i);
            graph.put(i, v);
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
            	
            
            Edge e = new Edge(graph.get(x-1),graph.get(y-1),w);
            graph.get(x-1).addEdge(e);
           // edges.add(e);
           // adj[x - 1].get(i);
            //cost[x - 1].add(e);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        scanner.close();
        //System.out.println(distance(adj, x, y,edges));
        System.out.println(findDistanceDijsktra(graph, x, y));
		}
        //*/
    }
	
	/*
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
*/

}

