package demirciy.ygslyspuanhesaplama;

public class NetHesapla {

    private double dogru,yanlis;

    public NetHesapla(double dogru, double yanlis)
    {
        this.dogru  = dogru;
        this.yanlis = yanlis;
    }
    public double getNet()
    {
        return dogru - (yanlis * 0.25);
    }
}
