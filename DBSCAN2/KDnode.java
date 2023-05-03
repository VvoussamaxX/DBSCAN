// Mohamed Oussama El Malki _ 300248698

public class KDnode {
    public Point3D point; //le Point3D associé à ce noeud
    public int axis; // l’axe de division (x, y, or z) representé par les entiers 0, 1 or 2
    public double value; // la valeur de division (la valeur de la coordonnée de l’axe de division)
    public KDnode left; // une référence au noeud du sous-arbre de gauche (ou null)
    public KDnode right; // une référence au noeud du sous-arbre de droite (ou null)



    public KDnode(Point3D pt, int axis) {
    this.point= pt;
    this.axis= axis;
    this.value= pt.get(axis);
    left= right= null;
    }

    }
