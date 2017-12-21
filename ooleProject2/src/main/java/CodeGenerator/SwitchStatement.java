package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SwitchStatement extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public SwitchStatement() throws IOException {
        GrammarParser.sequenceGenerator(this, "SwitchStatement");
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