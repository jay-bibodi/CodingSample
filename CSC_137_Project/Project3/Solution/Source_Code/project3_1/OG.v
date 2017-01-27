module OG
(
	input [2:0] q,
	output  z
);

 assign z = q[2] & ~q[1] & ~q[0];

endmodule
