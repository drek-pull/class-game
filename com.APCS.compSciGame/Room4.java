import java.util.Scanner;
public class Room4 extends Room
{
    Scanner s = new Scanner(System.in);
    String name = "Room 4";
    String answer = "";
    boolean escape = false;
    @Override
    public void Puzzle()
    {
        System.out.println("Welcome to "+name+"!");
        System.out.println("In order to make it to the next room, you have to go through all of the obstacles in this room.");
        System.out.println("David's father has three sons: Snap, Crackle, and _____?");

    }

    @Override
    public void getPuzzleAnswers()
    {
        while(!escape)
        {
            answer = s.nextLine();
            if(answer.equals("david"))
            {
                escape = true;
            }
            else
            {
                System.out.println("That is incorrect! Keep trying... that is the only way you can escape this room.");
            }
        }
        System.out.println("Congrats!!! You made it out!");
    }
}