package com.example.remotecontrol;

public class postHorizontal {
    private int horizontal;
    private int IdObj;
    private String message;
    public postHorizontal(int Id, int Horizontal){
        this.IdObj=Id;
        this.horizontal=Horizontal;
    }

    public String getMessage(){return message;}
}
