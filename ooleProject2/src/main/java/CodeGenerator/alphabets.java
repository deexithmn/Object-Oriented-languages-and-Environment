package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Class for possible values for alphabets
public class alphabets extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public alphabets() throws IOException {
         GrammarParser.sequenceGenerator(this, "alphabets");
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
