# Generate Silly Sentence game. Player A will enter noun , Player B will enter verb , Player A will enter preposition and lastly player B 
# will enter noun. join all the words entered by Player A and Player B and display it as string using interrupts. 

.data

Message:
	.ascii "Welcome to Silly Sentences!\n"

PlayerANoun:
	.ascii "Player A, Enter a Noun :"

PlayerANounUserValue:
	.space 20

PlayerBVerb:
	.ascii "Player B, Enter a Verb :"

PlayerBVerbUserValue:
	.space 20

PlayerAPreposition:
	.ascii "Player A, Enter a Preposition :"

PlayerAPrepositionUserValue:
	.space 20

PlayerBNoun:
	.ascii "Player B, Enter a Noun :"

PlayerBNounUserValue:
	.space 20	

SentenceCreated:
	.ascii "Sentence created is:\n"

VT:
	.ascii "\x1B[H\x1B[2J"

.text
.global _start

_start:

	#Write--> Welcome to silly Sentence

	mov $4, %eax
	mov $1, %ebx
	mov $Message, %ecx
	mov $28, %edx
	int $0x80

	#Write--> Player A, Enter a Noun:

	mov $4, %eax
	mov $1, %ebx
	mov $PlayerANoun, %ecx
	mov $24, %edx
	int $0x80

	#Read--> noun entered from player A 

	mov $3, %eax
	mov $0, %ebx
	mov $PlayerANounUserValue, %ecx
	mov $20, %edx
	int $0x80

	#Clear Screen

	mov $4, %eax
	mov $1, %ebx
	mov $VT, %ecx
	mov $7, %edx
	int $0x80

	#Write--> Welcome to silly Sentence for player B

	mov $4, %eax
	mov $1, %ebx
	mov $Message, %ecx
	mov $28, %edx
	int $0x80

	#Write--> Player B, Enter a Verb

	mov $4, %eax
	mov $1, %ebx
	mov $PlayerBVerb, %ecx
	mov $24, %edx
	int $0x80

	#Read--> verb entered from player B 

	mov $3, %eax
	mov $0, %ebx
	mov $PlayerBVerbUserValue, %ecx
	mov $20, %edx
	int $0x80

	#Clear Screen

	mov $4, %eax
	mov $1, %ebx
	mov $VT, %ecx
	mov $7, %edx
	int $0x80

	#Write--> Welcome to silly Sentence for player A

	mov $4, %eax
	mov $1, %ebx
	mov $Message, %ecx
	mov $28, %edx
	int $0x80

	#Write--> Player A, Enter a Preposition

	mov $4, %eax
	mov $1, %ebx
	mov $PlayerAPreposition, %ecx
	mov $31, %edx
	int $0x80

	#Read--> preposition entered from player A 

	mov $3, %eax
	mov $0, %ebx
	mov $PlayerAPrepositionUserValue, %ecx
	mov $20, %edx
	int $0x80

	#Clear Screen

	mov $4, %eax
	mov $1, %ebx
	mov $VT, %ecx
	mov $7, %edx
	int $0x80

	#Write--> Welcome to silly Sentence for player B to enter noun

	mov $4, %eax
	mov $1, %ebx
	mov $Message, %ecx
	mov $28, %edx
	int $0x80

	#Write--> Player B, Enter a noun

	mov $4, %eax
	mov $1, %ebx
	mov $PlayerBNoun, %ecx
	mov $24, %edx
	int $0x80

	#Read--> noun entered from player B 

	mov $3, %eax
	mov $0, %ebx
	mov $PlayerBNounUserValue, %ecx
	mov $20, %edx
	int $0x80

	#Clear Screen

	mov $4, %eax
	mov $1, %ebx
	mov $VT, %ecx
	mov $7, %edx
	int $0x80

	#Write--> Welcome to silly Sentence for sentence created

	mov $4, %eax
	mov $1, %ebx
	mov $Message, %ecx
	mov $28, %edx
	int $0x80
	
	mov $4, %eax
	mov $1, %ebx
	mov $SentenceCreated, %ecx
	mov $21, %edx
	int $0x80

	# Merge All Values Entered by both player A and player B

	mov $4, %eax
	mov $1, %ebx
	mov $PlayerANounUserValue, %ecx
	mov $20, %edx
	int $0x80

	mov $4, %eax
	mov $1, %ebx
	mov $PlayerBVerbUserValue, %ecx
	mov $20, %edx
	int $0x80

	mov $4, %eax
	mov $1, %ebx
	mov $PlayerAPrepositionUserValue, %ecx
	mov $20, %edx
	int $0x80

	mov $4, %eax
	mov $1, %ebx
	mov $PlayerBNounUserValue, %ecx
	mov $20, %edx
	int $0x80

	# Kill Program

	mov $1, %eax
	int $0x80

