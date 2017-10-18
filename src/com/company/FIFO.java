package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class FIFO extends Process {
    Queue<Task> taskQueue = new ArrayDeque<Task>();


    @Override
    public void start(ArrayList<Double> timeForNextTask, ArrayList<Double> durations) {
        listSystemReaction.clear();
        listTimeInSystem.clear();
        int index = 0;
        int taskCompleted = 0;

        onProcessor = new Task(0, 0);
        onProcessor.taskLeftTime = 0;
        onProcessor.taskInSystem = 0;
        double waitTime = timeForNextTask.get(index);

        int max = durations.size();
        while (taskCompleted < max) {
            if (((onProcessor.taskLeftTime > waitTime) || onProcessor.taskLeftTime == 0) && (index < max)) {

                systemTime += waitTime;

                if (onProcessor.taskLeftTime != 0) {
                    taskQueue.add(new Task(durations.get(index), systemTime));
                    onProcessor.taskLeftTime -= waitTime;
                } else {
                    onProcessor = new Task(durations.get(index), systemTime);
                    listSystemReaction.add(0.0);
                }

                waitTime = timeForNextTask.get(index++);
            } else {
                taskCompleted++;
                systemTime += onProcessor.taskLeftTime;
                waitTime -= onProcessor.taskLeftTime;
                if (!taskQueue.isEmpty()){
                    onProcessor.taskInSystem = systemTime - onProcessor.taskInSystem;
                    listTimeInSystem.add(onProcessor.taskInSystem);
                    onProcessor = taskQueue.remove();
                    listSystemReaction.add(systemTime - onProcessor.taskInSystem);
                } else {
                    onProcessor.taskLeftTime = 0;
                    onProcessor.taskInSystem = systemTime - onProcessor.taskInSystem;
                    listTimeInSystem.add(onProcessor.taskInSystem);
                }
            }
        }

        setMx();
        setDx();
        setReactionTime();

    }
}
