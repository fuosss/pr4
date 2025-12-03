package pr3;

import java.sql.Date;

public class kniga {
    private int idKnigi;
    private String nazvanie;
    private String avtor;
    private String kolvoStranic;
    private Date godPublikacii;

    public kniga(int idKnigi, String nazvanie, String avtor, String kolvoStranic, Date godPublikacii) {
        this.idKnigi = idKnigi;
        this.nazvanie = nazvanie;
        this.avtor = avtor;
        this.kolvoStranic = kolvoStranic;
        this.godPublikacii = godPublikacii;
    }

    public int getIdKnigi() { return idKnigi; }
    public String getNazvanie() { return nazvanie; }
    public String getAvtor() { return avtor; }
    public String getKolvoStranic() { return kolvoStranic; }
    public Date getGodPublikacii() { return godPublikacii; }
}

