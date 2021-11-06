import java.util.Hashtable;

public interface GenCons<T> {
    public T eval(Hashtable<String,Expression> namespace);
}
