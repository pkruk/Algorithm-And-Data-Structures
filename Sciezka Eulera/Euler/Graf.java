/**
 * @author Piotr Kruk 
 * @version 2012/12/15
 */
import java.util.*;
import java.io.*;

public class Graf{

	Deque <Integer> drogowy = new Deque<Integer>();
	Deque <Integer> cykl = new Deque<Integer>();
	Deque <Integer> poprzednicy = new Deque <Integer>();
	public int liczba_krawedzi = 0;
	private int dfs(int u){
		int x = 0; 
		Node tmp = wierzcholki[u];
		for(int i = 0; i < tmp.lista.size(); i = 0){
			liczba_krawedzi--;
			Node v = tmp.lista.remove(0);
			if (tmp == v) {
				drogowy.addLast(v.index);
				continue;}
			x++;
			euler(v.index);
		}
		return x;
	}
	public boolean flaga = true;
	public void euler(int s){
		int u = s;
		drogowy.addLast(u);
		
		while(dfs(u) == 0 && !drogowy.isEmpty()){
			if (liczba_krawedzi == 0) {
				break;
			}
			System.out.println(drogowy.toString() + " CYKL : " + cykl.toString() + " u: " + u);
			Scanner in = new Scanner(System.in);
		//	int a = in.nextInt();
			cykl.addLast(u);
			drogowy.removeLast();
			if (!drogowy.isEmpty()) {
				u = drogowy.getLast();
				poprzednicy.addLast(u);
			}
			if (wierzcholki[u].lista.size() == 0)
				return;

		}
		
		if(cykl.size() > 1 && drogowy.size() > 0 && (cykl.getLast() != drogowy.getFirst())){
			if (poprzednicy.getLast() == drogowy.getLast())
				flaga = true;
			else
				flaga = false;
			System.out.println("FLAGA NA FALSE");
			System.out.println(drogowy.toString());
			System.out.println(cykl.toString());
			System.out.println("DROGOWY : " + drogowy.getFirst() + "||" + cykl.getLast());
		} else {
			while(!drogowy.isEmpty())
				cykl.addLast(drogowy.removeLast());
		}

	}
	/*COLORS**/
		public static final String ANSI_RESET = "\u001B[0m";
		public static final String ANSI_BLACK = "\u001B[30m";
		public static final String ANSI_RED = "\u001B[31m";
		public static final String ANSI_GREEN = "\u001B[32m";
		public static final String ANSI_YELLOW = "\u001B[33m";
		public static final String ANSI_BLUE = "\u001B[34m";
		public static final String ANSI_PURPLE = "\u001B[35m";
		public static final String ANSI_CYAN = "\u001B[36m";

	/*COLORS**/
	public String wyswietl(){
		//System.out.println("__________");
		String wyjscie = "";
		if(cykl.getFirst() == 0){
			cykl.removeFirst();
			System.out.println("C");
			wyjscie += "c\ns ";
		}
		else if(cykl.getFirst() != 0 ){
			System.out.println("Droga: s");
			wyjscie += "s ";
		}
		if(liczba_krawedzi > 0 || flaga == false){
			System.out.println(ANSI_RED + "n" + ANSI_RESET);
			return "n";
		}
		while(!cykl.isEmpty()){
			int a = cykl.removeLast() +1;
			System.out.print(ANSI_RED + ""+ a + " ");
			wyjscie += a + " ";
		}
		wyjscie += "\n";
		System.out.println(ANSI_RESET);
		System.out.println("_______");
		return wyjscie;
	}

	public void Display(){
		System.out.println(ANSI_CYAN);
		for(int i = 0; i < len; i++){
			Node tmp = wierzcholki[i];
			System.out.print((tmp.index+1) + "=");
			for(int j = 0; j < tmp.lista.size(); j++)
				System.out.print((tmp.lista.get(j).index +1 ) + " -- ");
			System.out.println();
		}
		System.out.println(ANSI_RESET);

	}
	public void displayWierzcholki(){
		System.out.println(ANSI_YELLOW);
		for(int i = 0; i < len; i++)
			System.out.print(wierzcholki[i].index + " ");
		System.out.println();
		System.out.println(ANSI_RESET);
	}
	public void DisplayAdjMat(){
		for(int i = 0; i < AdjMat.length; i++){
			for(int j = 0; j < AdjMat.length; j++)
				System.out.print(ANSI_PURPLE + AdjMat[i][j] + " ");
			System.out.println();
		}
		System.out.println(ANSI_RESET);
			
	}
	public Graf(int tab[][]){
		this.len = tab.length;
		wierzcholki = new Node[len];
		for(int i = 0; i < len; i++)
			wierzcholki[i] = new Node(i);
		this.AdjMat = tab;
		for(int i = 0; i < len; i++)
			sasiedzi(i);

	}
	private void sasiedzi(int u){
		Node tmp = wierzcholki[u];
		for(int i = 0; i < len; i++){
			if(AdjMat[u][i] != 0){
				int k = AdjMat[u][i];
				for(int j = 0; j < k; j++)
					tmp.lista.add(wierzcholki[i]);
				liczba_krawedzi += k;
			}
		}
	}
	private int AdjMat[][];
	private int len;
	private Node wierzcholki[];
}
