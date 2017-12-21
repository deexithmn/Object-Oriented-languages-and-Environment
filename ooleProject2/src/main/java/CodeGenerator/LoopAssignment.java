package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoopAssignment extends Base {
    String type="null";
    public void setType(String type) {
        this.type = type;
    }
    List<Base> Attributes = new ArrayList<Base>();

    public LoopAssignment() throws IOException {
        GrammarParser.sequenceGenerator(this, "LoopAssignment");
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