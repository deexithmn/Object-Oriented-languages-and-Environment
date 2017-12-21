package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatementWithoutTrailingSubstatement extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();

    public StatementWithoutTrailingSubstatement() throws IOException {
        GrammarParser.sequenceGenerator(this, "StatementWithoutTrailingSubstatement");
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