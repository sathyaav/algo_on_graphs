import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

// prims algorithm
public class ConnectingPoints {
    private static double minimumDistance(ArrayList<Vertex> totalVertex) {
        double result = 0.;
       
        PriorityQueue<Vertex> pQueue = new PriorityQueue<Vertex>();
   		Vertex start = totalVertex.get(0);
   		start.distance=0.;
   		for(Vertex v: totalVertex)
   			pQueue.add(v);
   		
   		while(!pQueue.isEmpty()) {
   			
   			Vertex v = pQueue.poll();
   			for(Edge adjEdge: v.getEdges()) {
   				if(pQueue.contains(adjEdge.to) && adjEdge.to.getDistance() > adjEdge.cost) {
   					changePriority(pQueue,adjEdge.to, adjEdge.cost);
   				}
   			}
   			
   		}
   		
   		
   		for(Vertex v: totalVertex) {
   			result += v.distance;
   		}
   		return result;
        
    }
    
    private static void changePriority(PriorityQueue<Vertex> pQueue, Vertex to, Double cost) {
		pQueue.remove(to);
		to.setDistance(cost);
		pQueue.add(to);
		
	}

	public static class Point{
    	int x;
    	int y;
    	public Point(int x, int y) {
    		this.x =x;
    		this.y = y;
    	}
    }
    public static class Vertex implements Comparable<Vertex>{
		private Point id;
		private Double distance;
		private boolean visited; 
		//private Vertex parent;
		private ArrayList<Edge> edges;
		public Vertex(Point p) {
			//Point p = new Point(x, y);
			this.id = p;
			this.distance = Double.MAX_VALUE;
			this.visited = false;
			this.edges = new ArrayList<Edge>();
		}
		
		public Point getId() {
			return this.id;
		}
		
		public boolean isVisited() {
			return this.visited;
		}
		
		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		
		public void setDistance(Double d) {
			this.distance = d;
		}
		
		public Double getDistance() {
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
			Double diff = this.distance - o.distance;
			if(diff < 0) return -1;
			else if(diff >=0) return 1;
			else return 0;
		}
		
		
		
	}
   	
	public static class Edge {
   		Vertex from;
   		Vertex to;
   		Double cost;
   		public Edge(Vertex from,Vertex to, Double cost) {
   			this.from = from;
   			this.to = to;
   			this.cost = cost;
   			
   		}   		
   	}
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Vertex> totalVertex = new ArrayList<Vertex>(n);
        //int[] x = new int[n];
        //int[] y = new int[n];
        int x,y;
        for (int i = 0; i < n; i++) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            Point p = new Point(x,y);
            Vertex newVertex = new Vertex(p);
           
            for(Vertex v: totalVertex) {
            	double cost = Math.sqrt( Math.pow((v.id.x  - newVertex.id.x), 2) + Math.pow((v.id.y  - newVertex.id.y), 2)    );
            	Edge e = new Edge(v, newVertex, cost);
            	Edge e2 = new Edge(newVertex,v,cost);
            	v.addEdge(e);
            	newVertex.addEdge(e2);
            	
            }
            totalVertex.add(newVertex);
            
        }
        scanner.close();
        System.out.println(minimumDistance(totalVertex));
    }
	
}

