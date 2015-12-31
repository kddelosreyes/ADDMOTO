package project.addmoto.controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    
    private final App view;
    private final SupplierModel model;
    
    private final JScrollPane supplierPane;
    private final JLabel sCompanyName;
    private final JLabel sContactName;
    private final JLabel sContactTitle;
    private final JLabel sAddress;
    private final JLabel sCityCountry;
    private final JLabel sContactNo;
    private final JLabel sEdit;
    private final JLabel sDelete;
    private final JLabel sManageContacts;
    private final JLabel sProducts;
    
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
        sEdit.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(selectedSupplier == null) {
                    showSupplierError();
                } else {
                    JOptionPane.showMessageDialog(view, "Edit is selected " + selectedSupplier.getSupplierID());
                }
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setRedForeground(sEdit);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBlackForeground(sEdit);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        
        sDelete.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(selectedSupplier == null) {
                    showSupplierError();
                } else {
                    JOptionPane.showMessageDialog(view, "Delete is selected " + selectedSupplier.getSupplierID());
                }
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setRedForeground(sDelete);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBlackForeground(sDelete);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        
        sManageContacts.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(selectedSupplier == null) {
                    showSupplierError();
                } else {
                    JOptionPane.showMessageDialog(view, "Manage is selected " + selectedSupplier.getSupplierID());
                }
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setRedForeground(sManageContacts);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBlackForeground(sManageContacts);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        
        sProducts.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(selectedSupplier == null) {
                    showSupplierError();
                } else {
                    JOptionPane.showMessageDialog(view, "Products is selected " + selectedSupplier.getSupplierID());
                }
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setRedForeground(sProducts);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBlackForeground(sProducts);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
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
            sDetail.addMouseListener(new MouseListener() {
                
                @Override
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
                
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {}
                
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {}
                
                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}
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