`include "NSG.v"
`include "OG.v"
module FF
(
	input clock,reset,x,
	output out
);

wire [2:0] csnsg,nsnsg
reg [2:0] currentState, nextState;

// section 1
//always@(*)
//begin
	NSG nsgone(csnsg,x,nsnsg);
//end

// section 2
//always@(*)
//begin
	OG ogone(csnsg,out);
//end

// section 3
always@(posedge clock,posedge reset)
begin
	if(reset == 1)
	 	currentState <= 3'b000;
	else 
		currentState <= nextState;		
end

endmodule
