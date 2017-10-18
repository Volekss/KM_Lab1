package com.company;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
	// write your code here
        // TODO: 14-Oct-17
        ArrayList<Double> inputList = Generator.generateInput(100000, 3);
        ArrayList<Double> outputList= Generator.uncommonGenerateOutput(100000, 3.5, 50, 2);

        FIFO fifo = new FIFO();
        fifo.start(inputList, outputList);

        double fifoMx = fifo.getMx();
        double fifoDx = fifo.getDx();
        double fifoReaction = fifo.getReactionTime();
        double fifoFunc = -1 * fifoMx -2 * fifoDx -5 * fifoReaction;

        System.out.println("fifo mx = "+fifoMx);
        System.out.println("fifo dx = "+fifoDx);
        System.out.println("fifo reaction time = "+fifoReaction);
        System.out.println("fifo func = "+fifoFunc+ '\n');


        RAND rand = new RAND();
        rand.start(inputList, outputList);

        double randMx = rand.getMx();
        double randDx = rand.getDx();
        double randReaction = rand.getReactionTime();
        double randFunc = -1 * randMx -2 * randDx -5 * randReaction;

        System.out.println("rand mx = "+randMx);
        System.out.println("rand dx = "+randDx);
        System.out.println("rand reaction time = "+randReaction);
        System.out.println("rand func = "+randFunc);
    }
}
