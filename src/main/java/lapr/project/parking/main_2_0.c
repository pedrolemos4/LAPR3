#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
//#include "calc_estimativa.h"
#include "read_file.h"

struct Estacionamento arrayEstacionamentos[100];
int current = 0;

int main(void){
	//int run = 0;
	//while(run == 0){
		DIR *folder;
	
		struct dirent *entry;
	
		folder = opendir("./estacionamento");
	
		current = 0;
	
		while((entry = readdir(folder))!= NULL){
			char *extensao = strrchr(entry->d_name, '.');
			if(strcmp(extensao,".data") == 0){
				printf("nextreadfile\n");
				readFile(entry->d_name);
			}
		}
		//sleep(15);
	//}

	return 0;
}

int readFile(char* nomeFicheiro){
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
			tratamentoFile(nomeFicheiro,nomeFicheiroFlag);
		}
	}
	
	return 0;
}

int tratamentoFile(char* nomeFicheiro, char* nomeFicheiroFlag){
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
	
	struct Estacionamento samePark [current];
	
	int i;
	int j = 0;
	int correnteTotal = corrente;
	for(i = 0; i < current;i++){
		if(arrayEstacionamentos[i].idParque == idParque){
			samePark[j].dirFicheiro = arrayEstacionamentos[i].dirFicheiro;
			correnteTotal = correnteTotal + (arrayEstacionamentos[i].capacidadeBateria * 0.1);
			j++;
		}
	}
	printf("Passo a passo imroum: %d\n", correnteTotal);
	
	if(correnteTotal > capacidadeParque){
		capacidadeParque = capacidadeParque/(j+1);
		int k;
		for(i = 0; i < j;i++){
			for(k = 0; k < current; k++){
				
				if(strcmp(samePark[i].dirFicheiro,arrayEstacionamentos[k].dirFicheiro) == 0){
					estimateFile(arrayEstacionamentos[k].dirFicheiro,arrayEstacionamentos[k].percentagem,arrayEstacionamentos[k].capacidadeBateria,arrayEstacionamentos[k].idParque,arrayEstacionamentos[k].estimativa,capacidadeParque,arrayEstacionamentos[k].content);
				}
				
			}
		}
	}
	
	estimateFile(dirlock,percentagem,capacidade,idParque,estimativa,capacidadeParque, content);
	
	printf("Percentage: %d\nCapacidade: %d\nContent: %s\n",percentagem, capacidade, content);
		
	fclose(lockFilePtr);
		
	//estimativa = calc_estimativa(percentagem, capacidade, corrente);
	
	arrayEstacionamentos[current].percentagem = percentagem;
	printf("bateria: %d\n",arrayEstacionamentos[current].percentagem);
	arrayEstacionamentos[current].capacidadeBateria = capacidade;
	arrayEstacionamentos[current].idParque = idParque;
	arrayEstacionamentos[current].estimativa = estimativa;
	arrayEstacionamentos[current].dirFicheiro = dirlock;
	//arrayEstacionamentos[current].dirEstimate = direstimate;
	arrayEstacionamentos[current].content = content;
	printf("current:%d\n",current);
	current++;
	

	return 0;
}

int estimateFile(char* dirlock, int percentagem, int capacidade, int idParque, int estimativa, int corrente, char * content){
	
	estimativa = calc_estimativa(percentagem, capacidade, corrente);
	
	int i;
	for(i = 0; i < current; i++){
		if(dirlock == arrayEstacionamentos[i].dirFicheiro){
			arrayEstacionamentos[i].estimativa = estimativa;
		}
	}
	
	//criar ficheiro estimate
	
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
