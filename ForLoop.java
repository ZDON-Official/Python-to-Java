import java.util.ArrayList;
import java.util.HashMap;

public class ForLoop extends Statement{
    private Expression start;
    private Expression end;
    private Expression step;
    private ArrayList<Statement> body;
    private String var;
    

    public ForLoop(String s, ArrayList<Statement> body){
        this.body = body;

        // System.out.println("s is " + s);
        // System.out.println(s.substring(3, (s.indexOf("(")-8)).trim());

        //this.var = new Variable(s.substring(3, (s.indexOf("(")-8)).trim());
        this.var = s.substring(3, (s.indexOf("(")-8)).trim();
        s = s.substring(s.indexOf("(")+1,s.length()-2).trim();
        String[] par = s.split(",");
        
        // for(String r : par){
        //     System.out.println("par " + r);
        // }

        try {
            this.start = new Const(par[0]);
        } catch (Exception e) {
            this.start = new Variable(par[0]);
        }
        try {
            this.end = new Const(par[1]);
        } catch (Exception e) {
            this.end = new Variable(par[1]);
        }
        try {
            this.step = new Const(par[2]);
        } catch (Exception e) {
            this.step = new Variable(par[2]);
        }
    }


	@Override
	public void execute(HashMap<String, Expression> namespace) {
        //System.out.println("in execute");
        long st = this.start.eval(namespace);
        long e = this.end.eval(namespace);
        long sp = this.step.eval(namespace);
       
        for(long i = st; i < e;i += sp){
            //System.out.println("in here");
            namespace.put(var, new Const(i));
            for(Statement s : this.body){
                s.execute(namespace);
            }
        }
	}
}
