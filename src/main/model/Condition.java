package model;

import java.util.LinkedList;

public class Condition {

    private static int locationMin = 1;
    private static int locationMax = 100;
    private static int timeMin = 0;
    private static int timeMax = 24;
    private static int idMin = 500;
    private static int idMax = 1000;

    // REQUIRES: x
    // MODIFIES: this
    // EFFECTS: return the information if add successfully, otherwise return error message
    public static void inputInfoCon(LinkedList<CaseInfo> caseInfos, int location, int time, int caseID) {
        if (location < locationMin || location > locationMax) {
            System.out.println("invalid location");
        } else if (time < timeMin || time > timeMax) {
            System.out.println("invalid time");
        } else if (caseID < idMin || caseID > idMax) {
            System.out.println("invalid ID");
        } else {
            if (caseCheck(caseInfos,"i", location, time, caseID).isEmpty()) {
                printin(caseCheck(caseInfos,"i", location, time, caseID));
                System.out.println("Case (" + location + ", " + time + ", " + caseID + ") was added successfully");
            } else {
                System.out.println("Fail! The data has existed");
            }
        }

    }

    // REQUIRES: x
    // MODIFIES: this
    // EFFECTS: delete the info that already exists and return message
    public static void removeInfoCon(LinkedList<CaseInfo> caseInfos, int location, int time, int caseID) {
        if (location < locationMin || location > locationMax) {
            System.out.println("invalid location");
        } else if (caseID < idMin || caseID > idMax) {
            System.out.println("invalid ID");
        } else {
            if (!caseCheck(caseInfos,"r", location, time, caseID).isEmpty()) {
                printin(caseCheck(caseInfos,"r", location, time, caseID));
                System.out.println("was removed successfully");
            } else {
                System.out.println("Data was not found");
            }
        }

    }

    // REQUIRES: x
    // MODIFIES: this
    // EFFECTS: search person by number and return its basic information
    public static boolean searchIDCon(LinkedList<CaseInfo> caseInfos, int location, int time, int caseID) {
        boolean found = false;
        if (caseID < idMin || caseID > idMax) {
            System.out.println("invalid ID");

        } else {
            if (!caseCheck(caseInfos,"p", location, time, caseID).isEmpty()) {
                printin(caseCheck(caseInfos,"p", location, time, caseID));
                System.out.println("was found");
                found = true;
            } else {
                System.out.println("Data was not found");
            }
        }
        return found;

    }

    // REQUIRES: x
    // MODIFIES: this
    // EFFECTS: search pokemon by venue and return its basic information
    public static boolean searchLocationCon(LinkedList<CaseInfo> caseInfos, int location, int time, int caseID) {
        boolean found = false;
        if (location < locationMin || location > locationMax) {
            System.out.println("invalid location");

        } else {
            if (!caseCheck(caseInfos,"l", location, time, caseID).isEmpty()) {
                printin(caseCheck(caseInfos,"l", location, time, caseID));
                System.out.println("was found");
                found = true;
            } else {
                System.out.println("Data was not found");
            }
        }
        return found;

    }

    // REQUIRES: x
    // MODIFIES: this
    // EFFECTS: search a case by time and return its basic information
    public static boolean searchTimeCon(LinkedList<CaseInfo> caseInfos, int location, int time, int caseID) {
        boolean found = false;
        if (time < timeMin || time > timeMax) {
            System.out.println("invalid time");

        } else {
            if (!caseCheck(caseInfos,"t", location, time, caseID).isEmpty()) {
                printin(caseCheck(caseInfos,"t", location, time, caseID));
                System.out.println("was found");
                found = true;
            } else {
                System.out.println("Data was not found");
            }
        }
        return found;

    }

    // MODIFIES: this
    // EFFECTS: print all the information in the list
    public static void printin(LinkedList<CaseInfo> answer) {
        for (int i = 0; i < answer.size(); i++) {
            System.out.println("(" + answer.get(i).getLocation() + ", "
                    + answer.get(i).getTime() + ", "
                    + answer.get(i).getCaseID() + ")");
        }
    }

    public static LinkedList<CaseInfo> caseCheck(LinkedList<CaseInfo> caseInfos,
                                                 String type, int location, int time, int caseID) {
        LinkedList<CaseInfo> answer = new LinkedList();
        for (int i = 0; i < caseInfos.size(); i++) {
            if (checkboolean(caseInfos, type, location, time, caseID, i)) {
                answer.addLast(new CaseInfo(caseInfos.get(i).getLocation(),
                        caseInfos.get(i).getTime(), caseInfos.get(i).getCaseID()));
                if (type.equals("r")) {
                    caseInfos.remove(i);
                }
            }
        }
        if (type.equals("i") && answer.isEmpty()) {
            caseInfos.addLast(new CaseInfo(location, time, caseID));
        }
        return answer;
    }


    private static boolean checkboolean(LinkedList<CaseInfo> caseInfos,
                                        String type, int location, int time, int caseID, int i) {
        switch (type) {
            case "i":
            case "r":
                return caseInfos.get(i).getLocation() == location
                        && caseInfos.get(i).getTime() == time
                        && caseInfos.get(i).getCaseID() == caseID;
            case "l":
                return caseInfos.get(i).getLocation() == location;
            case "t":
                return caseInfos.get(i).getTime() == time;//Time is 24 hour format
            case "p":
                return caseInfos.get(i).getCaseID() == caseID;
            default:
                return false;
        }
    }
}
