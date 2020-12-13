import java.io.Serializable;

public class Bicycle implements Serializable{

    private String owner;
    private double value;
    private String make;

    public  Bicycle(String owner, double value, String make){
        setOwner(owner);
        setValue(value);
        setMake(make);
    }

    public  Bicycle(){
        this("Not Assigned", 0, "Not Assigned");
    }


    public void setOwner(String owner){
        this.owner = owner;
    }

    public void setMake(String make){
        this.make = make;
    }

    public void setValue(double value){
        this.value = value;
    }

    public String getOwner(){
        return owner;
    }

    public String getMake(){
        return make;
    }

    public double getValue(){
        return value;
    }

    public String toString() {
        return "Owner: " + getOwner() + " Make: " + getMake() + " Value: " + getValue();
    }
}
