#Calculate total price for all cookies bought ! All cookies are contained in different boxes and each box have different number of cookies.

.data

ChocolateCookie:	
	.ascii "Chocolate Cookie : 20 per box!\n\0"

OreoCookie:
	.ascii "Oreo Cookie : 10 per box!\n\0"

StrawberryCookie:
	.ascii "Strawberry Cookie : 12 per box!\n\0"

NumberOfChocolateCookieBox:
	.ascii "How many boxes of Chocolate Cookies?\n\0"

NumberOfOreoCookieBox:
	.ascii "How many boxes of Oreo Cookies?\n\0"

NumberOfStrawberryCookieBox:
	.ascii "How many boxes of Strawberry Cookies?\n\0"

WowText:
	.ascii "Wow! That's a total of \0"

totalCookies:
	.ascii " cookies!\n\0"

.text
.global _start

	mov $0, %eax

_start:
	mov $ChocolateCookie, %eax
	call PrintCString

	mov $OreoCookie, %eax
	call PrintCString

	mov $StrawberryCookie, %eax
	call PrintCString

	mov $NumberOfChocolateCookieBox, %eax
	call PrintCString

	call ScanUInt
	mov $20,%ebx
	mul %ebx
	
	mov %eax,%ecx
	
	mov $NumberOfOreoCookieBox,%eax
	call PrintCString

	call ScanUInt
	mov $10, %ebx
	mul %ebx

	ADD %eax,%ecx
	#mov %eax,%esi	

	mov $NumberOfStrawberryCookieBox,%eax
	call PrintCString

	call ScanUInt
	mov $12, %ebx
	mul %ebx
	
	ADD %eax,%ecx
	#mov %eax,%edi

	mov $WowText,%eax
	call PrintCString

	mov %ecx,%eax
	call PrintUInt

	mov $totalCookies,%eax
	call PrintCString	

	call EndProgram
	

