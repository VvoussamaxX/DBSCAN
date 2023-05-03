// Mohamed Oussama El Malki - 300248698
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class DBScan{

    TheStack<Point3D> S = new TheStack<Point3D>();
    NearestNeighborsKD neighbor;
    List<Point3D> N, DB;
    Point3D Q;
    double eps;
    int minPts, C;
    


 // A constuctor that accepts a List of point3D
    public DBScan(List<Point3D> DB){
        this.DB = DB;
        this.C = 0;

    }
// Methods to setEps and setMinPts methods 
    public void setEps(double eps){
        this.eps=eps;
    }

    public void setMinPts(int minPts){
        this.minPts=minPts;
    }
// Executing method of the DBScan algorithm
// DB contains the list of all points
// label(P) of all P is initialized to undefined
// Defining a Cluster counter 

    public void findClusters(){
        neighbor = new NearestNeighborsKD(DB);

        for ( Point3D P: DB){
            int ClusterLabel = P.getcluster();
            if(ClusterLabel != 0){
                continue;}

// Find neighbors 
             N = neighbor.rangeQuery(P, eps);
// Density check 
            if (N.size() < minPts){
                P.label= -1; // -1 means noise 
                continue;
            }
/* next cluster label */
            C ++;
            P.label= C;
            
            for(int i = 0; i< N.size(); i++){
                S.push(N.get(i));
            }
            while (!S.empty()){
                Q = S.pop(); /* Process point Q */
                int cl = Q.getcluster();

                if (cl== -1){
                    cl = C;
                }
                /* Noise becomes border pt */

                if (cl != 0){
                    continue;}
                Q.label = C;
                N = neighbor.rangeQuery(Q, eps); /* Find neighbors */
                if (N.size() >= minPts){
                    for(int i = 0; i< N.size(); i++){
                        S.push(N.get(i));} /* Add neighbors to stack */
                }

            }
        }
  
    }

    public int getNumberOfClusters(){
        return C;
    }

    public List<Point3D> getPoints(){
        return DB;
    }

//the read static method that accept a filename and returns a list of Point3D
    public static List<Point3D> read(String filename) throws IOException 
    {			
          List<Point3D> reads = new ArrayList<Point3D>();
          try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            //skipping the two first lines of the csv
            reader.readLine();
            reader.readLine();
            String line = reader.readLine();
            // lines of csv not null or x,y,z
            while (line!= null){
            // splitting elements as an array
                String[] cords = line.split(",");
                reads.add(new Point3D(Double.parseDouble(cords[0]), Double.parseDouble(cords[1]), Double.parseDouble(cords[2])));
                line = reader.readLine();
            }
          }
          catch (FileNotFoundException e){
            e.printStackTrace();
          }
          return reads;
    }

    //Random colors
    private double[][] randomcolor(){
        int nbofclusters = getNumberOfClusters();
        double[][] valeur = new double[nbofclusters][3];
        Random rd = new Random(); // creating Random object
        
        for (int i =0; i< nbofclusters; i++){
            valeur[i][0] = Math.round(rd.nextDouble()*100.0)/100.0;
            valeur[i][1] = Math.round(rd.nextDouble()*100.0)/100.0;
            valeur[i][2] = Math.round(rd.nextDouble()*100.0)/100.0;
        }     
        return valeur;
    }

//the save method that saves all the points with their cluster label and associated RGB color
    public void save(String filename) throws FileNotFoundException{
     
        double[][] RGB = randomcolor();
        OutputStreamWriter out = null;
        
        try{
            int x;
            out = new OutputStreamWriter(new FileOutputStream(filename+"_Clusters_"+eps+"_"+minPts+"_"+getNumberOfClusters()+".csv"));

            out.write("x,y,z,C,R,G,B\n");

            for (Point3D pt3d : DB){
                if (pt3d.getcluster() == -1){
                    out.write(pt3d.toString()+",0.0,0.0,0.0\n");
                }
                else {
                    x = pt3d.getcluster()-1;
                    out.write(pt3d.toString()+","+RGB[x][0]+","+RGB[x][1]+","+RGB[x][2]+"\n");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}





