package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assignment extends Base {

    String type="null";
    public void setType(String type) {
        this.type = type;
    }
    List<Base> Attributes = new ArrayList<Base>();

    public Assignment() throws IOException {
        GrammarParser.sequenceGenerator(this, "Assignment");
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
