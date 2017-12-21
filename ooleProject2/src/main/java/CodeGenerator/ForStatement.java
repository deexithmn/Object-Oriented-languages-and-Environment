package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ForStatement extends Base {
    String type="int";
    int expressionLayer = 0;
    List<Base> Attributes = new ArrayList<Base>();

    public ForStatement() throws IOException {
        GrammarParser.sequenceGenerator(this, "ForStatement");
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
