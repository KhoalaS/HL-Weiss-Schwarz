package com.khoale.hlcards.Logic;

public class PackOpening {

    public static String pull_Foil(){

        double prob = Math.random();

        if (prob <= 0.25){
            return "RR";
        }else if (prob <= 0.375){
            double prob_Foil = Math.random()*36;
            if (prob_Foil <= 20){
                return "SR";
            }else if (prob_Foil <= 30){
                return "RRR";
            }else if(prob_Foil <= 35){
                return "SP";
            }else{
                return "SSP";
            }
        }else if (prob <= 0.625){
            return "CR";
        }else{
            return "U";
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i<20; i++){
            System.out.println(pull_Foil());
        }

    }

}
