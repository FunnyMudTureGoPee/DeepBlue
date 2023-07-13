package src.GameObject;

public abstract class Module {
    protected String name;
    protected String type;
    protected String introduce;

    public String toString(){
        return "模块名称:"+name+"\n"+"模组类型:"+type+"\n"+"模块介绍:\n"+introduce;
    }
}
