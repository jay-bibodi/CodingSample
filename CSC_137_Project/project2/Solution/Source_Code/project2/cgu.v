module cgu(p,g,ci,c);
input [1:0] p;
input [1:0] g;
input ci;
output [1:0] c;

wire nor1,nor2,nor3,nor4,nor5;

nor g0ci(nor4,g[0],ci);
nor g0p0(nor5,g[0],p[0]);

nor nor45(c[0],nor4,nor5);

nor g1g0ci(nor1,g[1],g[0],ci);
nor g1g0p0(nor2,g[1],g[0],p[0]);
nor g1p1(nor3,g[1],p[1]);

nor nor123(c[1],nor1,nor2,nor3);

endmodule
