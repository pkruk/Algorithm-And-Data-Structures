public class Node{

	public String toString(){
		return "" + key + " P : " + p;
	}

	public Node(){
		key = "";
		p = -1; ile = -1;
		left = right = null;
		parent = null;
	}
	public Node(int i, double pr){
		this.ile = i;
		this.p = pr;
		key = "";
	}
	public Node(int i, double pr, String x){
		this.ile = i;
		this.p = pr;
		this.key = x;
	}
	public int index;
	public double p; // prawdopodobienstwo
	public int ile; //ile razy wystapilo dane slowo
	public String key; // zdanie kluczowe
	Node left, right;
	Node parent;

}