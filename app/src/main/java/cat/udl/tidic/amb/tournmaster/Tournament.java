package cat.udl.tidic.amb.tournmaster;

import com.google.gson.annotations.SerializedName;

public class Tournament {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("inscription_type")
    private String inscription;
    @SerializedName("start_date")
    private String start;
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;
    @SerializedName("facility")
    private String facility;
    @SerializedName("categories")
    private String categories;
    @SerializedName("description")
    private String description;

public Tournament(String name,String inscription,String start,String status,String type,String facility,String categories, String description){
    this.name = name;
    this.inscription = inscription;
    this.start = start;
    this.status= status;
    this.type = type;
    this.facility = facility;
    this.categories = categories;
    this.description = description;
}
    public Tournament(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof User)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        User e = (User) o;

        // Compare the data members and return accordingly
        return this.id == e.getId()
                && this.name.equals(e.getUsername())
                && this.name.equals(e.getName());

    }

}
