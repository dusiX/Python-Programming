import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

public class Slot {
    private final double volume; //mm^3
    private final int id;
    private final int ShelfID;
    private final int SpanID;
    private static int weirdID;
    private Product product;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public int getID(){
        return this.id;
    }

    public Slot(double volume, int ShelfID, int SpanID){
        this.id = Slot.weirdID++;
        this.volume = volume;
        this.product = Product.getNullProduct();
        this.ShelfID = ShelfID;
        this.SpanID = SpanID;
        save();
    }

    @Override
    public String toString() {
        return "Slot info:\n\n- ID of slot: " + this.id + "\n- ID of span: " + this.SpanID + "\n- ID of shelf: "
                + this.ShelfID + "\n- Volume: " + df.format(this.volume) + " mm^3 \n- ID of product in slot: " + (this.product.id == -1 ? "none" : this.product.id) + "\n";
    }

    public void save(){
        try {
            FileWriter w = new FileWriter("Slot_" + this.id + ".txt");
            w.write(this.id + "\n");
            w.write(this.SpanID + "\n");
            w.write(this.ShelfID + "\n");
            w.write(this.volume + "\n");
            w.write(this.product.id + "\n");
            w.close();
        }catch(Exception e){
            System.out.println("No file found!");
        }
        try {
            FileWriter w = new FileWriter("Span_" + this.SpanID + "_productsID.txt", true);
            w.append(String.valueOf(this.id)).append("\n").append(String.valueOf(this.product.id)).append("\n");
            w.close();
        }catch(Exception e){
            System.out.println("No file found!");
        }
        try {
            FileWriter w = new FileWriter("Shelf_" + this.ShelfID + "_productsID.txt", true);
            w.append(String.valueOf(this.id)).append("\n").append(String.valueOf(this.product.id)).append("\n");
            w.close();
        }catch(Exception e){
            System.out.println("No file found!");
        }

    }

    public void addProduct(Product p) throws IOException {
        if (p.getVolume() <= this.volume) {
            //SLOT
            String todeleteinslot;
            StringBuilder saveinslot = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader("Slot_" + this.id + ".txt"))) {
                for (int i = 0; i < 4; i++)
                    saveinslot.append(br.readLine()).append("\n");
                todeleteinslot = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (Objects.equals(todeleteinslot, "-1")) {
                FileWriter fw = new FileWriter("Slot_" + this.id + ".txt");
                fw.write(saveinslot + "" + p.id);
                fw.close();
                this.product = p;
            }

            //SPAN
            String todeleteinspan, line;
            StringBuilder saveinspan1 = new StringBuilder();
            StringBuilder saveinspan2 = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader("Span_" + this.SpanID + "_productsID.txt"))) {
                while (true) {
                    line = br.readLine();
                    if (Integer.parseInt(line) == this.id) {
                        saveinspan1.append(line).append("\n");
                        todeleteinspan = br.readLine();
                        line = br.readLine();
                        while (!Objects.equals(line, null)) {
                            saveinspan2.append(line).append("\n");
                            line = br.readLine();
                        }
                        break;
                    } else {
                        saveinspan1.append(br.readLine()).append("\n");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (Objects.equals(todeleteinspan, "-1")) {
                FileWriter fw = new FileWriter("Span_" + this.SpanID + "_productsID.txt");
                fw.write(saveinspan1 + "" + p.id + "\n" + saveinspan2);
                fw.close();
            }

            //SHELF
            String todeleteinshelf;
            StringBuilder saveinshelf1 = new StringBuilder();
            StringBuilder saveinshelf2 = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader("Shelf_" + this.ShelfID + "_productsID.txt"))) {
                while (true) {
                    line = br.readLine();
                    if (Integer.parseInt(line) == this.id) {
                        saveinshelf1.append(line).append("\n");
                        todeleteinshelf = br.readLine();
                        line = br.readLine();
                        while (!Objects.equals(line, null)) {
                            saveinshelf2.append(line).append("\n");
                            line = br.readLine();
                        }
                        break;
                    } else {
                        saveinshelf1.append(br.readLine()).append("\n");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (Objects.equals(todeleteinshelf, "-1")) {
                FileWriter fw = new FileWriter("Shelf_" + this.ShelfID + "_productsID.txt");
                fw.write(saveinshelf1 + "" + p.id + "\n" + saveinshelf2);
                fw.close();
                System.out.println("Product added to slot number " + this.id + " in span number " + this.SpanID + " in shelf number " + this.ShelfID + "\n");
            }
        } else {
            System.out.println("Product too big for this slot!\n");
        }
    }

    public void delProduct() throws IOException {
        //SLOT
        String todeleteinslot;
        StringBuilder saveinslot = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("Slot_" + this.id + ".txt"))) {
            for (int i = 0; i < 4; i++)
                saveinslot.append(br.readLine()).append("\n");
            todeleteinslot = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!Objects.equals(todeleteinslot, "-1")){
            FileWriter fw = new FileWriter("Slot_" + this.id + ".txt");
            fw.write(saveinslot + "-1");
            fw.close();
        }

        //SPAN
        String todeleteinspan, line;
        StringBuilder saveinspan1 = new StringBuilder();
        StringBuilder saveinspan2 = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("Span_" + this.SpanID + "_productsID.txt"))) {
            while (true) {
                line = br.readLine();
                if (Integer.parseInt(line) == this.id){
                    saveinspan1.append(line).append("\n");
                    todeleteinspan = br.readLine();
                    line = br.readLine();
                    while (!Objects.equals(line, null)){
                        saveinspan2.append(line).append("\n");
                        line = br.readLine();
                    }
                    break;
                } else {
                    saveinspan1.append(br.readLine()).append("\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!Objects.equals(todeleteinspan, "-1")){
            FileWriter fw = new FileWriter("Span_" + this.SpanID + "_productsID.txt");
            fw.write(saveinspan1 + "-1\n" + saveinspan2);
            fw.close();
        }

        //SHELF
        String todeleteinshelf;
        StringBuilder saveinshelf1 = new StringBuilder();
        StringBuilder saveinshelf2 = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("Shelf_" + this.ShelfID + "_productsID.txt"))) {
            while (true) {
                line = br.readLine();
                if (Integer.parseInt(line) == this.id){
                    saveinshelf1.append(line).append("\n");
                    todeleteinshelf = br.readLine();
                    line = br.readLine();
                    while (!Objects.equals(line, null)){
                        saveinshelf2.append(line).append("\n");
                        line = br.readLine();
                    }
                    break;
                } else {
                    saveinshelf1.append(br.readLine()).append("\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!Objects.equals(todeleteinshelf, "-1")){
            FileWriter fw = new FileWriter("Shelf_" + this.ShelfID + "_productsID.txt");
            fw.write(saveinshelf1 + "-1\n" + saveinshelf2);
            fw.close();
            System.out.println("Product deleted. ID of deleted product is: " + todeleteinslot + "\n");
        } else {
            System.out.println("This slot is empty!\n");
        }
    }

}
