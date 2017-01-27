`include "cla8bitAdder.v"
module tester();
reg [7:0] a;
reg [7:0] b;
reg cin;
wire [7:0] s,c;
//wire c6;
//wire c7;

cla8bitAdder adsub(a,b,cin,s,c);//c7,c6

initial begin
a=8'hFF;b=8'h01;cin=1'b0;
#10 $display("Sum = %8b CarryOut = %b",s,c[7]);

a=8'h7F;b=8'h01;cin=1'b0;
#10 $display("Sum = %8b CarryOut = %b",s,c[7]);

a=8'h01;b=8'hFF;cin=1'b0;
#10 $display("Sum = %8b CarryOut = %b",s,c[7]);

a=8'h55;b=8'hAA;cin=1'b0;
#10 $display("Sum = %8b CarryOut = %b",s,c[7]);

a=8'h80;b=8'h01;cin=1'b1;
#10 $display("Sum = %8b CarryOut = %b",s,c[7]);

a=8'h6C;b=8'hCA;cin=1'b1;
#10 $display("Sum = %8b CarryOut = %b",s,c[7]);

end
endmodule
