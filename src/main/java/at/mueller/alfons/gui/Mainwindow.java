package at.mueller.alfons.gui;

import at.mueller.alfons.ImagePanel;
import at.mueller.alfons.Patient;
import javafx.scene.control.SplitPane;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.io.DicomInputStream;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;

public class Mainwindow extends JFrame{
    JScrollPane browserContainer = new JScrollPane();
    JScrollPane infoContainer = new JScrollPane();
    JScrollPane controlContainer = new JScrollPane();
    JPanel imageContainer = new JPanel();
    JLabel statusBar = new JLabel();
    private PatientBrowser browser = new PatientBrowser();

    public Mainwindow(){
        initFrame();
        initMenu();
    }

    public void initFrame(){
        this.setTitle("Viewer");
        this.setLayout(new BorderLayout());
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        split.setDividerLocation(150); //Startposition des Splitters
        this.add(split, BorderLayout.CENTER);
        JPanel left = new JPanel(new GridLayout(0,1));
        split.add(left, JSplitPane.LEFT);
        left.add(browserContainer);
        left.add(infoContainer);
        left.add(controlContainer);
        split.add(imageContainer, JSplitPane.RIGHT);
        this.add(statusBar, BorderLayout.SOUTH);

        browserContainer.setBorder(new TitledBorder("Patients"));
        infoContainer.setBorder(new TitledBorder("Info"));
        controlContainer.setBorder(new TitledBorder("Contrast"));
        imageContainer.setBorder(new TitledBorder("Image"));
        imageContainer.setLayout(new BorderLayout());
        imageContainer.add(new ImagePanel());

        try{
            File dicom = new File("images\\SOUS - 702\\IM-0001-0001.dcm");
            DicomInputStream input = new DicomInputStream(dicom);
            DicomObject dcm = input.readDicomObject();
            Patient patient = new Patient(dcm);
            browser.add(patient);
            browserContainer.setViewportView(browser);
        }
        catch(java.io.IOException e){
            System.out.println("Error reading dicom file");
        }
    }

    public void initMenu(){
        JMenuBar mbar = new JMenuBar();
        this.setJMenuBar(mbar);
        JMenu filem = new JMenu("File");
        mbar.add(filem);
        JMenuItem loadDir = new JMenuItem("load Directory");
        filem.add(loadDir);
    }

}
