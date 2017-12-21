package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultStatement extends Base{
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public DefaultStatement() throws IOException {
        GrammarParser.sequenceGenerator(this, "DefaultStatement");
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