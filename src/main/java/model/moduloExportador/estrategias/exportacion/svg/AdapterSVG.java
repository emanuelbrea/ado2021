package model.moduloExportador.estrategias.exportacion.svg;

import model.moduloExportador.exportables.Exportable;

public class AdapterSVG implements AdapterExportadorSVG{
    private String nombreDeArchivo;
    public AdapterSVG(String nombreDeArchivo) {

        this.nombreDeArchivo = nombreDeArchivo;
    }

    public String exportar(Exportable exportable) {
        System.out.println("Imagen SVG exportada correctamente con datos: "
        + exportable.getDatos().toString());

        return exportable.getDatos().toString();
    }



}
