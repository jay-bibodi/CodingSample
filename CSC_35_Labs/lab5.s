# Take string as input from user and make all upper case characters entered by user in lower case and display string in lower case 

.data

Message:
	.ascii "Please enter text message :\n\0"
newMessage:
	.space 100
length:
	.long 0
fixed:
	.ascii "Fixed sentence with all lower case characters :\n\0"
newLine:
	.ascii "\n\0"
.text
.global _start

_start:

	mov $newLine, %eax
	call PrintCString

	mov $Message, %eax
	call PrintCString

	mov $newMessage, %eax
	mov $100, %ebx
	call ScanCString

	mov $newMessage, %eax
	call LengthCString
	
	mov %ebx, %ecx
	
	mov $newLine, %eax
	call PrintCString	

	mov $length, %eax
	mov $0, %ebx	

Loop:

	cmp %ecx, %ebx
	je End

	mov newMessage(%ebx), %al

	cmp $65, %al
	jl doNotChange
	cmp $90, %al
	jg doNotChange

	add $32, newMessage(%ebx)
	add $1, %ebx
	
	jmp Loop

doNotChange:

	add $1, %ebx
	jmp Loop
End:
	mov $fixed, %eax
	call PrintCString
	
	mov $newMessage, %eax
	call PrintCString

	mov $newLine, %eax
	call PrintCString

	call EndProgram
