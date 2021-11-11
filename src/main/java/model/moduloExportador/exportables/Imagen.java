package model.moduloExportador.exportables;

import java.util.*;

public class Imagen implements Exportable{
    private String encabezado;
    private String cuerpo;
    private String pie;

    public Imagen(String encabezado, String cuerpo, String pie) {
        this.encabezado = encabezado;
        this.cuerpo = cuerpo;
        this.pie = pie;
    }

    @Override
    public List<String> getDatos() {
        return Arrays.asList(encabezado, cuerpo, pie);
    }
}
