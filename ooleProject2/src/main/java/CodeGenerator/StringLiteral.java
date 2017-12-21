package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringLiteral extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public StringLiteral() throws IOException {
        GrammarParser.sequenceGenerator(this, "StringLiteral");
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