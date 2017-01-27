module Project1_AssignStatement
(
	input x,y,z,
	output f
);

assign f = ~y & x | y & z;

endmodule
