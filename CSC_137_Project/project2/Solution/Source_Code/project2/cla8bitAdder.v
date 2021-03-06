`include "cla2bitAdder.v"
module cla8bitAdder
(
	input [7:0] a,b,
	input cin,
	output [7:0] s, c
);

cla2bitAdder unit1(a[1:0],b[1:0],cin,s[1:0],c[1:0]);
cla2bitAdder unit2(a[3:2],b[3:2],c[1],s[3:2],c[3:2]);
cla2bitAdder unit3(a[5:4],b[5:4],c[3],s[5:4],c[5:4]);
cla2bitAdder unit4(a[7:6],b[7:6],c[5],s[7:6],c[7:6]);

endmodule

