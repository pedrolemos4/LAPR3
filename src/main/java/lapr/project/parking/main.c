#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include <stdlib.h>
#include "calc_estimativa.h"

int main(void){
	DIR *folder;
	FILE * lockPointer;
	FILE * estimatePointer;
	
	struct dirent *entry;
	
	folder = opendir("./estacionamento");
	
	char file_lock[100];
	char file_flag[100];
	int flag = 0;
	
	char * ptrFileLock = (char *) malloc (sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + sizeof(int));
	
	
	
	while((entry = readdir(folder))!= NULL){
		char *nome1 = strrchr(entry->d_name, '.');
		if(strcmp(nome1,".data") == 0){
			strcpy(file_lock,entry->d_name);
			ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + sizeof(int) + strlen(file_lock) + strlen(file_flag));
		}
		
		char *nome2 = strrchr(entry->d_name, '.');
		if(strcmp(nome2,".flag") == 0){
			strcpy(file_flag,entry->d_name);
			ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + sizeof(int) + strlen(file_lock) + strlen(file_flag));
			flag = 1;
		}
	}
	
	//printf("%s - a\n",file_lock);
	//printf("%s - b\n",file_flag);
	
	char dirlock[100] = "./estacionamento/";
	char dirflag[100] = "./estacionamento/";
	char direstimate[100] = "./estacionamento/";
	//char lock_flag_nome[] = ".flag";
	//char string[100];
	
	int percentagem;
	int capacidade;
	int estimativa;
	
	ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (4 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + (3 * strlen(direstimate)));
	
	//printf("%s\n",file_flag);
	
	if(flag == 1){
		strcat(dirlock, file_lock);
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (4 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + (2 * strlen(direstimate)));
		
		strcat(dirflag, file_flag);
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (4 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + strlen(dirflag) + strlen(direstimate));
		
		//printf("%s - a1\n", dirlock);
		//printf("%s - b2\n", dirflag);
		//printf("%d,%d\n", strlen(dirlock),strlen(dirflag));
		
		lockPointer = fopen(dirlock,"r");
		
		char content[100];
		
		fscanf(lockPointer, "%d,%d,%s", &percentagem, &capacidade, content);
		
		int correnteDebitada = 0.1 * capacidade;
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (5 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + strlen(dirflag) + strlen(direstimate) + strlen(content));
		
		//printf("%d\n%d\n%s\n",percentagem, potencia, content);
		
		fclose(lockPointer);
		
		estimativa = calc_estimativa(percentagem, capacidade, correnteDebitada);
		
		char *datetime = file_lock;
		int i;
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (7 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + strlen(dirflag) + strlen(direstimate) + strlen(content));
		
		for(i = 0; i < 4; i++){
			datetime++;
		}
		
		char estimate[100] = "estimate";
		strcat(estimate,datetime);
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (7 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + strlen(dirflag) + strlen(direstimate) + strlen(content));
		
		strcat(direstimate, estimate);
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (7 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + strlen(dirflag) + strlen(direstimate) + strlen(content));
		
		//printf("%s,%d\n",direstimate,estimativa);
		
		estimatePointer = fopen(direstimate,"w");
		
		char fileContent[100];
				
		sprintf(fileContent,"%d,%s",estimativa,content);
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (7 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + strlen(dirflag) + strlen(direstimate) + strlen(content) + strlen(fileContent));

		fputs(fileContent, estimatePointer);
		
		fclose(estimatePointer);
		
		strcat(direstimate, ".flag");
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (7 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + strlen(dirflag) + strlen(direstimate) + strlen(content) + strlen(fileContent));
		
		estimatePointer = fopen(direstimate,"w");
		
		fclose(estimatePointer);
		
		remove(dirlock);
		remove(dirflag);
	}else{
		strcat(dirlock, file_lock);
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (4 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + (2 * strlen(direstimate)));
		
		char *datetime = file_lock;
		int i;
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (6 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + (2 * strlen(direstimate)));
			
		for(i = 0; i < 4; i++){
			datetime++;
		}
		
		char estimate[100] = "estimate";
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (6 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + (2 * strlen(direstimate)) + strlen(estimate));
		
		strcat(estimate,datetime);
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (6 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + (2 * strlen(direstimate)) + strlen(estimate));
		
		strcat(direstimate, estimate);
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (6 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + strlen(direstimate) + strlen(dirflag) + strlen(estimate));
		
		estimatePointer = fopen(direstimate,"w");
		
		char fileContent[100];
		
		sprintf(fileContent,"%d",(-1));
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (6 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + strlen(direstimate) + strlen(dirflag) + strlen(estimate) + strlen(fileContent));
		
		fputs(fileContent, estimatePointer);
		
		fclose(estimatePointer);
		
		strcat(direstimate, ".flag");
		
		ptrFileLock = (char *) realloc (ptrFileLock,sizeof(folder) + sizeof(lockPointer) + sizeof(estimatePointer) + sizeof(entry) + (6 * sizeof(int)) + strlen(file_lock) + strlen(file_flag) + strlen(dirlock) + strlen(direstimate) + strlen(dirflag) + strlen(estimate) + strlen(fileContent));

		estimatePointer = fopen(direstimate,"w");
		
		fclose(estimatePointer);
		
		remove(dirlock);
	}
	
	free(ptrFileLock);

	return 0;
}
