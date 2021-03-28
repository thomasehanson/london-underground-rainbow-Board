package com.company;
import java.awt.*;
import java.util.Hashtable;

public class Info {
    // Returns line/mode object given line/mode id
    // Used for finding information about a line or mode by its id
    // for example, returning the name of the line with id hammersmith-city
    Hashtable<String, Object> lineDict = new Hashtable<String, Object>();
    Hashtable<String, Object> modeDict = new Hashtable<String, Object>();

    public void addLine(String id, Object line){
        this.lineDict.put(id, line);
    }

    public Object getLineById(String id){
        return this.lineDict.get(id);
    }

    public void addMode(String id, Object mode){
        this.modeDict.put(id, mode);
    }

    public Object getModeById(String id){
        return this.modeDict.get(id);
    }
}
