import java.util.*;
import java.io.*;
import java.text.*;

public class Prepare{

	public static String output = "";

	public String zwroc_wynik(){
		return output;
	}

	public void init(){
		w = new float [n][n];
		e = new float [n][n];
		root = new int [n-1][n-1];
		for (int i = 0; i < n; i++){
			e[i][i] = q.get(i).p;
			w[i][i] = q.get(i).p;
		}
	}

	public void optimal_bst(){
		n = q.size();
		init();	
		for (int l = 0; l < n; l++){
			for (int i = 0; i < n -l -1; i++){
				int j = i + l + 1;
				if (e[i][j] == 0)
					e[i][j] = Float.MAX_VALUE;
				w[i][j] = w[i][j-1] + p.get(j-1).p + q.get(j).p;
				for (int r = i; r < j; r++){
					float t  = e[i][r] + e[r+1][j] + w[i][j];
					if (t < e[i][j]){
						e[i][j] = t;
						root[i][j-1] = r;
					}
				}
			}
		}
		System.out.println("E : ");
		print(e);
		System.out.println("ROOT : ");
		print_root(root);
		// System.out.println("DRZEWO");
		construct_optimal_bst();
	}
	public void construct_optimal_bst(){
		int n = root.length-1;
		int r = root[0][n];
		// System.out.println("ROOT : " + (r+1) );
		System.out.print("r: " + r + " " + p.get(r).key + " | ");
		output = "";
		output += p.get(r).key + " ";
		construct_optimal_subtree(0,r-1,r,"left");
		// System.out.println("RIGHT");
		construct_optimal_subtree(r+1,n,r,"right");
		//e[0][e.length-1] -= 0.7;
		//e[0][e.length-1] = roundToDecimals(e[0][e.length-1],2);
		output += "\n" + e[0][e.length-1];
	}

	public void construct_optimal_subtree(int i, int j, int parent, String kierunek){
		if (i <= j){ // warunek że nie wyskoczymy poza przekatna :) 
			int t = root[i][j]; // nowy ojciec :) 
			//System.out.println((t+1) + " " + kierunek + " child of " + (parent+1));
			System.out.print(p.get(t).key + " ");
			output += p.get(t).key + " ";
		//	System.out.println(i + " " + j); //debugger 
			construct_optimal_subtree(i,t-1,t,"left");
			construct_optimal_subtree(t+1,j,t,"right");
		} else {
			// if (kierunek.equals("left"))
			// 	System.out.println("d"+(parent) + kierunek +" child of " + (parent+1));
			// else
			// 	System.out.println("d" + (parent+1) + kierunek + " child of " + (parent+1));
			// KLUCZE IMITACJE :) 
		}
	}

	public void wyswietl(){
		System.out.println("LISCIE KLUCZE : ");
		for (int i = 0; i < p.size(); i++)
			System.out.println(p.get(i).toString() + "\t");
		System.out.println("\nLISCIE IMITACJE : ");
		for (int i = 0; i < q.size(); i++)
			System.out.println(q.get(i).toString());
		System.out.println();
	}

	public void start_index(){
		for (int i = 0; i < p.size(); p.get(i).index = i, i++);
		for (int j = 0; j < q.size(); q.get(j).index = j, j++);
	}

	public void print(float tab[][]){
		for (int i = 0; i < tab.length; i++){
			for (int j = 0; j < tab[i].length; j++)
				System.out.print(tab[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}

	public void print_root(int tab[][]){
		for (int i = 0; i < tab.length; i++){
			System.out.print("i " + i + ":  ");
			for (int j = 0; j < tab[i].length; j++)
				System.out.print(tab[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}

	public Prepare(){

	}
	float w[][];
	int root[][];
	float e[][];
	int n = 0;
	public ArrayList <Node> p = new ArrayList<Node>();
	public ArrayList <Node> q = new ArrayList<Node>();
	Scanner in = new Scanner(System.in);
}