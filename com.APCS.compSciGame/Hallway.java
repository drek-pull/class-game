import lib.common;
import java.lang.StringBuilder;
/**
 * Write a description of class Hallway here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hallway
{
    Room room1, room2;
    Hallway(Room r1, Room r2){
        room1 = r1;
        room2 = r2;
    }
    private StringBuilder roomLayout = new StringBuilder();
    public Room Puzzle(){
        roomLayout.append("      |             |\n");
        roomLayout.append("      |             |\n");
        roomLayout.append("      |             |\n");
        roomLayout.append("______⅃             L_______\n");
        roomLayout.append("   |                    |\n");
        roomLayout.append("   |    <          >    |\n");
        roomLayout.append("   |                    |\n");
        roomLayout.append("‾‾‾‾‾‾‾˥             ┌‾‾‾‾‾‾‾\n");
        roomLayout.append("      |     YOU     |\n");
        roomLayout.append("      |      v      |\n");
        roomLayout.append("      |      █      |\n");
        common.Print(roomLayout.toString(), true);
        common.Print("\n    do you want to go left, right, or foreward?", true);
        String s = common.ScS().toLowerCase();
        if(s.equals("left") || s.equals("l")){
            return room1;
        }
        if(s.equals("right") || s.equals("r")){
            return room2;
        }
        return null;
    }
}
