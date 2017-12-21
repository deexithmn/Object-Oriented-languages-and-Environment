package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharacterLiteral extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();
    public CharacterLiteral() throws IOException {
        GrammarParser.sequenceGenerator(this, "CharacterLiteral");
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