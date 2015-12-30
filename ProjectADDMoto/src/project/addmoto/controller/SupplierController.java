package project.addmoto.controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import project.addmoto.model.SupplierModel;
import project.addmoto.mvc.Controller;
import project.addmoto.view.App;
import project.addmoto.view.SupplierDetails;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class SupplierController extends Controller {
    
    private App view;
    private SupplierModel model;
    
    private JScrollPane supplierPane;
    private JLabel sCompanyName;
    private JLabel sContactName;
    private JLabel sContactTitle;
    private JLabel sAddress;
    private JLabel sCityCountry;
    private JLabel sContactNo;
    private JLabel sEdit;
    private JLabel sDelete;
    private JLabel sManageContacts;
    private JLabel sProducts;
    
    public SupplierController(App view, final Connection connection) {
        this.view = view;
        this.model = new SupplierModel(connection);
        
        sCompanyName = view.getsCompanyName();
        sContactName = view.getsContactName();
        sContactTitle = view.getsContactTitle();
        sAddress = view.getsAddress();
        sCityCountry = view.getsCityCountry();
        sContactNo = view.getsContactNo();
        sEdit = view.getsEdit();
        sDelete = view.getsDelete();
        sManageContacts = view.getsManageContacts();
        sProducts = view.getsProducts();
        supplierPane = view.getsSupplierPane();
        JPanel supplierPanel = new JPanel();
        supplierPanel.setSize(770, 384);
        supplierPanel.setLayout(new GridLayout(0, 2));
        for(int i = 0; i < 10; i++) {
            SupplierDetails sDetails = new SupplierDetails();
            sDetails.getSupplierName().setText(String.valueOf(i));
            supplierPanel.add(sDetails);
        }
        supplierPane.setViewportView(supplierPanel);
        
        setListeners();
    }

    @Override
    public void setListeners() {
        sEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setRedForeground(sEdit);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBlackForeground(sEdit);
            }
        });
        
        sDelete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setRedForeground(sDelete);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBlackForeground(sDelete);
            }
        });
        
        sManageContacts.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setRedForeground(sManageContacts);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBlackForeground(sManageContacts);
            }
        });
        
        sProducts.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setRedForeground(sProducts);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBlackForeground(sProducts);
            }
        });
    }
    
    private void setRedForeground(JLabel label) {
        label.setForeground(Color.RED);
    }
    
    private void setBlackForeground(JLabel label) {
        label.setForeground(Color.BLACK);
    }
}