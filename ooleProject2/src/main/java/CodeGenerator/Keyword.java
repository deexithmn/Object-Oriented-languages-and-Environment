package CodeGenerator;

public class Keyword extends Base {
    String keyword;
    String type="null";

    public Keyword(String value,Base baseValue) {
        this.keyword="";
        if (baseValue instanceof ArrayDeclaration) {
            if(value.equals("es["))
                this.keyword = "[";
            else if(value.equals("es]"))
                this.keyword = "]";
            else
                this.keyword=" "+value;
        } else if(baseValue instanceof alphabets){
            this.keyword=keyword+value;
        } else if(baseValue instanceof Integer || baseValue instanceof Dot|| baseValue instanceof SingleQuotation || baseValue instanceof DoubleQuotation){
            this.keyword=keyword+value;
        } else {
            this.keyword=" "+value;
        }
    }
    public void ValuePrint() {
        code=code+keyword;
    }
}