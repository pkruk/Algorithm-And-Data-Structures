public class Edge{

	public Edge(int from, int to){
		this.from = from;
		this.to = to;
	}

	public Edge(){
		from = to = -1;
	}
	int from;
	int to;
}