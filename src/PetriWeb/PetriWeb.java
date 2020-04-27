//Trabalho para o Grau A – 2019/1 - Simulador de Redes de Petri - Joszef Barrionuevo

package PetriWeb;

import java.util.List;
import PetriArc.Arc;
import PetriEntity.Entity;
import PetriPlace.Place;
import PetriTransition.Transition;
import java.util.Collections;

public class PetriWeb {
    private List<Arc> a;
    private List<Place> p;
    private List<Transition> t;
    private int timer;
    private int cycle;

    public PetriWeb(List<Arc> a, List<Place> p, List<Transition> t, int timer, int cycle) {
        this.a=a;
        this.p=p;
        this.t=t;
        this.timer=timer;
        this.cycle=cycle;
    }

    public void createNet(){
        for(int i=0; i<a.size();i++) {
            this.createArc(a.get(i).getInputEntity(), a.get(i).getOutputEntity(), a.get(i).getWeidht());
        }
        this.enableDisablesTransitions();
    }

    public void createArc(Entity input, Entity output, int wt){
        boolean foundIN = false;
        boolean foundOUT = false;
        Arc arc = new Arc(wt);
        a.add(arc);
        for (Place pl : p) {
            if (input.equals(pl.getLabel())) {
                arc.setInputEntity(pl);
                pl.addInput(arc);
                foundIN = true;
                break;
            }
            if (output.equals(pl.getLabel())) {
                arc.setOutputEntity(pl);
                pl.addInput(arc);
                foundOUT = true;
                break;
            }
        }
        for (Transition tr : t) {
            if (input.equals(tr.getLabel()) && !foundIN) {
                arc.setInputEntity(tr);
                tr.addOutput(arc);
                break;
            }
            if (output.equals(tr.getLabel()) && !foundOUT) {
                arc.setOutputEntity(tr);
                tr.addInput(arc);
                break;
            }
        }
    }

    private void enableDisablesTransitions() {
        for (Transition tran : t) {
            for (Arc in : tran.getInput()) {
                if (in.getWeidht() > ((Place) in.getInputEntity()).getBrand()) {
                    tran.setAble(false);
                    break;
                }
                tran.setAble(true);
            }
        }
    }

    private void enableDisablesTransitions(Transition tra) {
        for (Arc in : tra.getInput()) {
            Place plIn = (Place) in.getInputEntity();
            if (in.getWeidht() > plIn.getBrand() || plIn.isDone()) {
                tra.setAble(false);
                break;
            }
        }
    }

    public void step(){
        if(this.isFinished()){
            return;
        }
        cycle++;
        Collections.shuffle(t);
        for (Transition tr : t) {
            enableDisablesTransitions(tr);
            if (tr.isAble()) {
                tr.getInput().forEach((input) -> {
                    Place placeInput = (Place) input.getInputEntity();
                    placeInput.decBrand(input.getWeidht());
                    timer += placeInput.getTime();
                    placeInput.setDone(true);
                });
                tr.getOutput().forEach((out) -> {
                    ((Place) out.getOutputEntity()).incBrand(out.getWeidht());
                });
                System.out.println("Disparou a transição: " + tr.getLabel());
            }
        }
        this.enableDisablesTransitions();
        p.forEach((pl) -> {
            pl.setDone(false);
        });
    }

    public boolean isFinished() {
        return t.stream().noneMatch((t) -> (t.isAble()));
    }
}