import java.util.*;

public class DFS {
	private int l = 0;
	public String poczynaj(){

		spojne.add(new ArrayList<Integer>());
		for (int i = 0; i < g.len; i++){
			if (visited[i] == false){
				spojne.get(l).add(i);
				if (g.sasiedzi.get(i).size() > 1){
					pktArtykulacji.add(i+1);
				}
				dfs(i);
				++l;
				spojne.add(new ArrayList<Integer>());
			}
		}
		System.out.println();
		return wynik();
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
				spojne.get(l).add(u); // nieodwiedzone
				dfs(u);
				if (low[u] >= nr[v] &&  !IN(pktArtykulacji,v+1)){
					// System.out.println("~" + (v)); // pkt artykulacji 
					pktArtykulacji.add(v+1);
				}
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
						if(!IN(dwuspojne.get(x),tmp.from+1))
							dwuspojne.get(x).add(tmp.from +1);
					} while(tmp.from != v || tmp.to != u);
					if (waga == 1){
						//System.out.println(dwuspojne.get(x).get(0) + " " + g.V.get(dwuspojne.get(x).get(0)-1).ojciec +"<-- MOST ");
						mosty.add(dwuspojne.get(x).get(0));
						mosty.add(g.V.get(dwuspojne.get(x).get(0)-1).ojciec);
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
	public String wynik(){
		System.out.println("===============================================");
		String wyjscie = "";
		for(int i = 0; i < spojne.size(); i++){
			Collections.sort(spojne.get(i));
			for (int j = 0; j < spojne.get(i).size(); j++)
				wyjscie += (spojne.get(i).get(j) +1 ) + " ";
			wyjscie += "\n";
		}
		wyjscie += "\n";
		for (int i = 0; i < dwuspojne.size(); i++){
			if (dwuspojne.get(i).size() > 2){
				for (int j = dwuspojne.get(i).size() -1 ; j >= 0; j--){
					wyjscie += dwuspojne.get(i).get(j) + " ";
				}
				wyjscie += "\n";
			}
		}
		wyjscie += "\n";
		for (int i = 0; i < mosty.size(); i++){
			if (i % 2 == 0 && i != 0)
				wyjscie += "\n";
			wyjscie += mosty.get(i) + " ";
		}
		wyjscie += "\n";
		Collections.sort(pktArtykulacji);
		for (int i = 0; i < pktArtykulacji.size(); i++)
			wyjscie += pktArtykulacji.get(i) + " ";
		//System.out.println(wyjscie);
		return wyjscie;
	}
	public boolean IN(ArrayList<Integer> x , int k){
		for (int i = x.size() -1; i >= 0; i--)
			if ( x.get(i) == k)
				return true;
		return false;
	}
	private ArrayList<ArrayList<Integer>> spojne = new ArrayList<ArrayList<Integer>>();
	private ArrayList <Integer> pktArtykulacji = new ArrayList<Integer>();
	private ArrayList<Integer> mosty = new ArrayList<Integer>();
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