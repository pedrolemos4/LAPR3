#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include "calc_estimativa.h"
#include "read_file.h"

struct Estacionamento arrayEstacionamentos[100];

int main(void){
	int run = 0;
	while(run == 0){
		DIR *folder;
	
		struct dirent *entry;
	
		folder = opendir("./estacionamento");
	
		int current = 0;
	
		while((entry = readdir(folder))!= NULL){
			char *extensao = strrchr(entry->d_name, '.');
			if(strcmp(extensao,".data") == 0){
				printf("nextreadfile\n");
				readFile(entry->d_name,current);
			}
		}
		sleep(15);
	}

	return 0;
}

int readFile(char* nomeFicheiro,int current){
	char flag[] = ".flag";
	char nomeFicheiroFlag[50];
	
	strcpy(nomeFicheiroFlag,nomeFicheiro);
	
	strcat(nomeFicheiroFlag,flag);
	
	DIR *folder;
	
	struct dirent *entry;
	
	folder = opendir("./estacionamento");
	
	while((entry = readdir(folder))!= NULL){
		if(strcmp(entry->d_name,nomeFicheiroFlag) == 0){
			printf("foundflag,tratamento\n");
			tratamentoFile(nomeFicheiro,nomeFicheiroFlag,current);
		}
	}
	
	return 0;
}

int tratamentoFile(char* nomeFicheiro, char* nomeFicheiroFlag, int current){
	int percentagem;
	int capacidade;
	int idParque;
	int estimativa;
	
	char dirlock[100] = "./estacionamento/";
	
	strcat(dirlock, nomeFicheiro);
	
	char dirflag[100] = "./estacionamento/";
	
	strcat(dirflag, nomeFicheiroFlag);
	
	//char direstimate[100] = "./estacionamento/";
	
	FILE * lockFilePtr;
	
	lockFilePtr = fopen(dirlock,"r");
	
	char content[100];
	
	int capacidadeParque;
		
	fscanf(lockFilePtr, "%d,%d,%d,%d,%d,%s", &percentagem, &capacidade, &idParque, &estimativa, &capacidadeParque,content);
		
	int corrente = 0.1 * capacidade;
	
	struct Estacionamento mesmoParque[current];
	
	struct Estacionamento * pointer = mesmoParque;
	
	int i;
	int j = 0;
	for(i = 0; i < current;i++){
		if(arrayEstacionamentos[i].idParque == idParque){
			*(pointer + j) = arrayEstacionamentos[i];
			j++;
		}
	}
	
	printf("%d\n%d\n%s\n",percentagem, capacidade, content);
		
	fclose(lockFilePtr);
		
	estimativa = calc_estimativa(percentagem, capacidade, corrente);
	
	arrayEstacionamentos[current].percentagem = percentagem;
	arrayEstacionamentos[current].capacidadeBateria = capacidade;
	arrayEstacionamentos[current].idParque = idParque;
	arrayEstacionamentos[current].estimativa = estimativa;

	return 0;
}

int numeroDeLocks(void){
	DIR *folder;
	
	struct dirent *entry;
	
	folder = opendir("./estacionamento");
	
	int numero;
	
	while((entry = readdir(folder))!= NULL){
		char *extensao = strrchr(entry->d_name, '.');
		if(strcmp(extensao,".data") == 0){
			numero++;
		}
	}
	
	return numero;
}
