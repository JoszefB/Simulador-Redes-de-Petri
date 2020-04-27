//Trabalho para o Grau A – 2019/1 - Simulador de Redes de Petri - Joszef Barrionuevo

package PetriArc;

import PetriEntity.Entity;

public class Arc {

    private int weidht;//peso da entidade
    private Entity inputEntity;
    private Entity outputEntity;

    public Arc(){

    }

    public Arc(int wt){
        weidht=wt;
    }

    public int getWeidht() {
        return weidht;
    }

    public void setWeidht(int weidht) {
        this.weidht = weidht;
    }

    public Entity getInputEntity() {
        return inputEntity;
    }

    public void setInputEntity(Entity inputEntity) {
        this.inputEntity = inputEntity;
    }

    public Entity getOutputEntity() {
        return outputEntity;
    }

    public void setOutputEntity(Entity outputEntity) {
        this.outputEntity = outputEntity;
    }
}
