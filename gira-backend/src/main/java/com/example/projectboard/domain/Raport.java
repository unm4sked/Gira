package com.example.projectboard.domain;

import java.io.Serializable;

public class Raport implements Serializable {
    private Long id;

    private int numberOfTasks;
    private int toDoTasks;
    private int inProgressTasks;
    private int doneTaks;

    public Raport(){
        numberOfTasks=0;
        toDoTasks=0;
        inProgressTasks=0;
        doneTaks=0;
    }

    public String getRaport(){
        return "Total tasks: "+
                this.numberOfTasks+
                "\nTo do Tasks: "+
                this.toDoTasks+
                "\nIn Progress Tasks: "+this.inProgressTasks+
                "\nDone Tasks: "+this.doneTaks
                ;
    }

    public Boolean isSomeNotZero(){
        if(numberOfTasks>0 || toDoTasks>0 || inProgressTasks>0 || doneTaks>0){
            return true;
        }
        return false;
    }
    public void projectTaskToRaportAdd(ProjectTask projectTask){
        this.numberOfTasks++;
        if(projectTask.getStatus().equals("TO_DO")){
            toDoTasks++;
        }
        else if(projectTask.getStatus().equals("IN_PROGRESS")){
            inProgressTasks++;
        }
        else if(projectTask.getStatus().equals("DONE")){
            doneTaks++;
        }

    }

    public void projectTaskToRaportDelete(ProjectTask projectTask){
        this.numberOfTasks--;
        if(this.numberOfTasks<0){
            this.numberOfTasks=0;
        }
        if(projectTask.getStatus().equals("TO_DO")){
            toDoTasks--;
            if(toDoTasks<0){
                toDoTasks=0;
            }
        }
        else if(projectTask.getStatus().equals("IN_PROGRESS")){
            inProgressTasks--;
            if(inProgressTasks<0){
                inProgressTasks=0;
            }
        }
        else if(projectTask.getStatus().equals("DONE")){
            doneTaks--;
            if(doneTaks<0){
                doneTaks=0;
            }
        }
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public int getToDoTasks() {
        return toDoTasks;
    }

    public void setToDoTasks(int toDoTasks) {
        this.toDoTasks = toDoTasks;
    }

    public int getInProgressTasks() {
        return inProgressTasks;
    }

    public void setInProgressTasks(int inProgressTasks) {
        this.inProgressTasks = inProgressTasks;
    }

    public int getDoneTaks() {
        return doneTaks;
    }

    public void setDoneTaks(int doneTaks) {
        this.doneTaks = doneTaks;
    }


}
