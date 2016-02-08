import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guyver on 25.01.16.
 */
public class Container<Type> {

    private List<Type> data;

    public Container() {
        data = new ArrayList<>();
    }

    public void add(Type obj) {
        data.add(obj);
    }

    public void delete(Type obj) {
        data.remove(obj);
    }

    public Type getElement(int index) {
        Type temp = null;
        if(index > -1 && index < data.size()) {
            temp = data.get(index);
        }
        return temp;
    }

}
