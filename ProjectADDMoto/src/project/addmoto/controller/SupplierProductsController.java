/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXSearchField;
import project.addmoto.custom.LabelRenderer;
import project.addmoto.custom.SupplierProductsTableModel;
import project.addmoto.data.ColorString;
import project.addmoto.data.SupplierProduct;
import project.addmoto.model.SupplierProductsModel;
import project.addmoto.mvc.Controller;
import project.addmoto.view.App;
import project.addmoto.view.SupplierProducts;

/**
 *
 * @author Kim Howel delos Reyes
 */
public final class SupplierProductsController extends Controller {

    private final App view;
    private final SupplierProductsModel supplierProductsModel;
    private SupplierProducts supplierProducts;

    private JLabel spTitle;
    private JTable spProductsTable;
    private JXSearchField spSearch;
    private DefaultTableModel tableModel;
    private ListSelectionModel selectionModel;

    private final int supplierID;

    private final ArrayList<SupplierProduct> supplierProductsList;

    public SupplierProductsController(final App view, final Connection connection, final int supplierID) {
        this.view = view;
        this.supplierID = supplierID;
        supplierProductsModel = new SupplierProductsModel(connection);
        supplierProductsList = supplierProductsModel.getSupplierProducts(this.supplierID);

        if (supplierProductsList.isEmpty()) {
            JOptionPane.showMessageDialog(
                    view,
                    "Supplier does not have any products listed on your database.",
                    "Notice",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            proceedToFunction();
        }
    }

    @Override
    public void setListeners() {
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    return;
                }
                List<SupplierProduct> productWithCode = supplierProductsList.stream()
                        .filter((p) -> p.getAddmotoCode().equals(spProductsTable.getValueAt(spProductsTable.getSelectedRow(), 1)))
                        .collect(Collectors.toList());
                
                JOptionPane.showMessageDialog(view, productWithCode.get(0).toString());
            }
        });
    }

    private void proceedToFunction() {
        supplierProducts = new SupplierProducts();

        spTitle = supplierProducts.getSupplierProductsTitle();
        spProductsTable = supplierProducts.getSupplierProductsTable();
        spProductsTable.setModel(new SupplierProductsTableModel(supplierProductsList));
        for(int i = 0; i < 8; i++) {
            switch(i) {
                case 0:
                case 1:
                    spProductsTable.getColumnModel().getColumn(i).setPreferredWidth(90);
                    break;
                case 2:
                    spProductsTable.getColumnModel().getColumn(i).setPreferredWidth(140);
                    break;
                case 3:
                    spProductsTable.getColumnModel().getColumn(i).setPreferredWidth(350);
                    break;
                case 4:
                    spProductsTable.getColumnModel().getColumn(i).setPreferredWidth(50);
                    break;
                case 5:
                case 6:
                    spProductsTable.getColumnModel().getColumn(i).setPreferredWidth(90);
                    break;
                case 7:
                    spProductsTable.getColumnModel().getColumn(i).setPreferredWidth(60);
                    break;
            }
        }
        spSearch = supplierProducts.getSearchField();
        selectionModel = (ListSelectionModel) spProductsTable.getSelectionModel();
        spProductsTable.setDefaultRenderer(ColorString.class, new LabelRenderer(true));

        setListeners();
        
        Object[] buttons = {"Close"};

        JOptionPane.showOptionDialog(
                view,
                supplierProducts,
                "ADD Moto - Motorcycle Parts and Accessories",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                buttons,
                buttons[0]
        );
    }
}
