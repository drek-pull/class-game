import lib.*;
/**
 * Code used by most types of Rooms.
 *
 * @author Derek P. (Circle Onyx)
 * @version 1.0
 */
public abstract class Room
{
    String name;
    boolean key;
    public abstract void Puzzle();
    public abstract void getPuzzleAnswers();
}
