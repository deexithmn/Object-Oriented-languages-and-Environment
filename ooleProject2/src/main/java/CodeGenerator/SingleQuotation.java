package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingleQuotation extends Base{
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public SingleQuotation() throws IOException {
        GrammarParser.sequenceGenerator(this, "SingleQuotation");
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