package pr3;

import java.sql.Date;

public class vydachaknig {
    private int idVydachi;
    private int idKlienta;
    private int idKnigi;
    private Date dataVydachi;
    private Date dataVozvrata;

    public vydachaknig(int idVydachi, int idKlienta, int idKnigi, Date dataVydachi, Date dataVozvrata) {
        this.idVydachi = idVydachi;
        this.idKlienta = idKlienta;
        this.idKnigi = idKnigi;
        this.dataVydachi = dataVydachi;
        this.dataVozvrata = dataVozvrata;
    }

    public int getIdVydachi() { return idVydachi; }
    public int getIdKlienta() { return idKlienta; }
    public int getIdKnigi() { return idKnigi; }
    public Date getDataVydachi() { return dataVydachi; }
    public Date getDataVozvrata() { return dataVozvrata; }
}