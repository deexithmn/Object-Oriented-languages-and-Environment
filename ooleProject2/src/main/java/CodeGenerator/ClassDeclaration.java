package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassDeclaration extends Base{
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();
    static String currentClass;
    public void addAttributes(Base attribute) {
        this.Attributes.add(attribute);
    }
    public ClassDeclaration() throws IOException {
        GrammarParser.sequenceGenerator(this, "ClassDeclaration");
        for(Base base : Attributes) {
            if(base instanceof Identifier) {
                TypeList.addTypes(((Identifier) base));
            }
        }
        symbolTable.addClasses(this);
    }
    public void ValuePrint() {
      for(Base Attribute: Attributes) {
            Attribute.ValuePrint();
        }
    }
}