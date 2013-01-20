import java.util.*;

public class Main {

	public static void main(String[] args) {
		praca();
	}


	public static void praca(){
		Graf g = konsola();
		g.printSasiedzi();
		DFS t = new DFS(g);
		t.poczynaj();
	}
	public static Graf konsola(){
			System.out.println("KONSOLA : ");
			Scanner in = new Scanner(System.in);
			int rozmiar = in.nextInt();
			Graf g = new Graf();
			for(int i = 0; i < rozmiar; i++)
				g.add(i);
			for(int i = 0; i < rozmiar; i++){
				int len = in.nextInt();
				for(int j = 0; j < len; j++){
					int odczyt = in.nextInt();
					g.sasiedzi.get(i).add(odczyt-1);
				}
			}
			System.out.println(".................................................................");
		return g;
	}
}
