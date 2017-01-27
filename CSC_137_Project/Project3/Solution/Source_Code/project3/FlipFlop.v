module FlipFlop
(
	input clock,reset,x,
	input [2:0] nextState,
	output reg [2:0] currentState	
);

always@(posedge clock,posedge reset)
begin
	if(reset == 1)
	 	currentState <= 3'b000;
	else 
		currentState <= nextState;		
end

endmodule

