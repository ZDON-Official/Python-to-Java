import java.util.HashMap;

/**
 * This abstract class contains the ADT for an expression - a piece of code
 * that evaluates to an integer.
 */
public abstract class Expression {
  //Expression<T>
  public abstract long eval(HashMap<String,Expression> namespace);
  //public abstract T eval(HashMap<String,Expression> namespace);
}
