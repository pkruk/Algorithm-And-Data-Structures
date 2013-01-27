import java.util.*;

public class Prepare{

	public void optimal_bst(){
		// init start
		n = p.size();
		double w[][] = new double [n+2][n+2];
		e = new double [n+2][n+2];
		//Cormen style n+1 NUMEROWANE OD 1 
		// n NUMEROWANE OD 0!
		root = new int [n+2][n+2];
		for (int i = 1; i < n+2; i++){
			e[i][i] = q.get(i-1).p;
			w[i][i] = q.get(i-1).p;
		}	
		System.out.println("E : ");
		print(e);
		n+=2;
		for (int l = 1; l < n; l++){
			for (int i = 1; i < n -l ; i++){
				int j = i + l -1;
				e[i][j] = 100.0;
				System.out.println("i " + i + " j " + j + " w[i][j-1] " + w[i][j] + " p " + p.get(j-1).p + " q : " + q.get(j).p);
				w[i][j] = w[i][j] + p.get(j-1).p + q.get(j).p;
				
			}
		}
		System.out.println(n);
		
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

	public Prepare(){

	}
	double w[][];
	int root[][];
	double e[][];
	int n = 0;
	public ArrayList <Node> p = new ArrayList<Node>();
	public ArrayList <Node> q = new ArrayList<Node>();
}