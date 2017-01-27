`include "cla8bitAdder.v"
`include "invertor.v"
`include "myXOR.v"

module adderSubtractor
(
	input [7:0] a,b,
	input cin,
	output [7:0] s,c,
	output ovf
);

wire [7:0] binvertor;

invertor invt1(cin,b[7:0],binvertor);

cla8bitAdder add1(a,binvertor,cin,s,c);

myXOR xyz(c[7],c[6],ovf);

endmodule
