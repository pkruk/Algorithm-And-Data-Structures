ZRODLA=$(wildcard *.cpp)
OBIEKTY=$(patsubst %.cpp, %.o, $(ZRODLA))
CC=clang++
FLAGI=
PROGRAM=zmirlacz

$(PROGRAM): $(OBIEKTY)
	# Linkujemy
	$(CC) $^ -o $@
%.o: %.cpp
	$(CC) -c $< -o $@

%.o: %.cpp %.hpp
	$(CC) -c $< -o $@
clean:
	rm -rf *.o


