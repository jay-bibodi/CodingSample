/*
 * Problem 1: Dog Enthusiast
 */
owner(sam,lucy).
owner(sam,max).
owner(sam,lola).
owner(kevin,toby).
owner(kevin,mau).
breed(lucy,dog).
breed(max,dog).
breed(lola,dog).
breed(toby,dog).
breed(mau,cat).

dogEnthusiast(X):- owner(X,Y),breed(Y,dog),owner(X,Z),breed(Z,dog),not(Y=Z).

/*
 * Problem 2: List Picker
 */

iteminlist([_|Y], M, Z):- not(M=1),NewM is M - 1,iteminlist(Y,NewM, Z).
iteminlist([X|_], 1, X).
listPicker([X|Y], [M|N], [L|K]):- iteminlist([X|Y], M, L), listPicker([X|Y],N,K).
listPicker([_|_],[], []).

/*
 *  Problem 3: Crypto
 */

genList(9,[9]).
genList(X,[X|Y]):- X < 10, Z is X + 1, genList(Z,Y).

inList(X,[X|_]).
inList(X,[_|Z]):- inList(X,Z).

crypto(G,R,I,P,T,O,C,K) :-
	genList(0,L),
	inList(T,L),inList(O,L),inList(C,L),inList(K,L),inList(G,L),inList(R,L),inList(I,L),inList(P,L),
	not(T=O),not(T=C),not(T=K),not(O=C),not(O=K),not(C=K),
	X is 1000*T + 100*O + 10*C + K,
	A is X * X,
	B is G*10000000 + R*1000000 + I*100000 + P*10000 + T*1000 + O*100 + C*10 + K,
	A = B.

/*
 *  Problem 4: Interleave
 */
interleave([X|Y],[M|N],[X,M|Z]):- interleave(Y,N,Z).
interleave([],[],[]).
interleave([],[M|N],[999,M|N]).
interleave([X|Y],[],[999,X|Y]).

/*
 * Problem 5: DigitInc
 */
digitinc(X,Y):- X < 10, X = 9 ,Y is 0.
digitinc(X,Y):- X < 10, not( X = 9 ), Y is X+1.
digitinc(X,Y):- M is X mod 10, not(M = 9), N is div(X,10), K is M+1, digitinc(N,L), Y is L*10+K.
digitinc(X,Y):- M is X mod 10, M = 9, N is div(X,10), K is 0, digitinc(N,L), Y is L*10+K.