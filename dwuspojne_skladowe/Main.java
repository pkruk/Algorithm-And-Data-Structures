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

	public static void DFS(Graf g){
		for (int i = 0; i < g.len; i++){
			g.V.get(i).color = BIALY;
			g.V.get(i).pi = null;
		} // gotowy do startu!
		///////////////////////////////
		preorder = new int [g.len];
		visited = new boolean [g.len];
		for(int i = 0; i < g.len; visited[i++] = false);
		low = new int [g.len];
		for (int i = 0; i < g.len; low[i++] = 999999);
		//////////////////////////////
		time = 0;
		for (int i = 0; i < g.len; i++)
			if (g.V.get(i).color == BIALY)
				DFS_VISIT(g,g.V.get(i));
	}

	public static void DFS_VISIT(Graf g, Node u){
		dsads;
		time++;
		u.d = time;
		u.color = SZARY;
		for (int i = 0; i < u.sasiedzi.size(); i++) {
			Node v = u.sasiedzi.get(i);
			if (v.color == BIALY){
				v.pi = u;
				DFS_VISIT(g,v); // idziemy dalej ziaa
			}
		}
		u.color = CZARNY;
		time++;
		u.f = time;
		
	}
	public static void low(Graf g){

		for (int i = 0; i < g.len; i++){
			if (!visited[i])
				low(g,i,g.V.get(i).sasiedzi.get(0).index);
		}
	}
	public static int licznik = 1;
	public static void low(Graf g,int v, int parent){
		visited[v] = true;
		low[v] = min(g.V.get(v).d,g.V.get(parent).d);
		System.out.println("v : " + v +" ojciec " + parent );
	}
	public static int min(int a, int b){
		if (a < b)
			return a;
		else 
			return b;
	}
	public static int preorder[];
	public static boolean visited[];
	public static int low[];


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
		sortuj(g);
		System.out.println(g.toString());
		g.printSasiedzi();
		System.out.println("\n\n\n");
		low(g);
		for(int i = 0; i < low.length; i++)
			System.out.print(low[i] + ", ");
		System.out.println();
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
