//Trabalho para o Grau A – 2019/1 - Simulador de Redes de Petri - Joszef Barrionuevo

package PetriEntity;

import PetriArc.Arc;
import java.util.ArrayList;
import java.util.List;

public class Entity {

    private String label;
    private List<Arc> input;
    private List<Arc> output;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Arc> getInput() {
        return input;
    }

    public void addInput(Arc input){
        if(input==null){
            this.input=new ArrayList<Arc>();
        }
        this.input.add(input);
    }

    public void setInput(List<Arc> input) {
        this.input=input;
    }

    public List<Arc> getOutput() {
        return output;
    }

    public void addOutput(Arc output){
        if(output==null){
            this.output=new ArrayList<Arc>();
        }
        this.output.add(output);
    }

    public void setOutput(List<Arc> output) {
        this.output=output;
    }
}
