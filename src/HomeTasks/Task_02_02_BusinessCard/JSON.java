package HomeTasks.Task_02_02_BusinessCard;

import java.util.Arrays;

public class JSON {
    private String name;
    private String surname;
    private String[] phones;
    private String[] sites;
    private Address address;

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

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public String[] getSites() {
        return sites;
    }

    public void setSites(String[] sites) {
        this.sites = sites;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Card:\n" +
                "\tname='" + name + "\'\n" +
                "\tsurname='" + surname + "\'\n" +
                "\tphones=" + Arrays.toString(phones) + '\n' +
                "\tsites=" + Arrays.toString(sites) + '\n' +
                "\taddress=" + address;
    }
}
