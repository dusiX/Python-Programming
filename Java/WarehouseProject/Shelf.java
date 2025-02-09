import java.io.*;
import java.text.DecimalFormat;
import java.util.Objects;

public class Shelf {
    double volume;
    private final int id;
    private static int weirdID;
    private final int numberofspans;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private int getNumberofspans(){
        return (int)(Math.random() * 5 + 1);
    }

    public Shelf(double volume){
        this.volume = volume;
        this.id = weirdID++;
        this.numberofspans = getNumberofspans();
        save();
        Spans();
    }

    public void Spans(){
        for(int i = 0; i < numberofspans; i++){
            Span span = new Span(this.volume/numberofspans, this.id);
            try {
                FileWriter w = new FileWriter("Shelf_" + this.id + "_spansID.txt", true);
                w.append(String.valueOf(span.getID())).append("\n");
                w.close();
            }catch(Exception e){
                System.out.println("No file found!");
            }
        }
    }

    public void save(){
        try {
            FileWriter w = new FileWriter("Shelf_" + this.id + ".txt");
            w.write(this.id + "\n");
            w.write(this.volume + "\n");
            w.write(this.numberofspans + "\n");
            w.close();
        }catch(Exception e) {
            System.out.println("No file found!");
        }
    }

    @Override
    public String toString() {
        StringBuilder products = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("Shelf_" + this.id + "_productsID.txt"))) {
            String line = br.readLine();
            while (!Objects.equals(line, null)) {
                line = br.readLine();
                if (!Objects.equals(line, "-1")) {
                    products.append(br.readLine()).append(" ");
                } else {
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Shelf info: \n- Volume: " + df.format(this.volume) + " mm^3 \n- Number of spans: " + this.numberofspans + "\n- IDs of products in shelf: " + (products.isEmpty() ? "none" : products) + "\n";
    }

    public void addProduct(Product p) throws IOException {
        String slot = "";
        String slotvolume = "";
        String span = "";
        try (BufferedReader br1 = new BufferedReader(new FileReader("Shelf_" + this.id + "_slotsID.txt"))) {
            String line = br1.readLine();
            while (!Objects.equals(line, null)) {
                slot = line;
                try (BufferedReader br2 = new BufferedReader(new FileReader("Slot_" + slot + ".txt"))) {
                    br2.readLine();
                    span = br2.readLine();
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
            try (BufferedReader br = new BufferedReader(new FileReader("Span_" + span + "_productsID.txt"))) {
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
                        saveinspan1.append(line).append("\n");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (Objects.equals(todeleteinspan, "-1")) {
                FileWriter fw = new FileWriter("Span_" + span + "_productsID.txt");
                fw.write(saveinspan1 + "" + p.id + "\n" + saveinspan2);
                fw.close();
            }

            //SHELF
            String todeleteinshelf;
            StringBuilder saveinshelf1 = new StringBuilder();
            StringBuilder saveinshelf2 = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader("Shelf_" + this.id + "_productsID.txt"))) {
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
                FileWriter fw = new FileWriter("Shelf_" + this.id + "_productsID.txt");
                fw.write(saveinshelf1 + "" + p.id + "\n" + saveinshelf2);
                fw.close();
                System.out.println("Product added to slot number " + slot + " in span number " + span + " in shelf number " + this.id + "\n");
            }
        } else {
            System.out.println("Product too big for slots in this shelf!\n");
        }
    }

    public void getProduct(int Productid) throws IOException {
        String slot = "";
        String slotproductid = "";
        String span = "";
        try (BufferedReader br1 = new BufferedReader(new FileReader("Shelf_" + this.id + "_slotsID.txt"))) {
            String line = br1.readLine();
            while (!Objects.equals(line, null)) {
                slot = line;
                try (BufferedReader br2 = new BufferedReader(new FileReader("Slot_" + slot + ".txt"))) {
                    br2.readLine();
                    span = br2.readLine();
                    for (int j = 0; j < 2; j++)
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
            try (BufferedReader br = new BufferedReader(new FileReader("Span_" + span + "_productsID.txt"))) {
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
                FileWriter fw = new FileWriter("Span_" + span + "_productsID.txt");
                fw.write(saveinspan1 + "-1\n" + saveinspan2);
                fw.close();
            }

            //SHELF
            String todeleteinshelf;
            StringBuilder saveinshelf1 = new StringBuilder();
            StringBuilder saveinshelf2 = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader("Shelf_" + this.id + "_productsID.txt"))) {
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
                    line = br.readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (!Objects.equals(todeleteinshelf, "-1")) {
                FileWriter fw = new FileWriter("Shelf_" + this.id + "_productsID.txt");
                fw.write(saveinshelf1 + "-1\n" + saveinshelf2);
                fw.close();
                System.out.println("Product deleted. ID of deleted product is: " + todeleteinslot + "\n");
            }
        } else {
            System.out.println("This shelf does not contain the product!\n");
        }
    }

}
