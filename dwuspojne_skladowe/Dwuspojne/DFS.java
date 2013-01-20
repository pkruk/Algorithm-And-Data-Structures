import java.util.*;

public class DFS {

	public void poczynaj(){
		for (int i = 0; i < g.len; i++)
			if (visited[i] == false)
				dfs(i);
	}

	public void dfs(int v){
		low[v] = nr[v] = ++time;
		visited[v] = true;
		// nr czas wejscia;
		for (int i = 0; i < g.sasiedzi.get(v).size(); i++){
			int u = g.sasiedzi.get(v).get(i); // sasiad;
			Edge tmp = new Edge(v,u); 
			if (visited[u] == false){
				p[u] = v; // poprzednik :D 
				S.push(tmp);
				dfs(u);
				if (low[u] >= nr[v] && (g.sasiedzi.get(v).size() == 1 || g.sasiedzi.get(v).size() > 2))
					System.out.println("~" + (v));
				if (low[u] >= nr[v]){
					//Wyznaczamy Dwuspojne skladowe wzdlug punktow artykulacji 
					int waga = 0;
					dwuspojne.add(new ArrayList<Integer>());

					do { 
						tmp = S.peek();
						S.pop();
						waga++;
						//System.out.print((tmp.from +1)+ " -- " + (tmp.to+1) + " ");
						g.V.get(tmp.from).ojciec = tmp.to +1;
						dwuspojne.get(x).add(tmp.from +1);
					} while(tmp.from != v || tmp.to != u);
					if (waga == 1)
						System.out.println(dwuspojne.get(x).get(0) + " " + g.V.get(dwuspojne.get(x).get(0)-1).ojciec +"<-- MOST ");
					else {
						for (int j = 0; j < dwuspojne.get(x).size(); j++){
							System.out.print(dwuspojne.get(x).get(j) + " ");
						}
						System.out.println();
					}
					x++;

				} else 
					low[v] = Math.min(low[v],low[u]);
			} else if( p[v] != u && nr[v] > nr[u]) { //gdy nieodwiedzony
				S.push(tmp);
				low[v] = Math.min(low[v],nr[u]);
			}
		}
	}

	public DFS(Graf g){
		this.g = g;
		nr = new int[g.len];
		low = new int [g.len];
		visited = new boolean[g.len];
		p = new int[g.len];
		for(int i = 0; i < g.len; nr[i] = -1,visited[i] = false, low[i] = -1, ++i);
	}
	private int x = 0;
	private int time = 0;
	private boolean visited[];
	private int nr[];
	private Stack<Edge> S = new Stack<Edge>(); // na krawedzie 
	private int p[];
	private int low[];
	private ArrayList<ArrayList<Integer>> dwuspojne = new ArrayList<ArrayList<Integer>>();
	private Graf g;
}