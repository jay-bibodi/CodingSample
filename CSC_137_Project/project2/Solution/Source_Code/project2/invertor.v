module invertor
(
	input cin,
	input [7:0] b,
	output reg [7:0] bout
);

always@(*)
begin
	if (cin==1'b1)
		bout = ~b;
	else
		bout = b;
end

endmodule
