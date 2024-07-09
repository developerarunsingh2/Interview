package com.ey;

public class ParkSpaces {

    public static void main(String[] args) {
        String parkLayout = "TAKEN FREE FREE FREE TAKEN FREE FREE TAKEN\n" +
                "FREE FREE FREE TAKEN FREE FREE TAKEN\n" +
                "TAKEN TAKEN FREE FREE FREE";

        int totalFreeSpaces = countFreeSpaces(parkLayout);

        System.out.println("Total free spaces between groups: " + totalFreeSpaces);
    }

    public static int countFreeSpaces(String parkLayout) {
        String[] rows = parkLayout.split("\n");
        int totalFreeSpaces = 0;

        for (String row : rows) {
            String[] spaces = row.split("\\s+");
            boolean groupStarted = false;
            int freeSpaces = 0;

            for (String space : spaces) {
                if (space.equals("TAKEN")) {
                    if (groupStarted) {
                        totalFreeSpaces += freeSpaces;
                        freeSpaces = 0;
                        groupStarted = false;
                    }
                } else {
                    freeSpaces++;
                    groupStarted = true;
                }
            }
        }

        return totalFreeSpaces;
    }
}

