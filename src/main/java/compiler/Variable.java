package compiler;

public class Variable {
    String name;

    Boolean isArray;
    Boolean isIterator;
    Boolean isInitialized;

    long rangeStart;
    long rangeEnd;

    public long location;

    public Variable(Type type, String name){
        this.name = name;
        isArray = type == Type.ARRAY;
        location = -1;
        this.isIterator = false;
        this.isInitialized = false;
    }

    public void setArray(long rangeStart, long rangeEnd){
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    public long getRange() {
        return rangeEnd-rangeStart+1;
    }
}

enum Type {
    VARIABLE,
    ARRAY
}
