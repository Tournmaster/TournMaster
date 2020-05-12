package cat.udl.tidic.amb.tournmaster;

import com.google.gson.annotations.SerializedName;

public class Facility {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("province")
    private String province;

    @SerializedName("town")
    private String town;

    @SerializedName("latitude")
    private float latitude;

    @SerializedName("longitude")
    private float longitude;

    public Facility() {
    }

    public Facility(int id, String name, String province, String town, float latitude, float longitude) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.town = town;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
