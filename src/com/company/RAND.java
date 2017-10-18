package com.company;

import java.util.*;

public class RAND extends Process {
    List<Task> taskQueue = new ArrayList<>();
    Random random = new Random();


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
                Task in = new Task(durations.get(index), systemTime);
                if (onProcessor.taskLeftTime != 0) {
                    taskQueue.add(in);
                    onProcessor.taskLeftTime -= waitTime;
                } else {
                    onProcessor = in;
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
                    onProcessor = taskQueue.remove(random.nextInt(taskQueue.size()));
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
