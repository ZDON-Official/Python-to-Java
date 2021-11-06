import java.util.ArrayList;
import java.util.HashMap;

/**
 * This abstract class contains the ADT for a Statement - a line of code
 */
public abstract class Statement {

  public abstract void execute(HashMap<String,Expression> namespace);

  //public abstract long get();

}
