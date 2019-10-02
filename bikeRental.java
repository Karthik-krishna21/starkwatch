import java.util.Scanner;
import java.util.LinkedList; 
import java.util.Queue; 
import java.util.Date;
import java.util.Vector;


class User {
    int rackID,bikeID;
    String username;
    String password;
    int payable=0,rent;
    long renttime=0;
    boolean isStaff;
    public void use()
    {
        
        if(isStaff)
            payable+=20;
        else
            payable+=10;
    }
}

class Rack {
        int rackid;
        Queue<Integer> bicycles= new LinkedList<>();
}

public class BicycleRental{

    public static void main(String[] args){
        // TODO Auto-generated method stub
        int ch,ch2;
        
        Rack racks[]=new Rack[5];
        
        Vector<User> users=new Vector<User>();
        Scanner sc=new Scanner(System.in);
        String str;
        User curr = null;
        User admin=new User();
        admin.username="admin";             //ADMIN LOGIN DETAILS
        admin.password="admin";
        
        
        
    
        int j=1;                    //INITIALIZE ALL RACKS AND BICYCLES WITH ID's
        for(int i=0;i<5;i++)
        {
            racks[i]=new Rack();
            racks[i].rackid=i+1;
            while(true)
            {
                racks[i].bicycles.add(j);
                if(j%5==0)
                {
                    j++;
                    break;
                }
                    j++;
            }
        }
        
        do {
        int login=0;
        System.out.println("Enter an option:\n1.Login\n2.Register\n3.Exit");
        ch=sc.nextInt();
        sc.nextLine();
        if(ch==1)           //LOGIN
        {
            System.out.println("Enter username:");
            str=sc.nextLine();
            if(str.equals("admin"))
            {
                System.out.println("Enter password");
                str=sc.nextLine();
                if(str.equals(admin.password))
                        login=2;
            }
            for(User i1:users)
            {
                if(i1.username.equals(str))
                {
                    System.out.println("Enter password:");
                    str=sc.nextLine();
                    if(str.equals(i1.password)){
                        login=1;
                        curr=i1;
                        break;
                        
                    }
                }       
            }
            if(login==1)
            
            {                                                   //USER OPTIONS.
                
                do {
                String opt;
                if(curr.rent==0)
                        opt="\n1.Rent";
                else
                {
                        System.out.println("You have rented Bike no: " +curr.bikeID + "from rack:"+curr.rackID+"\n");
                        opt="\n1.Park";
                }
                
                
                System.out.println("Enter an option: "+ opt+"\n2.Pay \n3.Logout\n Your Due:"+curr.payable+"\n");
                ch2=sc.nextInt();
                sc.nextLine();
                if(ch2==1)
                {
                    if(curr.rent==0)
                    {
                        System.out.println("\nEnter the rackID:(1-5) ");
                        curr.rackID=sc.nextInt();
                        sc.nextLine();
                        curr.renttime=System.currentTimeMillis();
                        curr.bikeID=racks[curr.rackID-1].bicycles.remove();
                        curr.rent=1;
                        curr.use();
                    }
                    else
                    {
                        System.out.println("\n Enter the rackID:(1-5)");
                        int k=sc.nextInt();
                        sc.nextLine();
                        racks[k-1].bicycles.add(curr.bikeID);
                        curr.rent=0;
                    }
                }
                else if(ch2==2)
                {
                    System.out.println("Deducted " + curr.payable + "amount\n");
                    curr.payable=0;
                }

                }while(ch2!=3);
                
    
            }
            else if(login==2)               //ADMIN ACTIONS
            {
                
                do {
                    System.out.println("Enter an option: \n1.Track bicycle\n2.Track User activity\n3.Logout");
                    ch2=sc.nextInt();
                    sc.nextLine();
                    if(ch2==1)
                    {
                        int x;
                        System.out.println("Enter the bikeID to track:");
                        x=sc.nextInt();
                        sc.nextLine();
                        outer:
                        for(int i=0;i<5;i++)
                        {
                            for(int j1:racks[i].bicycles)
                            {
                                if(j1==x)
                                {
                                    System.out.println("Bike is parked at rack:"+(i+1)+"\n");
                                    x=-1;
                                    break outer;
                                    
                                }
                            }
                        }
                        if(x!=-1)
                            System.out.println("Bike not Found!");
                    }
                    else if(ch2==2)
                    {
                        String usr;
                        System.out.println("Enter the username to track:");
                        usr=sc.nextLine();
                        for(User x:users)
                        {
                            if(x.username.equals(usr))
                            {
                                if(x.rent==1)
                                    System.out.println("User: "+ usr +" has rented bike: "+x.bikeID+" from rack: "+ x.rackID);
                                else
                                    System.out.println("User has not rented any bikes");
                                usr="found";
                            }
                        }
                        if(!usr.equals("found"))
                            System.out.println("username invalid");
                    }
                }while(ch2!=3);
            }
            else {
                System.out.println("Invalid username or password");
            }
                
        }
        else if(ch==2)      //REGISTRATION
        {
            User x=new User();
            System.out.println("Enter new username:");
            x.username=sc.nextLine();
            System.out.println("Enter new password:");
            x.password=sc.nextLine();
            System.out.println("Are you a staff?(y/n)");
            if(sc.nextLine().equals("y"))
                x.isStaff=true;
            else
                x.isStaff=false;
            users.add(x);
        }
        else if(ch!=3)
        {
            System.out.println("Enter a valid option!");
        }
        
        
        }while(ch!=3);
        
    }
}
