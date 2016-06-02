# Generating random number , ask user to guess the number generated randomly and display total tries user took to guess number randomly generated.

.data

Guess:
	.ascii "Guess:\0"
NumberGeneratedOne:
	.ascii "Random number generated between 1 and \0"
NumberGeneratedTwo:
	.ascii " ! Please guess the number. . .\n\0"
TooLow:
	.ascii "You are too low\n\0"
TooHigh:
	.ascii "You are too high\n\0"
TotalOne:
	.ascii "Correct! It took you a total of \0"
TotalTwo:
	.ascii " guesses!\n\0"
NewLine:
	.ascii "\n\0"

.global _start

	mov $0, %ebx

_start:

	mov $100, %esi

	mov $NewLine, %eax
	call PrintCString

	mov $NumberGeneratedOne, %eax
	call PrintCString

	mov %esi, %eax
	call PrintUInt

	mov $NumberGeneratedTwo, %eax
	call PrintCString

	mov $NewLine, %eax
	call PrintCString

	mov %esi, %eax
	call Random

	mov %eax, %ecx

Loop:

	mov $Guess, %eax
	call PrintCString

	call ScanUInt	
	cmp %eax,%ecx

	jg Low
	
	jl High

	je End

Low:
	
	mov $TooLow, %eax
	call PrintCString
	
	mov $NewLine, %eax
	call PrintCString

	add $1, %ebx

	jmp Loop

High:

	mov $TooHigh, %eax
	call PrintCString

	mov $NewLine, %eax
	call PrintCString

	add $1, %ebx

	jmp Loop
	
End:

	mov $TotalOne, %eax
	call PrintCString
	
	add $1, %ebx
	mov %ebx, %eax
	call PrintUInt

	mov $TotalTwo, %eax
	call PrintCString

	mov $NewLine, %eax
	call PrintCString

	call EndProgram
