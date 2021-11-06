import java.util.HashMap;

public class ReturnFun extends Statement{

    private Expression data;
    private String var;
    public ReturnFun(String s){
        //System.out.println(s);
        
        this.var = s.substring(s.indexOf("r"),s.indexOf("n")+1).trim();
        
        // System.out.println("var is_" + var);
        // System.out.println("data is_" + s.substring(8).trim());

        try {
            this.data = new Const(s.substring(s.indexOf("n")+1).trim());
        } catch (Exception e) {
            this.data = new Variable(s.substring(s.indexOf("n")+1).trim());
        }
    }

	@Override
	public void execute(HashMap<String, Expression> namespace) {

        // System.out.println("=================================");
        // System.out.println(namespace.keySet());
        // System.out.println(namespace.values());
        // System.out.println(this.data.eval(namespace));
        // System.out.println("=================================");

		namespace.put(this.var, new Const(this.data.eval(namespace)));
		
	}
    
}
