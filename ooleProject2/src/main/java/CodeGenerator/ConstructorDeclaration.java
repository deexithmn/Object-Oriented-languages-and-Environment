package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConstructorDeclaration extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public ConstructorDeclaration() throws IOException {
        GrammarParser.sequenceGenerator(this, "ConstructorDeclaration");

    }
    public void addAttributes(Base attribute) {
        this.Attributes.add(attribute);
    }

    public void ValuePrint() {
        for(Base Attribute: Attributes) {
            Attribute.ValuePrint();
        }
    }
}