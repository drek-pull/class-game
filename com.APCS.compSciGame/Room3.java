
import java.util.Scanner;
public class Room3 extends Room
{
    Scanner s = new Scanner(System.in);
    String name = "Room 3";
    String answer = "";
    boolean escape = false;
    @Override
    public void Puzzle()
    {
        System.out.println("Welcome to "+name+"!");
        System.out.println("In order to make it to the next room, you have to go through all of the obstacles in this room.");
        System.out.println("What has a head, a tail, is brown, and has no legs?");

    }

    @Override
    public void getPuzzleAnswers()
    {
        while(!escape)
        {
            answer = s.nextLine();
            if(answer.equals("penny"))
            {
                escape = true;
            }
            else
            {
                System.out.println("That is incorrect! Keep trying... that is the only way you can escape this room.");
            }
        }
        System.out.println("Congrats!!! You made it out! But you aren't done, time to move onto room 4...");
    }
}