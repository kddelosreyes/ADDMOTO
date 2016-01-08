/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.data;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class ColorString {
    
    private Color color;
    private String status;
    
    public ColorString(Color color, String status) {
        this.color = color;
        this.status = status;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
