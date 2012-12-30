ZRODLA=$(wildcard *.cpp)
OBIEKTY=$(patsubst %.cpp, %.o, $(ZRODLA))
CC=clang++
FLAGI=
PROGRAM=boss

$(PROGRAM): $(OBIEKTY)
	# Linkujemy
	$(CC) $^ -o $@

# Cel: dowolne pliki .o
# Wymaganie: pliki o takim samym prefiksie, lecz z końcówką .cpp
# np. main.o wymaga main.cpp
%.o: %.cpp
	# Kompilacja
	$(CC) -c $< -o $@

# Cel: dowolne pliki .o
# Wymaganie: pliki o tym samym prefiksie, lecz z końcówką .cpp,
# dodatkowo wymagamy plików nagłówkowych
%.o: %.cpp %.hpp
	# Kompilacja
	$(CC) -c $< -o $@



