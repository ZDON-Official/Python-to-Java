import java.util.ArrayList;
import java.util.HashMap;

public class WhileLoop extends Statement {
    private Expression left;
    private Expression right;
    private ArrayList<Statement> body;
    private int index;
    private String symbol;

    public WhileLoop(String s, ArrayList<Statement> body){
        //this.body = new ArrayList<Statement>();
        this.body = body;


        if(s.contains("==")){
            index = s.indexOf("=");
            symbol = "==";
        } else if(s.contains(">")){
            index = s.indexOf(">");
            symbol = ">";
        } else if(s.contains(">=")){
            index = s.indexOf(">");
            symbol = ">=";
        } else if(s.contains("<")){
            index = s.indexOf("<");
            symbol = "<";
        } else if(s.contains("<=")){
            index = s.indexOf("<");
            symbol = "<=";
        }  else if(s.contains("!=")){
            index = s.indexOf("!");
            symbol = "!=";
        }
        try {
            this.left = new Const(s.substring(s.indexOf("(")+1,index).trim());
        } catch (Exception e) {
            this.left = new Variable(s.substring(s.indexOf("(")+1,index).trim());
        }
        try {
            this.right = new Const(s.substring(index+2, s.length()-2).trim());
        } catch (Exception e) {
            this.right = new Variable(s.substring(index+2, s.length()-2).trim());
        }
    }

    @Override
    public void execute(HashMap<String, Expression> namespace) {
        if(symbol.equals("==")){
            while(this.left.eval(namespace) == this.right.eval(namespace)){
                run(namespace);
            }
        } else if(symbol.equals(">")){
            while(this.left.eval(namespace) > this.right.eval(namespace)){
                run(namespace);
            }
        } else if(symbol.equals(">=")){
            while(this.left.eval(namespace) >= this.right.eval(namespace)){
                run(namespace);
            }
        } else if(symbol.equals("<")){
            while(this.left.eval(namespace) < this.right.eval(namespace)){
                run(namespace);
            }
        } else if(symbol.equals("<=")){
            while(this.left.eval(namespace) <= this.right.eval(namespace)){
                run(namespace);
            }
        } else if(symbol.equals("!=")){
            while(this.left.eval(namespace) != this.right.eval(namespace)){
                run(namespace);
            }	
        }
    }

    /**
     * executes the code
     * @param namespace Hashmap that stores assingments
     */
    private void run(HashMap<String, Expression> namespace){
        for(Statement s : this.body){
            s.execute(namespace);
        }
    }
    
}
