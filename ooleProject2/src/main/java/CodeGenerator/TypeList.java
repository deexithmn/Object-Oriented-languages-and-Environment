package CodeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TypeList extends Base {
    String type="null";

    static Random rnd = new Random();
    static List<String> typeValues = new ArrayList<String>();

    public static void addTypes(Identifier name){
        typeValues.add(name.getIdentifier());
    }

    public TypeList() {
        if (typeValues.size() == 0) {
        } else {
            int typedecider2 = rnd.nextInt(typeValues.size());
            type = typeValues.get(typedecider2);
        }
    }

    public void ValuePrint() {
            code=code+" "+type;
    }
}