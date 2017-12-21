package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParameterList extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public ParameterList() throws IOException {
        GrammarParser.sequenceGenerator(this, "ParameterList");
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
