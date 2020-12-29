package model;

public class House {

    private int id;
    private String name;
    private String description;
    private String direction;
    private int city;
    private String postalCode;
    private int numMaxHabitantes;
    private int numHabit;
    private int numWc;
    private boolean amueb;
    private double nightPrice;
    private int owner;
    private int numReservas;

    public House(int id, String name, String description, String direction, int city, String postalCode,
            int numMaxHabitantes, int numHabit, int numWc, boolean amueb, double nightPrice, int owner, int numReservas) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.direction = direction;
        this.city = city;
        this.postalCode = postalCode;
        this.numMaxHabitantes = numMaxHabitantes;
        this.numHabit = numHabit;
        this.numWc = numWc;
        this.amueb = amueb;
        this.nightPrice = nightPrice;
        this.owner = owner;
        this.numReservas = numReservas;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getNumMaxHabitantes() {
        return numMaxHabitantes;
    }

    public void setNumMaxHabitantes(int numMaxHabitantes) {
        this.numMaxHabitantes = numMaxHabitantes;
    }

    public int getNumHabit() {
        return numHabit;
    }

    public void setNumHabit(int numHabit) {
        this.numHabit = numHabit;
    }

    public int getNumWc() {
        return numWc;
    }

    public void setNumWc(int numWc) {
        this.numWc = numWc;
    }

    public boolean isAmueb() {
        return amueb;
    }

    public void setAmueb(boolean amueb) {
        this.amueb = amueb;
    }

    public double getNightPrice() {
        return nightPrice;
    }

    public void setNightPrice(double nightPrice) {
        this.nightPrice = nightPrice;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getNumReservas() {
        return numReservas;
    }

    public void setNumReservas(int numReservas) {
        this.numReservas = numReservas;
    }

    @Override
    public String toString() {
        return "House{" + "id=" + id + ", name=" + name + ", description=" + description + ", "
                + "direction=" + direction + ", city=" + city + ", postalCode=" + postalCode + ", "
                + "numMaxHabitantes=" + numMaxHabitantes + ", numHabit=" + numHabit + ", numWc=" + numWc + ", "
                + "amueb=" + amueb + ", nightPrice=" + nightPrice + ", owner=" + owner + ", "
                + "numReservas=" + numReservas + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final House other = (House) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
