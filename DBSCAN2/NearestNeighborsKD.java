// Mohamed Oussama El Malki _ 300248698

import java.util.ArrayList;
import java.util.List;

public class NearestNeighborsKD{

    private List<Point3D> neighbors; 
    private KDTree kdtree;

// un constructeur qui accepte une liste de Point3D a constructor that accepts a List of Point3D
    NearestNeighborsKD(List<Point3D> liste) {
        kdtree = new KDTree();
        List<Point3D> neighbors; 
        for (Point3D p : liste) {
            kdtree.add(p); 
        }
    }
// La recherche des voisins dans le k-d tree sera fait par la méthode suivante (appelée par la méthode rangeQuery originale)
    private void rangeQuery(Point3D p, double eps,List<Point3D> neighbors, KDnode node){
        if (node == null){
            return;
        }
        if (p.distance(node.point) < eps){
            neighbors.add(node.point);
        }
        if (p.get(node.axis) - eps <= node.value){
        rangeQuery(p, eps, neighbors, node.left);
        }
        if (p.get(node.axis) + eps > node.value){
        rangeQuery(p, eps, neighbors, node.right);
        }
        return;
    }
// this method will call a new method searching a the k-d tree  
// une méthode rangeQuery qui trouve les voisins d’un point 3D a rangeQuery method that finds the nearest neighbors of a 3D point  
    public List<Point3D> rangeQuery(Point3D p, double eps) {
        List<Point3D> neighbors = new ArrayList<>();
        rangeQuery(p, eps, neighbors, kdtree.getroot());
        return neighbors;
    }
}
