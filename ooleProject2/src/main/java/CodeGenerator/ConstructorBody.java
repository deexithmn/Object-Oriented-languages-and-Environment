package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConstructorBody extends Base{
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public ConstructorBody() throws IOException {
        GrammarParser.sequenceGenerator(this, "ConstructorBody");
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