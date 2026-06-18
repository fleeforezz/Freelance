package models;

public class Player {

    private String playerId;
    private String clubId;
    private String playerName;
    private String position;
    private int shirtNumber;

    public Player() {
    }

    public Player(String playerId, String clubId, String playerName, String position, int shirtNumber) {
        this.playerId = playerId;
        this.clubId = clubId;
        this.playerName = playerName;
        this.position = position;
        this.shirtNumber = shirtNumber;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }
    
    public int compareTo(Player player) {
        return this.playerName.compareToIgnoreCase(player.playerName);
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s, %s, %d",
                playerId,
                clubId,
                playerName,
                position,
                shirtNumber
        );
    }
        
    /*
    #####################
    Display Club Info
    #####################
    */
    public String display() {
        return String.format(
                "| %-8s | %-8s | %-24s | %-13s | %-12s |\n",
                this.playerId, this.clubId, this.playerName, this.position, this.shirtNumber
        );
    }
}
