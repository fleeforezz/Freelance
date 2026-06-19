package controllers;

import interfaces.IList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import models.Player;
import utils.Acceptable;
import utils.DataSource;
import utils.Inputter;
import views.UI;

public class PlayerController implements IList<Player> {
    
    DataSource context = DataSource.getInstance();
    ClubController clubController = new ClubController();
    
    // UI Components
    String[] clubCols = {
            "Id",
            "Name",
            "Sponcer Brand",
            "Budget"
        };

    int[] clubColWidth = {
        8, 24, 13, 12
    };

    /*
     * ####################################################
     * Add new player
     * ####################################################
     */
    @Override
    public Player addRec() {
        
        // Input player id
        String playerId = Inputter.getString(
                "\nEnter player id: ",
                true, "Player id cannot be empty!!!",
                true, Acceptable.PLAYER_VALID_ID, "Player id must be Pxxxx (e.g., P0001)",
                true, "This player already exists!!!",
                id -> {
                    if (searchRecById(id) != null) {
                        return false;
                    }
                    return true;
                }
        );
        
        // Show club table
        UI.tableHeader(clubCols, clubColWidth);
        clubController.displayRec();
        UI.footer();
        
        // Input club id
        String clubId = Inputter.getString(
                "\nEnter club id: ",
                true, "Club id cannot be empty!!!",
                true, Acceptable.CLUB_VALID_ID, "Club id must be CL-xxxx (e.g., CL-0001)",
                true, "Club not found!!!",
                id -> {
                    if (clubController.searchRecById(id) == null) {
                        return false;
                    }
                    return true;
                }
        );
        
        // Input player name
        String playerName = Inputter.getString(
                "Enter player name: ",
                "Player name cannot be empty!!!"
        );
        
        // Input player position
        String position = Inputter.getString(
                "Enter position (Goalkeeper/Defender/Midfielder/Forward/Winger): ",
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
        
        // Input shirt number
        int shirtNumber = 0;
        while (true) {
            shirtNumber = Inputter.getInt(
                    "Enter new shirt number: ",
                    1, 99,
                    true
            );

            if (existShirtNumberInClub(shirtNumber, clubId) == null) {
                break;
            } else {
                System.out.println("Shirt number already exists in this club!");
            }
        }
        
        // Create new player object
        Player newPlayer = new Player(
                playerId,
                clubId,
                playerName,
                position,
                shirtNumber
        );
        
        // Add new player object to PLayer List
        context.playerList().add(newPlayer);
        context.markChanged();
        
        // Confirm and save file
        if (Inputter.confirmSave(
                "Player"
        )) {
            return newPlayer;
        }
        
        return null;
    }

    /*
     * ####################################################
     * Update selected player
     * ####################################################
     */
    @Override
    public Player updateRec(String id) {
        
        // Get exist player
        Player existPlayer = searchRecById(id);

        // Return null if no player found
        if (existPlayer == null) {
            return null;
        }

        // Input updated player name
        String playerName = Inputter.getString(
                "\nEnter new player name: "
        );
        if (!playerName.isEmpty()) {
            existPlayer.setPlayerName(playerName);
            context.markChanged();
        }

        // Input updated position
        String position = Inputter.getString(
                "Enter new position (Goalkeeper/Defender/Midfielder/Forward/Winger): ",
                false, null,
                false, null, null,
                true, "Invalid position! Must be one of: Goalkeeper, Defender, Midfielder, Forward, Winger",
                input -> {
                    if (input.isEmpty()) return true;
                    for (String pos : Acceptable.AVAILABLE_POSITION) {
                        if (pos.equalsIgnoreCase(input.trim())) {
                            return true;
                        }
                    }
                    return false;
                }
        );
        if (!position.isEmpty()) {
            existPlayer.setPosition(position);
            context.markChanged();
        }

        // Input updated shirt number
        while (true) {
            int shirtNumber = Inputter.getInt(
                    "Enter new shirt number: ",
                    1, 99,
                    true
            );

            if (shirtNumber == -1) {
                break;
            }

            if (existShirtNumberInClub(shirtNumber, existPlayer.getClubId()) == null) {
                existPlayer.setShirtNumber(shirtNumber);
                context.markChanged();
                break;
            } else {
                System.out.println("Shirt number already exists in this club!");
            }
        }

        // Confirm and save changes to list
        if (Inputter.confirmSave("Player")) {
            return existPlayer;
        }

        return null;
    }

    /*
     * ####################################################
     * Delete player with player id
     * ####################################################
     */
    @Override
    public boolean deleteRec(String id) {
        
        // Get exist player
        Player player = searchRecById(id);
        
        // Return null if no player found
        if (player == null) {
            return false;
        }

        // Remove player from player list
        context.playerList().remove(player);
        context.markChanged();

        // Confirm and save changes to list
        Inputter.confirmSave(
                "Player"
        );
        
        return true;
    }

    /*
     * ####################################################
     * List all player
     * ####################################################
     */
    @Override
    public List<Player> listAll() {
        return context.playerList();
    }
    
    /*
     * ####################################################
     * List players with format from A-Z
     * ####################################################
     */
    public List<Player> listAllAcending() {
        List<Player> players = listAll();
        
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player a, Player b) { 
                return a.getPlayerName().compareTo(b.getPlayerName());
            }
        });
        
        return players;
    }
    
    /*
     * ####################################################
     * List players with format from A-Z
     * ####################################################
     */
    public List<Player> listAllByPosition(String position) {
        List<Player> foundPlayers = new ArrayList<>();
        
        for (Player player : context.playerList()) {
            if (player.getPosition().equalsIgnoreCase(position)) {
                foundPlayers.add(player);
            }
        }
        
        return foundPlayers;
    }

    /*
     * ####################################################
     * Search player by id
     * ####################################################
     */
    @Override
    public Player searchRecById(String id) {
        
        for (Player player : context.playerList()) {
            if (player.getPlayerId().equalsIgnoreCase(id)) {
                return player;
            }
        }
        
        return null;
    }
    
    /*
     * ####################################################
     * Search player by name
     * ####################################################
     */
    public List<Player> searchRecByName(String name) {
        List<Player> foundPlayers = new ArrayList<>();
        
        for (Player player : context.playerList()) { 
            if (player.getPlayerName().toLowerCase().contains(name.toLowerCase())) {
                foundPlayers.add(player);
            }
        }
        
        return foundPlayers;
    }
    
    /*
     * ####################################################################
     * Search if there's an inputted shirt number is exists in current club
     * ####################################################################
     */
    public Player existShirtNumberInClub(int shirtNumber, String clubId) {
        List<Player> players = listAll();
        
        for (Player player : players) {
            if (player.getClubId().equalsIgnoreCase(clubId)) {
                if (player.getShirtNumber() == shirtNumber) {
                    return player;
                }
            }
        }
        
        return null;
    }

    /*
     * ####################################################
     * Display data
     * ####################################################
     */
    @Override
    public void displayRec() {
        List<Player> players = listAllAcending();

        if (players.isEmpty()) {
            System.out.println(
                    "|                        No data in the system                       |"
            ); 
        }

        for (Player player : players) {
            System.out.print(player.display());
        }
    }
    
    public void saveToFile() {
        context.savePlayers(context.playerList());
    }
}
