//Trabalho para o Grau A – 2019/1 - Simulador de Redes de Petri - Joszef Barrionuevo

package PetriMain;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import PetriArc.Arc;
import PetriPlace.Place;
import PetriTransition.Transition;
import PetriEntity.Entity;
import PetriWeb.PetriWeb;

public class Main {
    public static void main(String args[]){

        Scanner read=new Scanner(System.in);
        boolean loop=true;
        List<Arc> arc= new ArrayList<Arc>();
        List<Place> place=new ArrayList<Place>();
        List<Transition> transition=new ArrayList<Transition>();
        int positionPlace=0;
        int positionArc=0;
        int positionTransition=0;
        int time=0;
        int cycle=0;

        do {
            try {
                System.out.print("\nMenu:\n1-Inserir\n2-Iniciar silumação\n3-Sair\n->");
                int key=read.nextInt();
                switch (key){
                    case 1:
                        try{
                            boolean subLoop=true;
                            System.out.println("\nLugares");
                            do{
                                Place pTmp= new Place();
                                System.out.print("label: ");
                                pTmp.setLabel(read.next());
                                System.out.print("marcas: ");
                                pTmp.setBrand(read.nextInt());
                                System.out.print("Tempo (valor inteiro): ");
                                pTmp.setTime(read.nextInt());
                                System.out.print("Adicionar mais lugares;? (Y-sim / N-Não)");
                                String q=read.next();
                                if(q.equalsIgnoreCase("Y")){
                                    place.add(positionPlace, pTmp);
                                    positionPlace++;
                                }
                                else{
                                    place.add(positionPlace, pTmp);
                                    positionPlace++;
                                    subLoop=false;
                                }
                            }while (subLoop);
                            System.out.println("\nTransições");
                            do{
                                Transition tTmp= new Transition();
                                System.out.print("label: ");
                                tTmp.setLabel(read.next());
                                System.out.print("Adicionar mais transções? (Y-sim / N-Não)");
                                String q=read.next();
                                if(q.equalsIgnoreCase("Y")){
                                    transition.add(positionTransition, tTmp);
                                    positionTransition++;
                                }
                                else{
                                    transition.add(positionTransition, tTmp);
                                    positionTransition++;
                                    subLoop=false;
                                }
                            }while (subLoop);
                            System.out.println("\nArcos");
                            String in;
                            do{
                                boolean auxloop=true;
                                Arc aTmp= new Arc();
                                Entity eP=new Entity();
                                Entity eT=new Entity();
                                System.out.println("Para os arcos digitar o nome dos lugares para entrada e para saida as transições");
                                do {
                                    System.out.print("Entrada: ");
                                    in = read.next();
                                    for (int i = 0; i < place.size(); i++) {
                                        if (in.equalsIgnoreCase(place.get(i).getLabel())) {
                                            eP = place.get(i);
                                            auxloop = false;
                                        }
                                        else if(!in.equalsIgnoreCase(place.get(i).getLabel()) || eP.getLabel()==null) {
                                            System.out.println("Digite uma entrada valida");
                                        }
                                    }
                                }
                                while (auxloop);
                                auxloop=true;
                                aTmp.setInputEntity(eP);
                                do{
                                    System.out.print("Saida: ");
                                    in=read.next();
                                    for (int i = 0; i < transition.size(); i++) {
                                        if (in.equalsIgnoreCase(transition.get(i).getLabel())) {
                                            eT = transition.get(i);
                                            auxloop = false;
                                        } else if(!in.equalsIgnoreCase(transition.get(i).getLabel()) || eP.getLabel()==null){
                                            System.out.println("Digite uma saida valida");
                                        }
                                    }
                                }
                                while (auxloop);
                                aTmp.setOutputEntity(eT);
                                System.out.print("Peso (valor inteiro): ");
                                aTmp.setWeidht(read.nextInt());
                                System.out.print("Adicionar mais Arcos? (Y-sim / N-Não)");
                                String q=read.next();
                                if(q.equalsIgnoreCase("Y")){
                                    arc.add(positionArc, aTmp);
                                    positionArc++;
                                }
                                else{
                                    arc.add(positionArc, aTmp);
                                    positionArc++;
                                    subLoop=false;
                                }
                                System.out.print("Digite o  tempo de exucução da simulação:");
                                time=read.nextInt();
                                System.out.print("Digite o  ciclo de exucução da simulação:");
                                cycle=read.nextInt();
                            }while (subLoop);
                        }
                        catch (Exception e){
                            System.out.println("ERRO Favor digite um valor valido "+e);
                        }
                        break;
                    case 2:
                        PetriWeb petriWeb=new PetriWeb(arc, place, transition, time, cycle);
                        System.out.println("\n");
                        printNet(place, transition);
                        petriWeb.step();
                        System.out.println("\n");
                        printNet(place, transition);
                        break;
                    case 3:
                        loop=false;
                        break;
                    default:
                        System.out.println("Tente uma alternativa valida");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Ops! Ocorreu algum erro (T_T)");
            }
        }while (loop);
    }
    public static void printNet(List<Place> place, List<Transition> transition){
        String [][] m=new String[place.size()][transition.size()];
        String aux="";
        for (int i=0; i<m.length;i++){
            for(int j=0;j<m[i].length;j++){
                if(m[0][0]==null){
                    m[0][0]="|  |";
                }
                else if(m[0][j]==null){
                    m[0][j]="|"+transition.get(j).getLabel()+"|";
                }
                else if(m[i][0]==null){
                    m[i][0]="|"+place.get(j).getLabel()+"|";
                    aux=place.get(i).getLabel();
                }
                else if (((m[i][j]==null) || (m[i][j]!=null)) && aux.equalsIgnoreCase(place.get(i).getLabel())){
                    //m[i][j]="|"+Integer.toString(place.get(j).getBrand())+"|";
                    m[i][j]="|"+place.get(i).getBrand()+"|";
                }
                else {
                    m[i][j]="|  |";
                }
            }
        }
        for (int i=0; i<m.length;i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j]);
            }
        }
    }
}
