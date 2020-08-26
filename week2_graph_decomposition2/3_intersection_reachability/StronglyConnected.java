import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

//Compute the number of strongly connected components of a given directed graph with n vertices and m edges.
// kosaraju algorithm
public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        int len =adj.length;
    	
        //reverse edge 
        ArrayList<Integer>[] reversedGraph = reverseEdges(adj);
    	Stack<Integer> order = new Stack<Integer>();
    	boolean used[] = new boolean[len];
    	for(int i=0 ;i < len; i++) {
    		if(!used[i]) {
    			used[i] = true;
    			dfs(reversedGraph, used, order, i);
    		}
    		
    	}
    	
    	int count=0;
    	used = new boolean[len];
    	while(!order.isEmpty()) {
    		int v = order.pop();
    		//Stack<Integer> visitedNodes = new Stack<Integer>();
    		if(!used[v]) {
    			count++;
    			explore(adj, used, v);
    			
    			/*
    			 * 	exploreWithFoundNodes(adj, used, visitedNodes , v);
    				System.out.println("\nSCC: "+ count+" : ");
    				while(!visitedNodes.isEmpty())
    					System.out.print((visitedNodes.pop()+1)+" ");
    			*/
    		}
    	}
    	
    	return count;
    }
    
    
    
    private static void explore(ArrayList<Integer>[] adj, boolean[] used, int startVertex) {
    	used[startVertex] = true;
		for(int v: adj[startVertex] ) {
			if(!used[v])
				explore(adj, used,v);
		}	
    }
    /*
    private static void exploreWithFoundNodes(ArrayList<Integer>[] adj, boolean[] used, Stack<Integer> visitedNodes, int startVertex) {
		used[startVertex] = true;
		for(int v: adj[startVertex] ) {
			if(!used[v])
				explore(adj, used,visitedNodes,v);
		}
		//visitedNodes.push(startVertex);
		
	}

    */

	private static void dfs(ArrayList<Integer>[] adj, boolean[] used, Stack<Integer> order, int s) {
		used[s] = true;
    	for(int v : adj[s]) {
    		if(!used[v]) 
    			dfs(adj, used,order,v);
    		
    	}
    	//used[s] = true;
    	order.push(s);
    	//order.add(0,s);
    	
    	
    	
    }

    private static ArrayList<Integer>[] reverseEdges(ArrayList<Integer>[] adj) {
		int len = adj.length;
		ArrayList<Integer>[] reversedGraph = (ArrayList<Integer>[])new ArrayList[len];
        for (int i = 0; i < len; i++) {
        	reversedGraph[i] = new ArrayList<Integer>();
        }
    	for(int i=0; i<len ;i++) {
    		for(int v: adj[i])
    			reversedGraph[v].add(i);
    	}
    	
    	
		return reversedGraph;
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
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

