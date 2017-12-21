package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParameterVariable extends Base{
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();
    public ParameterVariable() throws IOException {
        GrammarParser.sequenceGenerator(this, "ParameterVariable");
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