package CodeGenerator;

public class Modifier extends Base{
    String type="null";
    String access = "public";
    public String getAccess() {
        return access;
    }

    public void setAccess(int accessSpecifier) {
        if (accessSpecifier == 1)
            this.access = "public";
        else if(accessSpecifier == 2)
            this.access = "protected";
        else
            this.access = "private";
    }

    Modifier(){
    }
    public void ValuePrint() {
         code=code+"\n"+access;
    }
}
