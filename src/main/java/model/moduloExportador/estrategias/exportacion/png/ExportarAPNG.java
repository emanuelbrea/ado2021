package model.moduloExportador.estrategias.exportacion.png;


import model.moduloExportador.estrategias.exportacion.EstrategiaDeExportacion;
import model.moduloExportador.exportables.Exportable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ExportarAPNG implements EstrategiaDeExportacion {
    private final String nombreDeArchivo;
    public ExportarAPNG(String nombreDeArchivo){
        this.nombreDeArchivo = nombreDeArchivo;
    }

    public String exportar(Exportable exportable) {
        String path = nombreDeArchivo;
        try{
            URL resource = getClass().getClassLoader().getResource("png_example.png");
            File file = new File( resource.toURI());
            BufferedImage image = ImageIO.read(file);
            path = file.getParent() + "\\" + path;
            ImageIO.write(image, "png", new File(path));
            System.out.println("Exportada imagen JPG correctamente a: " + path);
        }
        catch (Exception exception){
        }

        return path;
    }
}
