package rules;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;


import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.stringtemplate.v4.misc.Interval;

import expression.expression;

import parameter.parameter;
import program.program;
import statement.defineFunStatement;

public class testProg {
	public static void main(String[] args) throws RecognitionException, FileNotFoundException{
		
		Scanner sc= new Scanner(new File("src/input_sc.txt"));
		String input = "";
		String temp;
		while(sc.hasNext()){
			temp = sc.next();
			input = input + temp + " ";
		}
		
		CharStream charStream = new ANTLRStringStream(input);
		progLexer lexer = new progLexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		progParser parser = new progParser(tokenStream);
		program result = parser.prog();
		System.out.println("pass!");
		result.playGame();
	}
}
