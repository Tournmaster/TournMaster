package cat.udl.tidic.amb.tournmaster;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rounds {
    @SerializedName("round_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("category_id")
    private String idCategory;
    @SerializedName("tournament_id")
    private String idTournament;
    @SerializedName("tournament")
    private Tournament tournament;
    @SerializedName("matches")
    private List<Match> matches;
    public Rounds(String id,String name,String idCategory,String idTournament,Tournament tournament,List<Match> matches){
        this.id = id;
        this.name = name;
        this.idCategory = idCategory;
        this.idTournament = idTournament;
        this.tournament = tournament;
        this.matches = matches;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(String idTournament) {
        this.idTournament = idTournament;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }


    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Tournament)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Tournament e = (Tournament) o;

        // Compare the data members and return accordingly
        return this.id == e.getId()
                && this.name.equals(e.getName());

    }
}
