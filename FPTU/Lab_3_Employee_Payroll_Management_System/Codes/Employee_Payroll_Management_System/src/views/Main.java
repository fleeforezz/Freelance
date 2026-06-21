package views;

import controllers.ClubController;
import controllers.PlayerController;
import java.util.List;
import models.Club;
import models.Player;
import utils.Acceptable;
import utils.Inputter;

public class Main {

    public static void main(String[] args) {

        int choice = 0;

        // UI Components
        String appTitle = "Football Club & Player Management";

        String[] clubCols = {
            "Id",
            "Name",
            "Sponcer Brand",
            "Budget"
        };

        int[] clubColWidth = {
            8, 24, 13, 12
        };

        String[] playerCols = {
            "Id",
            "ClubId",
            "Name",
            "Position",
            "Shirt Number"
        };

        int[] playerColWidth = {
            8, 8, 24, 13, 12
        };

        // Controllers
        PlayerController playerController = new PlayerController();
        ClubController clubController = new ClubController();

        // Load Data from file then add to this list
        List<Club> clubList = clubController.listAll();
        List<Player> playerList = playerController.listAll();

        do {
            UI.titleHeader(appTitle);
            System.out.println("| 1. List All Clubs                                                  |"); // Done
            System.out.println("| 2. Add a new Club                                                  |"); // Done
            System.out.println("| 3. Search for a club by ID                                         |"); // Done
            System.out.println("| 4. Update a Club by ID                                             |"); // Done
            System.out.println("| 5. List of all Clubs with budget <= input value                    |"); // Done
            System.out.println("| 6. List all players in ascending order                             |"); // Done
            System.out.println("| 7. Search players by partial player name                           |"); // Done
            System.out.println("| 8. Add a new player                                                |"); // Done **NEED FIX**
            System.out.println("| 9. Remove a player with ID                                         |"); // Done
            System.out.println("| 10. Update a player with an ID                                     |"); // Done
            System.out.println("| 11. List all players by a specific position                        |"); // Done
            System.out.println("| 12. Save data to files                                             |");
            System.out.println("| 13. Load data from files                                           |");
            System.out.println("| 14. Quit program                                                   |"); // Done
            UI.footer();

            choice = Inputter.getInt(
                    "Enter your choice: ",
                    Inputter.MIN,
                    Inputter.MAX,
                    false
            );

            switch (choice) {
                case 1: //List All Clubs
                    if (clubList != null) {
                        UI.tableHeader(clubCols, clubColWidth);
                        for (Club allClub : clubList) {
                            System.out.print(allClub.display());
                        }
                        UI.footer();
                    } else {
                        UI.tableHeader(clubCols, clubColWidth);
                        UI.error("No data in system");
                        UI.footer();
                    }

                    break;
                case 2: //Add a new Club
                    if (clubController.addRec() != null) {
                        System.out.println("\nNew club created!!!\n");
                    } else {
                        System.out.println("\nCannot create new club!!!\n");
                    }
                    break;
                case 3: // Search for a club by ID
                    String clubId = Inputter.getString(
                            "\nEnter club id: ",
                            "Field cannot be empty!!!"
                    );

                    Club club = clubController.searchRecById(clubId);
                    if (club != null) {
                        UI.tableHeader(clubCols, clubColWidth);
                        System.out.print(club.display());
                        UI.footer();
                    } else {
                        UI.tableHeader(clubCols, clubColWidth);
                        UI.error("This club does not exists!");
                        UI.footer();
                    }

                    break;
                case 4: // Update a Club by ID
                    String updateClubId = Inputter.getString(
                            "\nEnter club id: ",
                            "Club id cannot be empty!!!"
                    );

                    if (clubController.updateRec(updateClubId) != null) {
                        System.out.println("\nClub updated!!!");
                    } else {
                        System.out.println("\n This club does not exist!");
                    }
                    break;
                case 5: // List of all Clubs with budget <= input value
                    int budget = Inputter.getInt(
                            "\nEnter budget: ",
                            Inputter.MIN, 99999,
                            false
                    );

                    if (clubController.listAllClubWithBudgetLessThanInput(budget) != null) {
                        UI.tableHeader(clubCols, clubColWidth);
                        for (Club clubWithCondition : clubController.listAllClubWithBudgetLessThanInput(budget)) {
                            System.out.print(clubWithCondition.display());
                        }
                        UI.footer();
                    } else {
                        UI.tableHeader(clubCols, clubColWidth);
                        UI.error("No data in system");
                        UI.footer();
                    }

                    break;
                case 6: // List all players in ascending order
                    if (!playerController.listAllAcending().isEmpty()) {
                        UI.tableHeader(playerCols, playerColWidth);
                        for (Player allPlayer : playerList) {
                            System.out.print(allPlayer.display());
                        }
                        UI.footer();
                    } else {
                        UI.tableHeader(playerCols, playerColWidth);
                        UI.error("No data in system");
                        UI.footer();
                    }

                    break;
                case 7: // Search players by partial player name
                    String playerName = Inputter.getString(
                            "\nEnter player name: ",
                            "Player name cannot be empty!!!"
                    );

                    List<Player> players = playerController.searchRecByName(playerName);

                    if (!players.isEmpty()) {
                        UI.tableHeader(playerCols, playerColWidth);
                        for (Player player : players) {
                            System.out.print(player.display());
                        }
                        UI.footer();
                    } else {
                        UI.tableHeader(playerCols, playerColWidth);
                        UI.error("| No matching player found");
                        UI.footer();
                    }
                    break;
                case 8: // Add a new player
                    if (playerController.addRec() != null) {
                        System.out.println("New player created");
                    } else {
                        System.out.println("Cannot create new player");
                    }
                    break;
                case 9: // Remove a player with ID
                    String playerId = Inputter.getString(
                            "\nEnter player id: ",
                            "Field cannot be empty!!!"
                    );

                    boolean isDeleted = playerController.deleteRec(playerId);
                    if (isDeleted) {
                        UI.tableHeader(playerCols, playerColWidth);
                        UI.success("Player has been removed");
                        UI.footer();
                    } else {
                        UI.tableHeader(playerCols, playerColWidth);
                        UI.error("This player does not exists!");
                        UI.footer();
                    }
                    break;
                case 10: // Update a player with an ID
                    String updatePlayerId = Inputter.getString(
                            "\nEnter player id: ",
                            "Player id cannot be empty!!!"
                    );

                    if (playerController.updateRec(updatePlayerId) != null) {
                        System.out.println("\nPlayer updated!!!");
                    } else {
                        System.out.println("\n This player does not exist!");
                    }
                    break;
                case 11: // List all players by a specific position
                    String position = Inputter.getString(
                            "\nEnter position (Goalkeeper/Defender/Midfielder/Forward/Winger): ",
                            true, "position cannot be empty!!!",
                            false, null, null,
                            true, "Invalid position! Must be one of: Goalkeeper, Defender, Midfielder, Forward, Winger",
                            input -> {
                                for (String pos : Acceptable.AVAILABLE_POSITION) {
                                    if (pos.equalsIgnoreCase(input.trim())) {
                                        return true;
                                    }
                                }
                                return false;
                            }
                    );

                    List<Player> allPlayers = playerController.listAllByPosition(position);

                    if (allPlayers != null) {
                        UI.tableHeader(playerCols, playerColWidth);
                        for (Player player : allPlayers) {
                            System.out.print(player.display());
                        }
                        UI.footer();
                    } else {
                        UI.tableHeader(playerCols, playerColWidth);
                        UI.error("No matching player found for current position");
                        UI.footer();
                    }

                    break;
                case 12: // Save data to files
                    clubController.saveToFile();
                    playerController.saveToFile();
                    System.out.println("\nData has been saved to files successfully!\n");
                    break;
                case 13: // Load data from files
                    clubController.reloadFromFile();
                    break;
                case 14: // Quit program
                    if (clubController.hasUnsavedChanges()) {
                        clubController.saveToFile();
                        playerController.saveToFile();
                        System.out.println("\nUnsaved changes detected — data has been saved.");
                    }
                    System.out.println("Goodbye !!!");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice >= 1 && choice <= 13);
    }
}
