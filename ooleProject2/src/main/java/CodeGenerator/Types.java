package CodeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Types extends Base{
    String type="null";
    static Random rnd = new Random();
    static List<String> typeValues = new ArrayList<String>();
    static List<String> defaulttypeValues = new ArrayList<String>(){{
        add("char");
        add("int");
        //add("float");
        add("double");
        add("boolean");
    }};

    public static void addTypes(Identifier name){
        typeValues.add(name.getIdentifier());
    }

    public Types() {
        int typedecider = rnd.nextInt(1);
        int typedecider2;
        if(typeValues.size() == 0) {
            typedecider2 = rnd.nextInt(defaulttypeValues.size());
            type = defaulttypeValues.get(typedecider2);
        } else {
            if(typedecider == 0){
                typedecider2 = rnd.nextInt(defaulttypeValues.size());
                type = defaulttypeValues.get(typedecider2);
            } else {
                typedecider2 = rnd.nextInt(typeValues.size());
                type = typeValues.get(typedecider2);
            }
        }
    }

    public void ValuePrint() {
        code=code+" "+type;
    }
}
