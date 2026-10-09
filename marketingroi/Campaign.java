/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marketingroi;

/**
 *
 * @author user
 */
public abstract class Campaign  implements PerformanceEvaluable {
    // ENCAPSULATION: private fields (hidden from outside)
    private String campaignId;
    private String campaignName;
    private String channel;
    private double cost;
    private double revenue;

    // CONSTRUCTOR: uses 'this' to set object fields
    public Campaign(String campaignId, String campaignName, String channel, double cost, double revenue) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.channel = channel;
        this.cost = cost;
        this.revenue = revenue;
}
public String getCampaignId() { return campaignId; }
    public void setCampaignId(String campaignId) { this.campaignId = campaignId; }

    public String getCampaignName() { return campaignName; }
    public void setCampaignName(String campaignName) { this.campaignName = campaignName; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public double getRevenue() { return revenue; }
    public void setRevenue(double revenue) { this.revenue = revenue; }

    // a normal method inherited by all subclasses
    public double calculateProfit() {
        return revenue - cost;
    }

    // INTERFACE method: shared ROI formula

    /**
     *
     * @return
     */
   
    @Override
    public double calculateROI() {
        if (cost == 0) return 0;
        return ((revenue - cost) / cost) * 100.0;
    }

    // INTERFACE method: shared rating rule

    /**
     *
     * @return
     */
    
    @Override
    public String getPerformanceLevel() {
        double roi = calculateROI();
        if (roi >= 50) {
            return "Good";
        } else if (roi >= 0) {
            return "Neutral";
        } else {
            return "Poor";
        }
    }

    // ABSTRACT method: each campaign type must describe itself
    public abstract String getCampaignType();
}