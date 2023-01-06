import java.io.IOException;

public class Main {
        public static void main(String[] args) throws IOException {
                /*

                [*]=====CLASS PRODUCT TEST=====[*]

                Product p1 = new Product();
                System.out.println(p1.toString());

                Product p2 = new Product();
                p2.setDimensions(100,200,300);
                System.out.println(p2.toString());

                Product p3 = new Product("Test", "red", 100, 200, 300, 50);
                System.out.println(p3.toString());

                Product p4 = new Product("red", 100, 200, 300, 50);
                System.out.println(p4.toString());

                 */


                /*

                [*]=====CLASS SHELF TEST=====[*]

                Product p1 = new Product("Bottle", "Blue", 50, 50, 300, 2);
                Product p2 = new Product("Box", "Red", 40, 50, 30, 6);
                Product p3 = new Product("Toy", "Yellow", 1500, 1500, 1500, 1);

                Shelf s1 = new Shelf(10000);
                System.out.println("Add product 1: ");
                s1.addProduct(p1);                              //Adding bigger product to smaller slot in shelf (?)
                System.out.println("Add product 2: ");
                s1.addProduct(p2);                              //Adding smaller product to bigger slot in shelf (?)
                System.out.println(s1.toString());              //Printing created span

                Shelf s2 = new Shelf(20000000);
                System.out.println("Add product 1: ");
                s2.addProduct(p1);
                System.out.println("Add product 2: ");
                s2.addProduct(p2);
                System.out.println("Add product 3: ");
                s2.addProduct(p3);
                System.out.println(s2.toString());
                System.out.println("Get product with id 6: ");
                s2.getProduct(6);                               //Getting non-existing product from slot
                System.out.println(s2.toString());

                Shelf s3 = new Shelf(30000000);
                System.out.println("Add product 1: ");
                s3.addProduct(p1);
                System.out.println(s3.toString());
                System.out.println("Get product 1: ");
                s3.getProduct(p1.id);                           //Getting existing product from slot

                 */


                /*

                [*]=====CLASS SPAN TEST=====[*]

                Product p1 = new Product("Bottle", "Blue", 50, 50, 300, 2);
                Product p2 = new Product("Box", "Red", 40, 50, 30, 6);
                Product p3 = new Product("Toy", "Yellow", 1500, 1500, 1500, 1);

                Span s1 = new Span(500, 0);
                System.out.println(s1.toString());              //Printing created span

                Span s2 = new Span(1000, 0);
                System.out.println("Add product 1: ");
                s2.addProduct(p1);                              //Adding bigger product to smaller span
                System.out.println(s2.toString());
                System.out.println("Get product with id 6: ");
                s2.getProduct(6);                               //Getting non-existing product from span

                Span s3 = new Span(1800000, 0);
                System.out.println("Add product 2: ");
                s3.addProduct(p2);                              //Adding smaller product to bigger slot
                System.out.println("Add product 3: ");
                s3.addProduct(p3);                              //Adding bigger product to smaller span
                System.out.println(s3.toString());
                System.out.println("Get product 2: ");
                s3.getProduct(p2.id);                           //Getting existing product from slot

                 */


                /*

                [*]=====CLASS SLOT TEST=====[*]

                Product p1 = new Product("Bottle", "Blue", 50, 50, 300, 2);
                Product p2 = new Product("Box", "Red", 40, 50, 30, 6);

                Slot s1 = new Slot(8000, 0, 0);
                s1.addProduct(p1);                              //Adding bigger product to smaller slot
                System.out.println(s1.toString());              //Printing created slot

                Slot s2 = new Slot(1000000, 0, 0);
                System.out.println("Add product 2: ");
                s2.addProduct(p2);                              //Adding smaller product to bigger slot
                System.out.println(s2.toString());
                System.out.println("Delete product: ");
                s2.delProduct();                                //Deleting existing product from slot
                System.out.println("Delete product: ");
                s2.delProduct();                                //Deleting product from empty slot
                System.out.println(s2.toString());

                */

        }

}