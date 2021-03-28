package com.company;
import java.util.Hashtable;

public class Creator {
    // Creates lines from the constant class of line ids, returning a dictionary of line objects with their line id as the key
    Info info = new Info();
    Constants constants = new Constants();
    Hashtable<String, Line> lineDict = new Hashtable<String, Line>();
    Hashtable<String, Mode> modeDict = new Hashtable<String, Mode>();

    public void createLines(){
        for(String line : constants.getLineIds()){
            Line lineObject = new Line(line);
            this.lineDict.put(line, lineObject);
        }
    }

    public Line getLine(String id){
        return this.lineDict.get(id);
    }

    public void createModes(){
        for(String mode : constants.getModes()){
            Mode modeObject = new Mode(mode);
            this.modeDict.put(mode, modeObject);
        }
    }

    public Mode getMode(String id){
        return this.modeDict.get(id);
    }

}
