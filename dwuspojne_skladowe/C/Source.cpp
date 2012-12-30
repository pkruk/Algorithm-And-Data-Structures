#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;


typedef vector <int> V;
V v,pi; // lista wierzcholkow i poprzednikow
vector<V> adj; //macierz sasiadow bjaaacz 
int cnt;
int len;
void konsola();
int nieskonczonosc = 100000000;

void init(int len){
	cout << "INIT" << endl;
	v.clear();
	v.resize(len,nieskonczonosc);
	pi.resize(len);
	cnt = 1;
	cout << "/INIT" << endl;
}

int dfs(int u){
	int ans = u;
	v[u] = cnt++;
	for(int i = 0; i < adj[u].size(); i++){
		int tmp = adj[u][i];
		cerr <<"T: " << tmp << " vt : " << v[tmp] << " p : " << pi[tmp] << " u: " << u << endl;

		if (tmp == u) continue; //ignorujemy cykl
		if (tmp == pi[u]) continue; // ignorujemy poprzednika!
		if (v[tmp] == nieskonczonosc ) { //nieodwiedzony 
			pi[tmp] = u; //nowy poprzednik na drodze
			tmp = dfs(tmp); //szukamy od niego drogi
		}
		cout << "(" << v[ans] <<","<<v[tmp] << ")" << endl;
		if(v[ans] > v[tmp]){
			cout << ans << "X: " << tmp << endl;
			ans = tmp;
		}
	}
	for (int i = 0; i < v.size(); i++)
		cout << v[i] << " ";
	cout << endl;
	if (ans == u && pi[u] != u)
		cout << "Most: "  << u +1 << " " << pi[u] +1 << endl;
	return ans;
}


int main(int argc, const char *argv[])
{
	konsola();
	init(len);
	cout << " XXX" << endl;
	//dfs(0);
	for(int i = 0; i < len; i++){
		if (v[i] == nieskonczonosc){
			cout << "****" << endl;
			pi[i] = i;
			cout << pi[i]<< endl;
			dfs(i);
		}
	}
	return 0;
}

void konsola(){
	int x;
	cout << "WITAM W PROGRAMIE SZUKAJACYM MOSTOW " << endl;
	cout << "Podaj rozmiar";
		cin >> len;
	adj.clear(); adj.resize(len);
	for (int i = 0; i < len; i++) {
		int n_sasiadow = 0;
		cout <<"Podaj rozmiar listy : ";
		cin >> n_sasiadow;
		cout << endl;
		for (int j = 0 ; j < n_sasiadow; j++){
			cout << "Podaj sasiada : " << endl;
			cin >> x;
			--x;
			adj[i].push_back(x);
		//	adj[x].push_back(i); //nieskierowany!
		}
	}
	cout << "Zakonczony zapis" << endl;
}

