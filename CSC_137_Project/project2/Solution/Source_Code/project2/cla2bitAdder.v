`include "pg_su.v"
module cla2bitAdder
(
	input [1:0] a,b,
	input ci,
	output [1:0] s,c
);

pg_su pg_su1(a,b,ci,s,c);

endmodule
