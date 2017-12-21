package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parameter1 extends Base{
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();
    public Parameter1() throws IOException {
        GrammarParser.sequenceGenerator(this, "Parameter1");
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
