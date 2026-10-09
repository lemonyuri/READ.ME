/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marketingroi;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CampaignManager manager = new CampaignManager();

        // sample objects (polymorphism: same reference type, different classes)
        manager.addCampaign(new SocialMediaCampaign("C1", "Facebook Ads", "Facebook",
                50000, 100000, "Facebook"));
        manager.addCampaign(new EmailCampaign("C2", "Newsletter Blast", "Email",
                15000, 30000, 50000));

        int choice;
        do {
            System.out.println("\n===== MARKETING ROI TRACKER =====");
            System.out.println("1. Add campaign");
            System.out.println("2. View all campaigns");
            System.out.println("3. View statistics");
            System.out.println("4. Search campaign by ID");
            System.out.println("5. Rank by ROI");
            System.out.println("6. Compare two campaigns");
            System.out.println("7. Filter by performance level");
            System.out.println("8. Channel summary");
            System.out.println("9. Budget recommendation");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = readInt(scanner);

            switch (choice) {
                case 1 -> addCampaign(scanner, manager);
                case 2 -> manager.printAll();
                case 3 -> manager.printStatistics();
                case 4 -> {
                    System.out.print("Enter campaign ID: ");
                    String id = scanner.nextLine().trim();
                    Campaign found = manager.findById(id);
                    if (found != null) {
                        System.out.printf("Found: %s | ROI: %.2f%% | %s%n",
                                found.getCampaignName(), found.calculateROI(),
                                found.getPerformanceLevel());
                    } else {
                        System.out.println("Campaign not found.");
                    }
                }
                case 5 -> manager.rankByROI();
                case 6 -> {
                    System.out.print("Enter first campaign ID: ");
                    String idA = scanner.nextLine().trim();
                    System.out.print("Enter second campaign ID: ");
                    String idB = scanner.nextLine().trim();
                    manager.compareTwo(idA, idB);
                }
                case 7 -> {
                    System.out.print("Show which level (Good / Neutral / Poor): ");
                    String level = scanner.nextLine().trim();
                    manager.filterByLevel(level);
                }
                case 8 -> manager.channelSummary();
                case 9 -> manager.budgetRecommendation();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice, try again.");
            }
        } while (choice != 0);

        scanner.close();
        System.out.println("Goodbye!");
    }

    private static void addCampaign(Scanner scanner, CampaignManager manager) {
        System.out.print("Campaign ID: ");
        String id = scanner.nextLine().trim();

        // TC-14: block duplicate IDs
        if (manager.findById(id) != null) {
            System.out.println("  Campaign ID '" + id + "' already exists. Not added.");
            return;
        }

        System.out.print("Campaign name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Channel: ");
        String channel = scanner.nextLine().trim();

        double cost = readNonNegativeDouble(scanner, "Cost: ");
        double revenue = readNonNegativeDouble(scanner, "Revenue: ");

        System.out.print("Type (1 = Social Media, 2 = Email): ");
        int type = readInt(scanner);

        // TC-15: success message only prints when actually added
        if (type == 1) {
            System.out.print("Platform (Facebook / TikTok / Instagram): ");
            String platform = scanner.nextLine().trim();
            manager.addCampaign(new SocialMediaCampaign(id, name, channel, cost, revenue, platform));
            System.out.println("Campaign added!");
        } else if (type == 2) {
            int emails = readNonNegativeInt(scanner, "Emails sent: ");
            manager.addCampaign(new EmailCampaign(id, name, channel, cost, revenue, emails));
            System.out.println("Campaign added!");
        } else {
            System.out.println("Unknown type, campaign not added.");
        }
    }

    // ---- safe input helpers (never crash on bad text) ----

    private static int readInt(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("  Please enter a whole number. Try again: ");
            }
        }
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.print("  Please enter a number. Try again: ");
            }
        }
    }

    private static int readNonNegativeInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            int value = readInt(scanner);
            if (value >= 0) {
                return value;
            }
            System.out.println("  Value cannot be negative. Try again.");
        }
    }

    private static double readNonNegativeDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            double value = readDouble(scanner);
            if (value >= 0) {
                return value;
            }
            System.out.println("  Value cannot be negative. Try again.");
        }
    }
}