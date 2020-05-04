package cat.udl.tidic.amb.tournmaster;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("genere")
    private String genere;
    @SerializedName("phone")
    private String phone;
    @SerializedName("photo")
    private String photo;
    @SerializedName("password")
    private String password;
    @SerializedName("prefsmash")
    private String PrefSmash;
    @SerializedName("position")
    private String Posicion;
    @SerializedName("rol")
    private String Rol;
    @SerializedName("matchname")
    private String Matchname;
    @SerializedName("club")
    private String Club;
    @SerializedName("license")
    private String license;


    // @JordiMateoUdl: Aqui tots els camps amb SerializedName aixi no caldrà treballar amb JSON...
    public User( String username, String email, String name, String surname, String genere,String password,String prefSmash, String posicion,String rol,String matchname,String club) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.genere = genere;
        this.password= password;
        this.Posicion= posicion;
        this.PrefSmash = prefSmash;
        this.Rol = rol;
        this.Matchname = matchname;
        this.Club = club;
    }

    public User(){

    }

    public String getMatchname() {
        return Matchname;
    }

    public void setMatchname(String matchname) {
        Matchname = matchname;
    }

    public String getClub() {
        return Club;
    }

    public void setClub(String club) {
        Club = club;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
        if(rol.equals("Organizador")){
            PrefSmash = "O";
        }
        if(genere.equals("Jugador")){
            PrefSmash = "P";
        }
    }

    public String getPrefSmash() {
        return PrefSmash;
    }

    public void setPrefSmash(String prefSmash) {
        PrefSmash = prefSmash;
        if(prefSmash.equals("Saque")){
            PrefSmash = "S";
        }
        if(prefSmash.equals("Right")){
            PrefSmash = "R";
        }
        if(prefSmash.equals("Globo")){
            PrefSmash = "G";
        }
        if(prefSmash.equals("Cortada")){
            PrefSmash = "C";
        }
        if(prefSmash.equals("Smash")){
            PrefSmash = "M";
        }
        if(prefSmash.equals("Volea")){
            PrefSmash = "V";
        }
    }

    public String getPosicion() {
        return Posicion;
    }

    public void setPosicion(String posicion) {
        Posicion = posicion;
        if(posicion.equals("Derecha")){
            Posicion = "R";
        }
        if(posicion.equals("Izquierda")){
            Posicion = "L";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
        if(genere.equals("Hombre")){
            PrefSmash = "H";
        }
        if(genere.equals("Mujer")){
            PrefSmash = "F";
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @NonNull
    @Override
    public String toString(){
        return this.name + " " + this.surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                && this.username.equals(e.getUsername())
                && this.name.equals(e.getName());

    }

    public String getPrefSmashText() {

        if(this.PrefSmash.equals("S")){
            return"Saque";
        }
        if(this.PrefSmash.equals("R")){
            return"Right";
        }
        if(this.PrefSmash.equals("G")){
            return"Globo";
        }
        if(this.PrefSmash.equals("C")){
            return"Cortada";
        }
        if(this.PrefSmash.equals("M")){
            return"Smash";
        }
        if(this.PrefSmash.equals("V")){
            return"Volea";
        }else{
            return "Error";
        }
    }


    public String getGenereText() {

        if(this.genere.equals("M")){
            return"Hombre";
        }else{
            return "Mujer";
        }
    }

    public String hasLicense(){
        if (this.license == null){
            return "No tiene licencia";
        }
        else {
            if (this.license.equals("Y")) {
                return "Tiene licencia";
            } else {
                return "No tiene licencia";
            }
        }
    }

    public String getPositionText() {

        if(this.Posicion.equals("R")){
            return "Derecha";
        }else{
            return "Izquierda";
        }
    }

    public String getRolText() {

        if(this.Rol.equals("O")){
            return "Organizador";
        }else{
            return "Jugador";
        }
    }


}
