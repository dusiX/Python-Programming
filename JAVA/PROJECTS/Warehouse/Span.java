import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

public class Span {
    private final double volume; //mm^3
    private final int id;
    private final int ShelfID;
    private static int weirdID;
    private final int numberofslots;
    private double spanvolume;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public int getID(){
        return this.id;
    }

    private int getNumberofslots(){
        return (int)(Math.random() * 3 + 1);
    }

    public Span(double volume, int ShelfID){
        this.id = Span.weirdID++;
        this.volume = volume;
        this.numberofslots = getNumberofslots();
        this.ShelfID = ShelfID;
        this.spanvolume = this.volume;
        save();
        Slots();
    }

    public void Slots(){

        for(int i = 0; i < numberofslots; i++){
            Slot slot;
            if(numberofslots == 1){
                slot = new Slot(spanvolume, this.ShelfID, this.id);
            } else if (i + 1 == numberofslots) {
                slot = new Slot(spanvolume, this.ShelfID, this.id);
            } else {
                double slotvolume = Math.random() * (this.volume/2) + 200;
                slot = new Slot(slotvolume, this.ShelfID, this.id);
                this.spanvolume -= slotvolume;
            }

            try {
                FileWriter w = new FileWriter("Span_" + this.id + "_slotsID.txt", true);
                w.append(String.valueOf(slot.getID())).append("\n");
                w.close();
            }catch(Exception e){
                System.out.println("No file found!");
            }
            try {
                FileWriter w = new FileWriter("Shelf_" + this.ShelfID + "_slotsID.txt", true);
                w.append(String.valueOf(slot.getID())).append("\n");
                w.close();
            }catch(Exception e){
                System.out.println("No file found!");
            }
        }
    }

    public void save(){
        try {
            FileWriter w = new FileWriter("Span_" + this.id + ".txt");
            w.write(this.id + "\n");
            w.write(this.volume + "\n");
            w.write(this.numberofslots + "\n");
            w.close();
        }catch(Exception e) {
            System.out.println("No file found!");
        }
    }

    @Override
    public String toString() {
        StringBuilder products = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("Span_" + this.id + "_productsID.txt"))) {
            String line = br.readLine();
            while (!Objects.equals(line, null)) {
                line = br.readLine();
                if (!Objects.equals(line, "-1")) {
                    products.append(br.readLine()).append(" ");
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Span info:\n\n- ID: " + this.id + "\n- Volume: " + df.format(this.volume) + " mm^3 \n- IDs of products in span: " + (products.isEmpty() ? "none" : products)  + "\n";
    }

    public void addProduct(Product p) throws IOException {
        String slot = "";
        String slotvolume = "";
        try (BufferedReader br1 = new BufferedReader(new FileReader("Span_" + this.id + "_slotsID.txt"))) {
            String line = br1.readLine();
            while (!Objects.equals(line, null)) {
                slot = line;
                try (BufferedReader br2 = new BufferedReader(new FileReader("Slot_" + slot + ".txt"))) {
                    for (int j = 0; j < 3; j++)
                        br2.readLine();
                    slotvolume = br2.readLine();
                    if (p.getVolume() <= Double.parseDouble(slotvolume)){
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                line = br1.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (p.getVolume() <= Double.parseDouble(slotvolume)) {
            //SLOT
            String todeleteinslot;
            StringBuilder saveinslot = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader("Slot_" + slot + ".txt"))) {
                for (int i = 0; i < 4; i++)
                    saveinslot.append(br.readLine()).append("\n");
                todeleteinslot = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (Objects.equals(todeleteinslot, "-1")) {
                FileWriter fw = new FileWriter("Slot_" + slot + ".txt");
                fw.write(saveinslot + "" + p.id);
                fw.close();
            }

            //SPAN
            String todeleteinspan, line;
            StringBuilder saveinspan1 = new StringBuilder();
            StringBuilder saveinspan2 = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader("Span_" + id + "_productsID.txt"))) {
                while (true) {
                    line = br.readLine();
                    if (Integer.parseInt(line) == Integer.parseInt(slot)) {
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
                FileWriter fw = new FileWriter("Span_" + id + "_productsID.txt");
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
                    if (Integer.parseInt(line) == Integer.parseInt(slot)) {
                        saveinshelf1.append(line).append("\n");
                        todeleteinshelf = br.readLine();
                        line = br.readLine();
                        while (!Objects.equals(line, null)) {
                            saveinshelf2.append(line).append("\n");
                            line = br.readLine();
                        }
                        break;
                    } else {
                        saveinshelf1.append(line).append("\n");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (Objects.equals(todeleteinshelf, "-1")) {
                FileWriter fw = new FileWriter("Shelf_" + this.ShelfID + "_productsID.txt");
                fw.write(saveinshelf1 + "" + p.id + "\n" + saveinshelf2);
                fw.close();
                System.out.println("Product added to slot number " + slot + " in span number " + this.id + " in shelf number " + this.ShelfID + "\n");
            }
        } else {
            System.out.println("Product too big for slots in this span!\n");
        }
    }

    public void getProduct(int Productid) throws IOException {
        String slot = "";
        String slotproductid = "";
        try (BufferedReader br1 = new BufferedReader(new FileReader("Span_" + this.id + "_slotsID.txt"))) {
            String line = br1.readLine();
            while (!Objects.equals(line, null)) {
                slot = line;
                try (BufferedReader br2 = new BufferedReader(new FileReader("Slot_" + slot + ".txt"))) {
                    for (int j = 0; j < 4; j++)
                        br2.readLine();
                    slotproductid = br2.readLine();
                    if (Productid == Integer.parseInt(slotproductid)){
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                line = br1.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (Productid <= Integer.parseInt(slotproductid)) {
            //SLOT
            String todeleteinslot;
            StringBuilder saveinslot = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader("Slot_" + slot + ".txt"))) {
                for (int i = 0; i < 4; i++)
                    saveinslot.append(br.readLine()).append("\n");
                todeleteinslot = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (!Objects.equals(todeleteinslot, "-1")) {
                FileWriter fw = new FileWriter("Slot_" + slot + ".txt");
                fw.write(saveinslot + "-1\n");
                fw.close();
            }

            //SPAN
            String todeleteinspan, line;
            StringBuilder saveinspan1 = new StringBuilder();
            StringBuilder saveinspan2 = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader("Span_" + this.id + "_productsID.txt"))) {
                while (true) {
                    line = br.readLine();
                    if (Integer.parseInt(line) == Integer.parseInt(slot)) {
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
            if (!Objects.equals(todeleteinspan, "-1")) {
                FileWriter fw = new FileWriter("Span_" + this.id + "_productsID.txt");
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
                    if (Integer.parseInt(line) == Integer.parseInt(slot)) {
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
            if (!Objects.equals(todeleteinshelf, "-1")) {
                FileWriter fw = new FileWriter("Shelf_" + this.ShelfID + "_productsID.txt");
                fw.write(saveinshelf1 + "-1\n" + saveinshelf2);
                fw.close();
                System.out.println("Product deleted. ID of deleted product is: " + todeleteinslot + "\n");
            }
        } else {
            System.out.println("This span does not contain the product!\n");
        }
    }
}
