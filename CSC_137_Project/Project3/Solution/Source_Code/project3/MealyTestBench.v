`include "MealyProject.v";

module MealyTestBench();
reg clock,reset,x;
wire z;
MealyProject projectTwo(clock,reset,x,z);

initial begin
$monitor("%4d:		z = %b",$time,z);
clock=0;
reset=1;      //Resets the FlipFlop
x=0; 
#10 reset=0;      //end reset
end 

always
begin
#5clock=~clock; //generates a clock signal with period 10
end

initial begin  //ONE INPUT PER CLOCK CYCLE
#10 x = 0; $display ("%4d:	  x = %b",$time,x);
#10 x = 0; $display ("%4d:    x = %b",$time,x);
#10 x = 1; $display ("%4d:    x = %b",$time,x);
#10 x = 0; $display ("%4d:    x = %b",$time,x);
#10 x = 0; $display ("%4d:    x = %b",$time,x);
#10 x = 1; $display ("%4d:    x = %b",$time,x);
#10 x = 1; $display ("%4d:    x = %b",$time,x);
#10 x = 0; $display ("%4d:    x = %b",$time,x);
#10 x = 0; $display ("%4d:    x = %b",$time,x);
#10 x = 1; $display ("%4d:    x = %b",$time,x);
#10 x = 0; $display ("%4d:    x = %b",$time,x);
#10 x = 0; $display ("%4d:    x = %b",$time,x);
#10 x = 1; $display ("%4d:    x = %b",$time,x);
#10 $finish;
end
endmodule
