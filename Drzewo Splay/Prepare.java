import java.util.*;
import java.io.*;
import java.text.*;

public class Prepare{

	public void optimal_bst(){
		// init start
		n = q.size();
		double w[][] = new double [n][n];
		e = new double [n][n];
		//Cormen style n+1 NUMEROWANE OD 1 
		// n NUMEROWANE OD 0!
		root = new int [n][n];
		for (int i = 0; i < n; i++){
			e[i][i] = q.get(i).p;
			w[i][i] = q.get(i).p;
		}	

		System.out.println("E : ");
		print(e);

		for (int l = 0; l < n; l++){
			for (int i = 0; i < n -l -1; i++){
				int j = i + l + 1;
				e[i][j] = 100000;
				w[i][j] = w[i][j-1] + p.get(j-1).p + q.get(j).p;
				w[i][j] = roundToDecimals(w[i][j],2);
				for (int r = i; r < j; r++){
					double t  = e[i][r] + e[r+1][j] + w[i][j];
					t = roundToDecimals(t,2);
					if (t < e[i][j]){
						e[i][j] = t;
						root[i][j] = r+1;
					}
				}
			}
		}


		
		
		System.out.println("W : ");
		print(w);
		System.out.println("E : ");
		print(e);
		System.out.println("ROOT : ");
		print_root(root);
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

	public void print(double tab[][]){
		for (int i = 0; i < tab.length; i++){
			for (int j = 0; j < tab[i].length; j++)
				System.out.print(tab[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}

	public void print_root(int tab[][]){
		for (int i = 0; i < tab.length; i++){
			for (int j = 0; j < tab[i].length; j++)
				System.out.print(tab[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}

	double roundToDecimals(double d, int c) {
		int t = (int)((d*Math.pow(10,c)));
		/*if ( t % 5 == 0 || t % 6 == 0 || t % 7 == 0 || t % 8 == 0 || t % 9 == 0)
			t+=1;*/
		double x = (((double)t)/Math.pow(10,c));
		return x;
	}
	public Prepare(){

	}
	double w[][];
	int root[][];
	double e[][];
	int n = 0;
	public ArrayList <Node> p = new ArrayList<Node>();
	public ArrayList <Node> q = new ArrayList<Node>();
}