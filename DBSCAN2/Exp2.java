 // Mohamed Oussama El Malki _ 300248698
 
import java.util.List;
import java.util.ArrayList;
import java.io.*;  
import java.util.Scanner;  

public class Exp2 {
  // reads a csv file of 3D points (rethrow exceptions!)
  public static List<Point3D> read(String filename) throws Exception {
	  
    List<Point3D> points= new ArrayList<Point3D>(); 
	double x,y,z;
	
	Scanner sc = new Scanner(new File(filename));  
	// sets the delimiter pattern to be , or \n \r
	sc.useDelimiter(",|\n|\r");  

	// skipping the first line x y z
	sc.next(); sc.next(); sc.next();
	
	// read points
	while (sc.hasNext())  
	{  
		x= Double.parseDouble(sc.next());
		y= Double.parseDouble(sc.next());
		z= Double.parseDouble(sc.next());
		points.add(new Point3D(x,y,z));  
	}   
	
	sc.close();  //closes the scanner  
	
	return points;
  }
  
 
  public static void main(String[] args) throws Exception {  
  // initialisation des arguments
	double eps= Double.parseDouble(args[1]);
    List<Point3D> Points= Exp2.read(args[2]);
	Integer number = Integer.parseInt(args[3]);
  
	
    // for each of the three point cloud files (1 to 3) provided, you will compute the time required (in ms) 
    // tofind the neighbors of every 10 points in a file, using the eps value 0.5
    NearestNeighborsKD nn= new NearestNeighborsKD(Points);
    NearestNeighbors nnn= new NearestNeighbors(Points);

    //compute the time to execute the method rangeQuery, not the time required to build the k-d tree
    long startTime = System.nanoTime();
	for(int i=0;i<Points.size();i++){
        
		if(i%number==0){

            double a=Points.get(i).getX();
            double b=Points.get(i).getY();
            double c=Points.get(i).getZ();
            Point3D query= new Point3D(a,b,c);
            List<Point3D> neighbors;
            

            if (args[0].length()==2){
                 neighbors= nn.rangeQuery(query,eps);}
                else{
                neighbors= nnn.rangeQuery(query,eps);
                }
        }
			
	}
    long endTime = System.nanoTime();
    long duration = (endTime - startTime)/1000000 ;
    System.out.println("le temps moyen pour trouver les voisins d’un point est : "+ duration);
}
}
