import java.util.HashMap;

public class Variable extends Expression{

    private String data;
    public Variable(String s){
        //System.out.println("s is " + s);
        this.data = s;
    }
    
    public long eval(HashMap<String, Expression> namespace) {
        // System.out.println("------------------------------");
        // System.out.println("data is " + this.data);
        // System.out.println(namespace.keySet());
        // System.out.println(namespace.values());
        // System.out.println("------------------------------");
        // System.out.println("data " + this.data);
        // System.out.println("----------------------");
        // System.out.println("val " + namespace.get(this.data).eval(namespace));
        return namespace.get(this.data).eval(namespace);
        // try {
        // } catch (Exception e) {
        //     System.out.println("error: " + this.data + " not found");
        //     //System.out.println("line " + );
        //     //System.out.println("error at line " + e.getStackTrace()[0].getLineNumber());
        //     System.exit(0);
        // }

        
        //return 0;
    }    
}
