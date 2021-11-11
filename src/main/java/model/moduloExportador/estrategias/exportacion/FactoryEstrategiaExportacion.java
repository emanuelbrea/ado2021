package model.moduloExportador.estrategias.exportacion;


import model.moduloExportador.estrategias.exportacion.jpg.ExportarAJPG;
import model.moduloExportador.estrategias.exportacion.png.ExportarAPNG;
import model.moduloExportador.estrategias.exportacion.svg.AdapterSVG;
import model.moduloExportador.estrategias.exportacion.svg.ExportarASVG;

public class FactoryEstrategiaExportacion {

    public static EstrategiaDeExportacion crearEstrategia(FormaDeExportacion formaDeExportacion, String nombreDelArchivo){
        EstrategiaDeExportacion estrategia = null;
        switch (formaDeExportacion){
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
