module myXOR(x,y,f);

input x,y;
output f;

wire xor1, xor2, xor3, xor4;

not  xxor1(xor1,x);
not  yxor2(xor2,y);

nand yxor1(xor3,xor1,y);
nand xxor2(xor4,xor2,x);
nand xor34(f,xor3,xor4);

endmodule
