#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
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
	
	char direstimate[100] = "./estacionamento/";
	
	char *datetime = nomeFicheiro;
	int i;
	
	for(i = 0; i < 4; i++){
			datetime++;
	}
	
	char estimate[100] = "estimate";
	strcat(estimate,datetime);
	strcat(direstimate, estimate);
	
	FILE * lockFilePtr;
	
	lockFilePtr = fopen(dirlock,"r");
	
	char content[100];
	
	int capacidadeParque;
		
	fscanf(lockFilePtr, "%d,%d,%d,%d,%d,%s", &percentagem, &capacidade, &idParque, &estimativa, &capacidadeParque,content);
	
	fclose(lockFilePtr);
		
	int corrente = 0.1 * capacidade;
	
	struct Estacionamento samePark [current];
	
	int j = 0;
	int correnteTotal = corrente;
	for(i = 0; i < current;i++){
		if(arrayEstacionamentos[i].idParque == idParque){
			char dir[100];
			strcpy(dir, arrayEstacionamentos[i].dirFicheiro);
			strcpy(samePark[j].dirFicheiro, dir);
			correnteTotal = correnteTotal + (arrayEstacionamentos[i].capacidadeBateria * 0.1);
			j++;
		}
	}
	
	
	
	int k;
	if(correnteTotal > capacidadeParque){
		
		capacidadeParque = capacidadeParque/(j+1);
		printf("Corrente dividida: %d\n", capacidadeParque);
		for(i = 0; i < j; i++){
			for(k = 0; k < current; k++){
				if(strcmp(samePark[i].dirFicheiro, arrayEstacionamentos[k].dirFicheiro) == 0){
					estimateFile(arrayEstacionamentos[k].dirFicheiro,arrayEstacionamentos[k].dirEstimate,arrayEstacionamentos[k].percentagem,arrayEstacionamentos[k].capacidadeBateria,arrayEstacionamentos[k].idParque,arrayEstacionamentos[k].estimativa,capacidadeParque,arrayEstacionamentos[k].content);
				}
			}
		}
	}
	estimateFile(dirlock,direstimate, percentagem,capacidade,idParque,estimativa,capacidadeParque, content);
	
	int percentagemFinal = percentagem;
	arrayEstacionamentos[current].percentagem = percentagemFinal;
	
	int capacidadeBateria = capacidade;
	arrayEstacionamentos[current].capacidadeBateria = capacidadeBateria;
	
	int idParqueFinal = idParque;
	arrayEstacionamentos[current].idParque = idParqueFinal;
	
	int estimativaFinal = estimativa;
	arrayEstacionamentos[current].estimativa = estimativaFinal;
	
	strcpy(arrayEstacionamentos[current].dirFicheiro ,dirlock);
	
	strcpy(arrayEstacionamentos[current].dirEstimate, direstimate);
	
	strcpy(arrayEstacionamentos[current].content ,content);
	
	current++;
	

	return 0;
}

int estimateFile(char* dirlock, char* direstimate, int percentagem, int capacidade, int idParque, int estimativa, int corrente, char * content){
	
	printf("Nova estimativa:\n");
	printf("%d,%d,%d\n",percentagem, capacidade, corrente);
	
	estimativa = calc_estimativa(percentagem, capacidade, corrente);
	
	printf("%d\n",estimativa);
	
	int i;
	for(i = 0; i < current; i++){
		if(strcmp(dirlock,arrayEstacionamentos[i].dirFicheiro) == 0){
			arrayEstacionamentos[i].estimativa = estimativa;
		}
	}
	
	FILE * filePointer = fopen(direstimate,"w");
	
	char fileContent[100];
	
	sprintf(fileContent,"%d,%d,%s",estimativa,idParque,content);
	
	fputs(fileContent, filePointer);
	
	fclose(filePointer);
	
	char flag[] = ".flag";
	char estimateFlag [100];
	
	strcpy(estimateFlag,direstimate);
	strcat(estimateFlag,flag);
	
	filePointer = fopen(estimateFlag,"w");
	
	fclose(filePointer);
	
	char fichApagar[100];
	
	strcpy(fichApagar,dirlock);
	
	remove(fichApagar);
	printf("%s\n", fichApagar);
	
	strcat(fichApagar,".flag");
	
	remove(fichApagar);
	
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
