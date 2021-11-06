import java.util.HashMap;

/**
 * A class to contain the information needed for a print statement.
 *
 */
public class PrintStatement extends Statement{

  private Expression data;
  private String statement;

  //add a way to print strings
  public PrintStatement(String s) {
    if(s.contains("\"")){
      this.statement = s.substring(7, s.length()-2);
    } else{
      // this.statement = s.substring(6, s.length()-1);
      try {
        this.data = new Const(s.substring(6,s.length() - 1));
      } catch (Exception e) {
        this.data = new Variable(s.substring(6, s.length()-1));
      }
    }
  }

  @Override
  //code should be here
  public void execute(HashMap<String,Expression> namespace) {
    //System.out.println("statement is " + statement);
    if(statement == null){
      System.out.println(data.eval(namespace));
    } else{
      System.out.println(statement);
    }
  }
}
