package rita.mysql1;

public class Flat {
    private int id;
    private String district;
    private String address;
    private double square;
    private int rooms;
    private double price;

    public int getId() {
        return id;
    }

    public String getDistrict() {
        return district;
    }

    public String getAddress() {
        return address;
    }

    public double getSquare() {
        return square;
    }

    public int getRooms() {
        return rooms;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return "Flat {" +
                "district: " + district +
                ", address: " + address +
                ", square: " + square +
                ", rooms: " + rooms +
                ", price: " + price +
                "}";
    }
}
