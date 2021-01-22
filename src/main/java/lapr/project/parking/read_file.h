#ifndef READ_FILE_H
#define READ_FILE_H
struct Estacionamento{
	int percentagem;
	int capacidadeBateria;
	int idParque;
	int estimativa;
	int correnteParque;
	char dirFicheiro[100];
	char dirEstimate[100];
	char content[100]; //idVeiculo, idEstafeta (se existir), idEstacionamento
};

int readFile(char* nomeFicheiro);
int tratamentoFile(char* nomeFicheiro, char* nomeFicheiroFlag);
int numeroDeLocks(void);
struct Estacionamento arrayEstacionamentos[100];
int calc_estimativa(int percentagem,int capacidade,int correnteDebitada);
int estimateFile(char* dirlock, char* direstimate, int percentagem, int capacidade, int idParque, int estimativa, int capacidadeParque, char* content);
int wrongParking(char* nomeFicheiro);
#endif
