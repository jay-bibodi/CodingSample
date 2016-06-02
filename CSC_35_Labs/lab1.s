#Write Hello World, Your name , any one quote and year from which you will graduate from university


.data

message :
	.ascii  "Hello World!\n\0"

name :
	.ascii  "My name is Jay Nikhil Bibodi\n\0"

quote :
	.ascii "Quote for the day : The best preparation for tomorrow is doing your best today.\n\0"

graduateTextOne :
	.ascii "I will graduate in \0"

graduateTextTwo :
	.ascii " from Sacramento State!\n\0"


.text
.global _start

_start:

	mov $message, %eax
	call PrintCString

	mov $name, %eax
	call PrintCString

	mov $quote, %eax
	call PrintCString
	
	mov $graduateTextOne, %eax
	call PrintCString

	mov $2018, %eax
	call PrintUInt

	mov $graduateTextTwo, %eax
	call PrintCString

	call EndProgram	


