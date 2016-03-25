package demirciy.ygslyspuanhesaplama;

public class LysCalculateScoreType extends Lys {

    private double ygsTr, ygsSos, ygsMat, ygsFen, lysMat, lysGeo, lysFizik, lysKimya, lysBiyo,
    lysEde, lysCog1, lysTarih, lysCog2, lysFel, lysYdil;

    public LysCalculateScoreType(double ygsTr, double ygsSos, double ygsMat, double ygsFen, double lysMat,
                                 double lysGeo, double lysFizik, double lysKimya, double lysBiyo, double lysEde, double lysCog1,
                                 double lysTarih, double lysCog2, double lysFel, double lysYdil)
    {
        this.ygsTr = ygsTr;
        this.ygsSos = ygsSos;
        this.ygsMat = ygsMat;
        this.ygsFen = ygsFen;
        this.lysMat = lysMat;
        this.lysGeo = lysGeo;
        this.lysFizik = lysFizik;
        this.lysKimya = lysKimya;
        this.lysBiyo = lysBiyo;
        this.lysEde = lysEde;
        this.lysCog1 = lysCog1;
        this.lysTarih = lysTarih;
        this.lysCog2 = lysCog2;
        this.lysFel = lysFel;
        this.lysYdil = lysYdil;
    }
    public double getMF1() {
        return 100 + ygsTr * 1.334 + ygsMat * 1.619 + ygsSos * 0.311 + ygsFen * 0.921 + lysMat * 2.732 +
                lysGeo * 1.439 + lysFizik * 1.059 + lysKimya * 0.391 + lysBiyo * 0.311;
    }
    public double getMF2()
    {
        return 99.990 + ygsTr*1.314 + ygsMat*1.314 + ygsSos*0.311 + ygsFen*1.439 + lysMat*1.568 +
                lysGeo*0.908 + lysFizik*1.469 + lysKimya*1.253 + lysBiyo*1.253;
    }
    public double getMF3()
    {
        return 100.010 + ygsTr*1.311 + ygsMat*1.311 + ygsSos*0.798 + ygsFen*1.311 + lysMat*1.245 +
                lysGeo*0.598 + lysFizik*1.348 + lysKimya*1.447 + lysBiyo*1.557;
    }
    public double getMF4()
    {
        return 99.990 + ygsTr*1.311 + ygsMat*1.447 + ygsSos*0.798 + ygsFen*0.800 + lysMat*2.200 +
                lysGeo*1.311 + lysFizik*1.348 + lysKimya*0.800 + lysBiyo*0.400;
    }
    public double getTM1()
    {
        return 100.002 + ygsTr*1.335 + ygsMat*1.337 + ygsSos*0.663 + ygsFen*0.465 + lysMat*1.901 +
                lysGeo*1.086 + lysEde*1.582 + lysCog1*1.374;
    }
    public double getTM2()
    {
        return 100.020 + ygsTr*1.488 + ygsMat*1.412 + ygsSos*0.619 + ygsFen*0.358 + lysMat*1.880 +
                lysGeo*0.450 + lysEde*1.890 + lysCog1*1.315;
    }
    public double getTM3()
    {
        return 99.982 + ygsTr*1.651 + ygsMat*1.035 + ygsSos*0.974 + ygsFen*0.382 + lysMat*1.573 +
                lysGeo*0.420 + lysEde*1.961 + lysCog1*1.553;
    }
    public double getTS1()
    {
        return 101.974 + ygsTr*1.276 + ygsMat*1.043 + ygsSos*1.024 + ygsFen*0.498 + lysEde*1.534 +
                lysCog1*0.954 + lysTarih*1.534 + lysCog2*0.843 + lysFel*1.534;
    }
    public double getTS2()
    {
        return 110.526 + ygsTr*1.809 + ygsMat*0.378 + ygsSos*1.033 + ygsFen*0.469 + lysEde*1.966 +
                lysCog1*0.809 + lysTarih*1.479 + lysCog2*0.809 + lysFel*1.125;
    }
    public double getDil1()
    {
        return 100 + ygsTr*1.5 + ygsMat*0.6 + ygsSos*1.3 + ygsFen*0.5 +lysYdil*3.25;
    }
    public double getDil2()
    {
        return 100 + ygsTr*2.5 + ygsMat*0.7 + ygsSos*1.3 + ygsFen*0.5 +lysYdil*2.5;
    }
    public double getDil3()
    {
        return 100 + ygsTr*4.8 + ygsMat*0.7 + ygsSos*2 + ygsFen*0.5 +lysYdil*1;
    }
}
