package at.mueller.alfons;


import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class dicom_reader {
        public dicom_reader() throws Exception {
            DicomInputStream input = new DicomInputStream(new File("images/0819A27F.dcm"));
            DicomObject dcm =input.readDicomObject();
            short[] pixelData =dcm.getShorts(Tag.PixelData);
            LookupTable lt = new LookupTable();
            lt.setAlpha(255);
            lt.setCenter(20);
            lt.setWidth(500);
            int rows =dcm.getInt(Tag.Rows);
            int columns = dcm.getInt(Tag.Columns);
            BufferedImage bi = new BufferedImage(columns,rows,BufferedImage.TYPE_INT_RGB) ;

            for(int r=0;r<rows;r++){
                for(int c =0;c<columns;c++){

                    bi.setRGB(c,r,lt.aRGB(pixelData[r*columns+c]));

                }
            }
            ImageIO.write(bi,"JPEG",new File("image2.jpeg"));


        }
}
