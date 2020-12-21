package compiler;

import java.util.ArrayList;

public class HeheMemory {
    ArrayList<Variable> memory = new ArrayList<>();
    ArrayList<Long> collection = new  ArrayList<>();

    long index = 1;

    public void declare(Variable var){
        var.location = index;
        if(var.isArray()) index+= var.getRange(); else index++;
        memory.add(var);
    }

    public void remove(Variable var){
        this.memory.remove(var);
        index--;
    }

    public Variable getTemp(){
        Variable var = new Variable(Type.VARIABLE,"for");
        var.location = index;
        index++;
        memory.add(var);
        return var;
    }

}
