package project.addmoto.controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import project.addmoto.data.SupplierSummary;
import project.addmoto.datacollections.SupplierSummaryList;
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
    
    private SupplierSummary selectedSupplier = null;
    
    private SupplierDetails[] sDetails;
    
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
        
        addSuppliersToPane();
        
        setListeners();
    }

    @Override
    public void setListeners() {
        sEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(selectedSupplier == null) {
                    showSupplierError();
                } else {
                    JOptionPane.showMessageDialog(view, "Edit is selected " + selectedSupplier.getSupplierID());
                }
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
                if(selectedSupplier == null) {
                    showSupplierError();
                } else {
                    JOptionPane.showMessageDialog(view, "Delete is selected " + selectedSupplier.getSupplierID());
                }
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
                if(selectedSupplier == null) {
                    showSupplierError();
                } else {
                    JOptionPane.showMessageDialog(view, "Manage is selected " + selectedSupplier.getSupplierID());
                }
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
                if(selectedSupplier == null) {
                    showSupplierError();
                } else {
                    JOptionPane.showMessageDialog(view, "Products is selected " + selectedSupplier.getSupplierID());
                }
            }
            
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setRedForeground(sProducts);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBlackForeground(sProducts);
            }
        });
    }
    
    private void addSuppliersToPane() {
        JPanel supplierPanel = new JPanel();
        supplierPanel.setSize(770, 384);
        supplierPanel.setLayout(new GridLayout(0, 2));
        
        SupplierSummaryList sList = model.getSuppliersWithContacts();
        sDetails = new SupplierDetails[sList.size()];
        
        for(int i = 0; i < sList.size(); i++) {
            sDetails[i] = new SupplierDetails();
        }
        
        int ctr = 0;
        for(SupplierSummary sSummary : sList) {
            SupplierDetails sDetail = sDetails[ctr++];
            sDetail.getsSupplierCompanyName().setText(sSummary.getSupplierName());
            sDetail.getsSupplierContactName().setText(sSummary.getContactName());
            sDetail.getsSupplierPosition().setText(sSummary.getContactPosition());
            sDetail.getsSupplierContactNo().setText(sSummary.getContactNo());
            sDetail.getsSupplierAddress().setText(sSummary.getSupplierAddress());
            sDetail.getsSupplierCity().setText(sSummary.getSupplierCity());
            sDetail.getsSupplierPostal().setText(String.valueOf(sSummary.getSupplierPostal()));
            sDetail.getsSupplierCountry().setText(sSummary.getSupplierCountry());
            sDetail.addMouseListener(new MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    JOptionPane.showMessageDialog(view, String.valueOf(sSummary.getSupplierID()));
                    sCompanyName.setText(sSummary.getSupplierName());
                    sContactName.setText(sSummary.getContactName());
                    sContactTitle.setText(sSummary.getContactPosition());
                    sAddress.setText(sSummary.getSupplierAddress());
                    sCityCountry.setText(sSummary.getSupplierCity() + ", " + sSummary.getSupplierCountry());
                    sContactNo.setText(sSummary.getContactNo());
                    selectedSupplier = sSummary;
                    setOtherToDefault(sDetail, sDetails);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {}
                
                public void mouseExited(java.awt.event.MouseEvent evt) {}
            });
            
            supplierPanel.add(sDetail);
        }
        supplierPane.setViewportView(supplierPanel);
    }
    
    private void setOtherToDefault(SupplierDetails sDetail, SupplierDetails[] sDetails) {
        for(SupplierDetails xDetail : sDetails) {
            if(sDetail.equals(xDetail)) {
                xDetail.setBackground(new Color(153, 255, 255));
            } else {
                xDetail.setBackground(new Color(240, 240, 240));
            }
        }
    }
    
    public void setAllDefault() {
        for(SupplierDetails sDetail : sDetails) {
            sDetail.setBackground(new Color(240, 240, 240));
        }
    }
    
    private void setRedForeground(JLabel label) {
        label.setForeground(Color.RED);
    }
    
    private void setBlackForeground(JLabel label) {
        label.setForeground(Color.BLACK);
    }
    
    private void showSupplierError() {
        JOptionPane.showMessageDialog(view, "No supplier is selected.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void setSelectedSupplier(SupplierSummary selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }
}