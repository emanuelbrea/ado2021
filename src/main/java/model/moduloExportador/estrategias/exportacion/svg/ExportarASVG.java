package model.moduloExportador.estrategias.exportacion.svg;

import model.moduloExportador.estrategias.exportacion.EstrategiaDeExportacion;
import model.moduloExportador.exportables.Exportable;

public class ExportarASVG implements EstrategiaDeExportacion {
    private AdapterExportadorSVG adapter;
    public ExportarASVG(AdapterExportadorSVG adapter){
        this.adapter = adapter;
    }

    public String exportar(Exportable exportable) {
        return this.adapter.exportar(exportable);
    }
}
