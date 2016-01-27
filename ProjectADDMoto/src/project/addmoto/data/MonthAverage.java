/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class MonthAverage {

    public int month;
    public int year;
    public double expense;

    public MonthAverage(int year, int month, double expense) {
        this.year = year;
        this.month = month;
        this.expense = expense;
    }
}
