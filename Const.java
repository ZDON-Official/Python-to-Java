import java.util.HashMap;

public class Const extends Expression {

  private long data;

  public Const(String s) {
    //System.out.println("in here " + s);
    this.data = Long.parseLong(s);
  }

  public Const(long data) {
    this.data = data;
  }

  public long getVal(){
    return this.data;
  }

  @Override
  public long eval(HashMap<String,Expression> namespace) {
    //System.out.println("name " + namespace);
    // /System.out.println("data " + data);
    return data;
  }
}
