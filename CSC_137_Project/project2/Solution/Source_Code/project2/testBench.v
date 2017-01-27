`include "adderSubtractor.v"
module tester();
reg [7:0] a,b;
reg cin;
wire [7:0] s,c;
wire ovf;

adderSubtractor adsub(a,b,cin,s,c,ovf);

initial begin
a=8'hFF;b=8'h01;cin=1'b0;
#10 $display("a=%8b b=%8b cin=%b sum = %8b Overflow = %b C7 = %b C6 = %b ",a,b,cin,s,ovf,c[7],c[6]);

a=8'h7F;b=8'h01;cin=1'b0;
#10 $display("a=%8b b=%8b cin=%b sum = %8b Overflow = %b C7 = %b C6 = %b ",a,b,cin,s,ovf,c[7],c[6]);

a=8'h01;b=8'hFF;cin=1'b0;
#10 $display("a=%8b b=%8b cin=%b sum = %8b Overflow = %b C7 = %b C6 = %b ",a,b,cin,s,ovf,c[7],c[6]);

a=8'h55;b=8'hAA;cin=1'b0;
#10 $display("a=%8b b=%8b cin=%b sum = %8b Overflow = %b C7 = %b C6 = %b ",a,b,cin,s,ovf,c[7],c[6]);

a=8'h80;b=8'h01;cin=1'b1;
#10 $display("a=%8b b=%8b cin=%b sum = %8b Overflow = %b C7 = %b C6 = %b ",a,b,cin,s,ovf,c[7],c[6]);

a=8'h6C;b=8'hCA;cin=1'b1;
#10 $display("a=%8b b=%8b cin=%b sum = %8b Overflow = %b C7 = %b C6 = %b ",a,b,cin,s,ovf,c[7],c[6]);

a=8'hDD;b=8'h09;cin=1'b1;
#10 $display("a=%8b b=%8b cin=%b sum = %8b Overflow = %b C7 = %b C6 = %b ",a,b,cin,s,ovf,c[7],c[6]);

a=8'hEF;b=8'h11;cin=1'b0;
#10 $display("a=%8b b=%8b cin=%b sum = %8b Overflow = %b C7 = %b C6 = %b ",a,b,cin,s,ovf,c[7],c[6]);

end
endmodule
