.section .data

.section .text
	.global calc_estimativa
	
calc_estimativa:
	#prologo
	pushl %ebp
	movl %esp, %ebp
	
	movl 8(%ebp),%ecx
	
	movl $100, %eax
	
	movl $1, %esi
loop:
	cmpl %eax, %ecx
	jnle end
	
	addl $30, %ecx
	incl %esi
	
end:
	incl %esi
	
	movl %esi, %eax
	
	#epilogo

	popl %esi
	
	movl %ebp, %esp
	popl %ebp

	ret
