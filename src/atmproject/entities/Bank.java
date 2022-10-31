package atmproject.entities;

public enum Bank {

    YAPI_KREDI(1, "Yapı Kredi"),
    IS_BANKASI(2, "İş Bankası"),
    QNB(3, "QNB"),
    AKBANK(4, "Akbank"),
    VAKIFBANK(5, "Vakıfbank"),
    GARANTI(6, "Garanti"),
    ING(7, "ING");
    private final int id;
    private final String name;

    Bank(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
