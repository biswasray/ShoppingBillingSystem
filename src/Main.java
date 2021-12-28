import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void clear() {
        try {
            if(System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }
            else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (Exception ex) {
            System.out.println("Something is wrong"+ex);
        }
    }
    public static void main(String[] args) {
        clear();
        login();
        K:do {
            clear();
            System.out.print("\n\n\t\t\t\t WELCOME"+"\n\n\n\n\n\t MAIN MENU"+"\n\t1. MAKE A PURCHASE"+"\n\t2. ADMIN MENU"+"\n\t3. EXIT"+"\n\t Please Select Your Option (1-3) : ");
            switch (sc.nextInt()) {
                case 1:
                    placeorder();
                    break;
                case 2:
                    adminmenu();
                    break;
                case 3:
                    clear();
                    break K;
                default:
                    clear();
                    System.out.println("Invalid option ....\nTry Again\n\n");
            }
        }while (true);
        last();
    }

    private static void last() {
        try {
            System.out.print("\t\t\t\t");
            System.out.println(",@@, \n\t\t"
                    +"                                     / / \n\t\t"
                    +"         Thanks For Shopping!!      / / \n\t\t"
                    +" \\~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/._/ \n\t\t"
                    +"  \\~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/ \n\t\t"
                    +"   \\~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/ \n\t\t"
                    +"    \\~~~~~~~~~~~~~~~~~~~~~~~~~~~/ \n\t\t"
                    +"     \\~~~~~~~~~~~~~~~~~~~~~~~~~/ \n\t\t"
                    +"      \\_______________________/ \n\t\t"
                    +"                         | | \n\t\t"
                    +"       |~~~~~~~~~~~~~~~~~~~~~~| \n\t\t"
                    +"       |______________________| \n\t\t"
                    +"          /``\\         /``\\ \n\t\t"
                    +"          \\__/         \\__/ \n\t\t");
            //System.in.read();
            Thread.sleep(1500);
        }
        catch (Exception ex) {
            System.out.println("something is wrong "+ex);
            sc.next();
        }
    }

    private static void adminmenu() {
        clear();
        System.out.print("\n\n\n\tAdmin Menu"+"\n\n\t1.Create a Product"+"\n\n\t2.Display all Products"+"\n\n\t3.Delete All Product"+"\n\n\t4.Back to Main Menu"+"\n\n\tEnter your Choice (1-4) : ");
        switch (sc.nextInt()) {
            case 1:
                clear();
                writeproduct();
                break;
            case 2:
                displayall();
                break;
            case 3:
                deleteall();
                break;
            case 4:
                return;
            default:
                adminmenu();
        }
    }

    private static void deleteall() {
        try {
            clear();
            System.err.print("\nAre you Sure You Want to Delete the records?? (Y or N) : ");
            switch (sc.next().charAt(0)) {
                case 'y':
                case 'Y':
                    File file=new File("products.xml");
                    if(file.delete())
                        System.out.println("\n\nRecord deleted...");
                    else
                        System.out.println("\n\nRecord can not be deleted...");
                    break;
                default:
                    System.out.println("\n\nCancel deleting Record ");
            }
            Thread.sleep(1000);
        }catch (Exception ex) {
            System.out.println("something is wrong "+ex);
            sc.next();
        }
    }

    private static void displayall() {
        try {
            File file=new File("products.xml");
            JAXBContext context=JAXBContext.newInstance(Products.class);
            Unmarshaller unmarshaller=context.createUnmarshaller();
            Products products=(Products)unmarshaller.unmarshal(file);
            clear();
            System.out.println("\n\n\n\t\tDISPLAY ALL RECORDS!!!\n\n");
            for(Product product:products.getProducts()) {
                System.out.println("The product no : "+product.getProNo());
                System.out.println("The product name : "+product.getProName());
                System.out.println("The product price : "+product.getProMrp()+"\n\n\n");
            }
            System.in.read();
            //sc.next();
        }catch (Exception ex) {
            System.out.println("something is wrong "+ex);
            sc.next();
        }
    }

    private static void writeproduct() {
        try {
            JAXBContext context=JAXBContext.newInstance(Products.class);
            Marshaller marshaller=context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            System.out.print("\n Enter product no.: ");
            int pn=sc.nextInt();
            sc.nextLine();
            System.out.print("\nEnter product name : ");
            String ps= sc.nextLine();
            System.out.print("\nEnter rate of product : ");
            float pm=sc.nextFloat();
            Product product=new Product(pn,0,ps,pm,0f);
            Products products=null;
            File file=new File("products.xml");
            if(file.exists()) {
                Unmarshaller unmarshaller=context.createUnmarshaller();
                products=(Products)unmarshaller.unmarshal(file);
                List<Product> temp=products.getProducts();
                temp.add(product);
                products.setProducts(temp);
            }
            else {
                List<Product> temp=new ArrayList<>();
                temp.add(product);
                products=new Products(1,temp);
            }
            FileOutputStream fs=new FileOutputStream("products.xml");
            marshaller.marshal(products,fs);
            fs.close();
            System.out.println("\n\nThe Product has been created");
            Thread.sleep(1000);
        }catch (Exception ex) {
            System.out.println("something is wrong "+ex);
            sc.next();
        }
    }

    private static void placeorder() {
        try {
            File file=new File("products.xml");
            if(!file.exists()) {
                System.out.println("ERROR!!! FILE COULD NOT BE OPEN\n\n\n Go To Admin Menu to create a file");
                Thread.sleep(1500);
                return;
            }
            JAXBContext context=JAXBContext.newInstance(Products.class);
            Unmarshaller unmarshaller=context.createUnmarshaller();
            Products products=(Products)unmarshaller.unmarshal(file);
            clear();
            System.out.println("\n\n\t\t\t\tProduct MENU\n");
            System.out.println("\t#############################################################");
            System.out.printf("\t%-10s%-25s%-15s","P.NO.","NAME","PRICE");
            System.out.println("\n\t#############################################################");
            for(Product product:products.getProducts()) {
                System.out.printf("\t%-10d%-25s%-15.2f\n",product.getProNo(),product.getProName(),product.getProMrp());
            }
            System.out.println("\n\n\t\t\t==================================");
            System.out.println("\t\t\t\t PLACE YOUR ORDER");
            System.out.println("\t\t\t==================================");
            char ch='y';
            do {
                System.out.print("\n\nEnter The Product Number : ");
                int pn= sc.nextInt();
                Product p=null;
                for(Product t:products.getProducts())
                    if(t.getProNo()==pn)
                        p=t;
                if(p!=null) {
                    System.out.print("\nEnter Quantity : ");
                    p.setQty(p.getQty()+ sc.nextInt());
                    p.setPrice(p.getProMrp()*p.getQty());
                }
                else {
                    System.out.println("Invalid product number");
                }
                System.out.print("\nDo You Want To Order Another Product ? (y/n) ");
                ch=sc.next().charAt(0);
            }while (ch=='y'||ch=='Y');
            System.out.println("\n\nThank You For Placing The Order");
            Thread.sleep(1000);
            clear();
            float total=0f;
            System.out.println("\n\n\t*************************************INVOICE************************************");
            System.out.printf("\t%-10s%-25s%-10s%-15s%-10s","P.NO.","NAME","Quantity","PRICE","Amount");
            System.out.println("\n\t********************************************************************************");
            for(Product product:products.getProducts()) {
                if(product.getQty()==0)
                    continue;
                total+=product.getPrice();
                System.out.printf("\t%-10d%-25s%-10d%-15.2f%-10.2f\n",product.getProNo(),product.getProName(),product.getQty(),product.getProMrp(),product.getPrice());
            }
            System.out.println("\n\n\t\t\t\t\tTOTAL = "+total);
            System.in.read();
        }catch (Exception ex) {
            System.out.println("something is wrong "+ex);
            sc.next();
        }
    }

    private static void login() {
        String username,password;
        username=password="";
        while (!username.equals("username")&&!password.equals("password")) {
            clear();
            System.out.print("\n\n\n\t\t\t Login to Access the Store\n\n\t\t\t Enter the detaiils below:\n\n\n\t\tUSERNAME :");
            username=sc.nextLine();
            System.out.print("\n\n\t\tPASSWORD : ");
            password=sc.nextLine();
            if(!username.equals("username")&&!password.equals("password")) {
                System.out.print("\n\n\tWRONG PASSWORD!!!! Want to try Again> (Y,y/N,n): ");
                String ch=sc.next();
                sc.nextLine();
                if(ch.equals("y")||ch.equals("Y"))
                    continue;
                else
                    System.exit(0);
            }
        }
    }
}
