 // Mohamed Oussama El Malki _ 300248698

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// compare the computational speed of DBScan using NearestNeighborsKD
// instead of the linear neighbor search implemented in P1
public class Exp3 {
   /*The Exp3 class containing the main method for running experiment 3. This class 
simply run the DBScan as in part 1 but, this time, using the new NearestNeighborsKD
class */ 
    public static void main(String[] args) throws IOException {
        /*The name of the output file must be the same than the one of the input file but to which you append the
    word ‘clusters’, the value of the parameters used and the number of clusters found using the format
    filename_clusters_eps_minPts_nclusters.*/
            String filename=args[0];
            Double eps =Double.parseDouble(args[1]);
            int minpts = Integer.parseInt(args[2]);
            List dbscan = new ArrayList<>();
            long startTime = System.nanoTime();
            dbscan  = (DBScan.read(filename+".csv"));
            DBScan Scan;
            Scan = new DBScan(dbscan);
            Scan.setEps(eps);
            Scan.setMinPts(minpts);
            Scan.findClusters();
            Scan.save(filename);
            long endTime = System.nanoTime();
            // the program should display the total runtime
            long duration = (endTime - startTime)/1000000 ;
            System.out.println("le temps moyen pour trouver les voisins d’un point est : "+ duration);
        }
    
}
