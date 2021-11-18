package model.moduloExportador.estrategias.exportacion;


import model.moduloExportador.Exportable;

public interface EstrategiaDeExportacion {
    String exportar(Exportable exportable);
}
