import java.util.ArrayList;
import java.util.HashMap;

public class FunctionStatement extends Statement {
    private ArrayList<Statement> body;
    private String[] par;
    private String name;
    private String ret;
    private Expression ans;

    

    public FunctionStatement(String s, ArrayList<Statement> body){
        //System.out.println("s " + s);
        this.body = body;
        name = s.substring(s.indexOf("f")+1,s.indexOf("(")).trim();
        s = s.substring(s.indexOf("(")+1,s.indexOf(")")).trim();
        //System.out.println("name "+name);
        
        // for(Statement p : body){
        //     p.execute(Boa.functionNamespace);
        // }

        par = s.split(",");
        // for(String i : par){
        //     System.out.println(i);
        // }
     
        //function
        

    }

    public void add(String[] p){
        for(int i=0;i<p.length;i++){
            par[i] = p[i];
        }
    }

    public String[] getPar(){
        return par;
    }

    public FunctionStatement(String s){
        this.ret = s.substring(0,7);
        try {
            this.ans = new Const(s.substring(7).trim());
        } catch (Exception e) {
            this.ans = new Variable(s.substring(7).trim());
        }
    }

    @Override
    public void execute(HashMap<String, Expression> namespace) {
        //namespace.put(this.name, ans);
        // HashMap<String,Expression> namespace2 = new HashMap<String,Expression>();
        
        // System.out.println(namespace.keySet());
        // System.out.println(namespace.values());
        // for(String p : par){
        //     System.out.println(namespace.get(p).eval(namespace));
        // }

        

        for(Statement s : body){
            s.execute(namespace);
        }

    }
    
}
