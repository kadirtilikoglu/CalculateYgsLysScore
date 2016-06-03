package demirciy.ygslyspuanhesaplama.model;

public class YgsCalculateScoreType {

    private double ygsTrN, ygsSosyalN, ygsMatN, ygsFenN;

    public YgsCalculateScoreType(double ygsTrN, double ygsSosyalN, double ygsMatN, double ygsFenN)
    {
        this.ygsTrN = ygsTrN;
        this.ygsSosyalN = ygsSosyalN;
        this.ygsMatN = ygsMatN;
        this.ygsFenN = ygsFenN;
    }
    public double getYgs1()
    {
        return 100.160 + (ygsTrN * 1.999) + (ygsSosyalN * 1) + (ygsMatN * 3.998) + (ygsFenN * 2.999);
    }
    public double getYgs2()
    {
        return 100.160 + (ygsTrN * 1.999) + (ygsSosyalN * 1) + (ygsMatN * 2.999) + (ygsFenN * 3.998);
    }
    public double getYgs3()
    {
        return 100.160 + (ygsTrN * 3.998) + (ygsSosyalN * 2.999) + (ygsMatN * 1.999) + (ygsFenN * 1);
    }
    public double getYgs4()
    {
        return 100.160 + (ygsTrN * 2.999) + (ygsSosyalN * 3.998) + (ygsMatN * 1.999) + (ygsFenN * 1);
    }
    public double getYgs5()
    {
        return 100.120 + (ygsTrN * 3.699) + (ygsSosyalN * 1.999) + (ygsMatN * 3.299) + (ygsFenN * 1);
    }
    public double getYgs6()
    {
        return 100.120 + (ygsTrN * 3.299) + (ygsSosyalN * 1) + (ygsMatN * 3.699) + (ygsFenN * 1.999);
    }
}
