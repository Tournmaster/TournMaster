package cat.udl.tidic.amb.tournmaster;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tournament {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("inscription_type")
    private String inscription;
    @SerializedName("start_date")
    private String start;
    @SerializedName("finish_date")
    private String finish;
    @SerializedName("finish_register_date")
    private String finish_register_date;
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;
    @SerializedName("facility")
    private Facility facility;
    @SerializedName("categories")
    private List<Category> categories;
    @SerializedName("description")
    private String description;
    @SerializedName("rounds")
    private List<Rounds> rounds;
    @SerializedName("owner")
    private String owner;
    @SerializedName("price_1")
    private String price_1;
    public Tournament(String id, String name, String inscription, String start, String status,
                      String type, Facility facility, List<Category> categories, String description, String owner, String finish, String price_1, String finish_register_date,List<Rounds> rounds) {
        this.id = id;
        this.name = name;
        this.inscription = inscription;
        this.start = start;
        this.status = status;
        this.type = type;
        this.facility = facility;
        this.categories = categories;
        this.description = description;
        this.owner = owner;
        this.finish = finish;
        this.price_1 = price_1;
        this.finish_register_date = finish_register_date;
        this.rounds = rounds;
    }

    public List<Rounds> getRounds() {
        return rounds;
    }

    public void setRounds(List<Rounds> rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString(){

        return "Id:" + id + " \n" +
                "Name:" + name + " \n" +
                "Inscription:" + inscription + " \n" +
                "Start:" + start + " \n" +
                "Finish:" + finish + " \n" +
                "finish_register_date:"+ finish_register_date + " \n" +
                "Status:" + status + " \n" +
                "Type:" + type + " \n" +
                "Categories:" + categories+ " \n" +
                "Owner:" + owner;
    }

    public Tournament(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getInscription() {
        return inscription;
    }

    public void setInscription(String inscription) {
        this.inscription = inscription;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getPrice_1() {
        return price_1;
    }

    public void setPrice_1(String price_1) {
        this.price_1 = price_1;
    }

    public String getFinish_register_date() {
        return finish_register_date;
    }

    public void setFinish_register_date(String finish_register_date) {
        this.finish_register_date = finish_register_date;
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
