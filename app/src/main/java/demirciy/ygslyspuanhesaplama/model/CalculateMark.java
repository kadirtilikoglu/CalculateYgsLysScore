package demirciy.ygslyspuanhesaplama.model;

public class CalculateMark {

    private double correct,incorrect;

    public CalculateMark(double correct, double incorrect)
    {
        this.correct  = correct;
        this.incorrect = incorrect;
    }
    public double getMark()
    {
        return correct - (incorrect * 0.25);
    }
}
