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
	public Node(int i, float pr){
		this.ile = i;
		this.p = pr;
		key = "";
	}
	public Node(int i, float pr, String x){
		this.ile = i;
		this.p = pr;
		this.key = x;
	}
	int h = 1;
	boolean visited = false;
	public int index;
	public float p; // prawdopodobienstwo
	public int ile; //ile razy wystapilo dane slowo
	public String key; // zdanie kluczowe
	Node left, right;
	Node parent;

}