`include "OG.v";
`include "FlipFlop.v";
`include "NSG.v";

module Project
(
	input clock,reset,x, 
	output out
);

wire [2:0] currentState, nextState;

NSG a(currentState,x,nextState);
FlipFlop b(clock,reset,x,nextState,currentState);
OG h(currentState,out);

endmodule