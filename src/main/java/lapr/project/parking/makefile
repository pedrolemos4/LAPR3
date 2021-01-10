INCLUDES = calc_estimativa.h
SOURCES = calc_estimativa.s main.c
OBJFILES = calc_estimativa.o main.o
EXEC = main

.SUFFIXES : .c .o

.c.o:
	gcc -Wall -g -c $<

.s.o:
	gcc -Wall -g -c $<

${EXEC}: ${OBJFILES}
	gcc -Wall -g -o ${EXEC} ${OBJFILES}

${OBJFILES} : ${SOURCES} ${INCLUDES}

run: ${EXEC}
	./${EXEC}

clean:
	rm -f ${OBJFILES} ${EXEC}
