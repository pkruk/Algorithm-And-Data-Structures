import java.util.*;


public class Wezel {
	public String toString(){
		return "[ " + index + " ]";
	}
	public Wezel(int index){
		this.index = index;
	}

	public Wezel(){
		index = 0;
	}
	public int d = 0;
	public int low = 1000000000;
	public ArrayList<Integer> Sasiedzi = new ArrayList<Integer>();
	public Wezel pi = null; //poprzednik 
	public int ojciec = -1; // ojciec
	public char color = 'W'; // kolor domyslnie bialy
	public int index; //index w tablicy grafu

}