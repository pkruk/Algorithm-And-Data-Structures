import java.util.*;
import java.io.*;


public class Main { 
	public static void main (String [] args) throws IOException
	{
		System.out.println(ANSI_BLUE + "HELLO");
		test();
		System.out.println("*************************************************************");
		konsola();
		System.out.println("END" + ANSI_RESET);
	}

	public static void strongly_connected_components(Graf g){
		DFS(g);
		System.out.println(g.toString() + "\n\n");
		//transponowany!
		Graf gt = new Graf();
		for(int i = 0; i < g.len; i++)
			gt.add(g.get(i).index);
		for(int i = 0; i < g.len; i++)
			gt.V.set(i,g.get(i));
		gt.printSasiedzi();
		sortuj(gt);
		DFS(gt);
		System.out.println("\n" + gt.toString());
		
	}
	

	public static int time;

	public static void DFS(Graf g){
		for (int i = 0; i < g.len; i++){
			Node u = g.get(i);
			u.color = BIALY;
			u.pi = null;
		}
		time = 0;
		System.out.println("Start DFS : " + time);
		for(int i = 0; i < g.len; i++){
			Node u = g.get(i);
		//	System.out.println(g.toString());
			if (u.color == BIALY){
				System.out.println("U : " + u.index);
				DFS_VISIT(g,u);
			}
			//System.out.println(g.toString());
		}
	}	

	public static void DFS_VISIT(Graf g, Node u){
		++time;
		u.d = time;
		u.color = SZARY;
	//	System.out.println(g.toString());
		for (int i = 0; i < u.sasiedzi.size(); i++){
			Node v = u.sasiedzi.get(i);
			if ( v.color == BIALY ){
				v.pi = u;
				DFS_VISIT(g,v);
			}
		}	
		u.color = CZARNY;
		++time;
		u.f = time;
	}
	
public static boolean debug  = false;
	public static void test(){
		Graf g = new Graf();
		for (int i = 0; i < 7; i++)
			g.add(i);
		int tab[] = {2,7};
		g.dodajSasiadow(0, tab);	
		int tab1[] = {1,3,6,7};
		g.dodajSasiadow(1,tab1);
		int tab2[] = {2,4,5};
		g.dodajSasiadow(2,tab2);
		int tab3[] = {3,5};
		g.dodajSasiadow(3,tab3);
		int tab4[] = {3,4};
		g.dodajSasiadow(4,tab4);
		int tab5[] = {2,7};
		g.dodajSasiadow(5,tab5);
		//int tab6[] = {3,1,2,6};
		int tab6[] = {1,2,3,6};
		g.dodajSasiadow(6,tab6);
		g.printSasiedzi();
		//nie ma sasiadow!
		g.get(0).color = 'B';
		g.get(2).color = 'G';
		if(debug == true) {
			System.out.println(g.toString() + "   ");
			System.out.println("\n\n");
 		}
		strongly_connected_components(g);
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
		System.out.println(g.toString() + "\n\n ");
		strongly_connected_components(g);
		
		System.out.println(".................................................................");
		
	}

	public static void  sortuj(Graf g){
		for(int i = 1; i < g.len; i++){
			int tmp = g.V.get(i).f;
			Node t = g.V.get(i);
			int j = i;
			while ((j > 0) && (g.V.get(j-1).f  < tmp)){
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
