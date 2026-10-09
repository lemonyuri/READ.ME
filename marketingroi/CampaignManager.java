package com.marketingroi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// RELATIONSHIP: manager HAS-A list of Campaign objects
public class CampaignManager {

    private List<Campaign> campaigns;   // POLYMORPHISM: holds SocialMedia or Email

    public CampaignManager() {
        this.campaigns = new ArrayList<>();
    }

    public void addCampaign(Campaign c) {
        campaigns.add(c);
    }

    public List<Campaign> getAllCampaigns() {
        return campaigns;
    }

    // find one campaign by ID
    public Campaign findById(String id) {
        for (Campaign c : campaigns) {
            if (c.getCampaignId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    // print all campaigns (polymorphic: each knows its own type)
    public void printAll() {
        if (campaigns.isEmpty()) {
            System.out.println("No campaigns yet.");
            return;
        }
        for (Campaign c : campaigns) {
            System.out.printf("ID: %-6s Name: %-18s Type: %-24s Cost: %.2f Revenue: %.2f ROI: %.2f%% [%s]%n",
                    c.getCampaignId(), c.getCampaignName(), c.getCampaignType(),
                    c.getCost(), c.getRevenue(), c.calculateROI(), c.getPerformanceLevel());
        }
    }

    // simple statistics
    public void printStatistics() {
        if (campaigns.isEmpty()) {
            System.out.println("No campaigns to summarize.");
            return;
        }
        double totalCost = 0, totalRevenue = 0, totalRoi = 0;
        int good = 0;
        for (Campaign c : campaigns) {
            totalCost += c.getCost();
            totalRevenue += c.getRevenue();
            totalRoi += c.calculateROI();
            if (c.getPerformanceLevel().equals("Good")) {
                good++;
            }
        }
        System.out.println("Total campaigns : " + campaigns.size());
        System.out.printf("Total cost      : %.2f%n", totalCost);
        System.out.printf("Total revenue   : %.2f%n", totalRevenue);
        System.out.printf("Average ROI     : %.2f%%%n", totalRoi / campaigns.size());
        System.out.println("Good campaigns  : " + good);
    }

    // 1) Rank campaigns from highest ROI to lowest
    public void rankByROI() {
        if (campaigns.isEmpty()) {
            System.out.println("No campaigns to rank.");
            return;
        }
        List<Campaign> sorted = new ArrayList<>(campaigns);
        Collections.sort(sorted, (a, b) -> Double.compare(b.calculateROI(), a.calculateROI()));

        System.out.println("--- RANKING BY ROI (best first) ---");
        int rank = 1;
        for (Campaign c : sorted) {
            System.out.printf("%d. %-18s ROI: %.2f%% [%s]%n",
                    rank++, c.getCampaignName(), c.calculateROI(), c.getPerformanceLevel());
        }
    }

    // 2) Compare two campaigns side by side
    public void compareTwo(String id1, String id2) {
        Campaign a = findById(id1);
        Campaign b = findById(id2);
        if (a == null || b == null) {
            System.out.println("One or both IDs not found.");
            return;
        }
        System.out.println("---------------- COMPARISON ----------------");
        System.out.printf("%-18s %18s %18s%n", "", a.getCampaignName(), b.getCampaignName());
        System.out.printf("%-18s %18.2f %18.2f%n", "Cost", a.getCost(), b.getCost());
        System.out.printf("%-18s %18.2f %18.2f%n", "Revenue", a.getRevenue(), b.getRevenue());
        System.out.printf("%-18s %18.2f %18.2f%n", "Profit", a.calculateProfit(), b.calculateProfit());
        System.out.printf("%-18s %17.2f%% %17.2f%%%n", "ROI", a.calculateROI(), b.calculateROI());

        if (a.calculateROI() > b.calculateROI()) {
            System.out.println(">> Better by ROI: " + a.getCampaignName());
        } else if (b.calculateROI() > a.calculateROI()) {
            System.out.println(">> Better by ROI: " + b.getCampaignName());
        } else {
            System.out.println(">> Equal ROI.");
        }
    }

    // 3) Filter by performance level (Good / Neutral / Poor)
    public void filterByLevel(String level) {
        boolean found = false;
        System.out.println("--- Campaigns with level: " + level + " ---");
        for (Campaign c : campaigns) {
            if (c.getPerformanceLevel().equalsIgnoreCase(level)) {
                System.out.printf("  %s | %s | ROI: %.2f%%%n",
                        c.getCampaignId(), c.getCampaignName(), c.calculateROI());
                found = true;
            }
        }
        if (!found) {
            System.out.println("  None found.");
        }
    }

    // 4) Summary grouped by marketing channel
    public void channelSummary() {
        if (campaigns.isEmpty()) {
            System.out.println("No campaigns.");
            return;
        }
        System.out.println("--- PERFORMANCE BY CHANNEL ---");
        for (Campaign c : campaigns) {
            System.out.printf("Channel: %-12s | %-18s | ROI: %.2f%%%n",
                    c.getChannel(), c.getCampaignName(), c.calculateROI());
        }
    }

    // 5) Budget recommendation: which to fund more, which to stop
    public void budgetRecommendation() {
        if (campaigns.isEmpty()) {
            System.out.println("No campaigns to evaluate.");
            return;
        }
        System.out.println("--- BUDGET RECOMMENDATION ---");
        for (Campaign c : campaigns) {
            String level = c.getPerformanceLevel();
            if (level.equals("Good")) {
                System.out.printf("  [INVEST MORE] %-18s ROI: %.2f%%  -> high return, add budget%n",
                        c.getCampaignName(), c.calculateROI());
            } else if (level.equals("Neutral")) {
                System.out.printf("  [KEEP / TEST]  %-18s ROI: %.2f%%  -> keep, monitor closely%n",
                        c.getCampaignName(), c.calculateROI());
            } else {
                System.out.printf("  [STOP / CUT]   %-18s ROI: %.2f%%  -> poor return, reduce or stop%n",
                        c.getCampaignName(), c.calculateROI());
            }
        }
    }

    // 6) Delete a campaign by ID
    public boolean deleteById(String id) {
        Campaign c = findById(id);
        if (c != null) {
            campaigns.remove(c);
            return true;
        }
        return false;
    }
}