#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include "calc_estimativa.h"

int main(void){
	DIR *folder;
	FILE * lockPointer;
	
	
	struct dirent *entry;
	
	folder = opendir("./scooter");

	char lock_nome[] = ".data";
	char lock_flag_nome[] = ".flag";
	char file_lock[100];
	char file_flag[100];
	
	while((entry = readdir(folder))!= NULL){
		char *nome1 = strrchr(entry->d_name, '.');
		if(strcmp(nome1,lock_nome) == 0){
			strcpy(file_lock,entry->d_name);
		}
		
		char *nome2 = strrchr(entry->d_name, '.');
		if(strcmp(nome2,lock_flag_nome) == 0){
			strcpy(file_flag,entry->d_name);
		}
	}
	
	//printf("%s - a\n",file_lock);
	//printf("%s - b\n",file_flag);
	
	char dirlock[100] = "./scooter/";
	char dirflag[100] = "./scooter/";
	char direstimate[100] = "./scooter/";
	//char string[100];
	
	int percentagem;
	int potencia;
	int estimativa;
	
	if(file_flag != NULL){
		strcat(dirlock, file_lock);
		
		strcat(dirflag, file_flag);
		
		//printf("%s - a1\n", dirlock);
		//printf("%s - b2\n", dirflag);
		
		lockPointer = fopen(dirlock,"r");
		
		fscanf(lockPointer, "%d,%d", &percentagem, &potencia);
		
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
		
		printf("%s,%d\n",estimate,estimativa);
	}

	return 0;
}
