import java.util.*;
import java.io.*;


public class Main { 
	public static void main (String [] args) throws IOException
	{
		System.out.println("START");
		//test();
		konsola();
		System.out.println("END" + ANSI_RESET);
	}

	public static int time = 0;
	public static boolean visited[];
	public static int nr[];
	public static int low[];
	public static ArrayList <Integer> punktyArtykulacji = new ArrayList<Integer>();
	public static LinkedList <Integer> dwuspojna = new LinkedList<Integer>();
	public static ArrayList<Integer> mosty = new ArrayList <Integer>();
	public static ArrayList<Integer> spojne = new ArrayList<Integer>();

	public static void init(Graf g){
		visited = new boolean[g.len]; // tablica statusu odwiedzony czy nie
		nr = new int [g.len]; // tablica nr czasow
		low = new int [g.len]; // tablica "wysokosci"
		time = 0;
		for(int i = 0; i < g.len; low[i] = -1, nr[i] = -1, visited[i] = false,++i);
		spojne.removeAll(spojne);
		mosty.removeAll(mosty);
		punktyArtykulacji.removeAll(punktyArtykulacji);
		while (!dwuspojna.isEmpty())
			dwuspojna.removeFirst();
	}
	public static void wyswietl(){
		//spojne skladowe 
		System.out.println("SPOJNE : ");
		for (int i = 0; i < spojne.size(); i++)
			if (spojne.get(i) != -1)
				System.out.print(spojne.get(i) + " ");
			else
				System.out.println();
		//END Spojne skladowe
		System.out.println("DWUSPOJNA");
		Collections.sort(dwuspojna);
		if (dwuspojna.size() > 1){
			int x = dwuspojna.remove(0);
			int y = 0;
			while(!dwuspojna.isEmpty()){
				y = dwuspojna.remove(0);
				for (int i = x; i < y; i++)
					System.out.print((i+1) + " ");
				x = y;
				System.out.println();
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("MOSTY : ");
		//wysweitlanie mostow
		for (int i = 0; i < mosty.size(); i++)
			if (mosty.get(i) != -1) 
				System.out.print(mosty.get(i) + " ");
			else
				System.out.println();
		//punkty artykulacji : 
		for (int i = 0; i < punktyArtykulacji.size(); i++)
			System.out.print(punktyArtykulacji.get(i) + " ");
		System.out.println("END");
	}
	public static void DFS(Graf g){
		//DFS(g);
		init(g);
		for(int i = 0; i < g.len; i++)
			if (!visited[i] ) {
				spojne.add(i+1);
				if (g.V.get(i).sasiedzi.size() >= 2){
					dwuspojna.addLast(i);
					//punktyArtykulacji.add(i+1);
				}
				DFS_BRIDGES(g,i,-1);
				spojne.add(-1);
			}
			dwuspojna.addLast(g.V.size());
			wyswietl();
	}
	public static int j = 0;
	public static Deque <Integer> s = new Deque<Integer>();
	public static void DFS_BRIDGES(Graf g, int v, int ojciec_v){
		visited[v] = true;
		time++;
		nr[v] = time; low[v] = time; // ustawiamy odpowiednio low
		//konczac dfs, wszystkie lowy sa rowne czasowi dojscia dfs
		for (int i = 0; i < g.V.get(v).sasiedzi.size(); i++){
			int u = g.V.get(v).sasiedzi.get(i).index;
			if ( u != ojciec_v )
				if( !visited[u]){
					spojne.add(u+1);
					s.addFirst(u);
					DFS_BRIDGES(g,u,v); //Normalny dfs do samego konca
					if(ojciec_v != -1 && low[u] >= nr[v]) {
					//	System.out.println("pkt artykulacji" + (v+1));
						punktyArtykulacji.add(v+1);
						dwuspojna.addLast(v+1);
						System.out.println("U : " + u +  " " + v);
						if(s.size() > 2);
							System.out.println(s.toString());
						while(!s.isEmpty() && s.getLast() != u){
							s.removeLast();
						}
						System.out.println(ANSI_RED + s.toString() + ANSI_RESET);
						System.out.println();
					}

					low[v] = min(low[v],low[u]); // Cofamy sie wybierajac mniejsze
				}else {
					low[v] = min(low[v],nr[u]); /// krawedz zwrotna 
				}
		}
		if(low[v] == nr[v] && ojciec_v != -1) {// d(u) <= low(v) 
			mosty.add(ojciec_v+1); mosty.add(v+1); mosty.add(-1);
		}
	}

	public static void DFS_VISIT(Graf g, int u){
		visited[u] = true;  
		time++;
		g.V.get(u).d = time; nr[u] = time;
		for(int i = 0; i < g.V.get(u).sasiedzi.size(); i++)
			if (!visited[g.V.get(u).sasiedzi.get(i).index])
				DFS_VISIT(g,g.V.get(u).sasiedzi.get(i).index);
	}
	public static int min(int a, int b){
		if (a <= b)
			return a;
		else 
			return b;
	}

	public static void test(){
		Graf g = new Graf();
		for (int i = 0; i < 7; i++)
			g.add(i);
		int tab[] = {2,3};
		g.dodajSasiadow(0, tab);	
		int tab1[] = {1,3};
		g.dodajSasiadow(1,tab1);
		int tab2[] = {1,2,4};
		g.dodajSasiadow(2,tab2);
		int tab3[] = {3,5,7};
		g.dodajSasiadow(3,tab3);
		int tab4[] = {4,6};
		g.dodajSasiadow(4,tab4);
		int tab5[] = {5,7};
		g.dodajSasiadow(5,tab5);
		int tab6[] = {4,6};
		g.dodajSasiadow(6,tab6);
		DFS(g);
	}
	public static void konsola(){
		System.out.println("KONSOLA : ");
		Scanner in = new Scanner(System.in);
		int rozmiar = in.nextInt();
		Graf g = new Graf();
		for(int i = 0; i < rozmiar; i++)
			g.add(i);
		for(int i = 0; i < rozmiar; i++){
			int len = in.nextInt();
			int tab[] = new int[len];
			for(int j = 0; j < len; j++){
				tab[j] = in.nextInt();
			}
			g.dodajSasiadow(i,tab);
		}
		System.out.println(".................................................................");
		DFS(g);
		
	}

	public static void  sortuj(Graf g){
		for(int i = 1; i < g.len; i++){
			int tmp = g.V.get(i).d;
			Node t = g.V.get(i);
			int j = i;
			while ((j > 0) && (g.V.get(j-1).d  < tmp)){
				g.V.set(j,g.V.get(j-1));
				--j;
			}
			g.V.set(j,t);
		}
	}



	public static final char BIALY = 'W';
	public static final char CZARNY = 'B';
	public static final char SZARY = 'G';
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RED = "\u001B[31m";


}
