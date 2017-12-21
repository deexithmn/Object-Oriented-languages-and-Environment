package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RelationalOperation extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public RelationalOperation(String type) throws IOException {
        this.type = type;
        GrammarParser.sequenceGenerator(this, "RelationalOperation");
    }

    public RelationalOperation() throws IOException {
        GrammarParser.sequenceGenerator(this, "RelationalOperation");
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