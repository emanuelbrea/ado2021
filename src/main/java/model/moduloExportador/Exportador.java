package model.moduloExportador;


import model.moduloExportador.estrategias.exportacion.EstrategiaDeExportacion;

public class Exportador {
    private EstrategiaDeExportacion estrategia;
    private Exportable exportable;

    public Exportador(EstrategiaDeExportacion estrategia) {
        this.estrategia = estrategia;
    }

    public void setExportable(Exportable exportable) {
        this.exportable = exportable;
    }

    public void setEstrategia(EstrategiaDeExportacion estrategia) {
        this.estrategia = estrategia;
    }

    public String exportar() {
        return this.estrategia.exportar(this.exportable);
    }

}
