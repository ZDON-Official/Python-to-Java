import java.util.ArrayList;
import java.util.HashMap;

public class ifStatements extends Statement {
    private Expression left;
    private Expression right;
    private ArrayList<Statement> body;
    private int index;
    private String symbol;
    
    public ifStatements(String s, ArrayList<Statement> body){
        //System.out.println(s);
        s = s.trim();

        this.body = new ArrayList<Statement>();
        this.body = body;


        if(s.contains("==")){
            index = s.indexOf("=");
            symbol = "==";
        } else if(s.contains(">=")){
            index = s.indexOf(">");
            symbol = ">=";
        } else if(s.contains(">")){
            index = s.indexOf(">");
            symbol = ">";
        } else if(s.contains("<=")){
            index = s.indexOf("<");
            symbol = "<=";
        } else if(s.contains("<")){
            index = s.indexOf("<");
            symbol = "<";
        }  else if(s.contains("!=")){
            index = s.indexOf("!");
            symbol = "!=";
        }

        // System.out.println("left_" + s.substring(s.indexOf("(")+1,index).trim());
        // System.out.println("right_" + s.substring(index+2, s.length()-2).trim());


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
        // System.out.println("in execute");        
        // System.out.println("right " + right.eval(namespace));
        // System.out.println("left " + left.eval(namespace));
        // for(Statement s : this.body){
        //     System.out.println("--------------------------------");
        //     s.execute(namespace);
        //     System.out.println("--------------------------------");
        // }

        //System.out.println("symbol " + symbol);

        if(symbol.equals("==")){
            if(left.eval(namespace) == right.eval(namespace)){
                run(namespace);
            }
        } else if(symbol.equals(">")){
            if(left.eval(namespace) > right.eval(namespace)){
                run(namespace);
            }
        } else if(symbol.equals(">=")){
            if(left.eval(namespace) >= right.eval(namespace)){
                run(namespace);
            }
        } else if(symbol.equals("<")){
            if(left.eval(namespace) < right.eval(namespace)){
                run(namespace);
            }
        } else if(symbol.equals("<=")){
            if(left.eval(namespace) <= right.eval(namespace)){
                run(namespace);
            }
        } else if(symbol.equals("!=")){
            if(left.eval(namespace) != right.eval(namespace)){
                run(namespace);
            }	
        }		
    }
    
    private void run(HashMap<String, Expression> namespace){
        for(Statement s : this.body){
            s.execute(namespace);
        }
    }
}
