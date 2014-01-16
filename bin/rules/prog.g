grammar prog;

options {
  language = Java;
}

@header {
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
  
}

@lexer::header {
  package rules;
  import java.util.*;
}

@members {
}

prog returns [program program]:
      { boolean hasPre = false; }
      (preAnnotation { hasPre = true; } )?
      PROGRAM 
      { $program = new program(); 
        if(hasPre)
          $program.pre = $preAnnotation.preAnnotation;
      }
      ID 
      '[' (inputs { $program.inputVar($inputs.inputs); })? ']' '=' 
      ( defineVarStatement { $program.addD($defineVarStatement.defineVarStatement); })*
      ( mechanismFunction { $program.mechanism = $mechanismFunction.mechanismFunction; }
      |
      agentTemplate { $program.agentTemplate.put($agentTemplate.agentTemplate.name, $agentTemplate.agentTemplate); })+
      execution { $program.code = $execution.execution; }
      END PROGRAM ID
      ( postAnnotation { $program.post = $postAnnotation.postAnnotation;} )?
     ;
preAnnotation returns[logicExpression preAnnotation]:
          '@' PRE ':'
            logicExpression { $preAnnotation = $logicExpression.logicExpression; }
          '@'
          ;
          
postAnnotation returns[logicExpression postAnnotation]: 
          '@' POST ':'
            logicExpression { $postAnnotation = $logicExpression.logicExpression; }
          '@'
          ;
     
statement returns [statement statement]:
          defineVarStatement { $statement = $defineVarStatement.defineVarStatement; }
          | assignStatement { $statement = $assignStatement.assignStatement; }
          | ifStatement { $statement = $ifStatement.ifStatement; }
          | forStatement { $statement = $forStatement.forStatement; }
          | defineFunStatement { $statement = $defineFunStatement.defineFunStatement; }
          | priorInfoStatement { $statement = $priorInfoStatement.priorInfo; }
          ;
priorInfoStatement returns [priorInfo priorInfo]:
          PRIOR_INFO ':' { $priorInfo = new priorInfo(); inputPrior x = null;}
          ( op1 = expr { x = new inputPrior($op1.expr); }
           '~' '[' 
           lower1 = NUMERIC ',' upper1 = NUMERIC ',' pr1 = NUMERIC 
           { x.intervals.add(Double.parseDouble($lower1.text));
             x.intervals.add(Double.parseDouble($upper1.text));
             x.intervals.add(Double.parseDouble($pr1.text)); }
             
           (';' lower2 = NUMERIC ',' upper2 = NUMERIC ',' pr2 =NUMERIC
           { x.intervals.add(Double.parseDouble($lower2.text));
             x.intervals.add(Double.parseDouble($upper2.text));
             x.intervals.add(Double.parseDouble($pr2.text)); }
            )*
          ']'{ $priorInfo.prior.add(x); }
          )+
          ';'
          ;
defineFunStatement returns [defineFunStatement defineFunStatement]: 
                     { boolean hasPre = false;}
                    ( preAnnotation { hasPre = true; } )?
                    FUN op1 = ID 
                    { $defineFunStatement = new defineFunStatement($op1.text);  
                      if(hasPre){
                        $defineFunStatement.pre = $preAnnotation.preAnnotation;
                       }
                    }
                    '['inputs { $defineFunStatement.input($inputs.inputs); }']' '[' output { $defineFunStatement.output($output.output); }']' '='
                    (statement { $defineFunStatement.addS($statement.statement); })+ 
                    END op2 = ID
                    ( postAnnotation  
                    { $defineFunStatement.post = $postAnnotation.postAnnotation; }
                    )?
                    ;
                    
defineVarStatement returns [defVarStatement defineVarStatement]: 
                    { String type = ""; int dimension = 0;}
                    (ptype { type = $ptype.text; }| ctype { type = $ctype.type;  dimension = $ctype.dimension; }) 
                    ID { $defineVarStatement = new defVarStatement(type, $ID.text); $defineVarStatement.dimension = dimension; }
                    ('=' expr { $defineVarStatement.init = $expr.expr; } )? ';'
                    
                    ;
                    
assignStatement returns [assignStatement assignStatement]:
                    { boolean access = false; ArrayList<expression> e = new ArrayList<expression>(); tl lhs = null;}
                    ID 
                    (
                    '[' op1 = expr ']'
                    { 
                      if(!access){ access = true; }
                      e.add($op1.expr);
                    }
                    )* '='
                    { 
                      if(access) {
                        lhs = new tl($ID.text);
                        lhs.indexes = e;
                        $assignStatement= new assignStatement(lhs);
                      }
                      else
                        $assignStatement= new assignStatement($ID.text);
                    }
                    
                    
                    (
                      op2 = expr { $assignStatement.assignment = $op2.expr; }
                      )
                    ';'
                    ;

ifStatement returns [ifStatement ifStatement]:
              IF  {$ifStatement = new ifStatement();} 
               op1=expr { $ifStatement.addCondition($op1.expr); } 
               
              THEN { ArrayList<statement> temp1 = new ArrayList<statement>(1); } 
              ( s1=statement { temp1.add($s1.statement); })+ 
              { $ifStatement.addConsq(temp1); }
              { ArrayList<statement> temp2; } 
             (ELSEIF op2=expr { $ifStatement.addCondition($op2.expr); }
              THEN { temp2 = new ArrayList<statement>(1); }
               ( s2=statement { temp2.add($s2.statement); } )+
               { $ifStatement.addConsq(temp2); } )*
               
             (ELSE { ArrayList<statement> temp3 = new ArrayList<statement>(1); }  
             ( s3=statement { temp3.add($s3.statement); } )+  
             { $ifStatement.addElse(temp3); } )?
             
             END IF
       ;
              
forStatement returns [forStatement forStatement]: 
              FOR { $forStatement = new forStatement(); }
              ID { $forStatement.iterator = $ID.text; }
              '=' op1 = expr TO op2 = expr 
              { $forStatement.lbound = $op1.expr;  $forStatement.ubound = $op2.expr;}
              DO
                (statement { $forStatement.addS($statement.statement); })+
              END FOR
              ;
agentTemplate returns [agentTemplate agentTemplate]: 
                AGENT op1 = ID { $agentTemplate = new agentTemplate($op1.text); }'='
                (statement { $agentTemplate.addS($statement.statement); })+
                END op2 = ID
                ;              
agentCreation returns [agentT agentCreation]: 
          NEW ID { $agentCreation = new agentT($ID.text); }
          '(' op1 = expr ',' op2 = expr ')'
          { 
            $agentCreation.index = $op2.expr; 
            $agentCreation.lable = $op1.expr; 
          }
          ;
variableCreation returns [newVar variableCreation]:
          NEW VAR 
          '(' op1 = expr ',' op2 = expr ')'
          {  $variableCreation = new newVar($op1.expr, $op2.expr); }
          ;
mechanismFunction returns [defineFunStatement mechanismFunction]:
                    { boolean hasPre = false; }
                   ( preAnnotation { hasPre = true;} )?
                    MECHANISM op1=ID 
                    { $mechanismFunction = new defineFunStatement($op1.text);  
                       if(hasPre){
                          $mechanismFunction.pre = $preAnnotation.preAnnotation;
                       }
                    }
                    '[' inputs ']' { $mechanismFunction.input($inputs.inputs); }
                    '[' output ']' '=' { $mechanismFunction.output($output.output); }
                    (statement { $mechanismFunction.addS($statement.statement); })+
                   END op2=ID
                   ;
                
inputs returns [ArrayList<parameter> inputs]: 
        { $inputs = new ArrayList<parameter>(); String type = ""; parameter p = null; int dimension =0; }
        (op1 = ptype  { type = $op1.text; dimension =0; }| op2 = ctype { type = $op2.type; dimension = $op2.dimension; } ) 
          op3 = ID { p = new parameter(type, $op3.text); p.dimension = dimension; $inputs.add(p); }
        (',' (op4 = ptype { type = $op4.text; dimension =0; } | op5 = ctype { type = $op5.type; dimension = $op5.dimension; }) op6 = ID
          { p = new parameter(type, $op6.text); p.dimension = dimension; $inputs.add(p);  }
        )* 
      ;
      
output returns [parameter output]: 
       { String type = ""; int dimension =0;}
      (ptype { type = $ptype.text; } | ctype { type = $ctype.type; dimension = $ctype.dimension; }) 
      ID { $output = new parameter(type, $ID.text); $output.dimension = dimension; }
      ;
      
execution returns [executeCode execution]: 
      EXECUTE { $execution = new executeCode(); }
      (statement { $execution.s.add($statement.statement);} )*
      END EXECUTE
      ;

expr returns [expression expr]:
         { boolean f1 = false;}
        op1=negation 
        { expression temp = $op1.negation;}
        (('&&' op2=negation 
        { f1=true;
          temp = new expression(temp, $op2.negation, "&&");
        }
        |
        '||' op2=negation 
        { f1=true;
          temp = new expression(temp, $op2.negation, "||"); 
        } 
        ))*
        {  $expr = temp;
            if(f1)
              $expr.type = "expression";
        }
      ;
    
negation returns [expression negation]: 
      relation 
      { $negation = $relation.relation;  }
      |
      '~' relation 
      {  $negation = new expression($relation.relation,"~"); 
          $negation.type = "expression"; 
      }
      ;

relation returns [expression relation]:
       { boolean f1 = false;}
      op1=add 
      { expression temp = $op1.add; }
      
      ('>' op2=add 
      { f1=true;
        temp = new expression(temp, $op2.add, ">"); 
      }
      |'<' op2=add 
      { f1=true;
        temp = new expression(temp, $op2.add, "<"); 
      }
      |'>=' op2=add 
      { f1=true; 
        temp = new expression(temp, $op2.add, ">="); 
      }
      |'<=' op2=add 
      { f1=true;
        temp = new expression(temp, $op2.add, "<="); 
      }
      |'!=' op2=add 
      { f1=true;
        temp = new expression(temp, $op2.add, "!="); 
      }
      |'==' op2=add 
      { f1=true;
        temp = new expression(temp, $op2.add, "==");
      }
      )?
      
      {
          $relation = temp; 
            if(f1)
              $relation.type = "expression";
      }
      ;

add returns [expression add]:
      { boolean f1 = false;}
      op1=mult 
      { expression temp = $op1.mult; }
      ( ('+' op2=mult 
      { f1 = true; 
        temp = new expression(temp, $op2.mult, "+"); 
      }
      | '-' op2=mult 
      { f1 = true; 
        temp = new expression(temp, $op2.mult, "-"); 
      }
      )  
     )*
      { 
        $add = temp; 
         if(f1)
              $add.type = "expression";
      }
      ;

mult returns [expression mult]:
      { boolean f1 = false; }
      op1=unary 
      { expression temp = $op1.unary; }
       
      (
      '*' op2=unary 
      { f1 = true; 
        temp = new expression(temp, $op2.unary, "*"); 
      }
      | DIV op2=unary 
      { f1 = true; 
        temp = new expression(temp, $op2.unary, $DIV.text); 
      }
     
      )*
      
      { 
          $mult= temp;
          if(f1)
              $mult.type = "expression";
      }
      ;

unary returns [expression unary]: 
      term 
      { $unary = new expression($term.term); 
        $unary.type = $term.term.type;
      }
      |  '-' term 
     {    $unary = new expression(new expression($term.term), "-"); 
          $unary.type = "expression";
      }
      ;
      
term returns [term term]:   
       NUMERIC 
      { $term = new num(Double.parseDouble($NUMERIC.text));  
      }
      |'('expr')' 
      { 
        $term = new expressionT($expr.expr); 
      }
      | hole 
      { 
        $term = $hole.hole;
      }
      |
      agentCreation { $term = $agentCreation.agentCreation; }
      | 
      variableCreation { $term = $variableCreation.variableCreation; }
      |
      ( op1 = ID 
        { 
          boolean f1 = false; //list access
          boolean f2 = false; // access private field
          boolean f3 = false; // function call
        
          ArrayList<expression> e = new ArrayList<expression>();
          ArrayList<expression> inputExpr = new ArrayList<expression>();
        
        }
          ('[' op2 = expr ']' 
            { 
              if(!f1) 
                 f1 = true; 
                  
              e.add($op2.expr); // list index
            }
          )* 
      
          ('.' op3 = ID { f2 = true; })? 
      
          ( 
            '(' ')' { f3 = true; }
            |
            '(' op4=expr 
                {   f3 = true; 
                    inputExpr.add($op4.expr);
                }
                (',' op5=expr { inputExpr.add($op5.expr); } )*
            ')'
          )?
            {  
              if(!f1 && !f2 && !f3){
                $term = new var($op1.text);
              }
              else if(f1 && !f2 && !f3){
                  tl tlaccess = new tl($op1.text);
                  tlaccess.indexes = e;
                  $term = tlaccess;
              }
              else if(f2 && !f3){
                  if(!f1)
                      $term = new accessT(new accessField($op1.text, $op3.text));
                  else{
                        tl tlaccess = new tl($op1.text);
                        tlaccess.indexes = e;
                        $term = new accessT(new accessField(tlaccess, $op3.text));
                      }
                   }
              else if(f3){
                  if(!f1 && !f2 ){
                      directCall tempc = new directCall($op1.text);
                      tempc.inputs = inputExpr;
                      $term = new functionCallT(tempc);
                    }
                  else if(f1 && f2){
                        tl tlaccess = new tl($op1.text);
                        tlaccess.indexes = e;
                        indirectCall tempc = new indirectCall(new accessField(tlaccess, $op3.text));
                        tempc.inputs = inputExpr;
                        $term = new functionCallT(tempc);
                   }
                  else{
                    System.out.println("error");
                  }
               }
            }
       )
      | functionCall { $term = new functionCallT($functionCall.functionCall); } 
      | setExclude   { $term = $setExclude.setExclude; }
      | sortedList   { $term = $sortedList.sortedList; }
      | expectedValue { $term = $expectedValue.expectedValue; }
      ;
sortedList returns [ sortedList sortedList]:
        SORTED  '('ID { sortedList lassertion = new sortedList($ID.text);  }
        (',' expr { lassertion.dimension.add($expr.expr); })* 
        ')'
        { $sortedList = lassertion;  }
        ;
        
setExclude returns [ setExclude setExclude]: 
          'setExclude' '('ID ',' expr ')' { $setExclude = new setExclude($ID.text, $expr.expr); }
          ;
          
functionCall returns [functionCall functionCall]:
        tupleOperation { $functionCall = $tupleOperation.tupleOperation; }
        |
        listOperation { $functionCall = $listOperation.listOperation; }
        ;
        
tupleOperation returns [tupleCall tupleOperation]:
        'size' '(' expr ')'
        { $tupleOperation = new tupleCall("size"); $tupleOperation.input($expr.expr); }
        |
        TUPLE { $tupleOperation = new tupleCall("construct"); }
        '('op1 = expr { $tupleOperation.input($op1.expr); }
        (',' op2 = expr { $tupleOperation.input($op2.expr); } )* ')'
        ;
        
listOperation returns [listCall listOperation]:
        'length' '(' expr ')' 
        { $listOperation = new listCall("length"); $listOperation.input($expr.expr); }
        |
        LIST { $listOperation = new listCall("construct"); }
        '('op1 = expr   { $listOperation.input($op1.expr); }
        (',' op2 = expr { $listOperation.input($op2.expr); } )* ')'
        | 'InsertSorted' { $listOperation = new listCall("insert-sorted"); }
        '(' op1 = expr { $listOperation.input($op1.expr); }
        (',' op2 = expr { $listOperation.input($op2.expr); } ) +
        ')'
        ;
        
logicExpression returns [logicExpression logicExpression]:
     { $logicExpression = new logicExpression();  }
    (quantifier { $logicExpression.q.add($quantifier.quantifier);  })*
   ( 
   '{' 
       op1 = logicExpression  { $logicExpression.l = $op1.logicExpression; }
          ( AND  { $logicExpression.operator = $AND.text; }
            | OR { $logicExpression.operator = $OR.text; }
            | IMPLIES { $logicExpression.operator = $IMPLIES.text; }
          ) 
       op2 = logicExpression { $logicExpression.r = $op2.logicExpression; }
    '}' 
   |
   '{' NOT  op3 = logicExpression   { $logicExpression.operator = $NOT.text; $logicExpression.r = $op3.logicExpression; } '}'
   | 
   '{' op4 = expr { $logicExpression.leaf = $op4.expr; } '}'
   )  
   ;

quantifier returns [quantifier quantifier]:
    ( FORALL 
    '('ID ',' op1 = expr ',' op2 = expr ')' ','
     { $quantifier = new forall($ID.text, $op1.expr, $op2.expr); }
     )
    |
    ( EXISTS 
    '('ID ',' op1 = expr ',' op2 = expr ')' ','
    { $quantifier = new exist($ID.text, $op1.expr, $op2.expr); }
    )
    ;
   
        
hole returns [holeT hole]: 
      '?' ID { $hole = new holeT($ID.text);}
      ;
ptype : 
        REAL
      | CONST
      ;
ctype returns [String type, int dimension]: 
       TUPLETYPE { $type = "tuple"; $dimension = 0; }
      | LISTTYPE { $type = "list"; int d = 0;} 
        ('[' ']' { d = d + 1; })+
        { $dimension = d;}
      ;

expectedValue returns [expectedValue expectedValue]: 
           EXPECTED '(' expr ')' { $expectedValue = new expectedValue($expr.expr); }
           ;
    

//type
REAL: 'real';
CONST: 'const';
TUPLETYPE: 'tuple';
LISTTYPE: 'list';
FUN: 'fun';
PROGRAM: 'program';

//operator
MOD: 'mod';
DIV: 'div';

// conjunctive
IF: 'if';
THEN: 'then';
ELSEIF: 'elseif';
ELSE: 'else';

//
LIST: 'List';
TUPLE: 'Tuple';

//logic 
AND: 'and';
OR: 'or';
NOT: 'not';
IMPLIES: 'implies';
FORALL: 'forall';
EXISTS: 'exist';
SORTED: 'sorted';
//special keywords
EXECUTE: 'execute';
TEMPLATE: 'template';
DO: 'do';
FOR: 'for';
TO: 'to';
ANNOTATION: '@';
PRE: 'pre';
POST: 'post';
END : 'end';
MECHANISM: 'mechanism';
AGENT: 'agent';
NEW: 'new';
VAR: 'var';
EXPECTED: 'expected';
PRIOR_INFO: 'prior_Info';
MULTI_COMMENTS: '/*' .* '*/'  { $channel = HIDDEN; };     
COMMENTS: '//' .* ('\n'|'\r')  { $channel = HIDDEN; };
NUMERIC: ('0'..'9')+ (|('.'(('0'..'9')+))); 
ID    : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')* ;
WS : ( ' ' | '\t' | '\n' | '\r' | '\u000C')+  { $channel = HIDDEN; }; 

