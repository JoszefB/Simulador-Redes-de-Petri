//Trabalho para o Grau A – 2019/1 - Simulador de Redes de Petri - Joszef Barrionuevo

package PetriTransition;

import PetriEntity.Entity;

public class Transition extends Entity {

    private boolean able;
    private int currentCycle;

    public void begCurrentCycle(){
        currentCycle++;
    }

    public boolean isAble() {
        return able;
    }

    public void setAble(boolean able) {
        this.able = able;
    }

    public int getCurrentCycle() {
        return currentCycle;
    }

    public void setCurrentCycle(int currentCycle) {
        this.currentCycle = currentCycle;
    }
}
