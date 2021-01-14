.section .data

.section .text
	.global calc_estimativa
	
calc_estimativa:
	#prologo
	pushl %ebp
	movl %esp, %ebp
	
	pushl %ebx
	pushl %esi
	
	movl $0, %edx # limpa %edx
	
	movl 8(%ebp),%eax # percentagem atual (%)
	movl 12(%ebp), %ecx # capacidade bateria (ah)
	
	movl $100, %ebx # percentagem total (100)
	
	subl %eax, %ebx # percentagem necessaria para os 100
	
	movl %ebx, %eax # percentagem necessaria para os 100
	
	movl $0, %ebx # limpa ebx
	movl $0, %edx # limpa edx
	
	imull %ecx, %eax # capacidade * (percentagem necessaria)
	
	movl $100, %ecx
	
	cdq
	
	idivl %ecx
	
	movl $13, %ecx
	
	idivl %ecx # corrente debitada pelo aparelho de carregamento
	
	#epilogo

	popl %esi
	popl %ebx
	
	movl %ebp, %esp
	popl %ebp

	ret
