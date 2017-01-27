module Project1
(
	input x,y,z,
	output f
);

wire out1, out2, out3;

not  n1(out1,y);
nand n2(out2,out1,x);
nand n3(out3,y,z);
nand n4(f,out2,out3);

endmodule
