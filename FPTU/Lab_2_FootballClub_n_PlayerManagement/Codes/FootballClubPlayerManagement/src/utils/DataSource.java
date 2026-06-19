package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Club;
import models.Player;

public class DataSource {
    
    private static DataSource instance;
    private boolean dataChanged = false;
    
    public void markChanged() {
        dataChanged = true;
    }
    
    public boolean isDataChanged() {
        return dataChanged;
    }
    
    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }
    
    private DataSource() { }

    /*
     * ###################
     * Club
     * ###################
     */
//    private static final String CLUB_FILE_PATH = "D:\\Repository\\Github\\Fleeforezz\\Lab\\teaching\\Coding\\clubs.txt";
    private final String CLUB_FILE_PATH = "D:\\Github\\fleeforezz\\Freelance\\FPTU\\Lab_2_FootballClub_n_PlayerManagement\\Codes\\FootballClubPlayerManagement\\src\\data\\clubs.txt";

    /*
     * ###################
     * Club
     * ###################
     */
//    private static final String PLAYER_FILE_PATH = "D:\\Repository\\Github\\Fleeforezz\\Lab\\teaching\\Coding\\players.txt";
    private final String PLAYER_FILE_PATH = "D:\\Github\\fleeforezz\\Freelance\\FPTU\\Lab_2_FootballClub_n_PlayerManagement\\Codes\\FootballClubPlayerManagement\\src\\data\\players.txt";
    
    private List<Club> clubs = null;
    private List<Player> players = null;
    
    public List<Club> clubList() {
        if (clubs == null) {
            clubs = loadClubs();
        }
        
        return clubs;
    }
    
    public List<Player> playerList() {
        if (players == null) {
            players = loadPLayers();
        }
        
        return players;
    }
    
    /*
    * #####################################################
    * Combine 2 list for load the data up
    * #####################################################
    */
    public boolean reloadAll() {
        List<Club> newClubs;
        List<Player> newPlayers;
        try {
            newClubs = loadClubs();
            newPlayers = loadPLayers();
        } catch (Exception e) {
            System.out.println("Load data failed!");
            return false;
        }

        if (clubs == null) {
            clubs = new ArrayList<>();
        }
        if (players == null) {
            players = new ArrayList<>();
        }
        clubs.clear();
        clubs.addAll(newClubs);
        players.clear();
        players.addAll(newPlayers);

        System.out.println("Load data successfully!");
        return true;
    }
    
    /*
    * #####################################################
    * Load clubs.txt file from machine and add to Club List
    * #####################################################
    */
    public List<Club> loadClubs() {
        List<Club> clubs = new ArrayList<>();

         try (BufferedReader br = new BufferedReader(new FileReader(CLUB_FILE_PATH))) {
             String line;
             
             while ((line = br.readLine()) != null) {                 
                 if (line.trim().isEmpty()) {
                     continue;
                 }
                 
                 String[] parts = line.split(",");
                 
                 if (parts.length != 4) {
                     System.out.println("Invalid record" + line);
                     continue;
                 }
                 
                 String id = parts[0].trim();
                 String name = parts[1].trim();
                 String sponcerBrand = parts[2].trim();
                 int budget = Integer.parseInt(parts[3].trim());
                 
                 Club club = new Club(
                         id,
                         name,
                         sponcerBrand,
                         budget
                 );
                 
                 clubs.add(club);
             }
             
         } catch (IOException e) {
             System.out.println("Cannot read file: " + e.getMessage());
         } catch (NumberFormatException e) {
             System.out.println("Invalid budget format: " + e.getMessage());
         }
        
        return clubs;
    }
    
    /*
    * ########################################################
    * Load player.txt file from machine and add to Player List
    * ########################################################
    */
    public List<Player> loadPLayers() {
        List<Player> players = new ArrayList<>();

         try (BufferedReader br = new BufferedReader(new FileReader(PLAYER_FILE_PATH))) {
             String line;
             
             while ((line = br.readLine()) != null) {                 
                 if (line.trim().isEmpty()) {
                     continue;
                 }
                 
                 String[] parts = line.split(",");
                 
                 if (parts.length != 5) {
                     System.out.println("Invalid record" + line);
                     continue;
                 }
                 
                 String playerId = parts[0].trim();
                 String clubId = parts[1].trim();
                 String playerName = parts[2].trim();
                 String position = parts[3].trim();
                 int shirtNumber = Integer.parseInt(parts[4].trim());
                 
                 Player player = new Player(
                         playerId,
                         clubId,
                         playerName,
                         position,
                         shirtNumber
                 );
                 
                 players.add(player);
             }
         } catch (IOException e) {
             System.out.println("Cannot read file: " + e.getMessage());
         } catch (NumberFormatException e) {
             System.out.println("Invalid budget format: " + e.getMessage());
         }
        
        return players;
    }
    
    /*
    * ########################################################
    * Save club list to file
    * ########################################################
    */
    public void saveClubs(List<Club> clubsToSave) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CLUB_FILE_PATH))) {
            for (Club c : clubsToSave) {
                bw.write(c.getClubId() + ", " + c.getClubName() + ", "
                        + c.getSponcerBrand() + ", " + c.getBudget());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot write club file: " + e.getMessage());
        }
    }

    /*
    * ########################################################
    * Save player list to file
    * ########################################################
    */
    public void savePlayers(List<Player> playersToSave) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PLAYER_FILE_PATH))) {
            for (Player p : playersToSave) {
                bw.write(p.getPlayerId() + ", " + p.getClubId() + ", "
                        + p.getPlayerName() + ", " + p.getPosition() + ", " + p.getShirtNumber());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot write player file: " + e.getMessage());
        }
    }

    public String getClubFilePath() {
        return CLUB_FILE_PATH;
    }

    public String getPlayerFilePath() {
        return PLAYER_FILE_PATH;
    }
}
