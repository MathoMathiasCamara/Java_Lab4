public class Quadrillateral extends Figure {
    public Quadrillateral(double xA, double yA, double xB, double yB, double xC, double yC, double xD, double yD) {
        super(xA, yA, xB, yB, xC, yC, xD, yD);
    }

    @Override
    public double area() {
        var a=sideDA();
        var b=sideBC();
        var c=sideAB();
        var s= (a+b+2*c)/2;
        return Math.sqrt((s-a)*(s-b)*Math.pow(s-c,2));
    }

    @Override
    public double perimeter() {
        return sideDA()+sideAB()+sideBC()+sideCD();
    }

    public boolean isIsoscelesTrapezoid()
    {
        return sideAB()==sideCD() ;
    }

    @Override
    public String toString() {
        return "Area="+area()+" ,Perimeter="+perimeter()+",IsTrapezoidIsosceles="+isIsoscelesTrapezoid();
    }
}
