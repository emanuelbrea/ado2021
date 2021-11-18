package model.moduloExportador;

import model.moduloExportador.estrategias.exportacion.EstrategiaDeExportacion;
import model.moduloExportador.estrategias.exportacion.FactoryEstrategiaExportacion;
import model.moduloExportador.estrategias.exportacion.FormaDeExportacion;
import model.publicacion.Publicacion;


public class FacadeExportador {

    public static String exportar(Publicacion publicacion, FormaDeExportacion formaDeExportacion) {
        Imagen imagenExportable = new Imagen(publicacion.getTitulo(), publicacion.getDescripcion(),
                publicacion.getEmpresa().getRazonSocial());
        String nombreDelArchivo = String.format("%s.%s", publicacion.getTitulo(), formaDeExportacion.name().toLowerCase());
        EstrategiaDeExportacion estrategiaDeExportacion = FactoryEstrategiaExportacion.crearEstrategia(formaDeExportacion, nombreDelArchivo);
        Exportador exportador = new Exportador(estrategiaDeExportacion);
        exportador.setExportable(imagenExportable);
        return exportador.exportar();
    }
}
