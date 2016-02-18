package demirciy.ygslyspuanhesaplama;

public class YgsPuanTuruHesaplama {

    private double ygs_tr_net, ygs_sosyal_net, ygs_mat_net, ygs_fen_net;

    public YgsPuanTuruHesaplama(double ygs_tr_net, double ygs_sosyal_net, double ygs_mat_net, double ygs_fen_net)
    {
        this.ygs_tr_net = ygs_tr_net;
        this.ygs_sosyal_net = ygs_sosyal_net;
        this.ygs_mat_net = ygs_mat_net;
        this.ygs_fen_net = ygs_fen_net;
    }
    public double getYgs1()
    {
        return 100.160 + (ygs_tr_net * 1.999) + (ygs_sosyal_net * 1) + (ygs_mat_net * 3.998) + (ygs_fen_net * 2.999);
    }
    public double getYgs2()
    {
        return 100.160 + (ygs_tr_net * 1.999) + (ygs_sosyal_net * 1) + (ygs_mat_net * 2.999) + (ygs_fen_net * 3.998);
    }
    public double getYgs3()
    {
        return 100.160 + (ygs_tr_net * 3.998) + (ygs_sosyal_net * 2.999) + (ygs_mat_net * 1.999) + (ygs_fen_net * 1);
    }
    public double getYgs4()
    {
        return 100.160 + (ygs_tr_net * 2.999) + (ygs_sosyal_net * 3.998) + (ygs_mat_net * 1.999) + (ygs_fen_net * 1);
    }
    public double getYgs5()
    {
        return 100.120 + (ygs_tr_net * 3.699) + (ygs_sosyal_net * 1.999) + (ygs_mat_net * 3.299) + (ygs_fen_net * 1);
    }
    public double getYgs6()
    {
        return 100.120 + (ygs_tr_net * 3.299) + (ygs_sosyal_net * 1) + (ygs_mat_net * 3.699) + (ygs_fen_net * 1.999);
    }
}
