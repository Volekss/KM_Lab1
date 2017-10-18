package com.company;

import java.util.ArrayList;
import java.util.List;



class Task {
    protected double taskLeftTime = 0;
    protected double taskInSystem = 0;

    Task(double taskLeftTime, double taskInSystem) {
        this.taskLeftTime = taskLeftTime;
        this.taskInSystem = taskInSystem;
    }
}

public abstract class Process {
    protected double systemTime = 0;
    protected double mu;
    protected double lambda;
    protected double mx;
    protected double Dx;
    protected Task onProcessor;

    public double getReactionTime() {
        return reactionTime;
    }

    protected void setReactionTime() {
        reactionTime = 0;
        for(double element: listSystemReaction){
            reactionTime += element;
        }
        reactionTime /= listSystemReaction.size();
    }

    protected double reactionTime;

    List<Double> listTimeInSystem = new ArrayList<>();
    List<Double> listSystemReaction = new ArrayList<>();

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public double getMx() {
        return mx;
    }

    protected void setMx() {
        mx = 0;
        for(double element: listTimeInSystem){
            mx += element;
        }
        mx /= listTimeInSystem.size();
    }

    public double getDx() {
        return Dx;
    }

    protected void setDx() {
        Dx = 0;
        for(double element: listTimeInSystem){
            Dx += (mx - element)*(mx - element);
        }
        Dx /= listTimeInSystem.size();
    }




    public abstract void start(ArrayList<Double> timeForNextTask, ArrayList<Double> durations);


}
