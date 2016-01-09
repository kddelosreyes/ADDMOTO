/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.custom;

import java.awt.Component;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import org.jdesktop.swingx.painter.AbstractLayoutPainter;
import project.addmoto.data.ColorString;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class LabelRenderer extends JLabel implements TableCellRenderer {

    Border unselectedBorder = null;
    Border selectedBorder = null;
    boolean isBordered = true;

    public LabelRenderer(boolean isBordered) {
        this.isBordered = isBordered;
        setForeground(Color.WHITE);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object colorString,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        
        ColorString newColorString = (ColorString) colorString;
        setText(newColorString.getStatus());
        setBackground(newColorString.getColor());
        setHorizontalAlignment(CENTER);
        
        if (isBordered) {
            if (isSelected) {
                if (selectedBorder == null) {
                    selectedBorder = BorderFactory.createMatteBorder(2,5,2,5,
                                              table.getSelectionBackground());
                }
                setBorder(selectedBorder);
            } else {
                if (unselectedBorder == null) {
                    unselectedBorder = BorderFactory.createMatteBorder(2,5,2,5,
                                              table.getBackground());
                }
                setBorder(unselectedBorder);
            }
        }
        
        setToolTipText("Yeah");
        return this;
    }
    
}
