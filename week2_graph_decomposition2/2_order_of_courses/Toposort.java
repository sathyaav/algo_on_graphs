 import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

//Compute a topological ordering of a given directed acyclic graph (DAG) with n vertices and m edges
public class Toposort {
    private static Stack<Integer> toposort(ArrayList<Integer>[] adj) {
    	int len = adj.length;
        boolean used[] = new boolean[len];
        Stack<Integer> order = new Stack<Integer>();
        
        for(int i=0; i<len;i++) {
        	if(!used[i]) {
        		dfs(adj,used,order,i);
        	}
        }
        
        
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, boolean[] used, Stack<Integer> order, int s) {
    	
    	for(int v : adj[s]) {
    		if(!used[v]) 
    			dfs(adj, used,order,v);
    		
    	}
    	used[s] = true;
    	order.push(s);
    	//order.add(0,s);
    	
    	
    	
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
        Stack<Integer> order = toposort(adj);
        while(!order.isEmpty())
        	System.out.print((order.pop()+1)+" ");/*
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }*/	
        scanner.close();
    }
}

