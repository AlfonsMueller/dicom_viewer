package at.mueller.alfons;

import at.mueller.alfons.dummies.DummyStudyRepository;
import at.mueller.alfons.gui.Mainwindow;
import at.mueller.alfons.persistence.IStudyRepository;
import at.mueller.alfons.persistence.PatientRepositoryJDBC;
import at.mueller.alfons.persistence.StudyRepositoryJDBC;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.PublicKey;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class App {
    public static void main( String[] args ) throws Exception {
/*
        File file = new File("images/SOUS - 702/IM-0001-0001.dcm");
        DicomInputStream input = new DicomInputStream(file);
        DicomObject dcm = input.readDicomObject();
        String name = dcm.getString(Tag.PatientName);
        String geburtstag = dcm.getString(Tag.PatientBirthDate);
        String patientID = dcm.getString(Tag.PatientID);
        String studieID = dcm.getString(Tag.StudyID);
        String serieID = dcm.getString(Tag.ClinicalTrialSeriesID);
        String instanzID = dcm.getString(Tag.CompoundGraphicInstanceID);
        String reihenRES = dcm.getString(Tag.Rows);
        String spaltenRES = dcm.getString(Tag.Columns);
        String modalität = dcm.getString(Tag.Modality);

        System.out.println(name);
        System.out.println(geburtstag);
        System.out.println(patientID);
        System.out.println(studieID);
        System.out.println(serieID);
        System.out.println(instanzID);
        System.out.println(modalität);
        System.out.println(spaltenRES + " x " + reihenRES);

        IStudyRepository  repository = new StudyRepositoryJDBC();
        File file = new File("images/SOUS - 702/IM-0001-0001.dcm");
        DicomInputStream input = new DicomInputStream(file);
        DicomObject dcm = input.readDicomObject();
        Study study = new Study(dcm);
        repository.save(study); //save Study in Database


        for (Study s:repository.allStudies()) {
            System.out.println(s);
        }

        PatientRepositoryJDBC patientRepository = new PatientRepositoryJDBC();
        Patient patient = new Patient(dcm);
        patientRepository.save(patient);

        for (Patient p:patientRepository.allPatients()) {
            System.out.println(p);
        }*/
        Mainwindow m = new Mainwindow();
        m.setSize(800,600);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setVisible(true);



     /*   BufferedImage img = new BufferedImage(2000,2000,TYPE_INT_RGB);
        for (int z=100;z<1900;z++){
            for(int s=0;s<2000;s++){
                if(s>850&&s<1150) {
                    img.setRGB(s,z,0xb20e0e);
                }
            }
            if(z>1050&&z<1350){
                for(int s=300;s<1700;s++){
                        img.setRGB(s,z,0xb20e0e);
                }
            }
        }
        ImageIO.write(img,"JPEG",new File("image.jpg"));*/
    }
    }
