package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BreakStatement extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public BreakStatement() throws IOException {
        GrammarParser.sequenceGenerator(this, "BreakStatement");
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