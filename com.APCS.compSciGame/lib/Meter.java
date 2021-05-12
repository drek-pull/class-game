package lib;
/**
 * A meter that has a minimum, maximum and current amount between them.
 *
 * @Circly O
 * @version 0.1
 */
public final class Meter
{
    public Meter(int min, int max){
        maximum = max;minimum = min;current = max;
    }
    private int minimum;
    private int maximum;
    private int current;
    public void Add(int add){
        current += add;
        if(current > maximum){current = maximum;}
    }
    public void Sub(int sub){
        current -= sub;
        if(current < minimum){current = minimum;}
    }
    public int GetMin(){
        return minimum;
    }
    public int GetMax(){
        return maximum;
    }
    public int GetCur(){
        return current;
    }
    public int Compare(Meter m){
        int diff = m.GetCur()-current;
        return diff;
    }
}
