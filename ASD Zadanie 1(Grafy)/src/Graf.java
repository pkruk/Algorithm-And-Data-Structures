import java.util.LinkedList;

public class Graf {

	public static void main(String[] args) {
		System.out.println("TEST");
		Vertex v = new Vertex(1,1);
		Vertex w = new Vertex(1,2);
		Edge e = new Edge(v,w,2.0);
		Vertex r = new Vertex(2,1);
		Edge p = new Edge(v,r,3.0);
		System.out.println(e.wyswietl() +"\n" + p.wyswietl());
	}

}

class Vertex { // wierzcholek
	public String wyswietl(){
		if(Wierzcholek.size() == 0)
			return "";
		String wyjscie = new String();
		for(Edge i : Wierzcholek)
			wyjscie += i.wyswietl() + "\n";
		return wyjscie;
		
	}
	public Edge getEdgeWychodzaca(int n){
		//zwracam n-ta w kolejnosci krawedz wychodzaca 
		if(n >= 0 && n < Wierzcholek.size())
			return Wierzcholek.get(n);
		else
			return null;
	}
	
	public void addEdge(Edge e){
		Wierzcholek.add(e); //dodaje krawedz
	}
	
	public Edge getEdge(int x,int y){
		for(Edge i : Wierzcholek){
			if(i.end.id_wiersza == x && i.begin.id_kolumny == y)
				return i;
		}
		return null;
	}//zwraca krawedz
	
	//usuwac nam nie kazano!
	
	public LinkedList<Edge> getEdgeList() {
		return new LinkedList<Edge>(Wierzcholek);
	}/**
	 * Zwraca kopie listy krawedzi wychodzacych z wierzcholka
	 */

	public int[] getIdWspolrzedneTab() {
		int tab[] = new int[2];
		tab[0] = id_wiersza;
		tab[1] = id_kolumny;
		return tab;
	}

	public String getIdWspolrzedne() {
		return Integer.toString(id_wiersza) + "," + Integer.toString(id_kolumny);
	}

	public Vertex(int wiersz, int kolumna) {
		this.id_wiersza = wiersz;
		this.id_kolumny = kolumna;
	}

	public Vertex() {
	};

	private LinkedList<Edge> Wierzcholek = new LinkedList<Edge>();
	public int id_wiersza;
	public int id_kolumny;
}

class Edge { // krawedz
	public Edge() {
	};
	public Edge(Vertex begin, Vertex end, double waga) {
		this.begin = begin;
		this.end = end;
		this.waga = waga;
	}

	public String wyswietl(){
		return begin.getIdWspolrzedne() + " ---( " +
				Double.toString(waga) + " )---> " +
				end.getIdWspolrzedne();
	}
	
	public Vertex begin;
	public Vertex end;
	public double waga;
}
