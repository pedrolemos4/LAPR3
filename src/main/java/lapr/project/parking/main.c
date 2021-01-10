#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include "calc_estimativa.h"

int main(void){
	
    DIR *folder;
    FILE * filePointer; 
    FILE * newFilePointer;
    FILE * newFlagFilePointer;

    struct dirent *entry;
	char nome[30];
	char currentdir[] = "./";
	char currentdir2[] = "./";
	char cd[30];

    folder = opendir(".");

	int i;
	for(i = 0; i < 6; i++){
		entry = readdir(folder);
	}
	
	strcpy(nome,entry->d_name);
	char nomelock[30];
	strcpy(nomelock,nome);
	
	entry = readdir(folder);
	
	strcpy(nome,entry->d_name);
	
	char *nome1 = strrchr(nome, '.');
	
	char flag[] = ".flag";
	
	if(strcmp(nome1,flag) == 0){
		strcat(currentdir,nomelock);
		
		filePointer = fopen(currentdir,"r");
		
		int percentagem;
		
		fscanf(filePointer, "%d", &percentagem);
		
		int res = calc_estimativa(percentagem);
		
		//printf("%s\n", nomelock);
		//printf("%d\n", res);
		
		char *datetime = nomelock;
		for(i = 0; i < 4; i++){
			datetime++;
		}
		
		char estimate[] = "estimate";
		
		strcat(estimate,datetime);
		
		strcat(currentdir2,estimate);
		
		//printf("%s\n", currentdir2);
		
		newFilePointer = fopen(estimate,"w");
		
		char flag[] = ".flag";
		
		//printf("%s\n", flag);
		
		strcat(estimate, flag);
		
		//printf("%s\n", estimate);
		
		newFlagFilePointer = fopen(estimate,"w");
		
		sprintf(cd, "%d", res);
		
		fputs(cd, newFilePointer);
		
		fclose(filePointer);
		fclose(newFilePointer);
		fclose(newFlagFilePointer);
	}

    closedir(folder);

    return 0;
    
}
