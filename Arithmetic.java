import java.util.HashMap;

public class Arithmetic extends Expression {

  //private long data;
  private Expression dataOne;
  private Expression dataTwo;
  private int index;
  private String symbol;

  public Arithmetic(String s) {
    //System.out.println("in arithmetic");

    //System.out.println("arith " + s);

    if(s.contains("+")){
      index = s.indexOf("+");
      symbol = "+";
    } else if(s.contains("-")){
      index = s.indexOf("-");
      symbol = "-";
    } else if(s.contains("*")){
      index = s.indexOf("*");
      symbol = "*";
    } else if(s.contains("/")){
      index = s.indexOf("/");
      symbol = "/";
    } else if(s.contains("%")){
      index = s.indexOf("%");
      symbol = "%";
    }

    try {
      this.dataOne = new Const(s.substring(0, index).trim());
    } catch (Exception e) {
      this.dataOne = new Variable(s.substring(0, index).trim());
    }
    try {
      this.dataTwo = new Const(s.substring(index+1).trim());
    } catch (Exception e) {
      this.dataTwo = new Variable(s.substring(index+1).trim());
    }
    //this.data = dataOne + dataTwo;
  }

  @Override
  public long eval(HashMap<String,Expression> namespace) {
    // System.out.println("execute arithmetic");
    // System.out.println("d1 " + dataOne.eval(namespace));
    // System.out.println("d2 " + dataTwo.eval(namespace));

    if(symbol.equals("+")){
      return dataOne.eval(namespace) + dataTwo.eval(namespace);
    } else if(symbol.equals("-")){
      return dataOne.eval(namespace) - dataTwo.eval(namespace);
    } else if(symbol.equals("*")){
      return dataOne.eval(namespace) * dataTwo.eval(namespace);
    } else if(symbol.equals("/")){
      return dataOne.eval(namespace) / dataTwo.eval(namespace);
    } else if(symbol.equals("%")){
      return dataOne.eval(namespace) % dataTwo.eval(namespace);
    }
    return 0;
  }
}