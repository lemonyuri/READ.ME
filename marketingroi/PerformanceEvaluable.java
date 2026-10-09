/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marketingroi;

/**
 *
 * @author user
 */
interface PerformanceEvaluable {
        double calculateROI();          // ROI in percent, e.g. 50.0 means 50%
    String getPerformanceLevel();   // "Good", "Neutral", or "Poor"
}

