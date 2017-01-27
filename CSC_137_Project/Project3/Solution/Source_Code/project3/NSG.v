module NSG
(
	input [2:0] q,
	input x,
	output [2:0] d
);

assign d[2] = (q[2] & q[1]) | (q[2] & q[0]) | (q[1] & q[0] & x);
assign d[1] = (q[2] & ~x) | (q[1] & ~q[0] & ~x) | (~q[1] & q[0] & ~x);
assign d[0] = (~q[1] & x) | (q[1] & ~q[0]);

endmodule
