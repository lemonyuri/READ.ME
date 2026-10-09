/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marketingroi;

/**
 *
 * @author user
 */
public class SocialMediaCampaign extends Campaign {
     private String platform;   // e.g. Facebook, TikTok, Instagram

    // constructor calls the superclass constructor with 'super'
    public SocialMediaCampaign(String campaignId, String campaignName, String channel, double cost, double revenue, String platform) {
        super(campaignId, campaignName, channel, cost, revenue);
        this.platform = platform;
    }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    // OVERRIDE: give this type its own description
    @Override
    public String getCampaignType() {
        return "Social Media (" + platform + ")";
    }
}
