import java.util.*;
import java.io.*;


public class Komiwojazer{

	double min( double tab[]){
		double min = tab[0];
		for (int i = 0; i < factore(len-1); i++){
			if (tab[i] < min){
				min = tab[i];
			}
		}
		return min;
	}

	void rozwiaz(){
		permutacja(0,len-2);
		for (int i = 0; i < factore(len-1); i++){
			P[i][0] = 0;
			P[i][len] = 0;
		}
		for (int i = 0; i < factore(len-1); i++){
			Distance[i] = 0;
			for (int j = 0; j < len; j++){
				Distance[i] = round( Distance[i] +Cost[P[i][j]][P[i][j+1]]);
			}
		}
	}

	void permutacja(int k, int m){
		if (k == m){
			for (int i = 0; i <= m; i++){
				P[r][i+1] = list.get(i);
			}
			r++;
		} else {
			for (int i = k; i <= m; i++){
				Collections.swap(list,k,i);
				permutacja(k+1,m);
				Collections.swap(list,k,i);
			}
		}
	}

	int factore(int x){
		int a = 1;
		if (x != 0){
			for (int i = 1; i <= x; i++){
				a = a * i;
			}
		}
		return a;
	}

	public void start(){
		for (int i = 0; i < len; sasiedzi.add(new ArrayList<Integer>()),i++);
		for (int i = 0; i < len; i++){
			for (int j = 0; j < len; j++){
				if (j != i){
					sasiedzi.get(i).add(j);
				}
			}
		} // dodani sasiedzi
		// wyswietl();
		// costs();
		rozwiaz();
		Display();
	}

	public void printSasiedzi(){
		for (int i = 0; i < len; i++)
			System.out.println(sasiedzi.get(i).toString());
	}

	public void printAdjMat(){
		for (int i = 0; i < len; i++){
			for (int j : AdjMat[i] )
				System.out.print(j + " ");
			System.out.println();
		}
	}

	public double zwroc_wage(Node x, Node y){
		return oblicz_wage(x.x,x.y,y.x,y.y);
	}

	public double oblicz_wage(int x1,int y1,int x2, int y2){
		double x = (double) Math.pow(Math.abs((x1-x2)),2);
		double y = (double) Math.pow(Math.abs((y1-y2)),2);
		double wynik = Math.sqrt(x+y) * 1000;
		wynik = Math.round(wynik);
		return (wynik/1000);
	}

	public void wyswietl(){
		System.out.println("Wierzcholki");
		for (Node i : tab){
			System.out.println(i.toString());
		}
		System.out.println("SASIEDZI : ");
		// printSasiedzi();
		printAdjMat();
		System.out.println("-----------------------------------------------------------------");
		
	}

	public void Display(){
		costs(); // wyswietlam kosztorys
		int k = 0; 
		for (int i = 0; i < factore(len-1); i++){
			k = i;
			if (Distance[i] == min(Distance)){
				break;
			}
		}
		for (int j = 0; j <= len; j++){
			System.out.print(tab[P[k][j]].ID);
		}
		System.out.println();
		System.out.println(Distance[k]);
	}

	public void costs(){
		for (int i = 0; i < len; i++){
			for (double j : Cost[i])
				System.out.print(j + "  ");
			System.out.println();
		}
	}

	public void test(){
		System.out.println(oblicz_wage(tab[0].x,tab[0].y,tab[1].x,tab[1].y));
	}

	public double round(double x){
		double w = Math.round( x * 1000);
		return (w/1000);

	}

	public Komiwojazer(Node tab[]){
		this.tab = tab;
		len = tab.length;
		AdjMat = new int[len][len];
		for (int i = 0; i < len; i++){
			for (int j = 0; j < len; j++){
				if (i != j)
					AdjMat[i][j] = 1;
			}
		}
		Cost = new double[len][len]; // tablica kosztow
		for (int i = 0; i < len; i++){
			for (int j = 0; j < len; j++){
				if( i != j)
					Cost[i][j] = zwroc_wage(tab[i],tab[j]);
			}
		}
		Distance = new double[factore(len-1)]; // ja pierdole silnia oO
		P = new int[factore(len-1)][len+1];
		for (int i = 0; i < len -1; i++){
			list.add(i+1); // droga polnocnych krawedzi
		}
	}
	int len;
	Node tab[];
	ArrayList<ArrayList<Integer>> sasiedzi = new ArrayList<ArrayList<Integer>> (); //lista
	int AdjMat[][];
	double Cost[][];
	double Distance[];
	int P[][];
	int r = 0;
	ArrayList <Integer> list = new ArrayList<Integer>();
}