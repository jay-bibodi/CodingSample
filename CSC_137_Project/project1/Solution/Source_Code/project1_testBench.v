//`timescale 1ns/1ps
`include "Project1_AssignStatement.v"
module tester();
reg x,y,z;

wire f;

Project1_AssignStatement po(x,y,z,f);

initial begin

$display ("Time x y z  f");
$monitor ("%4d        %b", $time,f);
x=0; y = 0; z = 0; $display("%4d %b %b %b", $time, x, y,z);
//test1 
#1 //simulate 

x=0; y = 0; z = 1; $display("%4d %b %b %b", $time, x, y,z);
//test2 
#1 //simulate

x=0; y = 1; z = 0; $display("%4d %b %b %b", $time, x, y,z);
//test3 
#1 //simulate 

x=0; y = 1; z = 1; $display("%4d %b %b %b", $time, x, y,z);
//test4 
#1 //simulate

x=1; y = 0; z = 0; $display("%4d %b %b %b", $time, x, y,z);
//test5 
#1 //simulate

x=1; y = 0; z = 1; $display("%4d %b %b %b", $time, x, y,z);
//test6 
#1 //simulate

x=1; y = 1; z = 0; $display("%4d %b %b %b", $time, x, y,z);
//test7 
#1 //simulate

x=1; y = 1; z = 1; $display("%4d %b %b %b", $time, x, y,z);
//test8 
#1 //simulate
$finish;
end
endmodule
