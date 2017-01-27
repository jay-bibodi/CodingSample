#include <math.h>
#include <stdio.h>
#include <string.h>
main()
{

float f1, f2, f3;
unsigned int x;

long double f4;

f1 = 0.625;
memcpy(&x, &f1, 4);
printf("Floating point %f is stored as %x\n\n", f1, x);

x = 0x41dd0000;  // Binary value : 0 10000011 10111010000000000000000
memcpy(&f1, &x, 4);
printf("32-bit %08x has floating point value %f\n\n", x, f1);

x = 0x7f7fffff; //+largest value Binary value : 0 11111110 11111111111111111111111
memcpy(&f1, &x, 4);
printf("32-bit %08x has floating point value %e\n\n", x, f1);

x = 0x00800000; //+smallest value  Binary value : 0 00000001 00000000000000000000000
memcpy(&f1, &x, 4);
printf("32-bit %08x has floating point value %e\n\n", x, f1);

x = 0x7fab9cc0;  // NaN  Binary value : 0 11111111 01010111001110011000000
memcpy(&f1,&x,4);
printf("32-bit %08x has floating point value %e\n\n", x, f1);

x = 0x7f800000;  // infinity Binary value : 0 11111111 00000000000000000000000
memcpy(&f1,&x,4);
printf("32-bit %08x has floating point value %e\n\n", x, f1);

x = 0xff7fffff;  // smallest negative Single precision FP numbers Binary value : 1 11111110 11111111111111111111111
memcpy(&f1,&x,4);
printf("32-bit %08x has floating point value %e\n\n", x, f1);	

x = 0x80800000;  // largest negative Single precision FP numbers Binary value : 1 00000001 00000000000000000000000
memcpy(&f1,&x,4);
printf("32-bit %08x has floating point value %e\n\n", x, f1);
}