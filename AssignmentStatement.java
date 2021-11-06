import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

//import sun.tools.tree.LessExpression;

/**
 * A class to contain the information needed for a print statement.
 *
 */
public class AssignmentStatement extends Statement{
  public Expression empt;
  public String var;
  public Expression value;

  public AssignmentStatement(String s) {
    //System.out.println("in here " + s);
    
    this.var = s.substring(0, s.indexOf("=")).trim();
    //System.out.println("var is " + var);
    if(s.contains("+")||s.contains("-")||s.contains("/")||s.contains("*")||s.contains("%")){
      this.value = new Arithmetic(s.substring(s.indexOf("=")+1).trim());
    } else if(s.contains("(") && s.contains(")")){
      this.value = new FuncExpression(s.substring(s.indexOf("=")+1).trim());
    }else {
      try {
        this.value = new Const(s.substring(s.indexOf("=")+1).trim());
      } catch (Exception e) {
        //System.out.println("in here for var " + var);
        this.value = new Variable(s.substring(s.indexOf("=")+1).trim());
      }
    } 
  }

  @Override
  public void execute(HashMap<String,Expression> namespace) {
    // System.out.println("in assign");
    // System.out.println("var is " + this.var);
    // System.out.println("val " + value.eval(namespace));

    // System.out.println("name before " + namespace);
    
    namespace.put(this.var, new Const(value.eval(namespace)));
  }
}
