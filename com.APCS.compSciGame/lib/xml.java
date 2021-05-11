package lib;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * creates an xml file and puts variables into it and takes them out.
 * 
 * @CircleOnyx - Company
 * @RageMan - Main Programmer
 * Alpha 0.1
 */
public class xml
{
    File f;//The file
    String fromVars;//The string which is used in writing the xml file.
    String fromFile;//The string which is used in reading the xml file.
    boolean debug; //for printing out what it's doing.
    public xml(String fileName){
        f = new File(fileName + ".xml");
        fileExists();
        ClearFile();
    }

    public Field[] loadClass(Object ob, String name){//WIP: will end up loading variables into the specified class
        Field[] f = ob.getClass().getDeclaredFields();
        return f;
    }

    public void StateMachine(boolean isClass){//WIP: will end up creating fromFile.
        StringBuilder build = new StringBuilder();
        try{
            Scanner ScF = new Scanner(f);
            if(isClass){ScF.nextLine();}
            while(ScF.hasNext()){
                String s = ScF.nextLine();
                switch(s){
                    case "":break;
                    case "<String>":build.append("String ");break;
                    case "<Integer>":build.append("Integer ");break;
                    case "<Short>":build.append("Short ");break;
                    case "<Long>":build.append("Long ");break;
                    case "<Boolean>":build.append("Boolean ");break;
                    case "<Byte>":build.append("Byte ");break;
                    case "<Character>":build.append("Character ");break;
                    case "<Field;>":build.append("Field ");break;
                    default:build.append("CustomClass" + " ");
                }

                s = ScF.nextLine();
                int i=s.lastIndexOf("name") + 6;
                s = s.substring(i+2,s.length());
                i = 0;
                while(s.charAt(i) !='"'){
                    build.append(s.charAt(i));
                    i++;
                }
                build.append(" ");
                i=s.lastIndexOf("value") + 5;
                s = s.substring(i+2,s.length());
                i = 0;
                while(s.charAt(i) !='"'){
                    build.append(s.charAt(i));
                    i++;
                }
                build.append(" \n");
                s = ScF.nextLine();
            }
        }catch(Exception e){System.out.println(e.getMessage());}
        fromFile = build.toString();
        common.Print(fromFile, true);
    }

    public void getInstanceInfo(Object ob, String name){//Creates fromVars for a specific instance of a class.
        fromVars += "<" + name + ">\n";
        Field[] field = ob.getClass().getDeclaredFields();
        int[] per = new int[field.length];
        for(int j = 0; j<field.length; j++){
            field[j].setAccessible(true);
            String s = field[j].toString();
            for(int i =0; i<s.length();i++){
                if(s.charAt(i) == '.'){
                    per[j]++;
                }
            }
        }
        try{
            for(int i = 0; i < field.length; i++){
                System.out.println("");
                if(ob.getClass() != this.getClass()){
                    variablesToStr(field[i].get(ob), field[i].getName());            
                }
            }
        }catch(Exception e){common.Print(e.getMessage(), true);}
        fromVars += "</" + name + ">\n";
    }

    public void setInstanceInfo(Object ob, String name){//WIP: will be used for setting the variables a specific instance of a class
        fromVars += "<" + name + ">\n";
        Field[] field = ob.getClass().getDeclaredFields();
        int[] per = new int[field.length];
        for(int j = 0; j<field.length; j++){
            field[j].setAccessible(true);
            String s = field[j].toString();
            for(int i =0; i<s.length();i++){
                if(s.charAt(i) == '.'){
                    per[j]++;
                }
            }
        }
        try{
            for(int i = 0; i < field.length; i++){
                System.out.println("");
                if(ob.getClass() != this.getClass()){
                    variablesToStr(field[i].get(ob), field[i].getName());            
                }
            }
        }catch(Exception e){common.Print(e.getMessage(), true);}
        fromVars += "</" + name + ">\n";
    }

    public void getClassInfo(Object ob){//WIP: trying to just create something that only gets the static variables.
        fromVars += "<" + ob.getClass().toString() + ">\n";
        Field[] vars = ob.getClass().getFields();
        Method[] meths = ob.getClass().getMethods();
        int[] per = new int[vars.length];
        for(int j = 0; j<vars.length; j++){
            String s = vars[j].toString();
            for(int i =0; i<s.length();i++){
                if(s.charAt(i) == '.'){
                    per[j]++;
                }
            }
        }
        try{
            for(int i = 0; i < vars.length; i++){
                System.out.println("");
                if(ob.getClass() != this.getClass()){
                    variablesToStr(vars[i].get(ob), vars[i].getName());            
                }
            }
        }catch(Exception e){}
        fromVars += "</" + ob.getClass().toString() + ">\n";
    }
    
    public void ClearFile(){//removes all contents of the file.
        try{
            fromVars = "";
            saveXml(false);
        }catch(Exception e){common.Print(e.getMessage(), true);}
    }

    public void variablesToStr(Object variable, String name){//adds onto fromVars for a single variable
        String temp = variable.getClass().toString();
        temp = temp.substring(temp.lastIndexOf(".")+1,temp.length());
        //prints the name -> what type of variable it is and the value it holds-> the name/
        if(name.contains("value")){common.Print("illegal variable name", true); return;}
        String x = "<"+temp+">\nname = \"" + name + "\" value=\""+variable+"\"\n</"+temp+">\n";
        common.Print(x, true);
        fromVars += x;
    }

    private String fileToVariables(String filler, String name){//adds onto fromFile for a single variable.
        String x = "";
        try{
            if(fromFile.contains("\"" +name+ "\"")){
                common.Print(fromFile, true);

                String sub = fromFile.substring( fromFile.indexOf("value"),fromFile.length());
                int b =  sub.indexOf('"')+1;
                while((sub.charAt(b) != '"')){
                    x+=sub.charAt(b);
                    b++;
                }
            }
        }catch(Exception e){common.Print(e.getMessage(), true);}
        return common.TranslateFromXml(x);
    }

    public void fileExists(){//Checks if the file exists. creates it if it doesn't
        try{
            if(!f.exists()){
                f.createNewFile();
            }
        }catch(Exception e){common.Print(e.getMessage(), true);}
    }

    public void saveXml(boolean b){//saves fromVars to the specified xml file
        try{
            FileWriter write = new FileWriter(f, b);
            write.write(fromVars);
            write.close();
        }catch(Exception e){common.Print(e.getMessage(), true);}
    }

    public String toString(){return fromVars;}
}