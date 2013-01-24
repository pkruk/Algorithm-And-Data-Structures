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
	public int ojciec = -1; // ojciec
	public int index; //index w tablicy grafu

}