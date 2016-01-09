/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.data;

import java.awt.Color;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class ColorString {
    
    private Color color;
    private String status;
    private String percentage;
    
    public ColorString(Color color, String status, String percentage) {
        this.color = color;
        this.status = status;
        this.percentage = percentage;
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

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "ColorString{" + "color=" + color + ", status=" + status + ", percentage=" + percentage + '}';
    }
}
