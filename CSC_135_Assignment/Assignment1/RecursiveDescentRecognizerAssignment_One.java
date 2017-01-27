import java.io.IOException;
import java.util.Scanner;

/** 
 * --------------------------------------------
 * Recognizer for expression grammar
 * Written by Jay Bibodi
 * 
 * to run on Athena (linux) -
 * save as: RecursiveDescentRecognizerAssignment_One.java
 * compile: javac RecursiveDescentRecognizerAssignment_One.java
 * execute: java RecursiveDescentRecognizerAssignment_One
 *
 * Tested for below test case : legal output
 * BAAAAAAIIIROED$
 * BR(11<00)BE$
 * BE$
 * 
 * EBNF Grammar is -
 *
 * block   ::= 	B {statemt} E [D]
 * statemt ::= 	asignmt | ifstmt | while | inpout | block
 * asignmt ::= 	A ident ~ exprsn
 * ifstmt  ::= 	I comprsn T block [L block]
 * while   ::= 	W comprsn block
 * inpout  ::= 	iosym ident {, ident}
 * comprsn ::= 	( oprnd opratr oprnd )
 * exprsn  ::= 	factor {sumop factor}
 * factor  ::= 	oprnd {prodop oprnd}
 * oprnd   ::= 	integer | ident | ( exprsn )
 * ident   ::= 	letter {char}
 * char    ::= 	letter | digit
 * integer ::= 	digit {digit}
 * iosym   ::= 	R | O
 * opratr  ::= 	< | = | > | !
 * sumop   ::= 	+ | -
 * prodop  ::= 	* | /
 * letter  ::= 	X | Y | Z
 * digit   ::= 	0 | 1
 *
 * --------------------------------------------
 */
public class RecursiveDescentRecognizerAssignment_One 
{
	// store string entered by user in inputString
	static String inputString;
	
	// maintain the index at which current token is parsed
	static int index = 0;
	
	// if no error occurs errorflag remains 0 else its value increase!
	static int errorflag = 0;
	
	/**
	 * Main method where execution of program begins and ends
	 * @param args
	 * @throws IOException
	 */
	public static void main (String[] args) throws IOException
	{
		RecursiveDescentRecognizerAssignment_One rec = new RecursiveDescentRecognizerAssignment_One();
		Scanner input = new Scanner(System.in);
		System.out.print("\n" + "enter an expression: ");
		inputString = input.nextLine();
		input.close();
		if(!inputString.contains("$"))
			inputString = inputString+("$");
		rec.start();
	}
	
	/**
	 * This method is used to check start and end of the input string and output legal if no errors occurs in input string
	 */
	private void start()
	{
		if(token() == 'B')
		{
			block();
			match('$');
			if (errorflag == 0)
				System.out.println("legal." + "\n");
			else
				System.out.println("errors found." + "\n");
		}
		else
		{
			System.out.println("errors found." + "\n");
		}
		
	}

	/**
	 * This method returns token at current index location
	 * char    ::= 	letter | digit
	 */
	private char token()
	{ 
		return (inputString.charAt(index)); 
	}
	
	/**
	 * This method increase current index location by 1
	 */
	private void advancePtr()
	{ 
		if (index < (inputString.length()-1)) 
			index++; 
	}

	/**
	 * This method matches the current token and 
	 * invokes advancePtr method to increase the index by 1 
	 * @param T is character
	 */
	private void match(char T)
	{ 
		if (T == token()) 
			advancePtr(); 
		else 
			error(); 
	}
	
	/**
	 * This method sets errorflag to 1 if any error occurs in program.
	 */
	private void error()
	{
		System.out.println("error at position: " + index);
		errorflag = 1;
		advancePtr();
	}
	
	// Start of all non terminals method initialization.
	
	/**
	 * This method is used when token in input string is either 0 or 1.
	 * digit   ::= 	0 | 1
	 */
	private void digit()
	{
		if(token()== '0' || token() == '1')
			match(token());
		else
			error();
	}
	
	/**
	 * This method is used when token in input string is either X,Y or Z.
	 * letter  ::= 	X | Y | Z
	 */
	private void letter()
	{
		if(token() == 'X' || token() == 'Y' || token() == 'Z')
			match(token());
		else
			error();
	}
	
	/**
	 * This method is used when token in input string is either * or /.
	 * prodop  ::= 	* | /
	 */
	private void prodop()
	{
		if(token() == '*' || token() == '/')
			match(token());
		else
			error();
	}
	
	/**
	 * This method is used when token in input string is either + or -.
	 * sumop   ::= 	+ | -
	 */
	private void sumop()
	{
		if(token() == '+' || token() == '-')
			match(token());
		else
			error();
	}
	
	/**
	 * This method is used when token in input string is either <,=,> or ! 
	 * opratr  ::= 	< | = | > | !
	 */
	private void opratr()
	{
		if(token() == '<' || token() == '=' || token() == '>' || token() == '!')
			match(token());
		else
			error();
	}
	
	/**
	 * This method is used when token in input string is either R or O.
	 * iosym   ::= 	R | O
	 */
	private void iosym()
	{
		if(token() == 'R' || token() == 'O')
			match(token());
		else
			error();
	}
	
	/**
	 * This method is used when token in input string is value of digit non terminal. 
	 * integer ::= 	digit {digit}
	 */
	private void integer()
	{
		if(token() == '0' || token() == '1')
			digit();
		while(token() == '0' || token() == '1')
			digit();
	}
	
	/**
	 * This method is used when token in input string is value of digit or letter non terminal. 
	 */
	private void charProduction()
	{
		if(token() == '0' || token() == '1')
			digit();
		if(token() == 'X' || token() == 'Y' || token() == 'Z') 
			letter();
	}
	
	/**
	 * This method is used when token in input string is value of letter or charProduction non terminal. 
	 * ident   ::= 	letter {char}
	 */
	private void ident()
	{
		if(token() == 'X' || token() == 'Y' || token() == 'Z')
			letter();
		while(token()=='0' || token() == '1' || token() == 'X' || token() == 'Y' || token() == 'Z')
			charProduction();
	}
	
	/**
	 * This method is used when token in input string is value of integer, token ( , exprsn , token ) or ident non terminal. 
	 * oprnd   ::= 	integer | ident | ( exprsn )
	 */
	private void oprnd()
	{
			if(token() == '0' || token() == '1')
				integer();
			if(token() == '(')
				match('(');
			//exprsn();
			if	(token()== '0' || token()=='1' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == '(')	
			{
				if(token()== '0' || token()=='1')
					integer();
				if(token() == 'X' || token() == 'Y' || token() == 'Z')
					letter();
				if(token() == '(')
					match('(');
			}
			if(token() == ')')	
				match(')');
			if(token() == 'X' || token() == 'Y' || token() == 'Z')
				ident();
	}
	
	/**
	 * This method is used when token in input string is value of oprnd, prodop or oprnd non terminal. 
	 * factor  ::= 	oprnd {prodop oprnd}
	 */
	private void factor()
	{
		if	(token()== '0' || token()=='1' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == '(' )
		{
			oprnd();
		}
		while(token()=='*' || token()=='/' || token()== '0' || token()=='1' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == '(' )
		{
			if(token()=='*' || token()=='/')
				prodop();
			if	(token()== '0' || token()=='1' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == '(' )
			{
				oprnd();
			}
		}
	}
	
	/**
	 * This method is used when token in input string is value of factor, sumop or factor non terminals
	 * exprsn  ::= 	factor {sumop factor}
	 */
	private void exprsn()
	{
		if(token()== '0' || token()=='1' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == '(' )
		{
			factor();
		}
		while(token() == '+' || token() == '-' || token()== '0' || token()=='1' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == '(')
		{
			if(token() == '+' || token() == '-')
				sumop();
			if(token()== '0' || token()=='1' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == '(' )
			{
				factor();
			}
		}
	}
	
	/**
	 * This method is used when token in input string is value of token (, oprnd, opratr or oprnd non terminals.
	 * comprsn ::= 	( oprnd opratr oprnd )
	 */
	private void comprsn()
	{
		if(token() == '(')
			match('(');
		while(token()=='0' || token() == '1' || token() == '(' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == '<' || token() == '=' || token() == '>' || token() == '!')
		{	
			if(token()=='0' || token() == '1' || token() == '(' || token() == 'X' || token() == 'Y' || token() == 'Z')	
			{
				oprnd();
			}
			if(token() == '<' || token() == '=' || token() == '>' || token() == '!')
				opratr();
			if(token()=='0' || token() == '1' || token() == '(' || token() == 'X' || token() == 'Y' || token() == 'Z')
			{
				oprnd();
			}
		}	
	}
	
	/**
	 * This method is used when token in input string is value of iosym, ident , token , , or ident non terminals
	 * inpout  ::= 	iosym ident {, ident}
	 */
	private void inpout()
	{
		if(token() == 'R' || token() == 'O')
			iosym();
		if(token() == 'X' || token() == 'Y' || token() == 'Z')	
			ident();
		while(token() == ',' || token() == 'X' || token() == 'Y' || token() == 'Z')
		{	
			if(token() == ',')
				match(',');
			if(token() == 'X' || token() == 'Y' || token() == 'Z')
				ident();
		}	
	}
	
	/**
	 * This method is used when token in input string is value of token W, comprsn or block non terminals
	 * while   ::= 	W comprsn block
	 */
	private void whileProduction()
	{
		if(token() == 'W')
			match('W');
		if(token() == '(')
			comprsn();
		if(token() == 'B' || token() == 'A' || token() == 'I' || token() == 'W' || token() == 'R' || token() == 'O' || token() == 'E' || token() == 'D')
			block();
	}
	
	/**
	 * This method is used when token in input string is value of token I, comprsn, token T, block, token L or block non terminals
	 * ifstmt  ::= 	I comprsn T block [L block]
	 */
	private void ifstmt()
	{
		if(token() == 'I')
			match('I');
		if(token() == '(')
			comprsn();
		if(token() == 'T')
			match('T');
		if(token() == 'B' || token() == 'A' || token() == 'I' || token() == 'W' || token() == 'R' || token() == 'O' || token() == 'E' || token() == 'D')
			block();
		if(token() == 'L')
			match('L');
		if(token() == 'B' || token() == 'A' || token() == 'I' || token() == 'W' || token() == 'R' || token() == 'O' || token() == 'E' || token() == 'D')
			block();
	}
	
	/**
	 * This method is used when token in input string is value of token A, ident, token ~ or exprsn non terminals
	 * asignmt ::= 	A ident ~ exprsn
	 */
	private void asignmt()
	{
		if(token() == 'A')
			match('A');
		if(token() == 'X' || token() == 'Y' || token() == 'Z')
			ident();
		if(token() == '~')	
			match('~');
		//exprsn();
		if	(token()== '0' || token()=='1' || token() == 'X' || token() == 'Y' || token() == 'Z' || token() == '(')
		{
			exprsn();
		}
	}
	
	/**
	 * This method is used when token in input string is value of asignmt, ifstmt, whileProduction, inpout or block non terminals
	 * statemt ::= 	asignmt | ifstmt | while | inpout | block
	 */
	private void stmt()
	{
			if(token() == 'A')
				asignmt();
			if(token() == 'I')
				ifstmt();
			if(token() == 'W')
				whileProduction();
			if(token() == 'R' || token() == 'O')
				inpout();
			if(token() == 'B' || token() == 'A' || token() == 'I' || token() == 'W' || token() == 'R' || token() == 'O' || token() == 'E' || token() == 'D')
				block();
	}
	
	/**
	 * This method is used when token in input string is value of token B, stmt, token E, token D non terminals
	 * block   ::= 	B {statemt} E [D]
	 */
	private void block()
	{
			if(token() == 'B')
				match('B');
			while(token() == 'A' || token() == 'I' || token() == 'W' || token() == 'R' || token() == 'O' || token() == 'B')
					stmt();
			if(token() == 'E')
				match('E');
			if(token() == 'D')
				match('D');
	}
	
	// End of production methods
}