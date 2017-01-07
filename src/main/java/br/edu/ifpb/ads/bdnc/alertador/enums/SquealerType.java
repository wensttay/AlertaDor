package br.edu.ifpb.ads.bdnc.alertador.enums;

/**
 * @version 1.0
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 07/01/2017 - 12:01:31
 */
public enum SquealerType {
    VITIMA(1, "Vitima"),
    PRESENCIADOR(2, "Presenciador");

    private int id;
    private String tittle;

    private SquealerType(int id, String tittle) {
        this.id = id;
        this.tittle = tittle;
    }

    public int getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public static SquealerType get(int id) {
        for (SquealerType st : SquealerType.values()) {
            if (st.getId() == id) {
                return st;
            }
        }
        
        return null;
    }
}
