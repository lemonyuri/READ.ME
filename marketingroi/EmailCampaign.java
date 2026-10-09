/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marketingroi;

/**
 *
 * @author user
 */
public class EmailCampaign extends Campaign {
    private int emailsSent;

    public EmailCampaign(String campaignId, String campaignName, String channel,
                         double cost, double revenue, int emailsSent) {
        super(campaignId, campaignName, channel, cost, revenue);
        this.emailsSent = emailsSent;
    }

    public int getEmailsSent() { return emailsSent; }
    public void setEmailsSent(int emailsSent) { this.emailsSent = emailsSent; }

    @Override
    public String getCampaignType() {
        return "Email (" + emailsSent + " emails sent)";
    }
}
