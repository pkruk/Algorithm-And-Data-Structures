import java.util.*;
import java.io.*;

public class Dynamic{

	public void start(){
		licz();
	}

	void initL(){
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if (i == 0 && j == 1){
					L[i][j] = Cost[AdjMat[i][j]][AdjMat[i][j+1]];
				} else if (i < j){
					L[i][j] = L[i][j-1] + Cost[AdjMat[i][j-1]][AdjMat[i][j]];
				} else if (j > 1 && i == j){

				}
			}
		}
	}

	void licz(){
		initL();
		for (int j = 1; j < n; j++){
			for (int i = 0; i < j; i++){
				if (i == 0 && j == 1){
					L[i][j] = Cost[AdjMat[i][j]][AdjMat[i][j+1]];
					N[i][j] = i;
				} else if (j > i){
					L[i][j] = L[i][j-1] + Cost[AdjMat[i][j-1]][AdjMat[i][j]];
					N[i][j] = j-1;
				} else {
					L[i][j] = Double.MAX_VALUE;
					for (int k = 0; k < i; k++){
						double q = L[k][i] + Cost[AdjMat[i][k]][AdjMat[i][j]];
						if (q < L[AdjMat[i][j]][AdjMat[i][j+1]]){
							L[i][j] = q;
							N[i][j] = k;
						}
					} // ja pierd.....
				}
			}
		}
		int k = 0;
		int i = n -1;
		int j = n;
		Stack s[] = new Stack[2];
		while ( j > 0){
			s[k].push(j);
			j = N[i][j];
			if (i < j){
				int tmp = i;
				i = j;
				j = tmp;
				k = 3 - k;
			}
		} //unsafe check it!
		s[0].push(1);
		while (!s[1].isEmpty()){
			s[0].push(s[1].pop());
		}
		for (i = 0; i < n; i++){
			System.out.print(s[0].pop() + " ");
		}
		System.out.println();
	}


	double zwroc_wage(Node x, Node y){
		return oblicz_wage(x.x,x.y,y.x,y.y);
	}

	double oblicz_wage(int x1,int y1,int x2, int y2){
		double x = (double) Math.pow(Math.abs((x1-x2)),2);
		double y = (double) Math.pow(Math.abs((y1-y2)),2);
		double wynik = Math.sqrt(x+y) * 1000;
		wynik = Math.round(wynik);
		return (wynik/1000);
	}

	
	public Dynamic(Node tab[]){
		n = tab.length;
		this.tab = tab;
		Cost = new double[n][n]; // tablica kosztow
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if( i != j)
					Cost[i][j] = zwroc_wage(tab[i],tab[j]);
			}
		}
		AdjMat = new int[n][n];
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if (i != j)
					AdjMat[i][j] = 1;
			}
		}
		L = new double[n][n];
		N = new int[n][n];
	}

	double L[][];
	int AdjMat[][];
	double Cost[][];
	int N[][];
	int n = 0;
	Node tab[];
}