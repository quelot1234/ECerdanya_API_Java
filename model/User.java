package model;

import controller.UserController;
import dao.EcDao;
import java.util.Date;

public class User {

    private int id;
    private String firstname;
    private String[] surnames;
    private String email;
    private String telf;
    private String postalCode;
    private String direction;
    private String token;
    private Date lastLoginAt;

    public User(String firstname, String[] surnames, String email, String telf, String postalCode, String direction) {
        this.firstname = firstname;
        this.surnames = surnames;
        this.email = email;
        this.telf = telf;
        this.postalCode = postalCode;
        this.direction = direction;
        this.id = EcDao.getInstance().getUserId(this);
        this.token = EcDao.getInstance().getUserToken(this);
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String[] getSurnames() {
        return surnames;
    }

    public String getFirstSurname() {
        return this.surnames[0];
    }

    public String getLastname() {
        return this.surnames[1];
    }

    public void setSurnames(String[] surnames) {
        this.surnames = surnames;
    }

    public void setFirstSurname(String firstSurname) {
        this.surnames[0] = firstSurname;
    }

    public void setLastName(String lastName) {
        this.surnames[1] = lastName;
    }

    public String getFullName() {
        return this.firstname + " " + this.surnames[0] + " " + this.surnames[1];
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastLogin() {
        return lastLoginAt;
    }

    public void setLastLoginDate(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstname=" + firstname + ", surnames=" + surnames + ", "
                + "email=" + email + ", telf=" + telf + ", postalCode=" + postalCode + ", "
                + "direction=" + direction + ", token=" + token + ", lastLoginAt=" + lastLoginAt + '}';
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
