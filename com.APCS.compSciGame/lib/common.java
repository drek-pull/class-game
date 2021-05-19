package lib;
import java.util.Scanner;
import java.util.Random;
/**
 * common things that everyone can use to make you have to type less generally.
 * 
 * @CircleOnyx - Company
 * @RageMan - Main Programmer
 * Alpha 0.1
 */
public final class common //final so nothing can inherit anything from it.
{
    private common(){//Private constructor so no instances can be created of it.
    }
    static StringBuilder Sbuild;
    static Random ran = new Random();
    static Scanner ScS = new Scanner(System.in);

    public static void Sleep(int milis){
        try{
            Thread.sleep(milis);
        }catch(Exception e){}
    }
    
    public static void Print(Object s, boolean ret)//Prints the object to the terminal. if ret is true it does println instead of just print.
    {
        if(ret){
            System.out.println(s);
        }
        else{
            System.out.print(s);
        }
    }

    public static int Random(int i, int j){//returns a random value between i and j
        return j > i ? ran.nextInt(j-i+1)+i : ran.nextInt(i-j+1)+j;
    }

    public static int ScI()//Scans the line and returns the Integer inside it.(makes it so you can accidentally type a letter or a space and it ignores that)
    {
        return Integer.parseInt(TrimNum(ScS.nextLine()));
    }

    public static String ScS()//Scans the line and return the String
    {
        return ScS.nextLine();
    }

    public static boolean IsOdd(int i)//Checks if the value is off or not. (just a simple thing that isn't needed for most things just something fun)
    {
        return (i&1) != 0;
    }

    public static String TrimNum(String number){//trims out every character from the string except for all numbers and decimals. may have an error for Integer.parseInt() if it contains multiple decimals.
        number = number.toLowerCase().trim();
        StringBuilder pally = new StringBuilder();
        for(int i = 0; i < number.length(); i++){
            if((number.charAt(i) >= '0' && number.charAt(i) <= '9') || number.charAt(i) == '.'){
                pally.append(number.charAt(i));
            }
        }
        if(pally.length() < 1){return "0";}
        return pally.toString();
    }

    public static String TranslateToXml(String s){//mainly used for xml but it changes all tabs or line returns and quotes to an xml-style format. makes it easier for xml to find where the end of a variable name/value is
        Sbuild = new StringBuilder(s.length());
        for(int i = 0; i < s.length();i++){
            char c = s.charAt(i);
            switch(c){
                case '<':Sbuild.append("'<'");break;
                case '>':Sbuild.append("'>'");break;
                case '\t':Sbuild.append("<t>");break;
                case '\n':Sbuild.append("<n>");break;
                case '\"':Sbuild.append("<q>");break;
                default: Sbuild.append(c);
            }
        }
        return Sbuild.toString();
    }

    public static String TranslateFromXml(String s){//reverse of TranslateToXml
        Sbuild = new StringBuilder(s.length());
        for(int i = 0; i < s.length()-2;i++){
            String c = "" +s.charAt(i) +s.charAt(i+1) +s.charAt(i+2);
            switch(c){
                case "'<'":Sbuild.append("<");break;
                case "'>'":Sbuild.append(">");break;
                case "<t>":Sbuild.append("\t");break;
                case "<n>":Sbuild.append("\n");break;
                case "<q>":Sbuild.append("\"");break;
                default: Sbuild.append(s.charAt(i));
            }
        }
        return Sbuild.toString();
    }
}
