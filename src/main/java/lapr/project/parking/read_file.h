#ifndef READ_FILE_H
#define READ_FILE_H
struct Estacionamento{
	int percentagem;
	int capacidadeBateria;
	int idParque;
	int estimativa;
	int correnteParque;
};

int readFile(char* nomeFicheiro,int current);
int tratamentoFile(char* nomeFicheiro, char* nomeFicheiroFlag,int current);
int numeroDeLocks(void);
struct Estacionamento arrayEstacionamentos[100];
#endif
