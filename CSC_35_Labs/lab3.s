# Conditional Jumping Program based on values entered by user

.data

SuperHuman:
	.ascii "Welcome to Super Human Generator Training! You will be placed in one of the four teams. Get ready to become SUPER HUMAN!\n\0"

NewLine:
	.ascii "\n\0"

SeekingGood:
	.ascii "Seeking good ? (1=yes, 2=no)\n\0"

SpiderWebText:
	.ascii "Do you like spider's web ? (1=yes, 2=no)\n\0"

AsgardText:
	.ascii "Do you want to travel Asgard ? (1=yes, 2=no)\n\0"

JokerText:
	.ascii "You are in Joker's Team!\n\0"

LokiText:
	.ascii "You are in Loki's Team!\n\0"

IronManText:
	.ascii "You are in Iron Man's Team!\n\0"

SpiderManText:
	.ascii "You are in Spider Man's Team!\n\0"

WrongInputText:
	.ascii "You entered wrong value! Program Ended. . .\n\0"

.text

.global _start

_start:

	mov $NewLine, %eax
	call PrintCString

	mov $SuperHuman, %eax
	call PrintCString
	
	mov $NewLine, %eax
	call PrintCString
	
	mov $SeekingGood,%eax
	call PrintCString

	call ScanUInt
	cmp $1, %eax
	je Good
	
	cmp $2, %eax
	je Bad

	mov $WrongInputText, %eax
	call PrintCString
	jmp End

Bad:
	
	mov $AsgardText, %eax
	call PrintCString

	call ScanUInt
	cmp $1, %eax
	je LokiTeam

	cmp $2, %eax
	je JokerTeam

	mov $WrongInputText, %eax
	call PrintCString
	jmp End	
	
Good:
	mov $SpiderWebText,%eax
	call PrintCString

	call ScanUInt
	cmp $1,%eax
	je SpiderManTeam

	cmp $2,%eax
	je IronManTeam

	mov $WrongInputText,%eax
	call PrintCString
	jmp End	

SpiderManTeam:
	
	mov $SpiderManText,%eax
	call PrintCString
	jmp End

IronManTeam:
	
	mov $IronManText,%eax
	call PrintCString
	jmp End

LokiTeam:
		
	mov $LokiText,%eax
	call PrintCString
	jmp End

JokerTeam:
	
	mov $JokerText,%eax
	call PrintCString

End:

	call EndProgram

