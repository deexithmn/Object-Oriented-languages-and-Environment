package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WhileStatement extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();
    public WhileStatement() throws IOException {
        GrammarParser.sequenceGenerator(this, "WhileStatement");
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