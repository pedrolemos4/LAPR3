#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include "calc_estimativa.h"

int main(void){
	DIR *folder;
	FILE * lockPointer;
	FILE * estimatePointer;
	
	struct dirent *entry;
	
	folder = opendir("./estacionamento");

	char lock_nome[] = ".data";
	char lock_flag_nome[] = ".flag";
	char file_lock[100];
	char file_flag[100];
	int flag = 0;
	
	while((entry = readdir(folder))!= NULL){
		char *nome1 = strrchr(entry->d_name, '.');
		if(strcmp(nome1,lock_nome) == 0){
			strcpy(file_lock,entry->d_name);
		}
		
		char *nome2 = strrchr(entry->d_name, '.');
		if(strcmp(nome2,lock_flag_nome) == 0){
			strcpy(file_flag,entry->d_name);
			flag = 1;
		}
	}
	
	//printf("%s - a\n",file_lock);
	//printf("%s - b\n",file_flag);
	
	char dirlock[100] = "./estacionamento/";
	char dirflag[100] = "./estacionamento/";
	char direstimate[100] = "./estacionamento/";
	//char string[100];
	
	int percentagem;
	int potencia;
	int estimativa;
	
	printf("%s\n",file_flag);
	
	if(flag == 1){
		strcat(dirlock, file_lock);
		
		strcat(dirflag, file_flag);
		
		//printf("%s - a1\n", dirlock);
		//printf("%s - b2\n", dirflag);
		
		lockPointer = fopen(dirlock,"r");
		
		char content[100];
		
		fscanf(lockPointer, "%d,%d,%s", &percentagem, &potencia, content);
		
		printf("%d\n%d\n%s\n",percentagem, potencia, content);
		
		fclose(lockPointer);
		
		estimativa = calc_estimativa(percentagem, potencia);
		
		char *datetime = file_lock;
		int i;
		for(i = 0; i < 4; i++){
			datetime++;
		}
		
		char estimate[100] = "estimate";
		strcat(estimate,datetime);
		
		strcat(direstimate, estimate);
		
		//printf("%s,%d\n",direstimate,estimativa);
		
		estimatePointer = fopen(direstimate,"w");
		
		char fileContent[100];
		
		sprintf(fileContent,"%d,%s",estimativa,content);
		
		fputs(fileContent, estimatePointer);
		
		fclose(estimatePointer);
		
		strcat(direstimate, lock_flag_nome);
		
		estimatePointer = fopen(direstimate,"w");
		
		fclose(estimatePointer);
		
		remove(dirlock);
		remove(dirflag);
	}else{
		strcat(dirlock, file_lock);
		
		char *datetime = file_lock;
		int i;
		for(i = 0; i < 4; i++){
			datetime++;
		}
		
		char estimate[100] = "estimate";
		strcat(estimate,datetime);
		
		strcat(direstimate, estimate);
		
		estimatePointer = fopen(direstimate,"w");
		
		char fileContent[100];
		
		sprintf(fileContent,"%d",(-1));
		
		fputs(fileContent, estimatePointer);
		
		fclose(estimatePointer);
		
		strcat(direstimate, lock_flag_nome);
		
		estimatePointer = fopen(direstimate,"w");
		
		fclose(estimatePointer);
		
		remove(dirlock);
	}

	return 0;
}
