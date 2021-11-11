package model.moduloExportador.estrategias.exportacion;


import model.moduloExportador.exportables.Exportable;

public interface EstrategiaDeExportacion {
	String exportar(Exportable exportable);
}
