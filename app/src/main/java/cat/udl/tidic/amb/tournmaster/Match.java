package cat.udl.tidic.amb.tournmaster;

import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("id")
    private int id;
    @SerializedName("couple1_player1")
    private User couple1Player1_id;
    @SerializedName("couple1_player2")
    private User couple1Player2_id;
    @SerializedName("couple2_player1")
    private User couple2Player1_id;
    @SerializedName("couple2_player2")
    private User couple2Player2_id;
    @SerializedName("set1")
    private String set1;
    @SerializedName("set2")
    private String set2;
    @SerializedName("set3")
    private String set3;


    public User getCouple1Player1_id() {
        return couple1Player1_id;
    }

    public void setCouple1Player1_id(User couple1Player1_id) {
        this.couple1Player1_id = couple1Player1_id;
    }

    public User getCouple1Player2_id() {
        return couple1Player2_id;
    }

    public void setCouple1Player2_id(User couple1Player2_id) {
        this.couple1Player2_id = couple1Player2_id;
    }

    public User getCouple2Player1_id() {
        return couple2Player1_id;
    }

    public void setCouple2Player1_id(User couple2Player1_id) {
        this.couple2Player1_id = couple2Player1_id;
    }

    public User getCouple2Player2_id() {
        return couple2Player2_id;
    }

    public void setCouple2Player2_id(User couple2Player2_id) {
        this.couple2Player2_id = couple2Player2_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getSet1() {
        return set1;
    }

    public void setSet1(String set1) {
        this.set1 = set1;
    }

    public String getSet2() {
        return set2;
    }

    public void setSet2(String set2) {
        this.set2 = set2;
    }

    public String getSet3() {
        return set3;
    }

    public void setSet3(String set3) {
        this.set3 = set3;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Match)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Match e = (Match) o;

        // Compare the data members and return accordingly
        return this.couple1Player1_id.equals((e.couple1Player1_id))
                &&  this.couple1Player2_id.equals(e.couple1Player2_id)
                &&  this.couple2Player1_id.equals(e.couple2Player1_id)
                &&  this.couple2Player2_id.equals(e.couple2Player2_id);

    }



}
