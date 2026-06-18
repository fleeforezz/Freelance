package controllers;

import interfaces.IList;
import java.util.ArrayList;
import java.util.List;
import models.Club;
import utils.Acceptable;
import utils.DataSource;
import utils.Inputter;

public class ClubController implements IList<Club> {

    DataSource context = new DataSource();
    String URL_PATH = context.getClubFilePath();

    /*
     * ####################################################
     * Add new club
     * ####################################################
     */
    @Override
    public Club addRec() {

        // Input club id:
        String clubId = Inputter.getString(
                "\nEnter club id: ",
                true, "Club id cannot be empty!!!",
                true, Acceptable.CLUB_VALID_ID, "Club id must be CL-xxxx (e.g., CL-0001)",
                true, "This club already exists!!!",
                id -> {
                    if (searchRecById(id) != null) {
                        return false;
                    }
                    return true;
                }
        );

        // Input club name:
        String clubName = Inputter.getString(
                "Enter club name: ",
                "Club name cannot be empty!!!"
        );

        // Input sponser brand
        String sponserBrand = Inputter.getString(
                "Enter sponcer brand: ",
                "Sponcer brand cannot be empty!!!"
        );

        // Enter budget:
        int budget = Inputter.getInt(
                "Enter budget: ",
                0, 999999,
                false
        );

        //Create new club object
        Club newClub = new Club(
                clubId,
                clubName,
                sponserBrand,
                budget
        );
        
        // Saved to club list
        context.clubList().add(newClub);

        // Confirm and save changes to file
        if (Inputter.confirmSaveFile(
                "Club",
                context.clubList(),
                URL_PATH
        )) {
            return newClub;
        }

        return null;
    }

    /*
     * ####################################################
     * Update club by id
     * ####################################################
     */
    @Override
    public Club updateRec(String id) {
        
        // Get exist club
        Club existClub = searchRecById(id);

        // Return null if no club found
        if (existClub == null) {
            return null;
        }

        // Input updated club name
        String clubName = Inputter.getString(
                "\nEnter new club name: "
        );
        if (!clubName.isEmpty()) {
            existClub.setClubName(clubName);
        }

        // Input updated player name
        String sponserBrand = Inputter.getString(
                "Enter new sponcer brand: "
        );
        if (!sponserBrand.isEmpty()) {
            existClub.setSponcerBrand(sponserBrand);
        }

        // Input updated club budget
        int budget = Inputter.getInt(
                "Enter new budget: ",
                1, 99999,
                true
        );
        if (budget != -1) {
            existClub.setBudget(budget);
        }
        
        // Confirm and save changes to file
        Inputter.confirmSaveFile(
                "Club", 
                context.clubList(), 
                URL_PATH
        );

        return existClub;
    }

    /*
     * ####################################################
     * Delete club
     * ####################################################
     */
    @Override
    public boolean deleteRec(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRec'");
    }

    /*
     * ####################################################
     * List all club
     * ####################################################
     */
    @Override
    public List<Club> listAll() {
        return context.clubList();
    }

    /*
     * ####################################################
     * Search club by id
     * ####################################################
     */
    @Override
    public Club searchRecById(String id) {

        for (Club club : context.clubList()) { 
            if (club.getClubId().equalsIgnoreCase(id)) {
                return club;
            }
        }

        return null;
    }
    
    /*
     * ####################################################
     * List all club with budgets <= input budget
     * ####################################################
     */
    public List<Club> listAllClubWithBudgetLessThanInput(int budget) {
        List<Club> foundClub = new ArrayList<>();
        
        for (Club club : context.clubList()) {
            if (club.getBudget() <= budget) {
                foundClub.add(club);
            }
        }
        
        return foundClub;
    }

    /*
     * ####################################################
     * Display data
     */
    @Override
    public void displayRec() {

        List<Club> clubs = context.clubList();

        if (clubs.isEmpty()) {
            System.out.println(
                    "|                        No data in the system                       |"
            );
        }

        for (Club club : clubs) {
            System.out.print(club.display());
        }
    }
}
