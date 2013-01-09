import java.util.*;
import java.io.*;


public class Main { 
	public static void main (String [] args) throws IOException
	{
		System.out.println(ANSI_BLUE + "HELLO");
		test();
		System.out.println("END" + ANSI_RESET);
	}

	public static int time = 0;
	public static boolean visited[];
	public static int nr[];
	public static int low[];
	public static void DFS(Graf g){
		visited = new boolean[g.len];
		nr = new int [g.len];
		low = new int [g.len];
		for(int i = 0; i < g.len; low[i] = -1, nr[i] = -1, visited[i] = false,++i);
		//DFS(g);
		DFS_BRIDGES(g,0,-1);
	}

	public static void DFS_BRIDGES(Graf g, int v, int ojciec_v){
		visited[v] = true;
		time++;
		nr[v] = time; g.V.get(v).d = time; low[v] = time;
		for (int i = 0; i < g.V.get(v).sasiedzi.size(); i++){
			int u = g.V.get(v).sasiedzi.get(i).index;
			if ( u != ojciec_v )
				if( !visited[u]){
					DFS_BRIDGES(g,u,v);
					System.out.println((v+1) + " " + (u +1));
					low[v] = min(low[v],low[u]);
				}else {
					System.out.println("****" + (v+1) + " " + (u+1));
					low[v] = min(low[v],nr[u]);
				}
		}
		if(low[v] == nr[v] && ojciec_v != -1)
			System.out.println("Most : " + (v+1) + " --- " + (ojciec_v+1));
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
		g.printSasiedzi();
		System.out.println(g.toString());
		DFS(g);
	}
	public static void konsola(){
		Scanner in = new Scanner(System.in);
		int rozmiar = in.nextInt();
		System.out.println("rozmiar grafu" + rozmiar);
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
		System.out.println(".................................");
		g.printSasiedzi();
		System.out.println(".................................................................");
		System.out.println(g.toString() + "\n");
		
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
