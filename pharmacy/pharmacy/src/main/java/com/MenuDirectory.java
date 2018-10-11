package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuDirectory extends JFrame{
    static EmployeeForm employeeForm;
    static EducationForm educationForm;
    static ModeReleaseForm modeReleaseForm;
    static PlaceForm placeForm;
    static PostionForm postionForm;
    static UnitForm unitForm;
    static WayApplyForm wayApplyForm;
    static AppMedForm appMedForm;
    static PharmacyForm pharmacyForm;
    static SupplierForm supplierForm;
    static ProducerForm producerForm;
    static PreparationForm preparationForm;
    static RecedeForm recedeForm;

    public MenuDirectory(){
        super("Справочники");

        this.getContentPane().add(rootPanel);
        this.setSize(500, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        buttonEmployee.addActionListener(new EmployeeListener());
        buttonEducation.addActionListener(new EducationFormListener());
        buttonModeRelease.addActionListener(new ModeReleaseFormListener());
        buttonPlaceStorage.addActionListener(new PlaceFormListener());
        buttonUnit.addActionListener(new UnitFormListener());
        buttonWayApply.addActionListener(new WayApplyFormListener());
        buttonPostion.addActionListener(new PostionFormListener());
        buttonAppMed.addActionListener(new AppMedListener());
        buttonPhermacy.addActionListener(new PharmacyListener());
        buttonSupplier.addActionListener(new SupplierListener());
        buttonProducer.addActionListener(new ProducerListener());
        buttonPreparation.addActionListener(new PreparationListener());
        buttonRecede.addActionListener(new RecedeListener());
    }

    private class RecedeListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(recedeForm == null || !recedeForm.isVisible()) {
                recedeForm = null;
                recedeForm = new RecedeForm();
                recedeForm.setVisible(true);
            }
        }
    }

    private class PreparationListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(preparationForm == null || !preparationForm.isVisible()) {
                preparationForm = null;
                preparationForm = new PreparationForm();
                preparationForm.setVisible(true);
            }
        }
    }

    private class ProducerListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(producerForm == null || !producerForm.isVisible()) {
                producerForm = null;
                producerForm = new ProducerForm();
                producerForm.setVisible(true);
            }
        }
    }

    private class SupplierListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(supplierForm == null || !supplierForm.isVisible()) {
                supplierForm = null;
                supplierForm = new SupplierForm();
                supplierForm.setVisible(true);
            }
        }
    }

    private class PharmacyListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(pharmacyForm == null || !pharmacyForm.isVisible()) {
                pharmacyForm = null;
                pharmacyForm = new PharmacyForm();
                pharmacyForm.setVisible(true);
            }
        }
    }

    private class AppMedListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(appMedForm == null || !appMedForm.isVisible()) {
                appMedForm = null;
                appMedForm = new AppMedForm();
                appMedForm.setVisible(true);
            }
        }
    }

    private class EmployeeListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(employeeForm == null || !employeeForm.isVisible()) {
                employeeForm = null;
                employeeForm = new EmployeeForm();
                employeeForm.setVisible(true);
            }
        }
    }

    private class EducationFormListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(educationForm == null || !educationForm.isVisible()) {
                educationForm = null;
                educationForm = new EducationForm();
                educationForm.setVisible(true);
            }
        }
    }

    private class ModeReleaseFormListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(modeReleaseForm == null || !modeReleaseForm.isVisible()) {
                modeReleaseForm = null;
                modeReleaseForm = new ModeReleaseForm();
                modeReleaseForm.setVisible(true);
            }
        }
    }

    private class PlaceFormListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(placeForm == null || !placeForm.isVisible()) {
                placeForm = null;
                placeForm = new PlaceForm();
                placeForm.setVisible(true);
            }
        }
    }

    private class UnitFormListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(unitForm == null || !unitForm.isVisible()) {
                unitForm = null;
                unitForm = new UnitForm();
                unitForm.setVisible(true);
            }
        }
    }

    private class WayApplyFormListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(wayApplyForm == null || !wayApplyForm.isVisible()) {
                wayApplyForm = null;
                wayApplyForm = new WayApplyForm();
                wayApplyForm.setVisible(true);
            }
        }
    }

    private class PostionFormListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(postionForm == null || !postionForm.isVisible()) {
                postionForm = null;
                postionForm = new PostionForm();
                postionForm.setVisible(true);
            }
        }
    }

    private JButton buttonEmployee;
    private JButton buttonSupplier;
    private JButton buttonAppMed;
    private JButton buttonPostion;
    private JButton buttonPhermacy;
    private JButton buttonProducer;
    private JButton buttonPreparation;
    private JButton buttonUnit;
    private JButton buttonPlaceStorage;
    private JButton buttonModeRelease;
    private JButton buttonEducation;
    private JButton buttonRecede;
    private JButton buttonWayApply;
    private JPanel rootPanel;
}
