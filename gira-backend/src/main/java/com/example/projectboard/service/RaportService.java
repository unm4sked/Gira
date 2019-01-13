package com.example.projectboard.service;

import com.example.projectboard.domain.Logger;
import com.example.projectboard.domain.ProjectTask;
import com.example.projectboard.domain.Raport;

import java.io.*;

public class RaportService {
    public Raport raport;

    public RaportService(){

    }

    public void wrapLogicSaveRaport(Raport raport, ProjectTask projectTask){
        Raport rap = this.getRaport();
        if(rap != null){
            raport.setDoneTaks(rap.getDoneTaks());
            raport.setNumberOfTasks(rap.getNumberOfTasks());
            raport.setInProgressTasks(rap.getInProgressTasks());
            raport.setToDoTasks(rap.getToDoTasks());
        }
        raport.projectTaskToRaportAdd(projectTask);
        this.saveRaport(raport);
    }
    public void wrapLogicDecRaport(Raport raport, ProjectTask projectTask){
        Raport rap = this.getRaport();
        if(rap != null){
            raport.setDoneTaks(rap.getDoneTaks());
            raport.setNumberOfTasks(rap.getNumberOfTasks());
            raport.setInProgressTasks(rap.getInProgressTasks());
            raport.setToDoTasks(rap.getToDoTasks());
        }
        raport.projectTaskToRaportDelete(projectTask);
        this.saveRaport(raport);
    }

    public Raport getRaport(){
        Raport r = null;
        try{
            FileInputStream fileIn = new FileInputStream("./raport.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            r = (Raport) in.readObject();
            in.close();
            fileIn.close();
            Logger.log(1,"Serializing an Object status ->OK");
            return r;
        }catch (Exception i){
            Logger.log(4,"Error with Deserializing an Object.");
            return null;
        }
    }

    public void saveRaport(Raport r){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("./raport.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(r);
            out.close();
            fileOut.close();
            Logger.log(1,"Serialized data is saved in <project_directory>./raport.ser");
        } catch (IOException i) {
            i.printStackTrace();
            Logger.log(4,"Error with serializing an Object.");
        }
    }
}
