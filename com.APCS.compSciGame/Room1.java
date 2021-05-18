import java.util.Scanner;
public class Room1 extends Room
{
    Scanner s = new Scanner(System.in);
    String name = "Room 1";
    String answer = "";
    boolean escape = false;
    @Override
    public void Puzzle()
    {
        System.out.println("Welcome to "+name+"!");
        System.out.println("In order to make it to the next room, you have to go through all of the obstacles in this room.");
        System.out.println("What has to be broken, before you use it?");
        getPuzzleAnswers();
        

    }

    @Override
    public void getPuzzleAnswers()
    {
        answer = s.nextLine();
        while(escape)
        {
            if(answer.equals("An egg"))
            {
                escape = true;
            }
            else
            {
                System.out.println("That is incorrect! Keep trying... that is the only way you can escape this room.");
                Puzzle();
            }
        }
        System.out.println("Congrats!!! You made it out! But you aren't done, time to move onto room 2...");
    }
}
