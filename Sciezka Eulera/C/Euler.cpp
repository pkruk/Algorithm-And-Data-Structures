#include <iostream>
using namespace std;

class Node;
int main(int argc, const char *argv[])
{
    
    return 0;
}

class Graf {
public : 
	Graf(int **tab){
		
	}
	Graf(){
		BIALY = 'W';
		CZARNY = 'B';
		SZARY = 'G';
}
private :
	static char BIALY;
	static char CZARNY;
	static char SZARY;
	int** AdjMat;
	Node* wierzcholki;
	int len;
};


class Node {
public:

    Node(){
        f = 0;
        d = 0;
        key = 0;
        pi = NULL;
        color = 'W';
    }

    int f;
    int d;
    int key;
    Node* pi;
    char color;
};

