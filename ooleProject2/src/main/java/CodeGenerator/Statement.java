package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Statement extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public Statement() throws IOException {
        GrammarParser.sequenceGenerator(this, "Statement");
    }

    public void addAttributes(Base attribute) {
        this.Attributes.add(attribute);
    }

    public void ValuePrint() {
        code=code+"\n";
        for(Base Attribute: Attributes) {
            Attribute.ValuePrint();
        }
    }
}