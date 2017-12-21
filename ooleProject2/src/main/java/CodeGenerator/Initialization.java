package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Initialization extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public Initialization(String type) throws IOException {
        this.type = type;
        GrammarParser.sequenceGenerator(this, "Initialization");
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