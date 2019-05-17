package com.terrainsoft.kredihesapla;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Formula {

    public double TaksitTuatarı(double tutar,double oran,int taksitSayisi){
        double taksitTutari=0;
        oran=oran+oran*0.20;
        //System.out.println("orran "+oran);

        taksitTutari=tutar*(oran/100*Math.pow((1+oran/100),taksitSayisi)/(Math.pow((1+oran/100),taksitSayisi)-1));

        return taksitTutari;
    }

    public ArrayList<String> TaksitDokum(double taksitTutarı,double oran,double anapara,int vade){
        double bakiye=anapara;

        ArrayList<String> taksitDokumu=new ArrayList<>();


        for (int i=0;i<vade;i++) {

            String s=null;
            double faiz=bakiye*oran/100;
            double KKDF=faiz*0.15;
            double BSMV=faiz*0.05;



            double netBorc=taksitTutarı-faiz-BSMV-KKDF;

            bakiye=bakiye-netBorc;


           /* taksitDokumu.add(i,  Integer.toString(i+1)+"." +new DecimalFormat("#,##0.00").format(taksitTutarı)+" TL "+
                    new DecimalFormat("#,##0.00").format(faiz)+ " TL "+
                    new DecimalFormat("#,##0.00").format(KKDF)+ " TL "+
                    new DecimalFormat("#,##0.00").format(BSMV)+ " TL "+
                    new DecimalFormat("#,##0.00").format(netBorc)+ " TL ");*/

            taksitDokumu.add(i,  Integer.toString(i+1)+" - " +new DecimalFormat("#,##0.00").format(taksitTutarı)+" TL "+
                    new DecimalFormat("#,##0.00").format(netBorc)+ " TL "+

                    new DecimalFormat("#,##0.00").format(faiz+KKDF+BSMV)+ " TL ");


        }

        return taksitDokumu;
        }

    }

