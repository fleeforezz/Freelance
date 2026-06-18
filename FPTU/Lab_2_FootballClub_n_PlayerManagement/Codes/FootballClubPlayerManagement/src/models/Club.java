package models;

public class Club {
    
    private String clubId;
    private String clubName;
    private String sponserBrand;
    private int budget;

    public Club() {
    }

    public Club(String clubId, String clubName, String sponserBrand, int budget) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.sponserBrand = sponserBrand;
        this.budget = budget;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getSponcerBrand() {
        return sponserBrand;
    }

    public void setSponcerBrand(String sponcerBrand) {
        this.sponserBrand = sponcerBrand;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s, %d",
                clubId,
                clubName,
                sponserBrand,
                budget
        );
    }

    /*
        #####################
        Display Club Info
        #####################
     */
    public String display() {
        return String.format(
                "| %-8s | %-24s | %-13s | %-12d |\n",
            this.clubId, this.clubName, this.sponserBrand, this.budget
        );
    }
}
