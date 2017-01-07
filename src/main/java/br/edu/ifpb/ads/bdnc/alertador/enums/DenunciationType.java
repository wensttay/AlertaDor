package br.edu.ifpb.ads.bdnc.alertador.enums;

/**
 * @version 1.0
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 07/01/2017 - 12:01:31
 */
public enum DenunciationType {
    HARASSMENT(1, "Assédio"),
    RAPE(2, "Estrupo"),
    AGGRESSION(3, "Agressão");

    private int id;
    private String tittle;

    private DenunciationType(int id, String tittle) {
        this.id = id;
        this.tittle = tittle;
    }

    public int getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public static DenunciationType get(int id) {
        for (DenunciationType dt : DenunciationType.values()) {
            if (dt.getId() == id) {
                return dt;
            }
        }
        
        return null;
    }
}
