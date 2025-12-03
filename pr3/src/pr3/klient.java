package pr3;

public class klient {
    private int idKlienta;
    private String fio;
    private String nomerTelephona;
    private String email;
    private String adresProzhivaniya;

    public klient(int idKlienta, String fio, String nomerTelephona, String email, String adresProzhivaniya) {
        this.idKlienta = idKlienta;
        this.fio = fio;
        this.nomerTelephona = nomerTelephona;
        this.email = email;
        this.adresProzhivaniya = adresProzhivaniya;
    }

    public int getIdKlienta() { return idKlienta; }
    public String getFio() { return fio; }
    public String getNomerTelephona() { return nomerTelephona; }
    public String getEmail() { return email; }
    public String getAdresProzhivaniya() { return adresProzhivaniya; }
}