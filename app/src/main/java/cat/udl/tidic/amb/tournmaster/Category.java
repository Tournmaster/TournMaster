package cat.udl.tidic.amb.tournmaster;

import com.google.gson.annotations.SerializedName;

enum TournamentGenere {
    H("H","Male"),F("F","Female"),X("X","Mixt");

    String name;
    String id;

    TournamentGenere(String _id, String _name){
        id = _id;
        name = _name;
    }
}

enum TournamentAge {
    M("M","Juniors"),S("S","Seniors");

    String name;
    String id;

    TournamentAge(String _id, String _name){
        id = _id;
        name = _name;
    }
}

public class Category {


    @SerializedName("id")
    private int id;

    @SerializedName("age")
    private TournamentAge age;

    @SerializedName("genere")
    private TournamentGenere genere;

    @SerializedName("level")
    private String level;


    public Category() {
    }

    public Category(int id, TournamentAge age, TournamentGenere genere, String level) {
        this.id = id;
        this.age = age;
        this.genere = genere;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TournamentAge getAge() {
        return age;
    }

    public void setAge(TournamentAge age) {
        this.age = age;
    }

    public TournamentGenere getGenere() {
        return genere;
    }

    public void setGenere(TournamentGenere genere) {
        this.genere = genere;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString(){
        return "" + this.age.name;
    }
    public void antirepeat(){

    }
}
