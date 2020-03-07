package clasesImagenes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Imagen {

	int chunkCols;
	int chunkMatrix;
	int chunkRows;
	int img1[][];
	int img2[][];
	int imgR[][];
	
	//Aquí declaro anchos minimos permitidos
	public static final int anchoPermitido=800;
    public static final int altoPermitido=600;
    //Matrices equivalente a la imagen 
    public Color arreglo[][];
    public Color auxarreglo[][];
    //ancho y alto estandar
    public int ancho;
    public int alto;
    public Imagen(String archivo) {
        arreglo = new Color[anchoPermitido][anchoPermitido];
        cargarImagen(archivo);
    }
    public void cargarImagen(String archivo){
        BufferedImage bf=null;
        try {
            bf = ImageIO.read(new File(archivo));
        } catch (IOException ex) {
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (bf.getWidth()<anchoPermitido) {
            ancho=bf.getWidth();
        }else
            ancho=anchoPermitido;
        if (bf.getHeight()<altoPermitido) {
            alto=bf.getHeight();
        }else
            alto=altoPermitido;
        //int cont=0;
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                //cont++;
                arreglo[i][j]		= new Color(bf.getRGB(j, i));
                //auxarreglo[i][j]	= arreglo[i][j];
                //System.out.println(cont +":"+"RedGreenBlue:"+ bf.getRGB(j, i));
            }
        }

    }
    public BufferedImage redimensionar(String archivo, double porcentaje ){

        BufferedImage bf = null;
        try {
            bf = ImageIO.read(new File(archivo));
        } catch (IOException ex) {
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        int ancho = bf.getWidth();
        int alto = bf.getHeight();
        int escalaAncho = (int)(porcentaje* ancho);
        int escalaAlto = (int)(porcentaje*alto);
        BufferedImage bufim = new BufferedImage(escalaAncho, escalaAlto, bf.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bf, 0,0, escalaAncho,escalaAlto, 0,0,ancho,alto, null);
        g.dispose();
        return bufim;
    }

    //Convierte un objeto de tipo imagen en BufferedImage
    public BufferedImage imprimirImagen(){
        BufferedImage salida = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < alto; i++)
            for (int j = 0; j < ancho; j++) 
            	//Asignamos cada color a una matriz de colores para formar una imagen
                salida.setRGB(j, i, arreglo[i][j].getRGB());
        
        return salida;
    }
	public BufferedImage sumar(Imagen obj, Imagen obj2) {
		// TODO Auto-generated method stub
		BufferedImage salida = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
    	for (int i = 0; i < alto; i++)
            for (int j = 0; j < ancho; j++)
            	salida.setRGB(j, i,( (obj.arreglo[i][j].getRGB()) + (obj2.arreglo[i][j].getRGB())));
    	return salida;
	}
	public BufferedImage restar(Imagen obj, Imagen obj2) {
		// TODO Auto-generated method stub
		BufferedImage salida = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
    	for (int i = 0; i < alto; i++)
            for (int j = 0; j < ancho; j++)
            	//Obtenemos el valor resultante de una resta de rgb
            	salida.setRGB(j, i,( (obj.arreglo[i][j].getRGB()) - (obj2.arreglo[i][j].getRGB())));
    	return salida;
	}
	public BufferedImage multiplicar(Imagen obj, Imagen obj2) {
		// TODO Auto-generated method stub
		BufferedImage A         = obj.imprimirImagen();
		BufferedImage B         = obj2.imprimirImagen();
		BufferedImage salida    = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		int k = 1 / 255;
		for(int i = 0; i < ancho; i++)
		{
			for(int j = 0; j < alto; j++)
			{
				/*
				 * siguinendo las indicaciones del profesor para la multiplicacion
				 * asi se declara la formula
				*/
				rgb1 = new Color(A.getRGB(i,j));
				rgb2 = new Color(B.getRGB(i,j));
					r =  (rgb1.getRed() * rgb2.getRed())/255;
					g =  (rgb1.getBlue() * rgb2.getBlue())/255;
					b =  (rgb1.getGreen() * rgb2.getGreen())/255;
				auxColor = new Color(r,g,b);
				/*
				 * Set RGB va creando una imagen conforme enviamos los pixeles
				 * en las pocisiones (i,j)
				*/
				salida.setRGB(i, j, auxColor.getRGB());

			}
		}
        return salida;
	}

	
	public int pixel2matrix(int alto,int ancho){
		BufferedImage res = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		Color rgb1, rgb2, auxColor;
		int r, g, b;
		//Corregir el return
		return 0;
	}
	
}
