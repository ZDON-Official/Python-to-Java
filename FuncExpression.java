import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class FuncExpression extends Expression {

    private String name;
    private ArrayList<Expression> val;
    //private String[] par;
    public FuncExpression(String s){
        //System.out.println(s);
        this.val = new ArrayList<Expression>();
        this.name = s.substring(0, s.indexOf("(")).trim();

        String tmp = s.substring(s.indexOf("(")+1,s.length()-1).trim();
        //System.out.println("tmp "+tmp);

        //par = tmp.split(",");
        for(String p : tmp.split(",")){
            //System.out.println("p is " + p);
            try {
                this.val.add(new Const(p.trim()));
            } catch (Exception e) {
                this.val.add(new Variable(p.trim()));
            }
        }


        // int i=0;
        // while(i<tmp.length()){
        //     if(tmp.contains(",")){
        //         try {
        //             val.add(new Const(tmp.substring(0, tmp.indexOf(",")).trim()));
        //         } catch (Exception e) {
        //             val.add(new Variable(tmp.substring(0, tmp.indexOf(",")).trim()));
        //         }
        //         tmp = tmp.substring(tmp.indexOf(",")+1);
        //     } else{
        //         try {
        //             val.add(new Const(tmp.substring(0).trim()));
        //         } catch (Exception e) {
        //             val.add(new Variable(tmp.substring(0).trim()));
        //         }
        //         tmp = "";
        //     }
            
            //System.out.println("tmp after " + tmp);
        //}
    }

    @Override
    public long eval(HashMap<String, Expression> namespace) {
        HashMap<String,Expression> namespace2 = new HashMap<String,Expression>();
        //.out.println("name is " + name);
        //FunctionStatement function = Boa.functionNamespace.get(name);
        //Boa.functionNamespace.get(name).add(par);
        String[] var = Boa.functionNamespace.get(name).getPar();
        
        // System.out.println("--------------------------------");
        // for(String v : var){
        //     System.out.println(v);
        // }
        // System.out.println("--------------------------------");

        try {
            int i=0;
            for(String f : var){
                //System.out.println("var " + f);
                namespace2.put(f, new Const(val.get(i).eval(namespace)));
                i++;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        // System.out.println(namespace2.keySet());
        // System.out.println(namespace2.values());
        // System.out.println("");
        // for(String q : var){
        //     System.out.print(namespace2.get(q).eval(namespace2) + " ");
        // }
        
        
        Boa.functionNamespace.get(name).execute(namespace2);
        
        // System.out.println("----------------------------------------------------------------------------");
        // System.out.println(namespace2.keySet());
        // System.out.println("----------------------------------------------------------------------------");
        
        return namespace2.get("return").eval(namespace2);
    }
    
}
