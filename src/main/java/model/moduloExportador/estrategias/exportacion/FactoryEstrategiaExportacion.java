package model.moduloExportador.estrategias.exportacion;


import model.moduloExportador.estrategias.exportacion.adapterSVG.AdapterSVG;
import model.moduloExportador.estrategias.exportacion.adapterSVG.ExportarASVG;

public class FactoryEstrategiaExportacion {

    public static EstrategiaDeExportacion crearEstrategia(FormaDeExportacion formaDeExportacion, String nombreDelArchivo) {
        EstrategiaDeExportacion estrategia = null;
        switch (formaDeExportacion) {
            case JPG:
                estrategia = new ExportarAJPG(nombreDelArchivo);
                break;
            case PNG:
                estrategia = new ExportarAPNG(nombreDelArchivo);
                break;
            case SVG:
                estrategia = new ExportarASVG(new AdapterSVG(nombreDelArchivo));
                break;
        }
        return estrategia;
    }
}
