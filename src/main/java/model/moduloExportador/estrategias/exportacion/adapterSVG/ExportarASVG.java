package model.moduloExportador.estrategias.exportacion.adapterSVG;

import model.moduloExportador.Exportable;
import model.moduloExportador.estrategias.exportacion.EstrategiaDeExportacion;

public class ExportarASVG implements EstrategiaDeExportacion {
    private AdapterExportadorSVG adapter;

    public ExportarASVG(AdapterExportadorSVG adapter) {
        this.adapter = adapter;
    }

    public String exportar(Exportable exportable) {
        return this.adapter.exportar(exportable);
    }
}
