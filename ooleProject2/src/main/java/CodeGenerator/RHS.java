package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RHS extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public RHS(String type) throws IOException {
        this.type = type;
        GrammarParser.sequenceGenerator(this, "RHS");
    }

    public RHS() throws IOException {
        GrammarParser.sequenceGenerator(this, "RHS");
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