package model.moduloExportador.estrategias.exportacion;

import model.moduloExportador.Exportable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ExportarAJPG implements EstrategiaDeExportacion {
    private final String nombreDeArchivo;

    public ExportarAJPG(String nombreDeArchivo) {
        this.nombreDeArchivo = nombreDeArchivo;
    }

    public String exportar(Exportable exportable) {
        String path = nombreDeArchivo;
        try {
            URL resource = getClass().getClassLoader().getResource("jpg_example.jpg");
            File file = new File(resource.toURI());
            BufferedImage image = ImageIO.read(file);
            path = file.getParent() + "\\" + path;
            ImageIO.write(image, "jpg", new File(path));
            System.out.println("Exportada imagen JPG correctamente a: " + path);
        } catch (Exception exception) {
        }
        return path;
    }


}
