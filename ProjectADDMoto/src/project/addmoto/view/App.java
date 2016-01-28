/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.view;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTable;
import project.addmoto.controller.DashboardController;
import project.addmoto.controller.ExpenseController;
import project.addmoto.controller.InventoryController;
import project.addmoto.controller.POSController;
import project.addmoto.controller.PurchaseOrderController;
import project.addmoto.controller.SupplierController;
import project.addmoto.data.SellerAccount;
import project.addmoto.database.Database;
import project.addmoto.utilities.TimerUtilities;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class App extends javax.swing.JFrame {

    private JFrame parent;
    private SellerAccount sellerAccount;
    
    private DashboardController dashboardController;
    private POSController posController;
    private InventoryController inventoryController;
    private SupplierController supplierController;
    private PurchaseOrderController purchaseOrderController;
    private ExpenseController expenseController;
    
    private final Connection connection;
    
    /**
     * Creates new form UI_Dashboard
     */
    public App(JFrame parent, SellerAccount sellerAccount) {
        this.parent = parent;
        this.sellerAccount = sellerAccount;
        initComponents();
        setLocationRelativeTo(null);
        TimerUtilities.runTime(UIDashboard_dateTimeLabel);
        
        String userType = sellerAccount.getUserType();
        if(userType.equals("ReportManager")) {
            tabbedPane.setEnabledAt(1, false);
            tabbedPane.setEnabledAt(2, false);
            tabbedPane.setEnabledAt(3, false);
            tabbedPane.setEnabledAt(4, false);
            tabbedPane.setEnabledAt(5, false);
            tabbedPane.setEnabledAt(6, false);
            tabbedPane.setEnabledAt(8, false);
        } else if(userType.equals("InventoryManager")) {
            tabbedPane.setEnabledAt(1, false);
            tabbedPane.setEnabledAt(5, false);
            tabbedPane.setEnabledAt(6, false);
            tabbedPane.setEnabledAt(7, false);
            tabbedPane.setEnabledAt(8, false);
        } else if(userType.equals("StoreSeller")) {
            tabbedPane.setEnabledAt(2, false);
            tabbedPane.setEnabledAt(3, false);
            tabbedPane.setEnabledAt(4, false);
            tabbedPane.setEnabledAt(8, false);
        }
        
        connection = new Database().getConnection();
        
        dashboardController = new DashboardController(this, connection);
        posController = new POSController(this, connection);
        inventoryController = new InventoryController(this, connection);
        supplierController = new SupplierController(this, connection);
        purchaseOrderController = new PurchaseOrderController(this, connection, sellerAccount);
        expenseController = new ExpenseController(this, connection, sellerAccount);
        
        appSellerName.setText("Hi, " + sellerAccount.getFirstName() + " " + sellerAccount.getLastName());
        appLogout.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int choice = JOptionPane.showConfirmDialog(App.this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.OK_CANCEL_OPTION);
                if(choice == JOptionPane.OK_OPTION) {
                    parent.setVisible(true);
                    App.this.dispose();
                }
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                appLogout.setForeground(Color.RED);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                appLogout.setForeground(Color.BLACK);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane.getSelectedIndex();
                System.out.println("Index: " + index);
                switch(index) {
                    case 0: // Dashboard
                        clearPOS();
                        clearSupplier();
                        dashboardController.setDefaultViews();
                        clearInventory();
                        break;
                    case 1: // Point Of Sale
                        clearDashboard();
                        clearSupplier();
                        clearInventory();
                        break;
                    case 2: // Inventory
                        clearDashboard();
                        clearPOS();
                        clearSupplier();
                        break;
                    case 3: // Purchase Order
                        clearDashboard();
                        clearPOS();
                        clearSupplier();
                        clearInventory();
                        break;
                    case 4: // Supplier
                        clearDashboard();
                        clearPOS();
                        clearInventory();
                        break;
                    case 5: // Expenses
                        clearDashboard();
                        clearPOS();
                        clearInventory();
                        clearSupplier();
                        break;
                    case 6: // Returns
                        clearDashboard();
                        clearPOS();
                        clearSupplier();
                        clearInventory();
                        break;
                    case 7: // Reports
                        clearDashboard();
                        clearPOS();
                        clearSupplier();
                        clearInventory();
                        break;
                    case 8: // Accounts
                        clearDashboard();
                        clearPOS();
                        clearSupplier();
                        clearInventory();
                        break;
                    default:
                        JOptionPane.showMessageDialog(App.this, "Something is wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
                        clearDashboard();
                        clearPOS();
                        clearSupplier();
                        clearInventory();
                }
            }
        });
    }
    
    private void clearPurchaseOrder() {
        
    }
    
    private void clearDashboard() {
        
    }
    
    private void clearInventory() {
        inventoryController.setIndexZero();
        inventoryController.setDefaultViews();
        inventoryController.isAddingRows = true;
        inventoryController.populate();
        inventoryController.isAddingRows = false;
    }
    
    private void clearPOS() {
        posController.populate();
        posSubTotal.setText("PhP   0.00");
        posTax.setText("PhP   0.00");
        posTotal.setText("PhP   0.00");
        posAmountDue.setText("PhP   0.00");
        posVoid.setEnabled(false);
        posPay.setEnabled(false);
        posAddProduct.setText("");
        posController.getSalesItemsList().clear();
        posController.setSelectedRow(-1);
        posController.setSelectedProduct(null);
        productCode.setText("-----");
        productLine.setText("-----");
        description.setText("-----");
        quantityOnHand.setText("NA");
        sellingPrice.setText("NA");
        addItem.setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) posItemsTable.getModel();
        while( model.getRowCount() > 0 ){
            model.removeRow(0);
        }
    }
    
    private void clearSupplier() {
        supplierController.setSelectedSupplier(null);
        supplierController.setAllDefault();
        sCompanyName.setText(" ");
        sContactName.setText(" ");
        sContactNo.setText(" ");
        sContactTitle.setText(" ");
        sAddress.setText(" ");
        sCityCountry.setText(" ");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        poPopup = new javax.swing.JPopupMenu();
        poTogglePaid = new javax.swing.JMenuItem();
        poViewDetails = new javax.swing.JMenuItem();
        poReceiveProducts = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        UIDashboard_dateTimeLabel = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        appSellerName = new javax.swing.JLabel();
        appLogout = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
        dashboardPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        dFilterDateType = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        dFromDate = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        dToDate = new com.toedter.calendar.JDateChooser();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        dBestMonth = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel52 = new javax.swing.JLabel();
        dHighestMonthlySales = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        dTopSelling = new javax.swing.JTable();
        dGoFilter = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        dTotalOrdersAmount = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        dTotalGoodCost = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        dNumberTransactions = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        dAverageTransaction = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jXMonthView1 = new org.jdesktop.swingx.JXMonthView();
        pointOfSalePanel = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        posItemsTable = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        posSubTotal = new javax.swing.JLabel();
        posTax = new javax.swing.JLabel();
        posTotal = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        posAmountDue = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        posPay = new javax.swing.JButton();
        posVoid = new javax.swing.JButton();
        posClear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        posAddProduct = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        productCode = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        productLine = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        quantityOnHand = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        sellingPrice = new javax.swing.JLabel();
        addItem = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        posProducts = new javax.swing.JTable();
        inventoryPanel = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jXImageView1 = new org.jdesktop.swingx.JXImageView();
        jScrollPane4 = new javax.swing.JScrollPane();
        iProductName = new javax.swing.JTextArea();
        jLabel55 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        iShowHistory = new javax.swing.JLabel();
        iProductLine = new javax.swing.JLabel();
        iItemDescription = new javax.swing.JLabel();
        iOnHandQuantity = new javax.swing.JLabel();
        iSellingPrice = new javax.swing.JTextField();
        iOnHandQuantity1 = new javax.swing.JLabel();
        iOnHandQuantity2 = new javax.swing.JLabel();
        iEditUpdateRSP = new javax.swing.JLabel();
        iCancelRSP = new javax.swing.JLabel();
        iEditUpdateUC = new javax.swing.JLabel();
        iCancelUC = new javax.swing.JLabel();
        iEditUpdateQT = new javax.swing.JLabel();
        iCancelQT = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        iAddMotoCode = new javax.swing.JLabel();
        iUnitCost = new javax.swing.JTextField();
        iThreshold = new javax.swing.JTextField();
        iStatus = new javax.swing.JLabel();
        iSupplier = new javax.swing.JLabel();
        iItemNo = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        iProductsTable = new org.jdesktop.swingx.JXTable();
        iSearchField = new org.jdesktop.swingx.JXSearchField();
        jLabel54 = new javax.swing.JLabel();
        iAddNew = new javax.swing.JButton();
        iAddNewPLine = new javax.swing.JButton();
        purchasePanel = new javax.swing.JPanel();
        poCreate = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        poTable = new javax.swing.JTable();
        poSearch = new org.jdesktop.swingx.JXSearchField();
        jLabel57 = new javax.swing.JLabel();
        suppliersPanel = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        sSupplierPane = new javax.swing.JScrollPane();
        newSupplier = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        sCompanyName = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        sContactName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        sContactTitle = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        sAddress = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        sCityCountry = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        sContactNo = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        sEdit = new javax.swing.JLabel();
        sDelete = new javax.swing.JLabel();
        sManageContacts = new javax.swing.JLabel();
        sProducts = new javax.swing.JLabel();
        expensesPanel = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        eMonthlyTable = new javax.swing.JTable();
        eMonthlyTotal = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        eTodayTotal = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        eTodayTable = new javax.swing.JTable();
        jPanel36 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        eExpensePanel = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        eAverageMonthly = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        eHighestMonthly = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        eLowestMonthly = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        eHighestLabel = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        eLowestLabel = new javax.swing.JLabel();
        eAdd = new javax.swing.JButton();
        returnsPanel = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        eMonthlyTable1 = new javax.swing.JTable();
        eMonthlyTotal1 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        eMonthlyTable2 = new javax.swing.JTable();
        jXSearchField2 = new org.jdesktop.swingx.JXSearchField();
        eAdd1 = new javax.swing.JButton();
        reportsPanel = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        accountsPanel = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        poCreate1 = new javax.swing.JButton();
        jPanel40 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();

        poTogglePaid.setText("Toggle Paid");
        poPopup.add(poTogglePaid);

        poViewDetails.setText("View Details");
        poPopup.add(poViewDetails);

        poReceiveProducts.setText("Receive Products");
        poPopup.add(poReceiveProducts);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ADD Moto - Motorcycle Parts and Accessories");
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(1100, 650));
        setSize(new java.awt.Dimension(1120, 650));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setMaximumSize(new java.awt.Dimension(1084, 80));
        jPanel1.setMinimumSize(new java.awt.Dimension(1084, 80));
        jPanel1.setPreferredSize(new java.awt.Dimension(1084, 80));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("manager");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 34)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ADD MOTO");

        UIDashboard_dateTimeLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        UIDashboard_dateTimeLabel.setForeground(new java.awt.Color(0, 102, 102));
        UIDashboard_dateTimeLabel.setText("DD MMM YYYY, HH:MM AM/PM");

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        appSellerName.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        appSellerName.setText("jLabel62");

        appLogout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        appLogout.setText("Logout");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UIDashboard_dateTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(appSellerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(appLogout)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator7)
                        .addComponent(UIDashboard_dateTimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(appSellerName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );

        tabbedPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tabbedPane.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Period:");

        dFilterDateType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dFilterDateType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Today", "This Week", "Last Week", "This Month", "Last Month", "Custom" }));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("From:");

        dFromDate.setEnabled(false);
        dFromDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("To:");

        dToDate.setEnabled(false);
        dToDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel15.setBackground(new java.awt.Color(0, 102, 153));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("   Key Indicators");

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/info44.png"))); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Indicator", "Period", "Current", "Previous"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowHeight(21);
        jTable1.setRowSelectionAllowed(false);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(100);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(2).setMinWidth(105);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(105);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(105);
            jTable1.getColumnModel().getColumn(3).setMinWidth(105);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(105);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(105);
        }

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText("Transactions");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText("Sales");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel46.setText("Expenses");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 153, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("2290.23%");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 153, 0));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("2290.23%");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 153, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("2290.23%");

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel49.setText("Best Month");

        dBestMonth.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        dBestMonth.setText("November, 2015");

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel52.setText("Highest Monthly Sales:");

        dHighestMonthlySales.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        dHighestMonthlySales.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dHighestMonthlySales.setText("Php 1,000,000.00");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel40)
                .addGap(120, 120, 120)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addGap(65, 65, 65))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(dBestMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dHighestMonthlySales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel52)))))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dHighestMonthlySales)
                    .addComponent(dBestMonth))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel17.setBackground(new java.awt.Color(0, 102, 153));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("   Fast Moving Products");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/black403.png"))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("These products are making maximum money");
        jLabel22.setToolTipText("These products are making maximum money");

        dTopSelling.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        dTopSelling.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dTopSelling.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dTopSelling.setGridColor(new java.awt.Color(204, 204, 204));
        dTopSelling.setRowHeight(22);
        dTopSelling.setRowMargin(3);
        dTopSelling.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        dTopSelling.getTableHeader().setResizingAllowed(false);
        dTopSelling.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(dTopSelling);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addGap(253, 253, 253))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        dGoFilter.setBackground(new java.awt.Color(0, 255, 102));
        dGoFilter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dGoFilter.setMnemonic('G');
        dGoFilter.setText("Go");
        dGoFilter.setEnabled(false);

        jPanel12.setLayout(new java.awt.GridLayout(1, 4, 0, 4));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setMaximumSize(new java.awt.Dimension(270, 100));
        jPanel6.setMinimumSize(new java.awt.Dimension(270, 100));
        jPanel6.setPreferredSize(new java.awt.Dimension(270, 100));
        jPanel6.setRequestFocusEnabled(false);

        jLabel23.setBackground(new java.awt.Color(0, 204, 153));
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/bar26.png"))); // NOI18N
        jLabel23.setOpaque(true);

        jPanel18.setBackground(new java.awt.Color(0, 204, 153));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jLabel27.setText("Total sales");

        dTotalOrdersAmount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        dTotalOrdersAmount.setText("P 999,999.99");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(dTotalOrdersAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(dTotalOrdersAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addGap(5, 5, 5))
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel12.add(jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setMaximumSize(new java.awt.Dimension(270, 100));
        jPanel7.setMinimumSize(new java.awt.Dimension(270, 100));
        jPanel7.setPreferredSize(new java.awt.Dimension(270, 100));

        jPanel19.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jLabel24.setBackground(new java.awt.Color(255, 51, 51));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/shopping156.png"))); // NOI18N
        jLabel24.setOpaque(true);

        jLabel28.setText("Total unit cost of products sold");

        dTotalGoodCost.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        dTotalGoodCost.setText("P 999,999.99");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(dTotalGoodCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(dTotalGoodCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addGap(5, 5, 5))
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel12.add(jPanel7);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setMaximumSize(new java.awt.Dimension(270, 100));
        jPanel8.setMinimumSize(new java.awt.Dimension(270, 100));
        jPanel8.setPreferredSize(new java.awt.Dimension(270, 100));

        jPanel20.setBackground(new java.awt.Color(0, 102, 153));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jLabel25.setBackground(new java.awt.Color(0, 102, 153));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/cart13.png"))); // NOI18N
        jLabel25.setOpaque(true);

        jLabel29.setText("Number of transactions");

        dNumberTransactions.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        dNumberTransactions.setText("999,999");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(dNumberTransactions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(dNumberTransactions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addGap(5, 5, 5)))
                .addContainerGap())
        );

        jPanel12.add(jPanel8);

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setMaximumSize(new java.awt.Dimension(270, 100));
        jPanel11.setMinimumSize(new java.awt.Dimension(270, 100));
        jPanel11.setPreferredSize(new java.awt.Dimension(270, 100));

        jPanel21.setBackground(new java.awt.Color(255, 204, 0));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jLabel26.setBackground(new java.awt.Color(255, 204, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/bars42.png"))); // NOI18N
        jLabel26.setOpaque(true);

        jLabel30.setText("Average transaction amount");

        dAverageTransaction.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        dAverageTransaction.setText("P 999,999.99");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(dAverageTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(dAverageTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addGap(5, 5, 5)))
                .addContainerGap())
        );

        jPanel12.add(jPanel11);

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel53.setBackground(new java.awt.Color(0, 102, 153));
        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("   Reminders");
        jLabel53.setOpaque(true);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jXMonthView1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout dashboardPanelLayout = new javax.swing.GroupLayout(dashboardPanel);
        dashboardPanel.setLayout(dashboardPanelLayout);
        dashboardPanelLayout.setHorizontalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 1142, Short.MAX_VALUE)
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dFilterDateType, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dGoFilter)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jXMonthView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        dashboardPanelLayout.setVerticalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dFilterDateType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dFromDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(dToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dGoFilter)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addComponent(jXMonthView1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabbedPane.addTab("Dashboard", new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/tab_dashboard.png")), dashboardPanel); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(153, 153, 153)));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        posItemsTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        posItemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Item Name", "Qty.", "Selling Price", "Ext Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        posItemsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        posItemsTable.setRowHeight(25);
        posItemsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        posItemsTable.getTableHeader().setResizingAllowed(false);
        posItemsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(posItemsTable);
        if (posItemsTable.getColumnModel().getColumnCount() > 0) {
            posItemsTable.getColumnModel().getColumn(0).setMinWidth(110);
            posItemsTable.getColumnModel().getColumn(0).setPreferredWidth(110);
            posItemsTable.getColumnModel().getColumn(0).setMaxWidth(110);
            posItemsTable.getColumnModel().getColumn(2).setMinWidth(50);
            posItemsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            posItemsTable.getColumnModel().getColumn(2).setMaxWidth(50);
            posItemsTable.getColumnModel().getColumn(3).setMinWidth(100);
            posItemsTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            posItemsTable.getColumnModel().getColumn(3).setMaxWidth(100);
            posItemsTable.getColumnModel().getColumn(4).setMinWidth(100);
            posItemsTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            posItemsTable.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Sub-total");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tax (12%)");

        posSubTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        posSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        posSubTotal.setText("PhP   0.00");

        posTax.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        posTax.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        posTax.setText("PhP   0.00");

        posTotal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        posTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        posTotal.setText("PhP   0.00");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setText("Total");

        posAmountDue.setFont(new java.awt.Font("Tahoma", 1, 38)); // NOI18N
        posAmountDue.setForeground(new java.awt.Color(204, 0, 0));
        posAmountDue.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        posAmountDue.setText("PhP   0.00");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Amount Due");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(posAmountDue, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(5, 5, 5)
                .addComponent(posAmountDue))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(posSubTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posTax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(posSubTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(posTax))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(posTotal)
                            .addComponent(jLabel8))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        posPay.setBackground(new java.awt.Color(0, 255, 102));
        posPay.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        posPay.setMnemonic('P');
        posPay.setText("Pay");
        posPay.setEnabled(false);

        posVoid.setBackground(new java.awt.Color(102, 102, 102));
        posVoid.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        posVoid.setForeground(new java.awt.Color(255, 255, 255));
        posVoid.setMnemonic('V');
        posVoid.setText("Void");
        posVoid.setEnabled(false);

        posClear.setBackground(new java.awt.Color(204, 0, 0));
        posClear.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        posClear.setForeground(new java.awt.Color(255, 255, 255));
        posClear.setMnemonic('C');
        posClear.setText("Clear");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(posClear, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(posVoid, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(posPay, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(posClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(posVoid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(posPay))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null));

        posAddProduct.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        posAddProduct.setToolTipText("");

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Item Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        productCode.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        productCode.setText("-----");

        jLabel11.setText("Product Line");

        productLine.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        productLine.setText("-----");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Qty on hand:");

        quantityOnHand.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        quantityOnHand.setText("NA");

        jLabel13.setText("Description");

        description.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        description.setText("-----");
        description.setAutoscrolls(true);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Selling Price:");

        sellingPrice.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        sellingPrice.setText("NA");

        addItem.setBackground(new java.awt.Color(0, 255, 102));
        addItem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addItem.setMnemonic('a');
        addItem.setText("Add");
        addItem.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantityOnHand)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sellingPrice))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(productLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addItem)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(productCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(description)
                    .addComponent(productLine, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(sellingPrice)
                    .addComponent(jLabel12)
                    .addComponent(quantityOnHand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addItem)
                .addContainerGap())
        );

        posProducts.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        posProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Item Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        posProducts.setRowHeight(20);
        posProducts.getTableHeader().setResizingAllowed(false);
        posProducts.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(posProducts);
        if (posProducts.getColumnModel().getColumnCount() > 0) {
            posProducts.getColumnModel().getColumn(0).setMinWidth(110);
            posProducts.getColumnModel().getColumn(0).setPreferredWidth(110);
            posProducts.getColumnModel().getColumn(0).setMaxWidth(110);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(posAddProduct))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(posAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pointOfSalePanelLayout = new javax.swing.GroupLayout(pointOfSalePanel);
        pointOfSalePanel.setLayout(pointOfSalePanelLayout);
        pointOfSalePanelLayout.setHorizontalGroup(
            pointOfSalePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pointOfSalePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pointOfSalePanelLayout.setVerticalGroup(
            pointOfSalePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pointOfSalePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pointOfSalePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Point of Sale", new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/tab_pos.png")), pointOfSalePanel); // NOI18N

        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jXImageView1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jXImageView1Layout = new javax.swing.GroupLayout(jXImageView1);
        jXImageView1.setLayout(jXImageView1Layout);
        jXImageView1Layout.setHorizontalGroup(
            jXImageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );
        jXImageView1Layout.setVerticalGroup(
            jXImageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        iProductName.setEditable(false);
        iProductName.setBackground(new java.awt.Color(240, 240, 240));
        iProductName.setColumns(20);
        iProductName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        iProductName.setLineWrap(true);
        iProductName.setRows(2);
        iProductName.setText("Hello world! Hello world! Hello world! Hello world!");
        iProductName.setWrapStyleWord(true);
        jScrollPane4.setViewportView(iProductName);

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel55.setText("Item No.:");

        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 153, 153));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel60.setText("Product Line: ");

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 153, 153));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel62.setText("Item Description: ");

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 153, 153));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel63.setText("Regular Selling Price: ");

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 153, 153));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel64.setText("Unit Cost: ");

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 153, 153));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel65.setText("On Hand Quantity: ");

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 153, 153));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel66.setText("Supplier: ");

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 153, 153));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel67.setText("Quantity Threshold: ");

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 153, 153));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel68.setText("Status: ");

        jPanel28.setBackground(new java.awt.Color(0, 102, 153));

        jLabel59.setBackground(new java.awt.Color(0, 102, 153));
        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("   Inventory Item Detail");
        jLabel59.setOpaque(true);

        iShowHistory.setForeground(new java.awt.Color(255, 255, 255));
        iShowHistory.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        iShowHistory.setText("Show History");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iShowHistory)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iShowHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        iProductLine.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iProductLine.setText("Product Line");

        iItemDescription.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iItemDescription.setText("Item Description");

        iOnHandQuantity.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iOnHandQuantity.setText("On Hand Quantity");

        iSellingPrice.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iSellingPrice.setText("10");
        iSellingPrice.setEnabled(false);
        iSellingPrice.setOpaque(false);

        iOnHandQuantity1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iOnHandQuantity1.setText("PhP");

        iOnHandQuantity2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iOnHandQuantity2.setText("PhP");

        iEditUpdateRSP.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iEditUpdateRSP.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        iEditUpdateRSP.setText("Edit");
        iEditUpdateRSP.setEnabled(false);

        iCancelRSP.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iCancelRSP.setText("Cancel");
        iCancelRSP.setEnabled(false);

        iEditUpdateUC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iEditUpdateUC.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        iEditUpdateUC.setText("Edit");
        iEditUpdateUC.setEnabled(false);

        iCancelUC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iCancelUC.setText("Cancel");
        iCancelUC.setEnabled(false);

        iEditUpdateQT.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iEditUpdateQT.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        iEditUpdateQT.setText("Edit");
        iEditUpdateQT.setEnabled(false);

        iCancelQT.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iCancelQT.setText("Cancel");
        iCancelQT.setEnabled(false);

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 153, 153));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel61.setText("ADD Moto Code:");

        iAddMotoCode.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iAddMotoCode.setText("ADD Moto Code");

        iUnitCost.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iUnitCost.setText("10");
        iUnitCost.setEnabled(false);
        iUnitCost.setOpaque(false);

        iThreshold.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iThreshold.setText("10");
        iThreshold.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        iThreshold.setEnabled(false);
        iThreshold.setOpaque(false);

        iStatus.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iStatus.setText("Status");
        iStatus.setOpaque(true);

        iSupplier.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        iSupplier.setText("Supplier");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jLabel68, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iStatus)
                            .addComponent(iSupplier)))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel27Layout.createSequentialGroup()
                                        .addComponent(iThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(iEditUpdateQT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(iCancelQT))
                                    .addComponent(iOnHandQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(iOnHandQuantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(iOnHandQuantity2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(iUnitCost, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                    .addComponent(iSellingPrice))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel27Layout.createSequentialGroup()
                                        .addComponent(iEditUpdateRSP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(iCancelRSP))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                        .addComponent(iEditUpdateUC, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(iCancelUC)))))
                        .addContainerGap())
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iProductLine)
                            .addComponent(iAddMotoCode)
                            .addComponent(iItemDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(iAddMotoCode, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(iProductLine))
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(iItemDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(iSellingPrice)
                    .addComponent(iOnHandQuantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iEditUpdateRSP, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iCancelRSP, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(iOnHandQuantity2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iEditUpdateUC, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iCancelUC, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iUnitCost))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(iOnHandQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(iThreshold)
                    .addComponent(iEditUpdateQT, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iCancelQT, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(iStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(iSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        iItemNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        iItemNo.setText("######");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jXImageView1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(iItemNo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iItemNo)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jXImageView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        iProductsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Code", "Product Line", "Description", "Unit Cost", "Selling Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        iProductsTable.setEditable(false);
        iProductsTable.setRowHeight(20);
        iProductsTable.getTableHeader().setResizingAllowed(false);
        iProductsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(iProductsTable);
        if (iProductsTable.getColumnModel().getColumnCount() > 0) {
            iProductsTable.getColumnModel().getColumn(0).setMinWidth(45);
            iProductsTable.getColumnModel().getColumn(0).setPreferredWidth(45);
            iProductsTable.getColumnModel().getColumn(0).setMaxWidth(45);
            iProductsTable.getColumnModel().getColumn(1).setMinWidth(90);
            iProductsTable.getColumnModel().getColumn(1).setPreferredWidth(90);
            iProductsTable.getColumnModel().getColumn(1).setMaxWidth(90);
            iProductsTable.getColumnModel().getColumn(2).setMinWidth(135);
            iProductsTable.getColumnModel().getColumn(2).setPreferredWidth(135);
            iProductsTable.getColumnModel().getColumn(2).setMaxWidth(135);
            iProductsTable.getColumnModel().getColumn(4).setMinWidth(70);
            iProductsTable.getColumnModel().getColumn(4).setPreferredWidth(70);
            iProductsTable.getColumnModel().getColumn(4).setMaxWidth(70);
            iProductsTable.getColumnModel().getColumn(5).setMinWidth(75);
            iProductsTable.getColumnModel().getColumn(5).setPreferredWidth(75);
            iProductsTable.getColumnModel().getColumn(5).setMaxWidth(75);
        }

        iSearchField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        iSearchField.setPrompt("Search Inventory");
        iSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iSearchFieldActionPerformed(evt);
            }
        });

        jLabel54.setBackground(new java.awt.Color(0, 102, 153));
        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/inventory_logo.png"))); // NOI18N
        jLabel54.setText("Inventory");

        iAddNew.setBackground(new java.awt.Color(0, 255, 102));
        iAddNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        iAddNew.setMnemonic('N');
        iAddNew.setText("New Product");

        iAddNewPLine.setBackground(new java.awt.Color(0, 255, 102));
        iAddNewPLine.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        iAddNewPLine.setMnemonic('N');
        iAddNewPLine.setText("New Product Line");

        javax.swing.GroupLayout inventoryPanelLayout = new javax.swing.GroupLayout(inventoryPanel);
        inventoryPanel.setLayout(inventoryPanelLayout);
        inventoryPanelLayout.setHorizontalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inventoryPanelLayout.createSequentialGroup()
                        .addComponent(iSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(inventoryPanelLayout.createSequentialGroup()
                        .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                            .addGroup(inventoryPanelLayout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addGap(0, 589, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(inventoryPanelLayout.createSequentialGroup()
                                .addComponent(iAddNewPLine)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(iAddNew))
                            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        inventoryPanelLayout.setVerticalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(iAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(iAddNewPLine, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(iSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Inventory", new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/tab_inventory.png")), inventoryPanel); // NOI18N

        poCreate.setBackground(new java.awt.Color(0, 255, 102));
        poCreate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        poCreate.setMnemonic('C');
        poCreate.setText("Create New PO");

        poTable.setAutoCreateRowSorter(true);
        poTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        poTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Order No.", "Order Date/Time", "Supplier", "Invoice Total (PhP)", "No. of Items", "Paid", "Status", "Target Receive Date", "Issuer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        poTable.setGridColor(new java.awt.Color(204, 204, 204));
        poTable.setRowHeight(23);
        poTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        poTable.getTableHeader().setResizingAllowed(false);
        poTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(poTable);
        if (poTable.getColumnModel().getColumnCount() > 0) {
            poTable.getColumnModel().getColumn(0).setMinWidth(75);
            poTable.getColumnModel().getColumn(0).setPreferredWidth(75);
            poTable.getColumnModel().getColumn(0).setMaxWidth(75);
            poTable.getColumnModel().getColumn(1).setMinWidth(120);
            poTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            poTable.getColumnModel().getColumn(1).setMaxWidth(120);
            poTable.getColumnModel().getColumn(4).setMinWidth(120);
            poTable.getColumnModel().getColumn(4).setPreferredWidth(120);
            poTable.getColumnModel().getColumn(4).setMaxWidth(120);
            poTable.getColumnModel().getColumn(5).setMinWidth(75);
            poTable.getColumnModel().getColumn(5).setPreferredWidth(75);
            poTable.getColumnModel().getColumn(5).setMaxWidth(75);
            poTable.getColumnModel().getColumn(6).setMinWidth(50);
            poTable.getColumnModel().getColumn(6).setPreferredWidth(50);
            poTable.getColumnModel().getColumn(6).setMaxWidth(50);
            poTable.getColumnModel().getColumn(7).setMinWidth(75);
            poTable.getColumnModel().getColumn(7).setPreferredWidth(75);
            poTable.getColumnModel().getColumn(7).setMaxWidth(75);
            poTable.getColumnModel().getColumn(8).setMinWidth(125);
            poTable.getColumnModel().getColumn(8).setPreferredWidth(125);
            poTable.getColumnModel().getColumn(8).setMaxWidth(125);
            poTable.getColumnModel().getColumn(9).setMinWidth(80);
            poTable.getColumnModel().getColumn(9).setPreferredWidth(80);
            poTable.getColumnModel().getColumn(9).setMaxWidth(80);
        }

        poSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/purchaseorder.png"))); // NOI18N
        jLabel57.setText("Purchase Order");

        javax.swing.GroupLayout purchasePanelLayout = new javax.swing.GroupLayout(purchasePanel);
        purchasePanel.setLayout(purchasePanelLayout);
        purchasePanelLayout.setHorizontalGroup(
            purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1142, Short.MAX_VALUE)
                    .addGroup(purchasePanelLayout.createSequentialGroup()
                        .addComponent(poSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(purchasePanelLayout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(poCreate)))
                .addContainerGap())
        );
        purchasePanelLayout.setVerticalGroup(
            purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57)
                    .addComponent(poCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(poSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Purchase Order", new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/tab_po.png")), purchasePanel); // NOI18N

        jPanel22.setBackground(new java.awt.Color(0, 102, 153));

        jLabel32.setBackground(new java.awt.Color(0, 102, 153));
        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 32)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText(" Suppliers");
        jLabel32.setOpaque(true);

        jLabel31.setBackground(new java.awt.Color(0, 155, 213));
        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("   This is a list of all suppliers.");
        jLabel31.setOpaque(true);

        newSupplier.setBackground(new java.awt.Color(0, 255, 102));
        newSupplier.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        newSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/add40.png"))); // NOI18N
        newSupplier.setText("New Supplier");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
            .addComponent(sSupplierPane)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(newSupplier)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(newSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sSupplierPane, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        sCompanyName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sCompanyName.setText("                                        ");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("Company Name");

        sContactName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sContactName.setText("                                        ");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("Contact Name");

        sContactTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sContactTitle.setText("                                        ");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel35.setText("Contact Title");

        sAddress.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sAddress.setText("                                        ");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("Address");

        sCityCountry.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sCityCountry.setText("                                        ");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setText("City, Country");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator3)
            .addComponent(jSeparator4)
            .addComponent(jSeparator5)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sCompanyName, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addComponent(sContactName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sContactTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sCityCountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sCompanyName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sContactName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sContactTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sAddress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sCityCountry)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addGap(54, 54, 54))
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel38.setBackground(new java.awt.Color(0, 255, 102));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/supplier_contact.png"))); // NOI18N
        jLabel38.setOpaque(true);

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel39.setText("Contact No.:");

        sContactNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sContactNo.setText("                                        ");

        jLabel41.setBackground(new java.awt.Color(0, 255, 102));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/view.png"))); // NOI18N
        jLabel41.setOpaque(true);

        jLabel42.setBackground(new java.awt.Color(0, 255, 102));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/supplier_edit.png"))); // NOI18N
        jLabel42.setOpaque(true);

        jLabel43.setBackground(new java.awt.Color(0, 255, 102));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/supplier_manage_contact.png"))); // NOI18N
        jLabel43.setOpaque(true);

        jLabel44.setBackground(new java.awt.Color(0, 255, 102));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/supplier_products.png"))); // NOI18N
        jLabel44.setOpaque(true);

        sEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sEdit.setText("View Full Details");

        sDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sDelete.setText("Edit");

        sManageContacts.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sManageContacts.setText("Manage Contacts");

        sProducts.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sProducts.setText("Products");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(sContactNo, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sManageContacts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(sContactNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39))
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sManageContacts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(sProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout suppliersPanelLayout = new javax.swing.GroupLayout(suppliersPanel);
        suppliersPanel.setLayout(suppliersPanelLayout);
        suppliersPanelLayout.setHorizontalGroup(
            suppliersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, suppliersPanelLayout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        suppliersPanelLayout.setVerticalGroup(
            suppliersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(suppliersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Suppliers", new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/dash_supplier.png")), suppliersPanel); // NOI18N

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/tab_expenses.png"))); // NOI18N
        jLabel69.setText("Expenses");

        jPanel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel73.setBackground(new java.awt.Color(0, 102, 153));
        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("   This Month's Expenses");
        jLabel73.setOpaque(true);

        eMonthlyTable.setAutoCreateRowSorter(true);
        eMonthlyTable.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        eMonthlyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Reason", "Amount", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eMonthlyTable.setRowHeight(24);
        eMonthlyTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eMonthlyTable.getTableHeader().setResizingAllowed(false);
        eMonthlyTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(eMonthlyTable);
        if (eMonthlyTable.getColumnModel().getColumnCount() > 0) {
            eMonthlyTable.getColumnModel().getColumn(1).setMinWidth(120);
            eMonthlyTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            eMonthlyTable.getColumnModel().getColumn(1).setMaxWidth(120);
            eMonthlyTable.getColumnModel().getColumn(1).setHeaderValue("Reason");
            eMonthlyTable.getColumnModel().getColumn(2).setMinWidth(80);
            eMonthlyTable.getColumnModel().getColumn(2).setPreferredWidth(80);
            eMonthlyTable.getColumnModel().getColumn(2).setMaxWidth(80);
            eMonthlyTable.getColumnModel().getColumn(2).setHeaderValue("Amount");
            eMonthlyTable.getColumnModel().getColumn(3).setMinWidth(0);
            eMonthlyTable.getColumnModel().getColumn(3).setPreferredWidth(0);
            eMonthlyTable.getColumnModel().getColumn(3).setMaxWidth(0);
            eMonthlyTable.getColumnModel().getColumn(3).setHeaderValue("ID");
        }

        eMonthlyTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        eMonthlyTotal.setText("PhP 10,000.00");

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(102, 102, 102));
        jLabel74.setText("Monthly Total");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eMonthlyTotal, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel74, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eMonthlyTotal)
                .addContainerGap())
        );

        jPanel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel76.setBackground(new java.awt.Color(0, 102, 153));
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("   Today's Expenses");
        jLabel76.setOpaque(true);

        eTodayTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        eTodayTotal.setText("PhP 10,000.00");
        eTodayTotal.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel78.setText("Total:");
        jLabel78.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        eTodayTable.setAutoCreateRowSorter(true);
        eTodayTable.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        eTodayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reason", "Detail", "Amount", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eTodayTable.setRowHeight(24);
        eTodayTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eTodayTable.getTableHeader().setResizingAllowed(false);
        eTodayTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(eTodayTable);
        if (eTodayTable.getColumnModel().getColumnCount() > 0) {
            eTodayTable.getColumnModel().getColumn(0).setMinWidth(130);
            eTodayTable.getColumnModel().getColumn(0).setPreferredWidth(130);
            eTodayTable.getColumnModel().getColumn(0).setMaxWidth(130);
            eTodayTable.getColumnModel().getColumn(2).setMinWidth(80);
            eTodayTable.getColumnModel().getColumn(2).setPreferredWidth(80);
            eTodayTable.getColumnModel().getColumn(2).setMaxWidth(80);
            eTodayTable.getColumnModel().getColumn(3).setMinWidth(0);
            eTodayTable.getColumnModel().getColumn(3).setPreferredWidth(0);
            eTodayTable.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eTodayTotal))
                    .addComponent(jScrollPane9))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eTodayTotal)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel79.setBackground(new java.awt.Color(0, 102, 153));
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("   Monthly Expense Statistics and Comparison");
        jLabel79.setOpaque(true);

        eExpensePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        eExpensePanel.setToolTipText("Graph here");

        javax.swing.GroupLayout eExpensePanelLayout = new javax.swing.GroupLayout(eExpensePanel);
        eExpensePanel.setLayout(eExpensePanelLayout);
        eExpensePanelLayout.setHorizontalGroup(
            eExpensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        eExpensePanelLayout.setVerticalGroup(
            eExpensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eExpensePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eExpensePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel80.setBackground(new java.awt.Color(0, 102, 153));
        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("   Summary");
        jLabel80.setOpaque(true);

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel81.setText("Average Monthly Expense:");

        eAverageMonthly.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        eAverageMonthly.setText("PhP 1,000.00");

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel83.setText("Highest Monthly Expense:");

        eHighestMonthly.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        eHighestMonthly.setText("PhP 1,000.00");

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel85.setText("Lowest Monthly Expense:");

        eLowestMonthly.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        eLowestMonthly.setText("PhP 1,000.00");

        eHighestLabel.setText("Month");

        eLowestLabel.setText("Month");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8)
                    .addComponent(jSeparator9)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel81)
                            .addComponent(eAverageMonthly)
                            .addComponent(jLabel83)
                            .addComponent(eHighestMonthly)
                            .addComponent(jLabel85)
                            .addComponent(eLowestMonthly)
                            .addComponent(eHighestLabel)
                            .addComponent(eLowestLabel))
                        .addGap(0, 78, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eAverageMonthly)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eHighestMonthly)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eHighestLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel85)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eLowestMonthly)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eLowestLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        eAdd.setBackground(new java.awt.Color(0, 255, 102));
        eAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eAdd.setMnemonic('C');
        eAdd.setText("Add Expense");

        javax.swing.GroupLayout expensesPanelLayout = new javax.swing.GroupLayout(expensesPanel);
        expensesPanel.setLayout(expensesPanelLayout);
        expensesPanelLayout.setHorizontalGroup(
            expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expensesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(expensesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eAdd))
                    .addGroup(expensesPanelLayout.createSequentialGroup()
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        expensesPanelLayout.setVerticalGroup(
            expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expensesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69)
                    .addComponent(eAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(expensesPanelLayout.createSequentialGroup()
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Expenses", new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/dash_expense.png")), expensesPanel); // NOI18N

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/tab_returns.png"))); // NOI18N
        jLabel70.setText("Returns");

        jPanel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel77.setBackground(new java.awt.Color(0, 102, 153));
        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("   Item Returns");
        jLabel77.setOpaque(true);

        eMonthlyTable1.setAutoCreateRowSorter(true);
        eMonthlyTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eMonthlyTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date of Return", "ADDMoto Code", "Item Name", "Qty", "Selling Price", "Total Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eMonthlyTable1.setRowHeight(24);
        eMonthlyTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eMonthlyTable1.getTableHeader().setResizingAllowed(false);
        eMonthlyTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(eMonthlyTable1);
        if (eMonthlyTable1.getColumnModel().getColumnCount() > 0) {
            eMonthlyTable1.getColumnModel().getColumn(2).setHeaderValue("Item Name");
            eMonthlyTable1.getColumnModel().getColumn(3).setMinWidth(40);
            eMonthlyTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
            eMonthlyTable1.getColumnModel().getColumn(3).setMaxWidth(40);
            eMonthlyTable1.getColumnModel().getColumn(3).setHeaderValue("Qty");
            eMonthlyTable1.getColumnModel().getColumn(4).setMinWidth(120);
            eMonthlyTable1.getColumnModel().getColumn(4).setPreferredWidth(120);
            eMonthlyTable1.getColumnModel().getColumn(4).setMaxWidth(120);
            eMonthlyTable1.getColumnModel().getColumn(4).setHeaderValue("Selling Price");
            eMonthlyTable1.getColumnModel().getColumn(5).setMinWidth(120);
            eMonthlyTable1.getColumnModel().getColumn(5).setPreferredWidth(120);
            eMonthlyTable1.getColumnModel().getColumn(5).setMaxWidth(120);
            eMonthlyTable1.getColumnModel().getColumn(5).setHeaderValue("Total Price");
        }

        eMonthlyTotal1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        eMonthlyTotal1.setText("PhP 10,000.00");

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(102, 102, 102));
        jLabel82.setText("Total Selling Price of Returned Products");

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(102, 102, 102));
        jLabel84.setText("Total of n items");

        jXSearchField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("From:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("To:");

        jDateChooser2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eMonthlyTotal1))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel82))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                        .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eMonthlyTotal1)
                    .addComponent(jLabel84))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel86.setBackground(new java.awt.Color(0, 102, 153));
        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("   List of Receipts");
        jLabel86.setOpaque(true);

        eMonthlyTable2.setAutoCreateRowSorter(true);
        eMonthlyTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eMonthlyTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Receipt No", "Total Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eMonthlyTable2.setRowHeight(24);
        eMonthlyTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eMonthlyTable2.getTableHeader().setResizingAllowed(false);
        eMonthlyTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(eMonthlyTable2);

        jXSearchField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        eAdd1.setBackground(new java.awt.Color(0, 255, 102));
        eAdd1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eAdd1.setMnemonic('C');
        eAdd1.setText("Return Product");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXSearchField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eAdd1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jXSearchField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(eAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout returnsPanelLayout = new javax.swing.GroupLayout(returnsPanel);
        returnsPanel.setLayout(returnsPanelLayout);
        returnsPanelLayout.setHorizontalGroup(
            returnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(returnsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(returnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(returnsPanelLayout.createSequentialGroup()
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(returnsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        returnsPanelLayout.setVerticalGroup(
            returnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(returnsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(returnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Returns", new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/dash_return.png")), returnsPanel); // NOI18N

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/tab_report.png"))); // NOI18N
        jLabel71.setText("Reports");

        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sales and Transactions", "Income Report", "Cash-outs / Expenses", "Inventory Level Summary", "Product History", "Returns Summary", "Supplier List and Contacts", "Records in Statistics" }));

        jLabel3.setBackground(new java.awt.Color(0, 102, 153));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("   Export File Templates:");
        jLabel3.setOpaque(true);

        jButton2.setBackground(new java.awt.Color(0, 255, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setMnemonic('E');
        jButton2.setText("Export");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, 0, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1137, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Sales and Transactions", jPanel30);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Cash-outs and Expenses", jPanel31);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1137, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Inventory Level Summary", jPanel32);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1137, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Records in Statistics", jPanel33);

        javax.swing.GroupLayout reportsPanelLayout = new javax.swing.GroupLayout(reportsPanel);
        reportsPanel.setLayout(reportsPanelLayout);
        reportsPanelLayout.setHorizontalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(reportsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        reportsPanelLayout.setVerticalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        tabbedPane.addTab("Reports", new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/dash_report.png")), reportsPanel); // NOI18N

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/tab_accounts.png"))); // NOI18N
        jLabel72.setText("Accounts");

        poCreate1.setBackground(new java.awt.Color(0, 255, 102));
        poCreate1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        poCreate1.setMnemonic('C');
        poCreate1.setText("New Account");

        jPanel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 433, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel75.setBackground(new java.awt.Color(0, 102, 153));
        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("   Account Options and Settings");
        jLabel75.setOpaque(true);

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 387, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout accountsPanelLayout = new javax.swing.GroupLayout(accountsPanel);
        accountsPanel.setLayout(accountsPanelLayout);
        accountsPanelLayout.setHorizontalGroup(
            accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(accountsPanelLayout.createSequentialGroup()
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(accountsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(poCreate1)))
                .addContainerGap())
        );
        accountsPanelLayout.setVerticalGroup(
            accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(poCreate1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72))
                .addGap(18, 18, 18)
                .addGroup(accountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Accounts", new javax.swing.ImageIcon(getClass().getResource("/project/addmoto/icons/dash_accounts.png")), accountsPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1189, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iSearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iSearchFieldActionPerformed
/**/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel UIDashboard_dateTimeLabel;
    private javax.swing.JPanel accountsPanel;
    private javax.swing.JButton addItem;
    private javax.swing.JLabel appLogout;
    private javax.swing.JLabel appSellerName;
    private javax.swing.JLabel dAverageTransaction;
    private javax.swing.JLabel dBestMonth;
    private javax.swing.JComboBox<String> dFilterDateType;
    private com.toedter.calendar.JDateChooser dFromDate;
    private javax.swing.JButton dGoFilter;
    private javax.swing.JLabel dHighestMonthlySales;
    private javax.swing.JLabel dNumberTransactions;
    private com.toedter.calendar.JDateChooser dToDate;
    private javax.swing.JTable dTopSelling;
    private javax.swing.JLabel dTotalGoodCost;
    private javax.swing.JLabel dTotalOrdersAmount;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JLabel description;
    private javax.swing.JButton eAdd;
    private javax.swing.JButton eAdd1;
    private javax.swing.JLabel eAverageMonthly;
    private javax.swing.JPanel eExpensePanel;
    private javax.swing.JLabel eHighestLabel;
    private javax.swing.JLabel eHighestMonthly;
    private javax.swing.JLabel eLowestLabel;
    private javax.swing.JLabel eLowestMonthly;
    private javax.swing.JTable eMonthlyTable;
    private javax.swing.JTable eMonthlyTable1;
    private javax.swing.JTable eMonthlyTable2;
    private javax.swing.JLabel eMonthlyTotal;
    private javax.swing.JLabel eMonthlyTotal1;
    private javax.swing.JTable eTodayTable;
    private javax.swing.JLabel eTodayTotal;
    private javax.swing.JPanel expensesPanel;
    private javax.swing.JLabel iAddMotoCode;
    private javax.swing.JButton iAddNew;
    private javax.swing.JButton iAddNewPLine;
    private javax.swing.JLabel iCancelQT;
    private javax.swing.JLabel iCancelRSP;
    private javax.swing.JLabel iCancelUC;
    private javax.swing.JLabel iEditUpdateQT;
    private javax.swing.JLabel iEditUpdateRSP;
    private javax.swing.JLabel iEditUpdateUC;
    private javax.swing.JLabel iItemDescription;
    private javax.swing.JLabel iItemNo;
    private javax.swing.JLabel iOnHandQuantity;
    private javax.swing.JLabel iOnHandQuantity1;
    private javax.swing.JLabel iOnHandQuantity2;
    private javax.swing.JLabel iProductLine;
    private javax.swing.JTextArea iProductName;
    private org.jdesktop.swingx.JXTable iProductsTable;
    private org.jdesktop.swingx.JXSearchField iSearchField;
    private javax.swing.JTextField iSellingPrice;
    private javax.swing.JLabel iShowHistory;
    private javax.swing.JLabel iStatus;
    private javax.swing.JLabel iSupplier;
    private javax.swing.JTextField iThreshold;
    private javax.swing.JTextField iUnitCost;
    private javax.swing.JPanel inventoryPanel;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXImageView jXImageView1;
    private org.jdesktop.swingx.JXMonthView jXMonthView1;
    private org.jdesktop.swingx.JXSearchField jXSearchField1;
    private org.jdesktop.swingx.JXSearchField jXSearchField2;
    private javax.swing.JButton newSupplier;
    private javax.swing.JButton poCreate;
    private javax.swing.JButton poCreate1;
    private javax.swing.JPopupMenu poPopup;
    private javax.swing.JMenuItem poReceiveProducts;
    private org.jdesktop.swingx.JXSearchField poSearch;
    private javax.swing.JTable poTable;
    private javax.swing.JMenuItem poTogglePaid;
    private javax.swing.JMenuItem poViewDetails;
    private javax.swing.JPanel pointOfSalePanel;
    private javax.swing.JTextField posAddProduct;
    private javax.swing.JLabel posAmountDue;
    private javax.swing.JButton posClear;
    private javax.swing.JTable posItemsTable;
    private javax.swing.JButton posPay;
    private javax.swing.JTable posProducts;
    private javax.swing.JLabel posSubTotal;
    private javax.swing.JLabel posTax;
    private javax.swing.JLabel posTotal;
    private javax.swing.JButton posVoid;
    private javax.swing.JLabel productCode;
    private javax.swing.JLabel productLine;
    private javax.swing.JPanel purchasePanel;
    private javax.swing.JLabel quantityOnHand;
    private javax.swing.JPanel reportsPanel;
    private javax.swing.JPanel returnsPanel;
    private javax.swing.JLabel sAddress;
    private javax.swing.JLabel sCityCountry;
    private javax.swing.JLabel sCompanyName;
    private javax.swing.JLabel sContactName;
    private javax.swing.JLabel sContactNo;
    private javax.swing.JLabel sContactTitle;
    private javax.swing.JLabel sDelete;
    private javax.swing.JLabel sEdit;
    private javax.swing.JLabel sManageContacts;
    private javax.swing.JLabel sProducts;
    private javax.swing.JScrollPane sSupplierPane;
    private javax.swing.JLabel sellingPrice;
    private javax.swing.JPanel suppliersPanel;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

    public SellerAccount getSellerAccount() {
        return this.sellerAccount;
    }
    
    /*
    Start of POS Components
    */
    public JTextField getPosAddProduct() {
        return posAddProduct;
    }

    public JLabel getPosAmountDue() {
        return posAmountDue;
    }

    public JButton getPosClear() {
        return posClear;
    }

    public JTable getPosItemsTable() {
        return posItemsTable;
    }

    public JButton getPosPay() {
        return posPay;
    }

    public JLabel getPosSubTotal() {
        return posSubTotal;
    }

    public JLabel getPosTax() {
        return posTax;
    }

    public JLabel getPosTotal() {
        return posTotal;
    }

    public JButton getPosVoid() {
        return posVoid;
    }

    public JLabel getProductCode() {
        return productCode;
    }

    public JTable getPosProducts() {
        return posProducts;
    }

    public JLabel getProductLine() {
        return productLine;
    }

    public JLabel getQuantityOnHand() {
        return quantityOnHand;
    }

    public JLabel getSellingPrice() {
        return sellingPrice;
    }
    
    public JButton getAddItem() {
        return addItem;
    }

    public JLabel getDescription() {
        return description;
    }
    /*
    End of POS Components
    */

    /*
    Start of Supplier Components
    */

    public JScrollPane getsSupplierPane() {
        return sSupplierPane;
    }

    public JLabel getsAddress() {
        return sAddress;
    }

    public JLabel getsCityCountry() {
        return sCityCountry;
    }

    public JLabel getsCompanyName() {
        return sCompanyName;
    }

    public JLabel getsContactName() {
        return sContactName;
    }

    public JLabel getsContactTitle() {
        return sContactTitle;
    }

    public JLabel getsDelete() {
        return sDelete;
    }

    public JLabel getsEdit() {
        return sEdit;
    }

    public JLabel getsManageContacts() {
        return sManageContacts;
    }

    public JLabel getsProducts() {
        return sProducts;
    }

    public JLabel getsContactNo() {
        return sContactNo;
    }

    public JButton getNewSupplier() {
        return newSupplier;
    }
    /*
    End of Supplier Components
    */

    /*
    Start of Dashboard Components
    */
    public JLabel getdAverageTransaction() {
        return dAverageTransaction;
    }

    public JComboBox<String> getdFilterDateType() {
        return dFilterDateType;
    }

    public JDateChooser getdFromDate() {
        return dFromDate;
    }

    public JButton getdGoFilter() {
        return dGoFilter;
    }

    public JLabel getdNumberTransactions() {
        return dNumberTransactions;
    }

    public JDateChooser getdToDate() {
        return dToDate;
    }

    public JLabel getdTotalGoodCost() {
        return dTotalGoodCost;
    }

    public JLabel getdTotalOrdersAmount() {
        return dTotalOrdersAmount;
    }

    public JLabel getdBestMonth() {
        return dBestMonth;
    }

    public JLabel getdExpensePct() {
        return jLabel50;
    }

    public JLabel getdHighestMonthlySales() {
        return dHighestMonthlySales;
    }

    public JTable getdIndicatorTable() {
        return jTable1;
    }

    public JLabel getdInformation() {
        return jLabel51;
    }

    public JLabel getdSalesPct() {
        return jLabel48;
    }

    public JLabel getdTransPct() {
        return jLabel47;
    }

    public JTable getdTopSelling() {
        return dTopSelling;
    }
    /*
    End of Dashboard Components
    */
    
    /*
    Start of Inventory Components
    */
    public JLabel getiCancelQT() {
        return iCancelQT;
    }

    public JLabel getiCancelRSP() {
        return iCancelRSP;
    }

    public JLabel getiCancelUC() {
        return iCancelUC;
    }

    public JLabel getiEditUpdateQT() {
        return iEditUpdateQT;
    }

    public JButton getiAddNewPLine() {
        return iAddNewPLine;
    }

    public JLabel getiEditUpdateRSP() {
        return iEditUpdateRSP;
    }

    public JLabel getiEditUpdateUC() {
        return iEditUpdateUC;
    }

    public JLabel getiItemDescription() {
        return iItemDescription;
    }

    public JLabel getiAddMotoCode() {
        return iAddMotoCode;
    }

    public JLabel getiItemNo() {
        return iItemNo;
    }

    public JLabel getiOnHandQuantity() {
        return iOnHandQuantity;
    }

    public JLabel getiProductLine() {
        return iProductLine;
    }

    public JTextArea getiProductName() {
        return iProductName;
    }

    public JXTable getiProductsTable() {
        return iProductsTable;
    }

    public JXSearchField getiSearchField() {
        return iSearchField;
    }

    public JTextField getiSellingPrice() {
        return iSellingPrice;
    }

    public JLabel getiShowHistory() {
        return iShowHistory;
    }

    public JLabel getiStatus() {
        return iStatus;
    }

    public JLabel getiSupplier() {
        return iSupplier;
    }
    
    public JTextField getiThreshold() {
        return iThreshold;
    }

    public JTextField getiUnitCost() {
        return iUnitCost;
    }
    
    /*
    End of Inventory Components
    */

    public JButton getiAddNew() {
        return iAddNew;
    }
    
    /*
    Start of PO Components
    */
    public JButton getPoCreate() {
        return poCreate;
    }

    public JXSearchField getPoSearch() {
        return poSearch;
    }

    public JTable getPoTable() {
        return poTable;
    }
    
    public JPopupMenu getPoPopup() {
        return poPopup;
    }

    public JMenuItem getPoReceiveProducts() {
        return poReceiveProducts;
    }

    public JMenuItem getPoTogglePaid() {
        return poTogglePaid;
    }

    public JMenuItem getPoViewDetails() {
        return poViewDetails;
    }
    /*
    End of PO Components
    */
    
    /*
    Start of Expense Components
    */
    public JButton geteAdd() {
        return eAdd;
    }

    public JLabel geteAverageMonthly() {
        return eAverageMonthly;
    }

    public JLabel geteHighestLabel() {
        return eHighestLabel;
    }

    public JLabel geteHighestMonthly() {
        return eHighestMonthly;
    }

    public JLabel geteLowestLabel() {
        return eLowestLabel;
    }

    public JLabel geteLowestMonthly() {
        return eLowestMonthly;
    }

    public JTable geteMonthlyTable() {
        return eMonthlyTable;
    }

    public JLabel geteMonthlyTotal() {
        return eMonthlyTotal;
    }

    public JTable geteTodayTable() {
        return eTodayTable;
    }

    public JLabel geteTodayTotal() {
        return eTodayTotal;
    }
    
    public JPanel geteExpensePanel() {
        return eExpensePanel;
    }
    /*
    End of Expense Components
    */
}