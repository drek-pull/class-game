import lib.common;
public class Runner
{
    public static void Main(String[] args){
        boolean key1 = false;
        boolean key2 = false;
        Room r = new Room1();
        Room r2 = new Room2();
        Room r3 = new Room3();
        Room r4 = new Room4();
        Hallway h = new Hallway(r,r2);
        while(!key1){
            Room roo = h.Puzzle();
            if(roo != null){
                roo.Puzzle();
            }
            if(r.key && r2.key){
                key1 = true;
            }
        }
        h.PrintLay();
        common.Print("go foreward to the next room??", true);
        String s =common.ScS().toLowerCase();
        if(s.equals("yes") || s.equals("y")){
        }
        else{common.Print("guess you lose", true);}
        h = new Hallway(r3,r4);
        while(!key2){
            Room roo = h.Puzzle();
            if(roo != null){
                roo.Puzzle();
            }
            common.Sleep(1000);
            if(r3.key && r4.key){
                key2 = true;
            }
        }
        h.PrintLay();
        common.Print("go foreward and exit??", true);
        s =common.ScS().toLowerCase();
        if(s.equals("yes") || s.equals("y")){
        }
        else{common.Print("guess you lose", true);}
    }
}
