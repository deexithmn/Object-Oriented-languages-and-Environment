package CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrePostOperations extends Base {
    String type="null";
    List<Base> Attributes = new ArrayList<Base>();
    public PrePostOperations() throws IOException {
    }

    public void addAttributes(Base attribute) {
        this.Attributes.add(attribute);
    }

    public void ValuePrint() {
       code=code+"++";
    }
}