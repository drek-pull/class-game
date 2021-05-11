package lib;
import java.lang.reflect.Field;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * Write a description of class JarbleSave here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public final class JarbleSave
{
    private JarbleSave(){}
    static Object Saving;
    static StringBuilder catting;
    static Object Loading;
    static String filename;
    /**
     * Save types
     * 0b00000001 -> JSave
     * 0b00000010 -> encodedSave
     * 0b00000100 -> decodedSave
     * 0b00001000 -> ioSave
     * 0b00010000 -> *****
     * 0b00100000 -> *****
     * 0b01000000 -> *****
     * 0b10000000 -> *****
     */
    public static void Save(Object save_this, byte save_Type, String file){
        Saving = save_this;
        filename = file;
        switch(save_Type){
            case 1:JSave();break;
            case 2:break;
            case 4:break;
            case 8:ioSave();break;
            case 16:break;
            default:
        }
    }

    public static void Load(Object load_this, byte save_Type){
        Loading = load_this;
        switch(save_Type){
            case 1:JLoad();break;
            case 2:break;
            case 4:break;
            case 8:ioLoad();break;
            case 16:break;
            default:
        }
    }

    private static void JSave(){
        Field[] field = Saving.getClass().getDeclaredFields();
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
                catting.append(field[i].get(Saving));
            }
            CSum();
        }catch(Exception e){common.Print(e.getMessage(), true);}
    }

    private static void JLoad(){
        Field[] field = Loading.getClass().getDeclaredFields();
        for(int j = 0; j<field.length; j++){
            field[j].setAccessible(true);
            try{
                field[j].set(Loading, new Object());
            }
            catch(Exception e){}
        }

        try{
            catting = new StringBuilder();
            for(int i = 0; i < field.length; i++){
                catting.append(field[i].get(Loading));
            }
        }catch(Exception e){}
    }

    private static int GetSum(){
        int i = catting.lastIndexOf("\n");
        if(i > -1){
            return Integer.parseInt(catting.toString().substring(i, catting.length()));
        }
        return -1;
    }

    private static void CSum(){
        char[] c = catting.toString().toCharArray();
        int sum = 0;
        for(int i : c){
            sum+=i;
        }
        sum ^= catting.toString().length();
        System.out.println(sum);
        catting.append("\n("+sum+")");
    }

    private static void RSum(int sum)throws Exception{
        String s = ((Integer)sum).toString();
        catting.delete(catting.length()-s.length()-1, catting.length());
        sum ^= catting.length();
        for (int i : catting.toString().toCharArray())
            sum -= i;
        if(sum!=0){
            throw new Exception("Data corrupted!!");
        }
    }

    private static void ioSave(){
        try{
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Saving);
            oos.close();
        }
        catch(Exception e){e.getMessage();}
    }

    private static void ioLoad(){
        try{
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Loading = ois.readObject();
            ois.close();
        }
        catch(Exception e){e.getMessage();}
    }
}
