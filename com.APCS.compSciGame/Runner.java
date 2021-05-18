
/**
 * Runs the program.
 *
 * @author whole class
 * @version 1.0 5/11/21
 */
public class Runner
{
    public static void Main(String[] args){
        boolean key = false;
        Room r = new Room1();
        Hallway h = new Hallway(r,r);
        while(!key){
            Room roo = h.Puzzle();
            if(roo != null){
                roo.Puzzle();
            }
        }
    }
}
