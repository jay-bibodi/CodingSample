`include "cgu.v"
module pg_su(a,b,ci,s,c);

input [1:0] a,b;
input ci;
output [1:0] s,c;

wire [1:0] pwire;
wire [1:0] gwire;

myXOR myXOR0(a[0],b[0],pwire[0]);
myXOR myXOR1(a[1],b[1],pwire[1]);

and gwire0(gwire[0],a[0],b[0]);
and gwire1(gwire[1],a[1],b[1]);

cgu cgu1(pwire,gwire,ci,c);

myXOR myXOR2(pwire[0],ci,s[0]);
myXOR myXOR3(pwire[1],c[0],s[1]);

endmodule
