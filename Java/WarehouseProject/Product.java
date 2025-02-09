import java.io.FileWriter;
import java.util.Random;

public class Product {

    public static Product getNullProduct(){
        return new Product("NONE\n");
    }

    public Product(String name){
        this.name = name;
        this.id = -1;
    }

    public static int weirdID = 0;

    public int id;
    private String name;
    private String color;
    private float length; //mm
    private float width; //mm
    private float height; //mm
    private float weight; //kg

    public void setWeirdID(){
        this.id = Product.weirdID++;
    }

    public Product(){
        this.setWeirdID();
        this.name = "Product";
        this.setColor("none");
        this.setDimensions(0, 0, 0);
        this.setWeight(0);
        save();
    }

    public void setDimensions(float length, float width, float height){
        if (length>=0 && length<=1500 && width>=0 && width<=1500 && height>=0 && height<=1500){
            this.length = length;
            this.width = width;
            this.height = height;
        } else{
            System.out.print("Incorrect variables!\n");
        }
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setWeight(float weight){
        if (weight > 0 && weight <= 500){
            this.weight = weight;
        } else{
            System.out.print("Incorrect variables!\n");
        }
    }

    public void save(){
        try {
            FileWriter w = new FileWriter(this.name + "_" + this.id + ".txt");
            w.write(this.name + "\n");
            w.write(this.color + "\n");
            w.write(this.length + "\n");
            w.write(this.width + "\n");
            w.write(this.height + "\n");
            w.write(this.weight + "\n");
            w.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }

    }

    @Override
    public String toString(){
        String x = "Details of the product:\n\n- ID: " + this.id + "\n- name: " + this.name + "\n- color: " + this.color + "\n- length: " + this.length + " mm\n- width: "
                + this.width + " mm\n- height: " + this.height + " mm\n- weight: " + this.weight + " kg\n\n";

        return x;
    }

    public Product(String name, String color, float  length, float width, float height, float weight){
        this.setWeirdID();
        this.name = name;
        this.setColor(color);
        this.setDimensions(length, width, height);
        this.setWeight(weight);
        save();
    }

    public Product(String color, float  length, float width, float height, float weight){
        this.setWeirdID();
        this.name = "Product";
        this.setColor(color);
        this.setDimensions(length, width, height);
        this.setWeight(weight);
        save();
    }

    public boolean isEmptyProduct(){
        return this.id == -1;
    }

    public double getVolume(){
        return this.height*this.width*this.length;
    }

    public static void getRandomSet(int max, int startID){
        Product.weirdID = startID;
        for (int i = 0; i < max; i++){
            Random rr = new Random();
            Product p = new Product("Blue", rr.nextFloat(1500), rr.nextFloat(1500), rr.nextFloat(1500), rr.nextFloat(500));
            System.out.println(p.toString());
        }
    }

}
