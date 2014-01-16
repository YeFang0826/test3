// $ANTLR 3.5 /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g 2014-01-02 13:30:57

  package rules;
  import java.util.*;
  import org.antlr.runtime.BitSet;
  import accessField.*;
  import deparen.*;
  import agent.*;
  import execute.*;
  import expression.*;
  import functionCall.*;
  import logicExpression.*;
  import quantifier.*;
  import parameter.*;
  import program.*;
  import term.*;
  import statement.*;
  


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class progParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AGENT", "AND", "ANNOTATION", 
		"COMMENTS", "CONST", "DIV", "DO", "ELSE", "ELSEIF", "END", "EXECUTE", 
		"EXISTS", "EXPECTED", "FOR", "FORALL", "FUN", "ID", "IF", "IMPLIES", "LIST", 
		"LISTTYPE", "MECHANISM", "MOD", "MULTI_COMMENTS", "NEW", "NOT", "NUMERIC", 
		"OR", "POST", "PRE", "PRIOR_INFO", "PROGRAM", "REAL", "SORTED", "TEMPLATE", 
		"THEN", "TO", "TUPLE", "TUPLETYPE", "VAR", "WS", "'!='", "'&&'", "'('", 
		"')'", "'*'", "'+'", "','", "'-'", "'.'", "':'", "';'", "'<'", "'<='", 
		"'='", "'=='", "'>'", "'>='", "'?'", "'InsertSorted'", "'['", "']'", "'length'", 
		"'setExclude'", "'size'", "'{'", "'||'", "'}'", "'~'"
	};
	public static final int EOF=-1;
	public static final int T__45=45;
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
	public static final int T__49=49;
	public static final int T__50=50;
	public static final int T__51=51;
	public static final int T__52=52;
	public static final int T__53=53;
	public static final int T__54=54;
	public static final int T__55=55;
	public static final int T__56=56;
	public static final int T__57=57;
	public static final int T__58=58;
	public static final int T__59=59;
	public static final int T__60=60;
	public static final int T__61=61;
	public static final int T__62=62;
	public static final int T__63=63;
	public static final int T__64=64;
	public static final int T__65=65;
	public static final int T__66=66;
	public static final int T__67=67;
	public static final int T__68=68;
	public static final int T__69=69;
	public static final int T__70=70;
	public static final int T__71=71;
	public static final int T__72=72;
	public static final int AGENT=4;
	public static final int AND=5;
	public static final int ANNOTATION=6;
	public static final int COMMENTS=7;
	public static final int CONST=8;
	public static final int DIV=9;
	public static final int DO=10;
	public static final int ELSE=11;
	public static final int ELSEIF=12;
	public static final int END=13;
	public static final int EXECUTE=14;
	public static final int EXISTS=15;
	public static final int EXPECTED=16;
	public static final int FOR=17;
	public static final int FORALL=18;
	public static final int FUN=19;
	public static final int ID=20;
	public static final int IF=21;
	public static final int IMPLIES=22;
	public static final int LIST=23;
	public static final int LISTTYPE=24;
	public static final int MECHANISM=25;
	public static final int MOD=26;
	public static final int MULTI_COMMENTS=27;
	public static final int NEW=28;
	public static final int NOT=29;
	public static final int NUMERIC=30;
	public static final int OR=31;
	public static final int POST=32;
	public static final int PRE=33;
	public static final int PRIOR_INFO=34;
	public static final int PROGRAM=35;
	public static final int REAL=36;
	public static final int SORTED=37;
	public static final int TEMPLATE=38;
	public static final int THEN=39;
	public static final int TO=40;
	public static final int TUPLE=41;
	public static final int TUPLETYPE=42;
	public static final int VAR=43;
	public static final int WS=44;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public progParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public progParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return progParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g"; }





	// $ANTLR start "prog"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:34:1: prog returns [program program] : ( preAnnotation )? PROGRAM ID '[' ( inputs )? ']' '=' ( defineVarStatement )* ( mechanismFunction | agentTemplate )+ execution END PROGRAM ID ( postAnnotation )? ;
	public final program prog() throws RecognitionException {
		program program = null;


		logicExpression preAnnotation1 =null;
		ArrayList<parameter> inputs2 =null;
		defVarStatement defineVarStatement3 =null;
		defineFunStatement mechanismFunction4 =null;
		agentTemplate agentTemplate5 =null;
		executeCode execution6 =null;
		logicExpression postAnnotation7 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:34:31: ( ( preAnnotation )? PROGRAM ID '[' ( inputs )? ']' '=' ( defineVarStatement )* ( mechanismFunction | agentTemplate )+ execution END PROGRAM ID ( postAnnotation )? )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:35:7: ( preAnnotation )? PROGRAM ID '[' ( inputs )? ']' '=' ( defineVarStatement )* ( mechanismFunction | agentTemplate )+ execution END PROGRAM ID ( postAnnotation )?
			{
			 boolean hasPre = false; 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:36:7: ( preAnnotation )?
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==ANNOTATION) ) {
				alt1=1;
			}
			switch (alt1) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:36:8: preAnnotation
					{
					pushFollow(FOLLOW_preAnnotation_in_prog63);
					preAnnotation1=preAnnotation();
					state._fsp--;

					 hasPre = true; 
					}
					break;

			}

			match(input,PROGRAM,FOLLOW_PROGRAM_in_prog76); 
			 program = new program(); 
			        if(hasPre)
			          program.pre = preAnnotation1;
			      
			match(input,ID,FOLLOW_ID_in_prog93); 
			match(input,64,FOLLOW_64_in_prog102); 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:43:11: ( inputs )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==CONST||LA2_0==LISTTYPE||LA2_0==REAL||LA2_0==TUPLETYPE) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:43:12: inputs
					{
					pushFollow(FOLLOW_inputs_in_prog105);
					inputs2=inputs();
					state._fsp--;

					 program.inputVar(inputs2); 
					}
					break;

			}

			match(input,65,FOLLOW_65_in_prog111); 
			match(input,58,FOLLOW_58_in_prog113); 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:44:7: ( defineVarStatement )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==CONST||LA3_0==LISTTYPE||LA3_0==REAL||LA3_0==TUPLETYPE) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:44:9: defineVarStatement
					{
					pushFollow(FOLLOW_defineVarStatement_in_prog124);
					defineVarStatement3=defineVarStatement();
					state._fsp--;

					 program.addD(defineVarStatement3); 
					}
					break;

				default :
					break loop3;
				}
			}

			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:45:7: ( mechanismFunction | agentTemplate )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=3;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==ANNOTATION||LA4_0==MECHANISM) ) {
					alt4=1;
				}
				else if ( (LA4_0==AGENT) ) {
					alt4=2;
				}

				switch (alt4) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:45:9: mechanismFunction
					{
					pushFollow(FOLLOW_mechanismFunction_in_prog138);
					mechanismFunction4=mechanismFunction();
					state._fsp--;

					 program.mechanism = mechanismFunction4; 
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:47:7: agentTemplate
					{
					pushFollow(FOLLOW_agentTemplate_in_prog156);
					agentTemplate5=agentTemplate();
					state._fsp--;

					 program.agentTemplate.put(agentTemplate5.name, agentTemplate5); 
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			pushFollow(FOLLOW_execution_in_prog168);
			execution6=execution();
			state._fsp--;

			 program.code = execution6; 
			match(input,END,FOLLOW_END_in_prog178); 
			match(input,PROGRAM,FOLLOW_PROGRAM_in_prog180); 
			match(input,ID,FOLLOW_ID_in_prog182); 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:50:7: ( postAnnotation )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==ANNOTATION) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:50:9: postAnnotation
					{
					pushFollow(FOLLOW_postAnnotation_in_prog192);
					postAnnotation7=postAnnotation();
					state._fsp--;

					 program.post = postAnnotation7;
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return program;
	}
	// $ANTLR end "prog"



	// $ANTLR start "preAnnotation"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:52:1: preAnnotation returns [logicExpression preAnnotation] : '@' PRE ':' logicExpression '@' ;
	public final logicExpression preAnnotation() throws RecognitionException {
		logicExpression preAnnotation = null;


		logicExpression logicExpression8 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:52:53: ( '@' PRE ':' logicExpression '@' )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:53:11: '@' PRE ':' logicExpression '@'
			{
			match(input,ANNOTATION,FOLLOW_ANNOTATION_in_preAnnotation222); 
			match(input,PRE,FOLLOW_PRE_in_preAnnotation224); 
			match(input,54,FOLLOW_54_in_preAnnotation226); 
			pushFollow(FOLLOW_logicExpression_in_preAnnotation240);
			logicExpression8=logicExpression();
			state._fsp--;

			 preAnnotation = logicExpression8; 
			match(input,ANNOTATION,FOLLOW_ANNOTATION_in_preAnnotation254); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return preAnnotation;
	}
	// $ANTLR end "preAnnotation"



	// $ANTLR start "postAnnotation"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:58:1: postAnnotation returns [logicExpression postAnnotation] : '@' POST ':' logicExpression '@' ;
	public final logicExpression postAnnotation() throws RecognitionException {
		logicExpression postAnnotation = null;


		logicExpression logicExpression9 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:58:55: ( '@' POST ':' logicExpression '@' )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:59:11: '@' POST ':' logicExpression '@'
			{
			match(input,ANNOTATION,FOLLOW_ANNOTATION_in_postAnnotation296); 
			match(input,POST,FOLLOW_POST_in_postAnnotation298); 
			match(input,54,FOLLOW_54_in_postAnnotation300); 
			pushFollow(FOLLOW_logicExpression_in_postAnnotation314);
			logicExpression9=logicExpression();
			state._fsp--;

			 postAnnotation = logicExpression9; 
			match(input,ANNOTATION,FOLLOW_ANNOTATION_in_postAnnotation328); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return postAnnotation;
	}
	// $ANTLR end "postAnnotation"



	// $ANTLR start "statement"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:64:1: statement returns [statement statement] : ( defineVarStatement | assignStatement | ifStatement | forStatement | defineFunStatement | priorInfoStatement );
	public final statement statement() throws RecognitionException {
		statement statement = null;


		defVarStatement defineVarStatement10 =null;
		assignStatement assignStatement11 =null;
		ifStatement ifStatement12 =null;
		forStatement forStatement13 =null;
		defineFunStatement defineFunStatement14 =null;
		priorInfo priorInfoStatement15 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:64:40: ( defineVarStatement | assignStatement | ifStatement | forStatement | defineFunStatement | priorInfoStatement )
			int alt6=6;
			switch ( input.LA(1) ) {
			case CONST:
			case LISTTYPE:
			case REAL:
			case TUPLETYPE:
				{
				alt6=1;
				}
				break;
			case ID:
				{
				alt6=2;
				}
				break;
			case IF:
				{
				alt6=3;
				}
				break;
			case FOR:
				{
				alt6=4;
				}
				break;
			case ANNOTATION:
			case FUN:
				{
				alt6=5;
				}
				break;
			case PRIOR_INFO:
				{
				alt6=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}
			switch (alt6) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:65:11: defineVarStatement
					{
					pushFollow(FOLLOW_defineVarStatement_in_statement365);
					defineVarStatement10=defineVarStatement();
					state._fsp--;

					 statement = defineVarStatement10; 
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:66:13: assignStatement
					{
					pushFollow(FOLLOW_assignStatement_in_statement381);
					assignStatement11=assignStatement();
					state._fsp--;

					 statement = assignStatement11; 
					}
					break;
				case 3 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:67:13: ifStatement
					{
					pushFollow(FOLLOW_ifStatement_in_statement397);
					ifStatement12=ifStatement();
					state._fsp--;

					 statement = ifStatement12; 
					}
					break;
				case 4 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:68:13: forStatement
					{
					pushFollow(FOLLOW_forStatement_in_statement413);
					forStatement13=forStatement();
					state._fsp--;

					 statement = forStatement13; 
					}
					break;
				case 5 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:69:13: defineFunStatement
					{
					pushFollow(FOLLOW_defineFunStatement_in_statement429);
					defineFunStatement14=defineFunStatement();
					state._fsp--;

					 statement = defineFunStatement14; 
					}
					break;
				case 6 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:70:13: priorInfoStatement
					{
					pushFollow(FOLLOW_priorInfoStatement_in_statement445);
					priorInfoStatement15=priorInfoStatement();
					state._fsp--;

					 statement = priorInfoStatement15; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return statement;
	}
	// $ANTLR end "statement"



	// $ANTLR start "priorInfoStatement"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:72:1: priorInfoStatement returns [priorInfo priorInfo] : PRIOR_INFO ':' (op1= expr '~' '[' lower1= NUMERIC ',' upper1= NUMERIC ',' pr1= NUMERIC ( ';' lower2= NUMERIC ',' upper2= NUMERIC ',' pr2= NUMERIC )* ']' )+ ';' ;
	public final priorInfo priorInfoStatement() throws RecognitionException {
		priorInfo priorInfo = null;


		Token lower1=null;
		Token upper1=null;
		Token pr1=null;
		Token lower2=null;
		Token upper2=null;
		Token pr2=null;
		expression op1 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:72:49: ( PRIOR_INFO ':' (op1= expr '~' '[' lower1= NUMERIC ',' upper1= NUMERIC ',' pr1= NUMERIC ( ';' lower2= NUMERIC ',' upper2= NUMERIC ',' pr2= NUMERIC )* ']' )+ ';' )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:73:11: PRIOR_INFO ':' (op1= expr '~' '[' lower1= NUMERIC ',' upper1= NUMERIC ',' pr1= NUMERIC ( ';' lower2= NUMERIC ',' upper2= NUMERIC ',' pr2= NUMERIC )* ']' )+ ';'
			{
			match(input,PRIOR_INFO,FOLLOW_PRIOR_INFO_in_priorInfoStatement478); 
			match(input,54,FOLLOW_54_in_priorInfoStatement480); 
			 priorInfo = new priorInfo(); inputPrior x = null;
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:74:11: (op1= expr '~' '[' lower1= NUMERIC ',' upper1= NUMERIC ',' pr1= NUMERIC ( ';' lower2= NUMERIC ',' upper2= NUMERIC ',' pr2= NUMERIC )* ']' )+
			int cnt8=0;
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==EXPECTED||LA8_0==ID||LA8_0==LIST||LA8_0==NEW||LA8_0==NUMERIC||LA8_0==SORTED||LA8_0==TUPLE||LA8_0==47||LA8_0==52||(LA8_0 >= 62 && LA8_0 <= 63)||(LA8_0 >= 66 && LA8_0 <= 68)||LA8_0==72) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:74:13: op1= expr '~' '[' lower1= NUMERIC ',' upper1= NUMERIC ',' pr1= NUMERIC ( ';' lower2= NUMERIC ',' upper2= NUMERIC ',' pr2= NUMERIC )* ']'
					{
					pushFollow(FOLLOW_expr_in_priorInfoStatement500);
					op1=expr();
					state._fsp--;

					 x = new inputPrior(op1); 
					match(input,72,FOLLOW_72_in_priorInfoStatement515); 
					match(input,64,FOLLOW_64_in_priorInfoStatement517); 
					lower1=(Token)match(input,NUMERIC,FOLLOW_NUMERIC_in_priorInfoStatement535); 
					match(input,51,FOLLOW_51_in_priorInfoStatement537); 
					upper1=(Token)match(input,NUMERIC,FOLLOW_NUMERIC_in_priorInfoStatement543); 
					match(input,51,FOLLOW_51_in_priorInfoStatement545); 
					pr1=(Token)match(input,NUMERIC,FOLLOW_NUMERIC_in_priorInfoStatement551); 
					 x.intervals.add(Double.parseDouble((lower1!=null?lower1.getText():null)));
					             x.intervals.add(Double.parseDouble((upper1!=null?upper1.getText():null)));
					             x.intervals.add(Double.parseDouble((pr1!=null?pr1.getText():null))); 
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:81:12: ( ';' lower2= NUMERIC ',' upper2= NUMERIC ',' pr2= NUMERIC )*
					loop7:
					while (true) {
						int alt7=2;
						int LA7_0 = input.LA(1);
						if ( (LA7_0==55) ) {
							alt7=1;
						}

						switch (alt7) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:81:13: ';' lower2= NUMERIC ',' upper2= NUMERIC ',' pr2= NUMERIC
							{
							match(input,55,FOLLOW_55_in_priorInfoStatement593); 
							lower2=(Token)match(input,NUMERIC,FOLLOW_NUMERIC_in_priorInfoStatement599); 
							match(input,51,FOLLOW_51_in_priorInfoStatement601); 
							upper2=(Token)match(input,NUMERIC,FOLLOW_NUMERIC_in_priorInfoStatement607); 
							match(input,51,FOLLOW_51_in_priorInfoStatement609); 
							pr2=(Token)match(input,NUMERIC,FOLLOW_NUMERIC_in_priorInfoStatement614); 
							 x.intervals.add(Double.parseDouble((lower2!=null?lower2.getText():null)));
							             x.intervals.add(Double.parseDouble((upper2!=null?upper2.getText():null)));
							             x.intervals.add(Double.parseDouble((pr2!=null?pr2.getText():null))); 
							}
							break;

						default :
							break loop7;
						}
					}

					match(input,65,FOLLOW_65_in_priorInfoStatement654); 
					 priorInfo.prior.add(x); 
					}
					break;

				default :
					if ( cnt8 >= 1 ) break loop8;
					EarlyExitException eee = new EarlyExitException(8, input);
					throw eee;
				}
				cnt8++;
			}

			match(input,55,FOLLOW_55_in_priorInfoStatement680); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return priorInfo;
	}
	// $ANTLR end "priorInfoStatement"



	// $ANTLR start "defineFunStatement"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:90:1: defineFunStatement returns [defineFunStatement defineFunStatement] : ( preAnnotation )? FUN op1= ID '[' inputs ']' '[' output ']' '=' ( statement )+ END op2= ID ( postAnnotation )? ;
	public final defineFunStatement defineFunStatement() throws RecognitionException {
		defineFunStatement defineFunStatement = null;


		Token op1=null;
		Token op2=null;
		logicExpression preAnnotation16 =null;
		ArrayList<parameter> inputs17 =null;
		parameter output18 =null;
		statement statement19 =null;
		logicExpression postAnnotation20 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:90:67: ( ( preAnnotation )? FUN op1= ID '[' inputs ']' '[' output ']' '=' ( statement )+ END op2= ID ( postAnnotation )? )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:91:22: ( preAnnotation )? FUN op1= ID '[' inputs ']' '[' output ']' '=' ( statement )+ END op2= ID ( postAnnotation )?
			{
			 boolean hasPre = false;
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:92:21: ( preAnnotation )?
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==ANNOTATION) ) {
				alt9=1;
			}
			switch (alt9) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:92:23: preAnnotation
					{
					pushFollow(FOLLOW_preAnnotation_in_defineFunStatement747);
					preAnnotation16=preAnnotation();
					state._fsp--;

					 hasPre = true; 
					}
					break;

			}

			match(input,FUN,FOLLOW_FUN_in_defineFunStatement774); 
			op1=(Token)match(input,ID,FOLLOW_ID_in_defineFunStatement780); 
			 defineFunStatement = new defineFunStatement((op1!=null?op1.getText():null));  
			                      if(hasPre){
			                        defineFunStatement.pre = preAnnotation16;
			                       }
			                    
			match(input,64,FOLLOW_64_in_defineFunStatement825); 
			pushFollow(FOLLOW_inputs_in_defineFunStatement826);
			inputs17=inputs();
			state._fsp--;

			 defineFunStatement.input(inputs17); 
			match(input,65,FOLLOW_65_in_defineFunStatement829); 
			match(input,64,FOLLOW_64_in_defineFunStatement831); 
			pushFollow(FOLLOW_output_in_defineFunStatement833);
			output18=output();
			state._fsp--;

			 defineFunStatement.output(output18); 
			match(input,65,FOLLOW_65_in_defineFunStatement836); 
			match(input,58,FOLLOW_58_in_defineFunStatement838); 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:100:21: ( statement )+
			int cnt10=0;
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==ANNOTATION||LA10_0==CONST||LA10_0==FOR||(LA10_0 >= FUN && LA10_0 <= IF)||LA10_0==LISTTYPE||LA10_0==PRIOR_INFO||LA10_0==REAL||LA10_0==TUPLETYPE) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:100:22: statement
					{
					pushFollow(FOLLOW_statement_in_defineFunStatement861);
					statement19=statement();
					state._fsp--;

					 defineFunStatement.addS(statement19); 
					}
					break;

				default :
					if ( cnt10 >= 1 ) break loop10;
					EarlyExitException eee = new EarlyExitException(10, input);
					throw eee;
				}
				cnt10++;
			}

			match(input,END,FOLLOW_END_in_defineFunStatement888); 
			op2=(Token)match(input,ID,FOLLOW_ID_in_defineFunStatement894); 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:102:21: ( postAnnotation )?
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==ANNOTATION) ) {
				int LA11_1 = input.LA(2);
				if ( (LA11_1==POST) ) {
					alt11=1;
				}
			}
			switch (alt11) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:102:23: postAnnotation
					{
					pushFollow(FOLLOW_postAnnotation_in_defineFunStatement918);
					postAnnotation20=postAnnotation();
					state._fsp--;

					 defineFunStatement.post = postAnnotation20; 
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return defineFunStatement;
	}
	// $ANTLR end "defineFunStatement"



	// $ANTLR start "defineVarStatement"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:107:1: defineVarStatement returns [defVarStatement defineVarStatement] : ( ptype | ctype ) ID ( '=' expr )? ';' ;
	public final defVarStatement defineVarStatement() throws RecognitionException {
		defVarStatement defineVarStatement = null;


		Token ID23=null;
		ParserRuleReturnScope ptype21 =null;
		ParserRuleReturnScope ctype22 =null;
		expression expr24 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:107:64: ( ( ptype | ctype ) ID ( '=' expr )? ';' )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:108:21: ( ptype | ctype ) ID ( '=' expr )? ';'
			{
			 String type = ""; int dimension = 0;
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:109:21: ( ptype | ctype )
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==CONST||LA12_0==REAL) ) {
				alt12=1;
			}
			else if ( (LA12_0==LISTTYPE||LA12_0==TUPLETYPE) ) {
				alt12=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}

			switch (alt12) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:109:22: ptype
					{
					pushFollow(FOLLOW_ptype_in_defineVarStatement1061);
					ptype21=ptype();
					state._fsp--;

					 type = (ptype21!=null?input.toString(ptype21.start,ptype21.stop):null); 
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:109:53: ctype
					{
					pushFollow(FOLLOW_ctype_in_defineVarStatement1066);
					ctype22=ctype();
					state._fsp--;

					 type = (ctype22!=null?((progParser.ctype_return)ctype22).type:null);  dimension = (ctype22!=null?((progParser.ctype_return)ctype22).dimension:0); 
					}
					break;

			}

			ID23=(Token)match(input,ID,FOLLOW_ID_in_defineVarStatement1092); 
			 defineVarStatement = new defVarStatement(type, (ID23!=null?ID23.getText():null)); defineVarStatement.dimension = dimension; 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:111:21: ( '=' expr )?
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==58) ) {
				alt13=1;
			}
			switch (alt13) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:111:22: '=' expr
					{
					match(input,58,FOLLOW_58_in_defineVarStatement1117); 
					pushFollow(FOLLOW_expr_in_defineVarStatement1119);
					expr24=expr();
					state._fsp--;

					 defineVarStatement.init = expr24; 
					}
					break;

			}

			match(input,55,FOLLOW_55_in_defineVarStatement1126); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return defineVarStatement;
	}
	// $ANTLR end "defineVarStatement"



	// $ANTLR start "assignStatement"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:115:1: assignStatement returns [assignStatement assignStatement] : ID ( '[' op1= expr ']' )* '=' (op2= expr ) ';' ;
	public final assignStatement assignStatement() throws RecognitionException {
		assignStatement assignStatement = null;


		Token ID25=null;
		expression op1 =null;
		expression op2 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:115:58: ( ID ( '[' op1= expr ']' )* '=' (op2= expr ) ';' )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:116:21: ID ( '[' op1= expr ']' )* '=' (op2= expr ) ';'
			{
			 boolean access = false; ArrayList<expression> e = new ArrayList<expression>(); tl lhs = null;
			ID25=(Token)match(input,ID,FOLLOW_ID_in_assignStatement1241); 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:118:21: ( '[' op1= expr ']' )*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==64) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:119:21: '[' op1= expr ']'
					{
					match(input,64,FOLLOW_64_in_assignStatement1286); 
					pushFollow(FOLLOW_expr_in_assignStatement1292);
					op1=expr();
					state._fsp--;

					match(input,65,FOLLOW_65_in_assignStatement1294); 
					 
					                      if(!access){ access = true; }
					                      e.add(op1);
					                    
					}
					break;

				default :
					break loop14;
				}
			}

			match(input,58,FOLLOW_58_in_assignStatement1341); 
			 
			                      if(access) {
			                        lhs = new tl((ID25!=null?ID25.getText():null));
			                        lhs.indexes = e;
			                        assignStatement = new assignStatement(lhs);
			                      }
			                      else
			                        assignStatement = new assignStatement((ID25!=null?ID25.getText():null));
			                    
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:136:21: (op2= expr )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:137:23: op2= expr
			{
			pushFollow(FOLLOW_expr_in_assignStatement1455);
			op2=expr();
			state._fsp--;

			 assignStatement.assignment = op2; 
			}

			match(input,55,FOLLOW_55_in_assignStatement1503); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return assignStatement;
	}
	// $ANTLR end "assignStatement"



	// $ANTLR start "ifStatement"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:142:1: ifStatement returns [ifStatement ifStatement] : IF op1= expr THEN (s1= statement )+ ( ELSEIF op2= expr THEN (s2= statement )+ )* ( ELSE (s3= statement )+ )? END IF ;
	public final ifStatement ifStatement() throws RecognitionException {
		ifStatement ifStatement = null;


		expression op1 =null;
		statement s1 =null;
		expression op2 =null;
		statement s2 =null;
		statement s3 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:142:46: ( IF op1= expr THEN (s1= statement )+ ( ELSEIF op2= expr THEN (s2= statement )+ )* ( ELSE (s3= statement )+ )? END IF )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:143:15: IF op1= expr THEN (s1= statement )+ ( ELSEIF op2= expr THEN (s2= statement )+ )* ( ELSE (s3= statement )+ )? END IF
			{
			match(input,IF,FOLLOW_IF_in_ifStatement1549); 
			ifStatement = new ifStatement();
			pushFollow(FOLLOW_expr_in_ifStatement1572);
			op1=expr();
			state._fsp--;

			 ifStatement.addCondition(op1); 
			match(input,THEN,FOLLOW_THEN_in_ifStatement1607); 
			 ArrayList<statement> temp1 = new ArrayList<statement>(1); 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:147:15: (s1= statement )+
			int cnt15=0;
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( (LA15_0==ANNOTATION||LA15_0==CONST||LA15_0==FOR||(LA15_0 >= FUN && LA15_0 <= IF)||LA15_0==LISTTYPE||LA15_0==PRIOR_INFO||LA15_0==REAL||LA15_0==TUPLETYPE) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:147:17: s1= statement
					{
					pushFollow(FOLLOW_statement_in_ifStatement1630);
					s1=statement();
					state._fsp--;

					 temp1.add(s1); 
					}
					break;

				default :
					if ( cnt15 >= 1 ) break loop15;
					EarlyExitException eee = new EarlyExitException(15, input);
					throw eee;
				}
				cnt15++;
			}

			 ifStatement.addConsq(temp1); 
			 ArrayList<statement> temp2; 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:150:14: ( ELSEIF op2= expr THEN (s2= statement )+ )*
			loop17:
			while (true) {
				int alt17=2;
				int LA17_0 = input.LA(1);
				if ( (LA17_0==ELSEIF) ) {
					alt17=1;
				}

				switch (alt17) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:150:15: ELSEIF op2= expr THEN (s2= statement )+
					{
					match(input,ELSEIF,FOLLOW_ELSEIF_in_ifStatement1684); 
					pushFollow(FOLLOW_expr_in_ifStatement1688);
					op2=expr();
					state._fsp--;

					 ifStatement.addCondition(op2); 
					match(input,THEN,FOLLOW_THEN_in_ifStatement1706); 
					 temp2 = new ArrayList<statement>(1); 
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:152:16: (s2= statement )+
					int cnt16=0;
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==ANNOTATION||LA16_0==CONST||LA16_0==FOR||(LA16_0 >= FUN && LA16_0 <= IF)||LA16_0==LISTTYPE||LA16_0==PRIOR_INFO||LA16_0==REAL||LA16_0==TUPLETYPE) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:152:18: s2= statement
							{
							pushFollow(FOLLOW_statement_in_ifStatement1729);
							s2=statement();
							state._fsp--;

							 temp2.add(s2); 
							}
							break;

						default :
							if ( cnt16 >= 1 ) break loop16;
							EarlyExitException eee = new EarlyExitException(16, input);
							throw eee;
						}
						cnt16++;
					}

					 ifStatement.addConsq(temp2); 
					}
					break;

				default :
					break loop17;
				}
			}

			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:155:14: ( ELSE (s3= statement )+ )?
			int alt19=2;
			int LA19_0 = input.LA(1);
			if ( (LA19_0==ELSE) ) {
				alt19=1;
			}
			switch (alt19) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:155:15: ELSE (s3= statement )+
					{
					match(input,ELSE,FOLLOW_ELSE_in_ifStatement1786); 
					 ArrayList<statement> temp3 = new ArrayList<statement>(1); 
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:156:14: (s3= statement )+
					int cnt18=0;
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( (LA18_0==ANNOTATION||LA18_0==CONST||LA18_0==FOR||(LA18_0 >= FUN && LA18_0 <= IF)||LA18_0==LISTTYPE||LA18_0==PRIOR_INFO||LA18_0==REAL||LA18_0==TUPLETYPE) ) {
							alt18=1;
						}

						switch (alt18) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:156:16: s3= statement
							{
							pushFollow(FOLLOW_statement_in_ifStatement1809);
							s3=statement();
							state._fsp--;

							 temp3.add(s3); 
							}
							break;

						default :
							if ( cnt18 >= 1 ) break loop18;
							EarlyExitException eee = new EarlyExitException(18, input);
							throw eee;
						}
						cnt18++;
					}

					 ifStatement.addElse(temp3); 
					}
					break;

			}

			match(input,END,FOLLOW_END_in_ifStatement1863); 
			match(input,IF,FOLLOW_IF_in_ifStatement1865); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ifStatement;
	}
	// $ANTLR end "ifStatement"



	// $ANTLR start "forStatement"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:162:1: forStatement returns [forStatement forStatement] : FOR ID '=' op1= expr TO op2= expr DO ( statement )+ END FOR ;
	public final forStatement forStatement() throws RecognitionException {
		forStatement forStatement = null;


		Token ID26=null;
		expression op1 =null;
		expression op2 =null;
		statement statement27 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:162:49: ( FOR ID '=' op1= expr TO op2= expr DO ( statement )+ END FOR )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:163:15: FOR ID '=' op1= expr TO op2= expr DO ( statement )+ END FOR
			{
			match(input,FOR,FOLLOW_FOR_in_forStatement1913); 
			 forStatement = new forStatement(); 
			ID26=(Token)match(input,ID,FOLLOW_ID_in_forStatement1931); 
			 forStatement.iterator = (ID26!=null?ID26.getText():null); 
			match(input,58,FOLLOW_58_in_forStatement1949); 
			pushFollow(FOLLOW_expr_in_forStatement1955);
			op1=expr();
			state._fsp--;

			match(input,TO,FOLLOW_TO_in_forStatement1957); 
			pushFollow(FOLLOW_expr_in_forStatement1963);
			op2=expr();
			state._fsp--;

			 forStatement.lbound = op1;  forStatement.ubound = op2;
			match(input,DO,FOLLOW_DO_in_forStatement1996); 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:168:17: ( statement )+
			int cnt20=0;
			loop20:
			while (true) {
				int alt20=2;
				int LA20_0 = input.LA(1);
				if ( (LA20_0==ANNOTATION||LA20_0==CONST||LA20_0==FOR||(LA20_0 >= FUN && LA20_0 <= IF)||LA20_0==LISTTYPE||LA20_0==PRIOR_INFO||LA20_0==REAL||LA20_0==TUPLETYPE) ) {
					alt20=1;
				}

				switch (alt20) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:168:18: statement
					{
					pushFollow(FOLLOW_statement_in_forStatement2015);
					statement27=statement();
					state._fsp--;

					 forStatement.addS(statement27); 
					}
					break;

				default :
					if ( cnt20 >= 1 ) break loop20;
					EarlyExitException eee = new EarlyExitException(20, input);
					throw eee;
				}
				cnt20++;
			}

			match(input,END,FOLLOW_END_in_forStatement2035); 
			match(input,FOR,FOLLOW_FOR_in_forStatement2037); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return forStatement;
	}
	// $ANTLR end "forStatement"



	// $ANTLR start "agentTemplate"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:171:1: agentTemplate returns [agentTemplate agentTemplate] : AGENT op1= ID '=' ( statement )+ END op2= ID ;
	public final agentTemplate agentTemplate() throws RecognitionException {
		agentTemplate agentTemplate = null;


		Token op1=null;
		Token op2=null;
		statement statement28 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:171:52: ( AGENT op1= ID '=' ( statement )+ END op2= ID )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:172:17: AGENT op1= ID '=' ( statement )+ END op2= ID
			{
			match(input,AGENT,FOLLOW_AGENT_in_agentTemplate2079); 
			op1=(Token)match(input,ID,FOLLOW_ID_in_agentTemplate2085); 
			 agentTemplate = new agentTemplate((op1!=null?op1.getText():null)); 
			match(input,58,FOLLOW_58_in_agentTemplate2088); 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:173:17: ( statement )+
			int cnt21=0;
			loop21:
			while (true) {
				int alt21=2;
				int LA21_0 = input.LA(1);
				if ( (LA21_0==ANNOTATION||LA21_0==CONST||LA21_0==FOR||(LA21_0 >= FUN && LA21_0 <= IF)||LA21_0==LISTTYPE||LA21_0==PRIOR_INFO||LA21_0==REAL||LA21_0==TUPLETYPE) ) {
					alt21=1;
				}

				switch (alt21) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:173:18: statement
					{
					pushFollow(FOLLOW_statement_in_agentTemplate2107);
					statement28=statement();
					state._fsp--;

					 agentTemplate.addS(statement28); 
					}
					break;

				default :
					if ( cnt21 >= 1 ) break loop21;
					EarlyExitException eee = new EarlyExitException(21, input);
					throw eee;
				}
				cnt21++;
			}

			match(input,END,FOLLOW_END_in_agentTemplate2129); 
			op2=(Token)match(input,ID,FOLLOW_ID_in_agentTemplate2135); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return agentTemplate;
	}
	// $ANTLR end "agentTemplate"



	// $ANTLR start "agentCreation"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:176:1: agentCreation returns [agentT agentCreation] : NEW ID '(' op1= expr ',' op2= expr ')' ;
	public final agentT agentCreation() throws RecognitionException {
		agentT agentCreation = null;


		Token ID29=null;
		expression op1 =null;
		expression op2 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:176:45: ( NEW ID '(' op1= expr ',' op2= expr ')' )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:177:11: NEW ID '(' op1= expr ',' op2= expr ')'
			{
			match(input,NEW,FOLLOW_NEW_in_agentCreation2187); 
			ID29=(Token)match(input,ID,FOLLOW_ID_in_agentCreation2189); 
			 agentCreation = new agentT((ID29!=null?ID29.getText():null)); 
			match(input,47,FOLLOW_47_in_agentCreation2203); 
			pushFollow(FOLLOW_expr_in_agentCreation2209);
			op1=expr();
			state._fsp--;

			match(input,51,FOLLOW_51_in_agentCreation2211); 
			pushFollow(FOLLOW_expr_in_agentCreation2217);
			op2=expr();
			state._fsp--;

			match(input,48,FOLLOW_48_in_agentCreation2219); 
			 
			            agentCreation.index = op2; 
			            agentCreation.lable = op1; 
			          
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return agentCreation;
	}
	// $ANTLR end "agentCreation"



	// $ANTLR start "variableCreation"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:184:1: variableCreation returns [newVar variableCreation] : NEW VAR '(' op1= expr ',' op2= expr ')' ;
	public final newVar variableCreation() throws RecognitionException {
		newVar variableCreation = null;


		expression op1 =null;
		expression op2 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:184:51: ( NEW VAR '(' op1= expr ',' op2= expr ')' )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:185:11: NEW VAR '(' op1= expr ',' op2= expr ')'
			{
			match(input,NEW,FOLLOW_NEW_in_variableCreation2262); 
			match(input,VAR,FOLLOW_VAR_in_variableCreation2264); 
			match(input,47,FOLLOW_47_in_variableCreation2277); 
			pushFollow(FOLLOW_expr_in_variableCreation2283);
			op1=expr();
			state._fsp--;

			match(input,51,FOLLOW_51_in_variableCreation2285); 
			pushFollow(FOLLOW_expr_in_variableCreation2291);
			op2=expr();
			state._fsp--;

			match(input,48,FOLLOW_48_in_variableCreation2293); 
			  variableCreation = new newVar(op1, op2); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return variableCreation;
	}
	// $ANTLR end "variableCreation"



	// $ANTLR start "mechanismFunction"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:189:1: mechanismFunction returns [defineFunStatement mechanismFunction] : ( preAnnotation )? MECHANISM op1= ID '[' inputs ']' '[' output ']' '=' ( statement )+ END op2= ID ;
	public final defineFunStatement mechanismFunction() throws RecognitionException {
		defineFunStatement mechanismFunction = null;


		Token op1=null;
		Token op2=null;
		logicExpression preAnnotation30 =null;
		ArrayList<parameter> inputs31 =null;
		parameter output32 =null;
		statement statement33 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:189:65: ( ( preAnnotation )? MECHANISM op1= ID '[' inputs ']' '[' output ']' '=' ( statement )+ END op2= ID )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:190:21: ( preAnnotation )? MECHANISM op1= ID '[' inputs ']' '[' output ']' '=' ( statement )+ END op2= ID
			{
			 boolean hasPre = false; 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:191:20: ( preAnnotation )?
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==ANNOTATION) ) {
				alt22=1;
			}
			switch (alt22) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:191:22: preAnnotation
					{
					pushFollow(FOLLOW_preAnnotation_in_mechanismFunction2369);
					preAnnotation30=preAnnotation();
					state._fsp--;

					 hasPre = true;
					}
					break;

			}

			match(input,MECHANISM,FOLLOW_MECHANISM_in_mechanismFunction2396); 
			op1=(Token)match(input,ID,FOLLOW_ID_in_mechanismFunction2400); 
			 mechanismFunction = new defineFunStatement((op1!=null?op1.getText():null));  
			                       if(hasPre){
			                          mechanismFunction.pre = preAnnotation30;
			                       }
			                    
			match(input,64,FOLLOW_64_in_mechanismFunction2445); 
			pushFollow(FOLLOW_inputs_in_mechanismFunction2447);
			inputs31=inputs();
			state._fsp--;

			match(input,65,FOLLOW_65_in_mechanismFunction2449); 
			 mechanismFunction.input(inputs31); 
			match(input,64,FOLLOW_64_in_mechanismFunction2473); 
			pushFollow(FOLLOW_output_in_mechanismFunction2475);
			output32=output();
			state._fsp--;

			match(input,65,FOLLOW_65_in_mechanismFunction2477); 
			match(input,58,FOLLOW_58_in_mechanismFunction2479); 
			 mechanismFunction.output(output32); 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:200:21: ( statement )+
			int cnt23=0;
			loop23:
			while (true) {
				int alt23=2;
				int LA23_0 = input.LA(1);
				if ( (LA23_0==ANNOTATION||LA23_0==CONST||LA23_0==FOR||(LA23_0 >= FUN && LA23_0 <= IF)||LA23_0==LISTTYPE||LA23_0==PRIOR_INFO||LA23_0==REAL||LA23_0==TUPLETYPE) ) {
					alt23=1;
				}

				switch (alt23) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:200:22: statement
					{
					pushFollow(FOLLOW_statement_in_mechanismFunction2504);
					statement33=statement();
					state._fsp--;

					 mechanismFunction.addS(statement33); 
					}
					break;

				default :
					if ( cnt23 >= 1 ) break loop23;
					EarlyExitException eee = new EarlyExitException(23, input);
					throw eee;
				}
				cnt23++;
			}

			match(input,END,FOLLOW_END_in_mechanismFunction2529); 
			op2=(Token)match(input,ID,FOLLOW_ID_in_mechanismFunction2533); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return mechanismFunction;
	}
	// $ANTLR end "mechanismFunction"



	// $ANTLR start "inputs"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:204:1: inputs returns [ArrayList<parameter> inputs] : (op1= ptype |op2= ctype ) op3= ID ( ',' (op4= ptype |op5= ctype ) op6= ID )* ;
	public final ArrayList<parameter> inputs() throws RecognitionException {
		ArrayList<parameter> inputs = null;


		Token op3=null;
		Token op6=null;
		ParserRuleReturnScope op1 =null;
		ParserRuleReturnScope op2 =null;
		ParserRuleReturnScope op4 =null;
		ParserRuleReturnScope op5 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:204:45: ( (op1= ptype |op2= ctype ) op3= ID ( ',' (op4= ptype |op5= ctype ) op6= ID )* )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:205:9: (op1= ptype |op2= ctype ) op3= ID ( ',' (op4= ptype |op5= ctype ) op6= ID )*
			{
			 inputs = new ArrayList<parameter>(); String type = ""; parameter p = null; int dimension =0; 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:206:9: (op1= ptype |op2= ctype )
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==CONST||LA24_0==REAL) ) {
				alt24=1;
			}
			else if ( (LA24_0==LISTTYPE||LA24_0==TUPLETYPE) ) {
				alt24=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}

			switch (alt24) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:206:10: op1= ptype
					{
					pushFollow(FOLLOW_ptype_in_inputs2604);
					op1=ptype();
					state._fsp--;

					 type = (op1!=null?input.toString(op1.start,op1.stop):null); dimension =0; 
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:206:60: op2= ctype
					{
					pushFollow(FOLLOW_ctype_in_inputs2614);
					op2=ctype();
					state._fsp--;

					 type = (op2!=null?((progParser.ctype_return)op2).type:null); dimension = (op2!=null?((progParser.ctype_return)op2).dimension:0); 
					}
					break;

			}

			op3=(Token)match(input,ID,FOLLOW_ID_in_inputs2635); 
			 p = new parameter(type, (op3!=null?op3.getText():null)); p.dimension = dimension; inputs.add(p); 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:208:9: ( ',' (op4= ptype |op5= ctype ) op6= ID )*
			loop26:
			while (true) {
				int alt26=2;
				int LA26_0 = input.LA(1);
				if ( (LA26_0==51) ) {
					alt26=1;
				}

				switch (alt26) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:208:10: ',' (op4= ptype |op5= ctype ) op6= ID
					{
					match(input,51,FOLLOW_51_in_inputs2648); 
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:208:14: (op4= ptype |op5= ctype )
					int alt25=2;
					int LA25_0 = input.LA(1);
					if ( (LA25_0==CONST||LA25_0==REAL) ) {
						alt25=1;
					}
					else if ( (LA25_0==LISTTYPE||LA25_0==TUPLETYPE) ) {
						alt25=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 25, 0, input);
						throw nvae;
					}

					switch (alt25) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:208:15: op4= ptype
							{
							pushFollow(FOLLOW_ptype_in_inputs2655);
							op4=ptype();
							state._fsp--;

							 type = (op4!=null?input.toString(op4.start,op4.stop):null); dimension =0; 
							}
							break;
						case 2 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:208:65: op5= ctype
							{
							pushFollow(FOLLOW_ctype_in_inputs2665);
							op5=ctype();
							state._fsp--;

							 type = (op5!=null?((progParser.ctype_return)op5).type:null); dimension = (op5!=null?((progParser.ctype_return)op5).dimension:0); 
							}
							break;

					}

					op6=(Token)match(input,ID,FOLLOW_ID_in_inputs2674); 
					 p = new parameter(type, (op6!=null?op6.getText():null)); p.dimension = dimension; inputs.add(p);  
					}
					break;

				default :
					break loop26;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return inputs;
	}
	// $ANTLR end "inputs"



	// $ANTLR start "output"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:213:1: output returns [parameter output] : ( ptype | ctype ) ID ;
	public final parameter output() throws RecognitionException {
		parameter output = null;


		Token ID36=null;
		ParserRuleReturnScope ptype34 =null;
		ParserRuleReturnScope ctype35 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:213:34: ( ( ptype | ctype ) ID )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:214:8: ( ptype | ctype ) ID
			{
			 String type = ""; int dimension =0;
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:215:7: ( ptype | ctype )
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==CONST||LA27_0==REAL) ) {
				alt27=1;
			}
			else if ( (LA27_0==LISTTYPE||LA27_0==TUPLETYPE) ) {
				alt27=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:215:8: ptype
					{
					pushFollow(FOLLOW_ptype_in_output2739);
					ptype34=ptype();
					state._fsp--;

					 type = (ptype34!=null?input.toString(ptype34.start,ptype34.stop):null); 
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:215:40: ctype
					{
					pushFollow(FOLLOW_ctype_in_output2745);
					ctype35=ctype();
					state._fsp--;

					 type = (ctype35!=null?((progParser.ctype_return)ctype35).type:null); dimension = (ctype35!=null?((progParser.ctype_return)ctype35).dimension:0); 
					}
					break;

			}

			ID36=(Token)match(input,ID,FOLLOW_ID_in_output2757); 
			 output = new parameter(type, (ID36!=null?ID36.getText():null)); output.dimension = dimension; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return output;
	}
	// $ANTLR end "output"



	// $ANTLR start "execution"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:219:1: execution returns [executeCode execution] : EXECUTE ( statement )* END EXECUTE ;
	public final executeCode execution() throws RecognitionException {
		executeCode execution = null;


		statement statement37 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:219:42: ( EXECUTE ( statement )* END EXECUTE )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:220:7: EXECUTE ( statement )* END EXECUTE
			{
			match(input,EXECUTE,FOLLOW_EXECUTE_in_execution2790); 
			 execution = new executeCode(); 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:221:7: ( statement )*
			loop28:
			while (true) {
				int alt28=2;
				int LA28_0 = input.LA(1);
				if ( (LA28_0==ANNOTATION||LA28_0==CONST||LA28_0==FOR||(LA28_0 >= FUN && LA28_0 <= IF)||LA28_0==LISTTYPE||LA28_0==PRIOR_INFO||LA28_0==REAL||LA28_0==TUPLETYPE) ) {
					alt28=1;
				}

				switch (alt28) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:221:8: statement
					{
					pushFollow(FOLLOW_statement_in_execution2801);
					statement37=statement();
					state._fsp--;

					 execution.s.add(statement37);
					}
					break;

				default :
					break loop28;
				}
			}

			match(input,END,FOLLOW_END_in_execution2814); 
			match(input,EXECUTE,FOLLOW_EXECUTE_in_execution2816); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return execution;
	}
	// $ANTLR end "execution"



	// $ANTLR start "expr"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:225:1: expr returns [expression expr] :op1= negation ( ( '&&' op2= negation | '||' op2= negation ) )* ;
	public final expression expr() throws RecognitionException {
		expression expr = null;


		expression op1 =null;
		expression op2 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:225:31: (op1= negation ( ( '&&' op2= negation | '||' op2= negation ) )* )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:226:10: op1= negation ( ( '&&' op2= negation | '||' op2= negation ) )*
			{
			 boolean f1 = false;
			pushFollow(FOLLOW_negation_in_expr2855);
			op1=negation();
			state._fsp--;

			 expression temp = op1;
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:229:9: ( ( '&&' op2= negation | '||' op2= negation ) )*
			loop30:
			while (true) {
				int alt30=2;
				int LA30_0 = input.LA(1);
				if ( (LA30_0==46||LA30_0==70) ) {
					alt30=1;
				}

				switch (alt30) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:229:10: ( '&&' op2= negation | '||' op2= negation )
					{
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:229:10: ( '&&' op2= negation | '||' op2= negation )
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==46) ) {
						alt29=1;
					}
					else if ( (LA29_0==70) ) {
						alt29=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 29, 0, input);
						throw nvae;
					}

					switch (alt29) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:229:11: '&&' op2= negation
							{
							match(input,46,FOLLOW_46_in_expr2878); 
							pushFollow(FOLLOW_negation_in_expr2882);
							op2=negation();
							state._fsp--;

							 f1=true;
							          temp = new expression(temp, op2, "&&");
							        
							}
							break;
						case 2 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:234:9: '||' op2= negation
							{
							match(input,70,FOLLOW_70_in_expr2913); 
							pushFollow(FOLLOW_negation_in_expr2917);
							op2=negation();
							state._fsp--;

							 f1=true;
							          temp = new expression(temp, op2, "||"); 
							        
							}
							break;

					}

					}
					break;

				default :
					break loop30;
				}
			}

			  expr = temp;
			            if(f1)
			              expr.type = "expression";
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return expr;
	}
	// $ANTLR end "expr"



	// $ANTLR start "negation"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:245:1: negation returns [expression negation] : ( relation | '~' relation );
	public final expression negation() throws RecognitionException {
		expression negation = null;


		expression relation38 =null;
		expression relation39 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:245:39: ( relation | '~' relation )
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==EXPECTED||LA31_0==ID||LA31_0==LIST||LA31_0==NEW||LA31_0==NUMERIC||LA31_0==SORTED||LA31_0==TUPLE||LA31_0==47||LA31_0==52||(LA31_0 >= 62 && LA31_0 <= 63)||(LA31_0 >= 66 && LA31_0 <= 68)) ) {
				alt31=1;
			}
			else if ( (LA31_0==72) ) {
				alt31=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}

			switch (alt31) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:246:7: relation
					{
					pushFollow(FOLLOW_relation_in_negation2980);
					relation38=relation();
					state._fsp--;

					 negation = relation38;  
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:249:7: '~' relation
					{
					match(input,72,FOLLOW_72_in_negation3005); 
					pushFollow(FOLLOW_relation_in_negation3007);
					relation39=relation();
					state._fsp--;

					  negation = new expression(relation39,"~"); 
					          negation.type = "expression"; 
					      
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return negation;
	}
	// $ANTLR end "negation"



	// $ANTLR start "relation"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:255:1: relation returns [expression relation] :op1= add ( '>' op2= add | '<' op2= add | '>=' op2= add | '<=' op2= add | '!=' op2= add | '==' op2= add )? ;
	public final expression relation() throws RecognitionException {
		expression relation = null;


		expression op1 =null;
		expression op2 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:255:39: (op1= add ( '>' op2= add | '<' op2= add | '>=' op2= add | '<=' op2= add | '!=' op2= add | '==' op2= add )? )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:256:8: op1= add ( '>' op2= add | '<' op2= add | '>=' op2= add | '<=' op2= add | '!=' op2= add | '==' op2= add )?
			{
			 boolean f1 = false;
			pushFollow(FOLLOW_add_in_relation3051);
			op1=add();
			state._fsp--;

			 expression temp = op1; 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:260:7: ( '>' op2= add | '<' op2= add | '>=' op2= add | '<=' op2= add | '!=' op2= add | '==' op2= add )?
			int alt32=7;
			switch ( input.LA(1) ) {
				case 60:
					{
					alt32=1;
					}
					break;
				case 56:
					{
					alt32=2;
					}
					break;
				case 61:
					{
					alt32=3;
					}
					break;
				case 57:
					{
					alt32=4;
					}
					break;
				case 45:
					{
					alt32=5;
					}
					break;
				case 59:
					{
					alt32=6;
					}
					break;
			}
			switch (alt32) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:260:8: '>' op2= add
					{
					match(input,60,FOLLOW_60_in_relation3076); 
					pushFollow(FOLLOW_add_in_relation3080);
					op2=add();
					state._fsp--;

					 f1=true;
					        temp = new expression(temp, op2, ">"); 
					      
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:264:8: '<' op2= add
					{
					match(input,56,FOLLOW_56_in_relation3098); 
					pushFollow(FOLLOW_add_in_relation3102);
					op2=add();
					state._fsp--;

					 f1=true;
					        temp = new expression(temp, op2, "<"); 
					      
					}
					break;
				case 3 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:268:8: '>=' op2= add
					{
					match(input,61,FOLLOW_61_in_relation3120); 
					pushFollow(FOLLOW_add_in_relation3124);
					op2=add();
					state._fsp--;

					 f1=true; 
					        temp = new expression(temp, op2, ">="); 
					      
					}
					break;
				case 4 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:272:8: '<=' op2= add
					{
					match(input,57,FOLLOW_57_in_relation3142); 
					pushFollow(FOLLOW_add_in_relation3146);
					op2=add();
					state._fsp--;

					 f1=true;
					        temp = new expression(temp, op2, "<="); 
					      
					}
					break;
				case 5 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:276:8: '!=' op2= add
					{
					match(input,45,FOLLOW_45_in_relation3164); 
					pushFollow(FOLLOW_add_in_relation3168);
					op2=add();
					state._fsp--;

					 f1=true;
					        temp = new expression(temp, op2, "!="); 
					      
					}
					break;
				case 6 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:280:8: '==' op2= add
					{
					match(input,59,FOLLOW_59_in_relation3186); 
					pushFollow(FOLLOW_add_in_relation3190);
					op2=add();
					state._fsp--;

					 f1=true;
					        temp = new expression(temp, op2, "==");
					      
					}
					break;

			}


			          relation = temp; 
			            if(f1)
			              relation.type = "expression";
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return relation;
	}
	// $ANTLR end "relation"



	// $ANTLR start "add"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:293:1: add returns [expression add] :op1= mult ( ( '+' op2= mult | '-' op2= mult ) )* ;
	public final expression add() throws RecognitionException {
		expression add = null;


		expression op1 =null;
		expression op2 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:293:29: (op1= mult ( ( '+' op2= mult | '-' op2= mult ) )* )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:294:7: op1= mult ( ( '+' op2= mult | '-' op2= mult ) )*
			{
			 boolean f1 = false;
			pushFollow(FOLLOW_mult_in_add3257);
			op1=mult();
			state._fsp--;

			 expression temp = op1; 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:297:7: ( ( '+' op2= mult | '-' op2= mult ) )*
			loop34:
			while (true) {
				int alt34=2;
				int LA34_0 = input.LA(1);
				if ( (LA34_0==50||LA34_0==52) ) {
					alt34=1;
				}

				switch (alt34) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:297:9: ( '+' op2= mult | '-' op2= mult )
					{
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:297:9: ( '+' op2= mult | '-' op2= mult )
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==50) ) {
						alt33=1;
					}
					else if ( (LA33_0==52) ) {
						alt33=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 33, 0, input);
						throw nvae;
					}

					switch (alt33) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:297:10: '+' op2= mult
							{
							match(input,50,FOLLOW_50_in_add3277); 
							pushFollow(FOLLOW_mult_in_add3281);
							op2=mult();
							state._fsp--;

							 f1 = true; 
							        temp = new expression(temp, op2, "+"); 
							      
							}
							break;
						case 2 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:301:9: '-' op2= mult
							{
							match(input,52,FOLLOW_52_in_add3300); 
							pushFollow(FOLLOW_mult_in_add3304);
							op2=mult();
							state._fsp--;

							 f1 = true; 
							        temp = new expression(temp, op2, "-"); 
							      
							}
							break;

					}

					}
					break;

				default :
					break loop34;
				}
			}

			 
			        add = temp; 
			         if(f1)
			              add.type = "expression";
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return add;
	}
	// $ANTLR end "add"



	// $ANTLR start "mult"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:314:1: mult returns [expression mult] :op1= unary ( '*' op2= unary | DIV op2= unary )* ;
	public final expression mult() throws RecognitionException {
		expression mult = null;


		Token DIV40=null;
		expression op1 =null;
		expression op2 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:314:31: (op1= unary ( '*' op2= unary | DIV op2= unary )* )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:315:7: op1= unary ( '*' op2= unary | DIV op2= unary )*
			{
			 boolean f1 = false; 
			pushFollow(FOLLOW_unary_in_mult3373);
			op1=unary();
			state._fsp--;

			 expression temp = op1; 
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:319:7: ( '*' op2= unary | DIV op2= unary )*
			loop35:
			while (true) {
				int alt35=3;
				int LA35_0 = input.LA(1);
				if ( (LA35_0==49) ) {
					alt35=1;
				}
				else if ( (LA35_0==DIV) ) {
					alt35=2;
				}

				switch (alt35) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:320:7: '*' op2= unary
					{
					match(input,49,FOLLOW_49_in_mult3406); 
					pushFollow(FOLLOW_unary_in_mult3410);
					op2=unary();
					state._fsp--;

					 f1 = true; 
					        temp = new expression(temp, op2, "*"); 
					      
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:324:9: DIV op2= unary
					{
					DIV40=(Token)match(input,DIV,FOLLOW_DIV_in_mult3429); 
					pushFollow(FOLLOW_unary_in_mult3433);
					op2=unary();
					state._fsp--;

					 f1 = true; 
					        temp = new expression(temp, op2, (DIV40!=null?DIV40.getText():null)); 
					      
					}
					break;

				default :
					break loop35;
				}
			}

			 
			          mult = temp;
			          if(f1)
			              mult.type = "expression";
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return mult;
	}
	// $ANTLR end "mult"



	// $ANTLR start "unary"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:338:1: unary returns [expression unary] : ( term | '-' term );
	public final expression unary() throws RecognitionException {
		expression unary = null;


		term term41 =null;
		term term42 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:338:33: ( term | '-' term )
			int alt36=2;
			int LA36_0 = input.LA(1);
			if ( (LA36_0==EXPECTED||LA36_0==ID||LA36_0==LIST||LA36_0==NEW||LA36_0==NUMERIC||LA36_0==SORTED||LA36_0==TUPLE||LA36_0==47||(LA36_0 >= 62 && LA36_0 <= 63)||(LA36_0 >= 66 && LA36_0 <= 68)) ) {
				alt36=1;
			}
			else if ( (LA36_0==52) ) {
				alt36=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 36, 0, input);
				throw nvae;
			}

			switch (alt36) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:339:7: term
					{
					pushFollow(FOLLOW_term_in_unary3497);
					term41=term();
					state._fsp--;

					 unary = new expression(term41); 
					        unary.type = term41.type;
					      
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:343:10: '-' term
					{
					match(input,52,FOLLOW_52_in_unary3517); 
					pushFollow(FOLLOW_term_in_unary3519);
					term42=term();
					state._fsp--;

					    unary = new expression(new expression(term42), "-"); 
					          unary.type = "expression";
					      
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return unary;
	}
	// $ANTLR end "unary"



	// $ANTLR start "term"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:349:1: term returns [term term] : ( NUMERIC | '(' expr ')' | hole | agentCreation | variableCreation | (op1= ID ( '[' op2= expr ']' )* ( '.' op3= ID )? ( '(' ')' | '(' op4= expr ( ',' op5= expr )* ')' )? ) | functionCall | setExclude | sortedList | expectedValue );
	public final term term() throws RecognitionException {
		term term = null;


		Token op1=null;
		Token op3=null;
		Token NUMERIC43=null;
		expression op2 =null;
		expression op4 =null;
		expression op5 =null;
		expression expr44 =null;
		holeT hole45 =null;
		agentT agentCreation46 =null;
		newVar variableCreation47 =null;
		functionCall functionCall48 =null;
		setExclude setExclude49 =null;
		sortedList sortedList50 =null;
		expectedValue expectedValue51 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:349:25: ( NUMERIC | '(' expr ')' | hole | agentCreation | variableCreation | (op1= ID ( '[' op2= expr ']' )* ( '.' op3= ID )? ( '(' ')' | '(' op4= expr ( ',' op5= expr )* ')' )? ) | functionCall | setExclude | sortedList | expectedValue )
			int alt41=10;
			switch ( input.LA(1) ) {
			case NUMERIC:
				{
				alt41=1;
				}
				break;
			case 47:
				{
				alt41=2;
				}
				break;
			case 62:
				{
				alt41=3;
				}
				break;
			case NEW:
				{
				int LA41_4 = input.LA(2);
				if ( (LA41_4==ID) ) {
					alt41=4;
				}
				else if ( (LA41_4==VAR) ) {
					alt41=5;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 41, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ID:
				{
				alt41=6;
				}
				break;
			case LIST:
			case TUPLE:
			case 63:
			case 66:
			case 68:
				{
				alt41=7;
				}
				break;
			case 67:
				{
				alt41=8;
				}
				break;
			case SORTED:
				{
				alt41=9;
				}
				break;
			case EXPECTED:
				{
				alt41=10;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}
			switch (alt41) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:350:8: NUMERIC
					{
					NUMERIC43=(Token)match(input,NUMERIC,FOLLOW_NUMERIC_in_term3561); 
					 term = new num(Double.parseDouble((NUMERIC43!=null?NUMERIC43.getText():null)));  
					      
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:353:8: '(' expr ')'
					{
					match(input,47,FOLLOW_47_in_term3579); 
					pushFollow(FOLLOW_expr_in_term3580);
					expr44=expr();
					state._fsp--;

					match(input,48,FOLLOW_48_in_term3581); 
					 
					        term = new expressionT(expr44); 
					      
					}
					break;
				case 3 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:357:9: hole
					{
					pushFollow(FOLLOW_hole_in_term3600);
					hole45=hole();
					state._fsp--;

					 
					        term = hole45;
					      
					}
					break;
				case 4 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:362:7: agentCreation
					{
					pushFollow(FOLLOW_agentCreation_in_term3625);
					agentCreation46=agentCreation();
					state._fsp--;

					 term = agentCreation46; 
					}
					break;
				case 5 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:364:7: variableCreation
					{
					pushFollow(FOLLOW_variableCreation_in_term3644);
					variableCreation47=variableCreation();
					state._fsp--;

					 term = variableCreation47; 
					}
					break;
				case 6 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:366:7: (op1= ID ( '[' op2= expr ']' )* ( '.' op3= ID )? ( '(' ')' | '(' op4= expr ( ',' op5= expr )* ')' )? )
					{
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:366:7: (op1= ID ( '[' op2= expr ']' )* ( '.' op3= ID )? ( '(' ')' | '(' op4= expr ( ',' op5= expr )* ')' )? )
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:366:9: op1= ID ( '[' op2= expr ']' )* ( '.' op3= ID )? ( '(' ')' | '(' op4= expr ( ',' op5= expr )* ')' )?
					{
					op1=(Token)match(input,ID,FOLLOW_ID_in_term3668); 
					 
					          boolean f1 = false; //list access
					          boolean f2 = false; // access private field
					          boolean f3 = false; // function call
					        
					          ArrayList<expression> e = new ArrayList<expression>();
					          ArrayList<expression> inputExpr = new ArrayList<expression>();
					        
					        
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:376:11: ( '[' op2= expr ']' )*
					loop37:
					while (true) {
						int alt37=2;
						int LA37_0 = input.LA(1);
						if ( (LA37_0==64) ) {
							alt37=1;
						}

						switch (alt37) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:376:12: '[' op2= expr ']'
							{
							match(input,64,FOLLOW_64_in_term3692); 
							pushFollow(FOLLOW_expr_in_term3698);
							op2=expr();
							state._fsp--;

							match(input,65,FOLLOW_65_in_term3700); 
							 
							              if(!f1) 
							                 f1 = true; 
							                  
							              e.add(op2); // list index
							            
							}
							break;

						default :
							break loop37;
						}
					}

					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:385:11: ( '.' op3= ID )?
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==53) ) {
						alt38=1;
					}
					switch (alt38) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:385:12: '.' op3= ID
							{
							match(input,53,FOLLOW_53_in_term3749); 
							op3=(Token)match(input,ID,FOLLOW_ID_in_term3755); 
							 f2 = true; 
							}
							break;

					}

					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:387:11: ( '(' ')' | '(' op4= expr ( ',' op5= expr )* ')' )?
					int alt40=3;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==47) ) {
						int LA40_1 = input.LA(2);
						if ( (LA40_1==48) ) {
							alt40=1;
						}
						else if ( (LA40_1==EXPECTED||LA40_1==ID||LA40_1==LIST||LA40_1==NEW||LA40_1==NUMERIC||LA40_1==SORTED||LA40_1==TUPLE||LA40_1==47||LA40_1==52||(LA40_1 >= 62 && LA40_1 <= 63)||(LA40_1 >= 66 && LA40_1 <= 68)||LA40_1==72) ) {
							alt40=2;
						}
					}
					switch (alt40) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:388:13: '(' ')'
							{
							match(input,47,FOLLOW_47_in_term3794); 
							match(input,48,FOLLOW_48_in_term3796); 
							 f3 = true; 
							}
							break;
						case 2 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:390:13: '(' op4= expr ( ',' op5= expr )* ')'
							{
							match(input,47,FOLLOW_47_in_term3826); 
							pushFollow(FOLLOW_expr_in_term3830);
							op4=expr();
							state._fsp--;

							   f3 = true; 
							                    inputExpr.add(op4);
							                
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:394:17: ( ',' op5= expr )*
							loop39:
							while (true) {
								int alt39=2;
								int LA39_0 = input.LA(1);
								if ( (LA39_0==51) ) {
									alt39=1;
								}

								switch (alt39) {
								case 1 :
									// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:394:18: ',' op5= expr
									{
									match(input,51,FOLLOW_51_in_term3868); 
									pushFollow(FOLLOW_expr_in_term3872);
									op5=expr();
									state._fsp--;

									 inputExpr.add(op5); 
									}
									break;

								default :
									break loop39;
								}
							}

							match(input,48,FOLLOW_48_in_term3891); 
							}
							break;

					}

					  
					              if(!f1 && !f2 && !f3){
					                term = new var((op1!=null?op1.getText():null));
					              }
					              else if(f1 && !f2 && !f3){
					                  tl tlaccess = new tl((op1!=null?op1.getText():null));
					                  tlaccess.indexes = e;
					                  term = tlaccess;
					              }
					              else if(f2 && !f3){
					                  if(!f1)
					                      term = new accessT(new accessField((op1!=null?op1.getText():null), (op3!=null?op3.getText():null)));
					                  else{
					                        tl tlaccess = new tl((op1!=null?op1.getText():null));
					                        tlaccess.indexes = e;
					                        term = new accessT(new accessField(tlaccess, (op3!=null?op3.getText():null)));
					                      }
					                   }
					              else if(f3){
					                  if(!f1 && !f2 ){
					                      directCall tempc = new directCall((op1!=null?op1.getText():null));
					                      tempc.inputs = inputExpr;
					                      term = new functionCallT(tempc);
					                    }
					                  else if(f1 && f2){
					                        tl tlaccess = new tl((op1!=null?op1.getText():null));
					                        tlaccess.indexes = e;
					                        indirectCall tempc = new indirectCall(new accessField(tlaccess, (op3!=null?op3.getText():null)));
					                        tempc.inputs = inputExpr;
					                        term = new functionCallT(tempc);
					                   }
					                  else{
					                    System.out.println("error");
					                  }
					               }
					            
					}

					}
					break;
				case 7 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:434:9: functionCall
					{
					pushFollow(FOLLOW_functionCall_in_term3937);
					functionCall48=functionCall();
					state._fsp--;

					 term = new functionCallT(functionCall48); 
					}
					break;
				case 8 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:435:9: setExclude
					{
					pushFollow(FOLLOW_setExclude_in_term3950);
					setExclude49=setExclude();
					state._fsp--;

					 term = setExclude49; 
					}
					break;
				case 9 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:436:9: sortedList
					{
					pushFollow(FOLLOW_sortedList_in_term3964);
					sortedList50=sortedList();
					state._fsp--;

					 term = sortedList50; 
					}
					break;
				case 10 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:437:9: expectedValue
					{
					pushFollow(FOLLOW_expectedValue_in_term3978);
					expectedValue51=expectedValue();
					state._fsp--;

					 term = expectedValue51; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return term;
	}
	// $ANTLR end "term"



	// $ANTLR start "sortedList"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:439:1: sortedList returns [ sortedList sortedList] : SORTED '(' ID ( ',' expr )* ')' ;
	public final sortedList sortedList() throws RecognitionException {
		sortedList sortedList = null;


		Token ID52=null;
		expression expr53 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:439:44: ( SORTED '(' ID ( ',' expr )* ')' )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:440:9: SORTED '(' ID ( ',' expr )* ')'
			{
			match(input,SORTED,FOLLOW_SORTED_in_sortedList4005); 
			match(input,47,FOLLOW_47_in_sortedList4008); 
			ID52=(Token)match(input,ID,FOLLOW_ID_in_sortedList4009); 
			 sortedList lassertion = new sortedList((ID52!=null?ID52.getText():null));  
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:441:9: ( ',' expr )*
			loop42:
			while (true) {
				int alt42=2;
				int LA42_0 = input.LA(1);
				if ( (LA42_0==51) ) {
					alt42=1;
				}

				switch (alt42) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:441:10: ',' expr
					{
					match(input,51,FOLLOW_51_in_sortedList4022); 
					pushFollow(FOLLOW_expr_in_sortedList4024);
					expr53=expr();
					state._fsp--;

					 lassertion.dimension.add(expr53); 
					}
					break;

				default :
					break loop42;
				}
			}

			match(input,48,FOLLOW_48_in_sortedList4039); 
			 sortedList = lassertion;  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return sortedList;
	}
	// $ANTLR end "sortedList"



	// $ANTLR start "setExclude"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:446:1: setExclude returns [ setExclude setExclude] : 'setExclude' '(' ID ',' expr ')' ;
	public final setExclude setExclude() throws RecognitionException {
		setExclude setExclude = null;


		Token ID54=null;
		expression expr55 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:446:44: ( 'setExclude' '(' ID ',' expr ')' )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:447:11: 'setExclude' '(' ID ',' expr ')'
			{
			match(input,67,FOLLOW_67_in_setExclude4088); 
			match(input,47,FOLLOW_47_in_setExclude4090); 
			ID54=(Token)match(input,ID,FOLLOW_ID_in_setExclude4091); 
			match(input,51,FOLLOW_51_in_setExclude4093); 
			pushFollow(FOLLOW_expr_in_setExclude4095);
			expr55=expr();
			state._fsp--;

			match(input,48,FOLLOW_48_in_setExclude4097); 
			 setExclude = new setExclude((ID54!=null?ID54.getText():null), expr55); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return setExclude;
	}
	// $ANTLR end "setExclude"



	// $ANTLR start "functionCall"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:450:1: functionCall returns [functionCall functionCall] : ( tupleOperation | listOperation );
	public final functionCall functionCall() throws RecognitionException {
		functionCall functionCall = null;


		tupleCall tupleOperation56 =null;
		listCall listOperation57 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:450:49: ( tupleOperation | listOperation )
			int alt43=2;
			int LA43_0 = input.LA(1);
			if ( (LA43_0==TUPLE||LA43_0==68) ) {
				alt43=1;
			}
			else if ( (LA43_0==LIST||LA43_0==63||LA43_0==66) ) {
				alt43=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 43, 0, input);
				throw nvae;
			}

			switch (alt43) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:451:9: tupleOperation
					{
					pushFollow(FOLLOW_tupleOperation_in_functionCall4139);
					tupleOperation56=tupleOperation();
					state._fsp--;

					 functionCall = tupleOperation56; 
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:453:9: listOperation
					{
					pushFollow(FOLLOW_listOperation_in_functionCall4161);
					listOperation57=listOperation();
					state._fsp--;

					 functionCall = listOperation57; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return functionCall;
	}
	// $ANTLR end "functionCall"



	// $ANTLR start "tupleOperation"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:456:1: tupleOperation returns [tupleCall tupleOperation] : ( 'size' '(' expr ')' | TUPLE '(' op1= expr ( ',' op2= expr )* ')' );
	public final tupleCall tupleOperation() throws RecognitionException {
		tupleCall tupleOperation = null;


		expression op1 =null;
		expression op2 =null;
		expression expr58 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:456:50: ( 'size' '(' expr ')' | TUPLE '(' op1= expr ( ',' op2= expr )* ')' )
			int alt45=2;
			int LA45_0 = input.LA(1);
			if ( (LA45_0==68) ) {
				alt45=1;
			}
			else if ( (LA45_0==TUPLE) ) {
				alt45=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}

			switch (alt45) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:457:9: 'size' '(' expr ')'
					{
					match(input,68,FOLLOW_68_in_tupleOperation4199); 
					match(input,47,FOLLOW_47_in_tupleOperation4201); 
					pushFollow(FOLLOW_expr_in_tupleOperation4203);
					expr58=expr();
					state._fsp--;

					match(input,48,FOLLOW_48_in_tupleOperation4205); 
					 tupleOperation = new tupleCall("size"); tupleOperation.input(expr58); 
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:460:9: TUPLE '(' op1= expr ( ',' op2= expr )* ')'
					{
					match(input,TUPLE,FOLLOW_TUPLE_in_tupleOperation4235); 
					 tupleOperation = new tupleCall("construct"); 
					match(input,47,FOLLOW_47_in_tupleOperation4247); 
					pushFollow(FOLLOW_expr_in_tupleOperation4252);
					op1=expr();
					state._fsp--;

					 tupleOperation.input(op1); 
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:462:9: ( ',' op2= expr )*
					loop44:
					while (true) {
						int alt44=2;
						int LA44_0 = input.LA(1);
						if ( (LA44_0==51) ) {
							alt44=1;
						}

						switch (alt44) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:462:10: ',' op2= expr
							{
							match(input,51,FOLLOW_51_in_tupleOperation4265); 
							pushFollow(FOLLOW_expr_in_tupleOperation4271);
							op2=expr();
							state._fsp--;

							 tupleOperation.input(op2); 
							}
							break;

						default :
							break loop44;
						}
					}

					match(input,48,FOLLOW_48_in_tupleOperation4278); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return tupleOperation;
	}
	// $ANTLR end "tupleOperation"



	// $ANTLR start "listOperation"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:465:1: listOperation returns [listCall listOperation] : ( 'length' '(' expr ')' | LIST '(' op1= expr ( ',' op2= expr )* ')' | 'InsertSorted' '(' op1= expr ( ',' op2= expr )+ ')' );
	public final listCall listOperation() throws RecognitionException {
		listCall listOperation = null;


		expression op1 =null;
		expression op2 =null;
		expression expr59 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:465:47: ( 'length' '(' expr ')' | LIST '(' op1= expr ( ',' op2= expr )* ')' | 'InsertSorted' '(' op1= expr ( ',' op2= expr )+ ')' )
			int alt48=3;
			switch ( input.LA(1) ) {
			case 66:
				{
				alt48=1;
				}
				break;
			case LIST:
				{
				alt48=2;
				}
				break;
			case 63:
				{
				alt48=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 48, 0, input);
				throw nvae;
			}
			switch (alt48) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:466:9: 'length' '(' expr ')'
					{
					match(input,66,FOLLOW_66_in_listOperation4314); 
					match(input,47,FOLLOW_47_in_listOperation4316); 
					pushFollow(FOLLOW_expr_in_listOperation4318);
					expr59=expr();
					state._fsp--;

					match(input,48,FOLLOW_48_in_listOperation4320); 
					 listOperation = new listCall("length"); listOperation.input(expr59); 
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:469:9: LIST '(' op1= expr ( ',' op2= expr )* ')'
					{
					match(input,LIST,FOLLOW_LIST_in_listOperation4351); 
					 listOperation = new listCall("construct"); 
					match(input,47,FOLLOW_47_in_listOperation4363); 
					pushFollow(FOLLOW_expr_in_listOperation4368);
					op1=expr();
					state._fsp--;

					 listOperation.input(op1); 
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:471:9: ( ',' op2= expr )*
					loop46:
					while (true) {
						int alt46=2;
						int LA46_0 = input.LA(1);
						if ( (LA46_0==51) ) {
							alt46=1;
						}

						switch (alt46) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:471:10: ',' op2= expr
							{
							match(input,51,FOLLOW_51_in_listOperation4383); 
							pushFollow(FOLLOW_expr_in_listOperation4389);
							op2=expr();
							state._fsp--;

							 listOperation.input(op2); 
							}
							break;

						default :
							break loop46;
						}
					}

					match(input,48,FOLLOW_48_in_listOperation4396); 
					}
					break;
				case 3 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:472:11: 'InsertSorted' '(' op1= expr ( ',' op2= expr )+ ')'
					{
					match(input,63,FOLLOW_63_in_listOperation4408); 
					 listOperation = new listCall("insert-sorted"); 
					match(input,47,FOLLOW_47_in_listOperation4420); 
					pushFollow(FOLLOW_expr_in_listOperation4426);
					op1=expr();
					state._fsp--;

					 listOperation.input(op1); 
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:474:9: ( ',' op2= expr )+
					int cnt47=0;
					loop47:
					while (true) {
						int alt47=2;
						int LA47_0 = input.LA(1);
						if ( (LA47_0==51) ) {
							alt47=1;
						}

						switch (alt47) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:474:10: ',' op2= expr
							{
							match(input,51,FOLLOW_51_in_listOperation4439); 
							pushFollow(FOLLOW_expr_in_listOperation4445);
							op2=expr();
							state._fsp--;

							 listOperation.input(op2); 
							}
							break;

						default :
							if ( cnt47 >= 1 ) break loop47;
							EarlyExitException eee = new EarlyExitException(47, input);
							throw eee;
						}
						cnt47++;
					}

					match(input,48,FOLLOW_48_in_listOperation4461); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return listOperation;
	}
	// $ANTLR end "listOperation"



	// $ANTLR start "logicExpression"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:478:1: logicExpression returns [logicExpression logicExpression] : ( quantifier )* ( '{' op1= logicExpression ( AND | OR | IMPLIES ) op2= logicExpression '}' | '{' NOT op3= logicExpression '}' | '{' op4= expr '}' ) ;
	public final logicExpression logicExpression() throws RecognitionException {
		logicExpression logicExpression = null;


		Token AND61=null;
		Token OR62=null;
		Token IMPLIES63=null;
		Token NOT64=null;
		logicExpression op1 =null;
		logicExpression op2 =null;
		logicExpression op3 =null;
		expression op4 =null;
		quantifier quantifier60 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:478:58: ( ( quantifier )* ( '{' op1= logicExpression ( AND | OR | IMPLIES ) op2= logicExpression '}' | '{' NOT op3= logicExpression '}' | '{' op4= expr '}' ) )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:479:6: ( quantifier )* ( '{' op1= logicExpression ( AND | OR | IMPLIES ) op2= logicExpression '}' | '{' NOT op3= logicExpression '}' | '{' op4= expr '}' )
			{
			 logicExpression = new logicExpression();  
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:480:5: ( quantifier )*
			loop49:
			while (true) {
				int alt49=2;
				int LA49_0 = input.LA(1);
				if ( (LA49_0==EXISTS||LA49_0==FORALL) ) {
					alt49=1;
				}

				switch (alt49) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:480:6: quantifier
					{
					pushFollow(FOLLOW_quantifier_in_logicExpression4501);
					quantifier60=quantifier();
					state._fsp--;

					 logicExpression.q.add(quantifier60);  
					}
					break;

				default :
					break loop49;
				}
			}

			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:481:4: ( '{' op1= logicExpression ( AND | OR | IMPLIES ) op2= logicExpression '}' | '{' NOT op3= logicExpression '}' | '{' op4= expr '}' )
			int alt51=3;
			int LA51_0 = input.LA(1);
			if ( (LA51_0==69) ) {
				switch ( input.LA(2) ) {
				case NOT:
					{
					alt51=2;
					}
					break;
				case EXISTS:
				case FORALL:
				case 69:
					{
					alt51=1;
					}
					break;
				case EXPECTED:
				case ID:
				case LIST:
				case NEW:
				case NUMERIC:
				case SORTED:
				case TUPLE:
				case 47:
				case 52:
				case 62:
				case 63:
				case 66:
				case 67:
				case 68:
				case 72:
					{
					alt51=3;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 51, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 51, 0, input);
				throw nvae;
			}

			switch (alt51) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:482:4: '{' op1= logicExpression ( AND | OR | IMPLIES ) op2= logicExpression '}'
					{
					match(input,69,FOLLOW_69_in_logicExpression4516); 
					pushFollow(FOLLOW_logicExpression_in_logicExpression4530);
					op1=logicExpression();
					state._fsp--;

					 logicExpression.l = op1; 
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:484:11: ( AND | OR | IMPLIES )
					int alt50=3;
					switch ( input.LA(1) ) {
					case AND:
						{
						alt50=1;
						}
						break;
					case OR:
						{
						alt50=2;
						}
						break;
					case IMPLIES:
						{
						alt50=3;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 50, 0, input);
						throw nvae;
					}
					switch (alt50) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:484:13: AND
							{
							AND61=(Token)match(input,AND,FOLLOW_AND_in_logicExpression4547); 
							 logicExpression.operator = (AND61!=null?AND61.getText():null); 
							}
							break;
						case 2 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:485:15: OR
							{
							OR62=(Token)match(input,OR,FOLLOW_OR_in_logicExpression4566); 
							 logicExpression.operator = (OR62!=null?OR62.getText():null); 
							}
							break;
						case 3 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:486:15: IMPLIES
							{
							IMPLIES63=(Token)match(input,IMPLIES,FOLLOW_IMPLIES_in_logicExpression4584); 
							 logicExpression.operator = (IMPLIES63!=null?IMPLIES63.getText():null); 
							}
							break;

					}

					pushFollow(FOLLOW_logicExpression_in_logicExpression4612);
					op2=logicExpression();
					state._fsp--;

					 logicExpression.r = op2; 
					match(input,71,FOLLOW_71_in_logicExpression4620); 
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:491:4: '{' NOT op3= logicExpression '}'
					{
					match(input,69,FOLLOW_69_in_logicExpression4631); 
					NOT64=(Token)match(input,NOT,FOLLOW_NOT_in_logicExpression4633); 
					pushFollow(FOLLOW_logicExpression_in_logicExpression4640);
					op3=logicExpression();
					state._fsp--;

					 logicExpression.operator = (NOT64!=null?NOT64.getText():null); logicExpression.r = op3; 
					match(input,71,FOLLOW_71_in_logicExpression4646); 
					}
					break;
				case 3 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:493:4: '{' op4= expr '}'
					{
					match(input,69,FOLLOW_69_in_logicExpression4657); 
					pushFollow(FOLLOW_expr_in_logicExpression4663);
					op4=expr();
					state._fsp--;

					 logicExpression.leaf = op4; 
					match(input,71,FOLLOW_71_in_logicExpression4667); 
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return logicExpression;
	}
	// $ANTLR end "logicExpression"



	// $ANTLR start "quantifier"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:497:1: quantifier returns [quantifier quantifier] : ( ( FORALL '(' ID ',' op1= expr ',' op2= expr ')' ',' ) | ( EXISTS '(' ID ',' op1= expr ',' op2= expr ')' ',' ) );
	public final quantifier quantifier() throws RecognitionException {
		quantifier quantifier = null;


		Token ID65=null;
		Token ID66=null;
		expression op1 =null;
		expression op2 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:497:43: ( ( FORALL '(' ID ',' op1= expr ',' op2= expr ')' ',' ) | ( EXISTS '(' ID ',' op1= expr ',' op2= expr ')' ',' ) )
			int alt52=2;
			int LA52_0 = input.LA(1);
			if ( (LA52_0==FORALL) ) {
				alt52=1;
			}
			else if ( (LA52_0==EXISTS) ) {
				alt52=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 52, 0, input);
				throw nvae;
			}

			switch (alt52) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:498:5: ( FORALL '(' ID ',' op1= expr ',' op2= expr ')' ',' )
					{
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:498:5: ( FORALL '(' ID ',' op1= expr ',' op2= expr ')' ',' )
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:498:7: FORALL '(' ID ',' op1= expr ',' op2= expr ')' ','
					{
					match(input,FORALL,FOLLOW_FORALL_in_quantifier4695); 
					match(input,47,FOLLOW_47_in_quantifier4702); 
					ID65=(Token)match(input,ID,FOLLOW_ID_in_quantifier4703); 
					match(input,51,FOLLOW_51_in_quantifier4705); 
					pushFollow(FOLLOW_expr_in_quantifier4711);
					op1=expr();
					state._fsp--;

					match(input,51,FOLLOW_51_in_quantifier4713); 
					pushFollow(FOLLOW_expr_in_quantifier4719);
					op2=expr();
					state._fsp--;

					match(input,48,FOLLOW_48_in_quantifier4721); 
					match(input,51,FOLLOW_51_in_quantifier4723); 
					 quantifier = new forall((ID65!=null?ID65.getText():null), op1, op2); 
					}

					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:503:5: ( EXISTS '(' ID ',' op1= expr ',' op2= expr ')' ',' )
					{
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:503:5: ( EXISTS '(' ID ',' op1= expr ',' op2= expr ')' ',' )
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:503:7: EXISTS '(' ID ',' op1= expr ',' op2= expr ')' ','
					{
					match(input,EXISTS,FOLLOW_EXISTS_in_quantifier4751); 
					match(input,47,FOLLOW_47_in_quantifier4758); 
					ID66=(Token)match(input,ID,FOLLOW_ID_in_quantifier4759); 
					match(input,51,FOLLOW_51_in_quantifier4761); 
					pushFollow(FOLLOW_expr_in_quantifier4767);
					op1=expr();
					state._fsp--;

					match(input,51,FOLLOW_51_in_quantifier4769); 
					pushFollow(FOLLOW_expr_in_quantifier4775);
					op2=expr();
					state._fsp--;

					match(input,48,FOLLOW_48_in_quantifier4777); 
					match(input,51,FOLLOW_51_in_quantifier4779); 
					 quantifier = new exist((ID66!=null?ID66.getText():null), op1, op2); 
					}

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return quantifier;
	}
	// $ANTLR end "quantifier"



	// $ANTLR start "hole"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:510:1: hole returns [holeT hole] : '?' ID ;
	public final holeT hole() throws RecognitionException {
		holeT hole = null;


		Token ID67=null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:510:26: ( '?' ID )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:511:7: '?' ID
			{
			match(input,62,FOLLOW_62_in_hole4826); 
			ID67=(Token)match(input,ID,FOLLOW_ID_in_hole4828); 
			 hole = new holeT((ID67!=null?ID67.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return hole;
	}
	// $ANTLR end "hole"


	public static class ptype_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "ptype"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:513:1: ptype : ( REAL | CONST );
	public final progParser.ptype_return ptype() throws RecognitionException {
		progParser.ptype_return retval = new progParser.ptype_return();
		retval.start = input.LT(1);

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:513:7: ( REAL | CONST )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:
			{
			if ( input.LA(1)==CONST||input.LA(1)==REAL ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ptype"


	public static class ctype_return extends ParserRuleReturnScope {
		public String type;
		public int dimension;
	};


	// $ANTLR start "ctype"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:517:1: ctype returns [String type, int dimension] : ( TUPLETYPE | LISTTYPE ( '[' ']' )+ );
	public final progParser.ctype_return ctype() throws RecognitionException {
		progParser.ctype_return retval = new progParser.ctype_return();
		retval.start = input.LT(1);

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:517:43: ( TUPLETYPE | LISTTYPE ( '[' ']' )+ )
			int alt54=2;
			int LA54_0 = input.LA(1);
			if ( (LA54_0==TUPLETYPE) ) {
				alt54=1;
			}
			else if ( (LA54_0==LISTTYPE) ) {
				alt54=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 54, 0, input);
				throw nvae;
			}

			switch (alt54) {
				case 1 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:518:8: TUPLETYPE
					{
					match(input,TUPLETYPE,FOLLOW_TUPLETYPE_in_ctype4888); 
					 retval.type = "tuple"; retval.dimension = 0; 
					}
					break;
				case 2 :
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:519:9: LISTTYPE ( '[' ']' )+
					{
					match(input,LISTTYPE,FOLLOW_LISTTYPE_in_ctype4900); 
					 retval.type = "list"; int d = 0;
					// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:520:9: ( '[' ']' )+
					int cnt53=0;
					loop53:
					while (true) {
						int alt53=2;
						int LA53_0 = input.LA(1);
						if ( (LA53_0==64) ) {
							alt53=1;
						}

						switch (alt53) {
						case 1 :
							// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:520:10: '[' ']'
							{
							match(input,64,FOLLOW_64_in_ctype4914); 
							match(input,65,FOLLOW_65_in_ctype4916); 
							 d = d + 1; 
							}
							break;

						default :
							if ( cnt53 >= 1 ) break loop53;
							EarlyExitException eee = new EarlyExitException(53, input);
							throw eee;
						}
						cnt53++;
					}

					 retval.dimension = d;
					}
					break;

			}
			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ctype"



	// $ANTLR start "expectedValue"
	// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:524:1: expectedValue returns [expectedValue expectedValue] : EXPECTED '(' expr ')' ;
	public final expectedValue expectedValue() throws RecognitionException {
		expectedValue expectedValue = null;


		expression expr68 =null;

		try {
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:524:52: ( EXPECTED '(' expr ')' )
			// /Users/yefang/Documents/research/frontEndGen/test3/src/rules/prog.g:525:12: EXPECTED '(' expr ')'
			{
			match(input,EXPECTED,FOLLOW_EXPECTED_in_expectedValue4960); 
			match(input,47,FOLLOW_47_in_expectedValue4962); 
			pushFollow(FOLLOW_expr_in_expectedValue4964);
			expr68=expr();
			state._fsp--;

			match(input,48,FOLLOW_48_in_expectedValue4966); 
			 expectedValue = new expectedValue(expr68); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return expectedValue;
	}
	// $ANTLR end "expectedValue"

	// Delegated rules



	public static final BitSet FOLLOW_preAnnotation_in_prog63 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_PROGRAM_in_prog76 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_prog93 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_prog102 = new BitSet(new long[]{0x0000041001000100L,0x0000000000000002L});
	public static final BitSet FOLLOW_inputs_in_prog105 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_prog111 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_58_in_prog113 = new BitSet(new long[]{0x0000041003000150L});
	public static final BitSet FOLLOW_defineVarStatement_in_prog124 = new BitSet(new long[]{0x0000041003000150L});
	public static final BitSet FOLLOW_mechanismFunction_in_prog138 = new BitSet(new long[]{0x0000000002004050L});
	public static final BitSet FOLLOW_agentTemplate_in_prog156 = new BitSet(new long[]{0x0000000002004050L});
	public static final BitSet FOLLOW_execution_in_prog168 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_END_in_prog178 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_PROGRAM_in_prog180 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_prog182 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_postAnnotation_in_prog192 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ANNOTATION_in_preAnnotation222 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_PRE_in_preAnnotation224 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_54_in_preAnnotation226 = new BitSet(new long[]{0x0000000000048000L,0x0000000000000020L});
	public static final BitSet FOLLOW_logicExpression_in_preAnnotation240 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_ANNOTATION_in_preAnnotation254 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ANNOTATION_in_postAnnotation296 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_POST_in_postAnnotation298 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_54_in_postAnnotation300 = new BitSet(new long[]{0x0000000000048000L,0x0000000000000020L});
	public static final BitSet FOLLOW_logicExpression_in_postAnnotation314 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_ANNOTATION_in_postAnnotation328 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_defineVarStatement_in_statement365 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignStatement_in_statement381 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ifStatement_in_statement397 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_forStatement_in_statement413 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_defineFunStatement_in_statement429 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_priorInfoStatement_in_statement445 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIOR_INFO_in_priorInfoStatement478 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_54_in_priorInfoStatement480 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_priorInfoStatement500 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_72_in_priorInfoStatement515 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_priorInfoStatement517 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_NUMERIC_in_priorInfoStatement535 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_priorInfoStatement537 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_NUMERIC_in_priorInfoStatement543 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_priorInfoStatement545 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_NUMERIC_in_priorInfoStatement551 = new BitSet(new long[]{0x0080000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_55_in_priorInfoStatement593 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_NUMERIC_in_priorInfoStatement599 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_priorInfoStatement601 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_NUMERIC_in_priorInfoStatement607 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_priorInfoStatement609 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_NUMERIC_in_priorInfoStatement614 = new BitSet(new long[]{0x0080000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_priorInfoStatement654 = new BitSet(new long[]{0xC090822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_55_in_priorInfoStatement680 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_preAnnotation_in_defineFunStatement747 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_FUN_in_defineFunStatement774 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_defineFunStatement780 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_defineFunStatement825 = new BitSet(new long[]{0x0000041001000100L});
	public static final BitSet FOLLOW_inputs_in_defineFunStatement826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_defineFunStatement829 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_defineFunStatement831 = new BitSet(new long[]{0x0000041001000100L});
	public static final BitSet FOLLOW_output_in_defineFunStatement833 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_defineFunStatement836 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_58_in_defineFunStatement838 = new BitSet(new long[]{0x00000414013A0140L});
	public static final BitSet FOLLOW_statement_in_defineFunStatement861 = new BitSet(new long[]{0x00000414013A2140L});
	public static final BitSet FOLLOW_END_in_defineFunStatement888 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_defineFunStatement894 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_postAnnotation_in_defineFunStatement918 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ptype_in_defineVarStatement1061 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ctype_in_defineVarStatement1066 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_defineVarStatement1092 = new BitSet(new long[]{0x0480000000000000L});
	public static final BitSet FOLLOW_58_in_defineVarStatement1117 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_defineVarStatement1119 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_55_in_defineVarStatement1126 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_assignStatement1241 = new BitSet(new long[]{0x0400000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_assignStatement1286 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_assignStatement1292 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_assignStatement1294 = new BitSet(new long[]{0x0400000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_58_in_assignStatement1341 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_assignStatement1455 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_55_in_assignStatement1503 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IF_in_ifStatement1549 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_ifStatement1572 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_THEN_in_ifStatement1607 = new BitSet(new long[]{0x00000414013A0140L});
	public static final BitSet FOLLOW_statement_in_ifStatement1630 = new BitSet(new long[]{0x00000414013A3940L});
	public static final BitSet FOLLOW_ELSEIF_in_ifStatement1684 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_ifStatement1688 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_THEN_in_ifStatement1706 = new BitSet(new long[]{0x00000414013A0140L});
	public static final BitSet FOLLOW_statement_in_ifStatement1729 = new BitSet(new long[]{0x00000414013A3940L});
	public static final BitSet FOLLOW_ELSE_in_ifStatement1786 = new BitSet(new long[]{0x00000414013A0140L});
	public static final BitSet FOLLOW_statement_in_ifStatement1809 = new BitSet(new long[]{0x00000414013A2140L});
	public static final BitSet FOLLOW_END_in_ifStatement1863 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_IF_in_ifStatement1865 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FOR_in_forStatement1913 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_forStatement1931 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_58_in_forStatement1949 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_forStatement1955 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_TO_in_forStatement1957 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_forStatement1963 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_DO_in_forStatement1996 = new BitSet(new long[]{0x00000414013A0140L});
	public static final BitSet FOLLOW_statement_in_forStatement2015 = new BitSet(new long[]{0x00000414013A2140L});
	public static final BitSet FOLLOW_END_in_forStatement2035 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_FOR_in_forStatement2037 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AGENT_in_agentTemplate2079 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_agentTemplate2085 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_58_in_agentTemplate2088 = new BitSet(new long[]{0x00000414013A0140L});
	public static final BitSet FOLLOW_statement_in_agentTemplate2107 = new BitSet(new long[]{0x00000414013A2140L});
	public static final BitSet FOLLOW_END_in_agentTemplate2129 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_agentTemplate2135 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEW_in_agentCreation2187 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_agentCreation2189 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_47_in_agentCreation2203 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_agentCreation2209 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_agentCreation2211 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_agentCreation2217 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_agentCreation2219 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEW_in_variableCreation2262 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_VAR_in_variableCreation2264 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_47_in_variableCreation2277 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_variableCreation2283 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_variableCreation2285 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_variableCreation2291 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_variableCreation2293 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_preAnnotation_in_mechanismFunction2369 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_MECHANISM_in_mechanismFunction2396 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_mechanismFunction2400 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_mechanismFunction2445 = new BitSet(new long[]{0x0000041001000100L});
	public static final BitSet FOLLOW_inputs_in_mechanismFunction2447 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_mechanismFunction2449 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_mechanismFunction2473 = new BitSet(new long[]{0x0000041001000100L});
	public static final BitSet FOLLOW_output_in_mechanismFunction2475 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_mechanismFunction2477 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_58_in_mechanismFunction2479 = new BitSet(new long[]{0x00000414013A0140L});
	public static final BitSet FOLLOW_statement_in_mechanismFunction2504 = new BitSet(new long[]{0x00000414013A2140L});
	public static final BitSet FOLLOW_END_in_mechanismFunction2529 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_mechanismFunction2533 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ptype_in_inputs2604 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ctype_in_inputs2614 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_inputs2635 = new BitSet(new long[]{0x0008000000000002L});
	public static final BitSet FOLLOW_51_in_inputs2648 = new BitSet(new long[]{0x0000041001000100L});
	public static final BitSet FOLLOW_ptype_in_inputs2655 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ctype_in_inputs2665 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_inputs2674 = new BitSet(new long[]{0x0008000000000002L});
	public static final BitSet FOLLOW_ptype_in_output2739 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ctype_in_output2745 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_output2757 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EXECUTE_in_execution2790 = new BitSet(new long[]{0x00000414013A2140L});
	public static final BitSet FOLLOW_statement_in_execution2801 = new BitSet(new long[]{0x00000414013A2140L});
	public static final BitSet FOLLOW_END_in_execution2814 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_EXECUTE_in_execution2816 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_negation_in_expr2855 = new BitSet(new long[]{0x0000400000000002L,0x0000000000000040L});
	public static final BitSet FOLLOW_46_in_expr2878 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_negation_in_expr2882 = new BitSet(new long[]{0x0000400000000002L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_expr2913 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_negation_in_expr2917 = new BitSet(new long[]{0x0000400000000002L,0x0000000000000040L});
	public static final BitSet FOLLOW_relation_in_negation2980 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_72_in_negation3005 = new BitSet(new long[]{0xC010822050910000L,0x000000000000001CL});
	public static final BitSet FOLLOW_relation_in_negation3007 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_add_in_relation3051 = new BitSet(new long[]{0x3B00200000000002L});
	public static final BitSet FOLLOW_60_in_relation3076 = new BitSet(new long[]{0xC010822050910000L,0x000000000000001CL});
	public static final BitSet FOLLOW_add_in_relation3080 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_56_in_relation3098 = new BitSet(new long[]{0xC010822050910000L,0x000000000000001CL});
	public static final BitSet FOLLOW_add_in_relation3102 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_61_in_relation3120 = new BitSet(new long[]{0xC010822050910000L,0x000000000000001CL});
	public static final BitSet FOLLOW_add_in_relation3124 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_57_in_relation3142 = new BitSet(new long[]{0xC010822050910000L,0x000000000000001CL});
	public static final BitSet FOLLOW_add_in_relation3146 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_45_in_relation3164 = new BitSet(new long[]{0xC010822050910000L,0x000000000000001CL});
	public static final BitSet FOLLOW_add_in_relation3168 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_59_in_relation3186 = new BitSet(new long[]{0xC010822050910000L,0x000000000000001CL});
	public static final BitSet FOLLOW_add_in_relation3190 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_mult_in_add3257 = new BitSet(new long[]{0x0014000000000002L});
	public static final BitSet FOLLOW_50_in_add3277 = new BitSet(new long[]{0xC010822050910000L,0x000000000000001CL});
	public static final BitSet FOLLOW_mult_in_add3281 = new BitSet(new long[]{0x0014000000000002L});
	public static final BitSet FOLLOW_52_in_add3300 = new BitSet(new long[]{0xC010822050910000L,0x000000000000001CL});
	public static final BitSet FOLLOW_mult_in_add3304 = new BitSet(new long[]{0x0014000000000002L});
	public static final BitSet FOLLOW_unary_in_mult3373 = new BitSet(new long[]{0x0002000000000202L});
	public static final BitSet FOLLOW_49_in_mult3406 = new BitSet(new long[]{0xC010822050910000L,0x000000000000001CL});
	public static final BitSet FOLLOW_unary_in_mult3410 = new BitSet(new long[]{0x0002000000000202L});
	public static final BitSet FOLLOW_DIV_in_mult3429 = new BitSet(new long[]{0xC010822050910000L,0x000000000000001CL});
	public static final BitSet FOLLOW_unary_in_mult3433 = new BitSet(new long[]{0x0002000000000202L});
	public static final BitSet FOLLOW_term_in_unary3497 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_52_in_unary3517 = new BitSet(new long[]{0xC000822050910000L,0x000000000000001CL});
	public static final BitSet FOLLOW_term_in_unary3519 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMERIC_in_term3561 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_47_in_term3579 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_term3580 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_term3581 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_hole_in_term3600 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_agentCreation_in_term3625 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variableCreation_in_term3644 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_term3668 = new BitSet(new long[]{0x0020800000000002L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_term3692 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_term3698 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_term3700 = new BitSet(new long[]{0x0020800000000002L,0x0000000000000001L});
	public static final BitSet FOLLOW_53_in_term3749 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_term3755 = new BitSet(new long[]{0x0000800000000002L});
	public static final BitSet FOLLOW_47_in_term3794 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_term3796 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_47_in_term3826 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_term3830 = new BitSet(new long[]{0x0009000000000000L});
	public static final BitSet FOLLOW_51_in_term3868 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_term3872 = new BitSet(new long[]{0x0009000000000000L});
	public static final BitSet FOLLOW_48_in_term3891 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_functionCall_in_term3937 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_setExclude_in_term3950 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_sortedList_in_term3964 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expectedValue_in_term3978 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SORTED_in_sortedList4005 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_47_in_sortedList4008 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_sortedList4009 = new BitSet(new long[]{0x0009000000000000L});
	public static final BitSet FOLLOW_51_in_sortedList4022 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_sortedList4024 = new BitSet(new long[]{0x0009000000000000L});
	public static final BitSet FOLLOW_48_in_sortedList4039 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_setExclude4088 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_47_in_setExclude4090 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_setExclude4091 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_setExclude4093 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_setExclude4095 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_setExclude4097 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tupleOperation_in_functionCall4139 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_listOperation_in_functionCall4161 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_tupleOperation4199 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_47_in_tupleOperation4201 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_tupleOperation4203 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_tupleOperation4205 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TUPLE_in_tupleOperation4235 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_47_in_tupleOperation4247 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_tupleOperation4252 = new BitSet(new long[]{0x0009000000000000L});
	public static final BitSet FOLLOW_51_in_tupleOperation4265 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_tupleOperation4271 = new BitSet(new long[]{0x0009000000000000L});
	public static final BitSet FOLLOW_48_in_tupleOperation4278 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_66_in_listOperation4314 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_47_in_listOperation4316 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_listOperation4318 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_listOperation4320 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LIST_in_listOperation4351 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_47_in_listOperation4363 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_listOperation4368 = new BitSet(new long[]{0x0009000000000000L});
	public static final BitSet FOLLOW_51_in_listOperation4383 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_listOperation4389 = new BitSet(new long[]{0x0009000000000000L});
	public static final BitSet FOLLOW_48_in_listOperation4396 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_listOperation4408 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_47_in_listOperation4420 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_listOperation4426 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_listOperation4439 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_listOperation4445 = new BitSet(new long[]{0x0009000000000000L});
	public static final BitSet FOLLOW_48_in_listOperation4461 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quantifier_in_logicExpression4501 = new BitSet(new long[]{0x0000000000048000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_logicExpression4516 = new BitSet(new long[]{0x0000000000048000L,0x0000000000000020L});
	public static final BitSet FOLLOW_logicExpression_in_logicExpression4530 = new BitSet(new long[]{0x0000000080400020L});
	public static final BitSet FOLLOW_AND_in_logicExpression4547 = new BitSet(new long[]{0x0000000000048000L,0x0000000000000020L});
	public static final BitSet FOLLOW_OR_in_logicExpression4566 = new BitSet(new long[]{0x0000000000048000L,0x0000000000000020L});
	public static final BitSet FOLLOW_IMPLIES_in_logicExpression4584 = new BitSet(new long[]{0x0000000000048000L,0x0000000000000020L});
	public static final BitSet FOLLOW_logicExpression_in_logicExpression4612 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_logicExpression4620 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_logicExpression4631 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_NOT_in_logicExpression4633 = new BitSet(new long[]{0x0000000000048000L,0x0000000000000020L});
	public static final BitSet FOLLOW_logicExpression_in_logicExpression4640 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_logicExpression4646 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_logicExpression4657 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_logicExpression4663 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_71_in_logicExpression4667 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FORALL_in_quantifier4695 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_47_in_quantifier4702 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_quantifier4703 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_quantifier4705 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_quantifier4711 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_quantifier4713 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_quantifier4719 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_quantifier4721 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_quantifier4723 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EXISTS_in_quantifier4751 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_47_in_quantifier4758 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_quantifier4759 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_quantifier4761 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_quantifier4767 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_quantifier4769 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_quantifier4775 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_quantifier4777 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_quantifier4779 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_62_in_hole4826 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_ID_in_hole4828 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TUPLETYPE_in_ctype4888 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LISTTYPE_in_ctype4900 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_64_in_ctype4914 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_ctype4916 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
	public static final BitSet FOLLOW_EXPECTED_in_expectedValue4960 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_47_in_expectedValue4962 = new BitSet(new long[]{0xC010822050910000L,0x000000000000011CL});
	public static final BitSet FOLLOW_expr_in_expectedValue4964 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_expectedValue4966 = new BitSet(new long[]{0x0000000000000002L});
}
