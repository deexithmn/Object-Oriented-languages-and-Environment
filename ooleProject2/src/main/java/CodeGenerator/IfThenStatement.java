package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IfThenStatement extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public IfThenStatement() throws IOException {
        GrammarParser.sequenceGenerator(this, "IfThenStatement");
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