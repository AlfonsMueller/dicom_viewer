package at.mueller.alfons.gui;

import at.mueller.alfons.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class PatientBrowser extends JTree {
    private DefaultMutableTreeNode rootNode;
    private DefaultTreeModel model;

    public PatientBrowser(){
        rootNode = new DefaultMutableTreeNode();
        model = new DefaultTreeModel(rootNode);
        this.setModel(model);
        this.setCellRenderer(new Renderer());
    }


    public void add(Patient p){
        // patient is the "user object" of this node
        DefaultMutableTreeNode patientNode
                = new DefaultMutableTreeNode(p);
        rootNode.add(patientNode);
        for (Study st : p.getStudyList()){
            // study is "user object" of this node
            DefaultMutableTreeNode studyNode
                    = new DefaultMutableTreeNode(st);
            patientNode.add(studyNode);
            // go on with series ...
            for(Series sr : st.getSeriesList()){
                DefaultMutableTreeNode seriesNode
                        = new DefaultMutableTreeNode(sr);
                studyNode.add(seriesNode);
                for(Instance in : sr.getInstanceList()){
                    DefaultMutableTreeNode instanceNode
                            = new DefaultMutableTreeNode(in);
                    seriesNode.add(instanceNode);
                }
            }
        }
        model.reload();
    }

    private static class Renderer extends DefaultTreeCellRenderer{
        @Override
        public Component getTreeCellRendererComponent(
                JTree tree, Object value, boolean sel,
                boolean expanded, boolean leaf, int row,
                boolean hasFocus) {
            System.out.println(value);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            Object userObject = node.getUserObject();
            String display = "...";
            if (userObject instanceof Patient){
                Patient p = (Patient)userObject;
                display = p.getPatientID() + " " + p.getName();
            }
            if (userObject instanceof Study){
                Study s = (Study)userObject;
                display = s.getStudyDate().toString();
            }
            if(userObject instanceof Series){
                Series s = (Series)userObject;
                display = s.getSeriesInstanceUID().toString();
            }


            return super.getTreeCellRendererComponent(tree, display, sel, expanded, leaf, row, hasFocus);
        }
    }

}
