import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


import java.util.Iterator;
import java.util.HashMap;

/**
 * This class implements an interpreter for the Boa language, a subset of
 * Python.
 */
public class Boa {

  /**
   * Usage: 
   * java Boa filename.py
   * will run the interpreter on the code contained in filename.py
   *
   */
  public static void main(String[] args) throws FileNotFoundException {
    Scanner inp = new Scanner(new File(args[0]));

    ArrayList<String> rawCode = new ArrayList<String>();
    
    // rawCode.add("# hello mate");
    // rawCode.add(" ");
    // rawCode.add("print(5)");
    // rawCode.add("print(\"hello\")");

    long start = System.currentTimeMillis();
    while(inp.hasNext()) {
      rawCode.add(inp.nextLine());
    }

    
    ArrayList<Statement> mainStatements = parse(rawCode, 0,rawCode.size());
    HashMap<String,Expression> namespace = new HashMap<String,Expression>();
    // System.out.println("line num "+ lineNum);
    // System.out.println("size " + mainStatements.size());
    lineNum -= (mainStatements.size());
    // System.out.println("line num " + lineNum);
    for(Statement s : mainStatements) {
      try {
        s.execute(namespace);
      } catch (Exception e) {
        System.out.println("error at line " + (lineNum + mainStatements.indexOf(s)+1));
        System.exit(0);
      }
    }
    long end = System.currentTimeMillis();
    long elapsedTime = end - start;
    //System.out.println("The program took " + elapsedTime + " milliseconds");
  }


  public static HashMap<String,FunctionStatement> functionNamespace = new HashMap<String,FunctionStatement>();
  public static int lineNum;


  /**
   * A function that parses an arrayList of rawCodeStatements and turns it
   * into CodeElement objects.
   *
   * @param rawCode the list of all code statements
   * @param start the first index to parse into code
   * @param end one more than the last index to parse into code
   *
   * @return An arraylist of statement objects, one for each main statement
   * at the current level
   */
  public static ArrayList<Statement> parse(ArrayList<String> rawCode, int start, int end) {
    ArrayList<Statement> statements = new ArrayList<Statement>();
    int lineNo = start;
    // System.out.println("start is " + start);
    // System.out.println("end is " + end);
    while(lineNo < end) {
      String s = rawCode.get(lineNo);
      if(s.contains("#")){
        
      } else if(s.contains("print")){
        statements.add(new PrintStatement(s.trim()));
      } else if(s.contains("=") && !s.contains("if") && !(s.contains("while")) && !(s.contains("def"))){
        statements.add(new AssignmentStatement(s.trim()));
      } else if(s.contains("if")){
        int endline = lineNo;
        String tmp = rawCode.get(lineNo+1);
        int endint = calculateIndentLevel(s);

        while(calculateIndentLevel(tmp)>endint){
          endline++;     
          try {
            tmp = rawCode.get(endline);
          } catch (Exception e) {
            break;
          }
        }
        statements.add(new ifStatements(s,parse(rawCode,lineNo+1,endline)));
        lineNo = endline-1;
      } else if(s.contains("while")){
        int endline = lineNo;
        String tmp = rawCode.get(lineNo+1);
        int endint = calculateIndentLevel(s);

        while(calculateIndentLevel(tmp)>endint){
          endline++;     
          try {
            tmp = rawCode.get(endline);
          } catch (Exception e) {
            break;
          }
        }
        statements.add(new WhileLoop(s,parse(rawCode,lineNo+1,endline)));
        lineNo = endline-1;
      } else if(s.contains("for")){
        int endline = lineNo;
        String tmp = rawCode.get(lineNo+1);
        int endint = calculateIndentLevel(s);

        while(calculateIndentLevel(tmp)>endint){
          endline++;     
          try {
            tmp = rawCode.get(endline);
          } catch (Exception e) {
            break;
          }
        }
        statements.add(new ForLoop(s,parse(rawCode,lineNo+1,endline)));
        lineNo = endline-1;
      } else if(s.contains("def")){
        int endline = lineNo;
        String tmp = rawCode.get(lineNo+1);
        int endint = calculateIndentLevel(s);

        while(calculateIndentLevel(tmp)>endint){
          endline++;     
          try {
            tmp = rawCode.get(endline);
          } catch (Exception e) {
            break;
          }
        }
        functionNamespace.put(s.substring(s.indexOf("f")+1, s.indexOf("(")).trim(), new FunctionStatement(s,parse(rawCode,lineNo+1,endline)));
        lineNo = endline-1;
      } else if(s.contains("return")){
        statements.add(new ReturnFun(s));
      } else if(s.isBlank()){
        //System.out.println("s is_"+s+(lineNo+1));
      } else{
        System.out.println("Syntax error at line " + (lineNo+1));
        System.exit(0);
      }
      lineNum++;
      lineNo++;
    }
    return statements;
  }

  /**
   * A possibly useful method when dealing with if/while/function statements.
   * Will be useful for the second part of the project
   */
  protected static int calculateIndentLevel(String line) {
    if(line.isEmpty()){
      return 0;
    }
    int x = 0;
    while(true) {
      char c = line.charAt(x);
      if((c == ' ' || c == '\t')) {
        x++;
      } else {
        return x;
      }
    }
  }

}

