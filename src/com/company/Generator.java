package com.company;

import java.util.ArrayList;

public class Generator {

    public static ArrayList<Double> generateInput(int quantityTask, double lambda) {
        ArrayList<Double> list = new ArrayList<Double>();
        for(int i = 0; i < quantityTask; )
        {
            int in = getPoisson(lambda);
            double t = 0;
            while(in == 0)
            {
                t += 1;
                in = getPoisson(lambda);
            }

            i += in;
            list.add(t + 1.0/in);
            for(int j = 0; j < in - 1; ++j)
                list.add(1.0/in);
        }
        return list;
    }

    public static int getPoisson(double lambda) {
        double L = Math.exp(-lambda);
        double p = 1.0;
        int k = 0;

        do {
            k++;
            p *= Math.random();
        } while (p > L);

        return k - 1;
    }

    public static ArrayList<Double> generateOutput(int quantityTask, double mu) {
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < quantityTask; i++) {
            list.add(1 / mu);
        }
        return list;
    }

    public static ArrayList<Double> uncommonGenerateOutput(int quantityTask, double mu, int indexTask, double durationOfUncommonTask) {
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < quantityTask; i++) {
            if ((i+1) % indexTask == 0) {
                list.add(durationOfUncommonTask);
                continue;
            }
            list.add(1 / mu);
        }
        return list;
    }


}
