//Trabalho para o Grau A – 2019/1 - Simulador de Redes de Petri - Joszef Barrionuevo

package PetriPlace;

import PetriEntity.Entity;

public class Place extends Entity {
    private int brand;
    private int time;
    private boolean done;

    public Place(){
        done=false;
    }


    public void incBrand(int val){
        brand+=val;
    }

    public void decBrand(int val) {
        if (brand >= val) {
            brand -= val;
        }
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
