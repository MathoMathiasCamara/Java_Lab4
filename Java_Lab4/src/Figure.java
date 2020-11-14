public abstract class Figure implements IFigure {
    double xA;
    double yA;
    double xB;
    double yB;
    double xC;
    double yC;
    double xD;
    double yD;

    public double getxA() {
        return xA;
    }

    public void setxA(double xA) {
        this.xA = xA;
    }

    public double getyA() {
        return yA;
    }

    public void setyA(double yA) {
        this.yA = yA;
    }

    public double getxB() {
        return xB;
    }

    public void setxB(double xB) {
        this.xB = xB;
    }

    public double getyB() {
        return yB;
    }

    public void setyB(double yB) {
        this.yB = yB;
    }

    public double getxC() {
        return xC;
    }

    public void setxC(double xC) {
        this.xC = xC;
    }

    public double getyC() {
        return yC;
    }

    public void setyC(double yC) {
        this.yC = yC;
    }

    public double getxD() {
        return xD;
    }

    public void setxD(double xD) {
        this.xD = xD;
    }

    public double getyD() {
        return yD;
    }

    public void setyD(double yD) {
        this.yD = yD;
    }


    // Constructor
    public Figure(double xA, double yA, double xB, double yB, double xC, double yC, double xD, double yD) {
        this.xA = xA;
        this.yA = yA;
        this.xB = xB;
        this.yB = yB;
        this.xC = xC;
        this.yC = yC;
        this.xD = xD;
        this.yD = yD;
    }

    //Sides of the quadrilateral
    public double sideAB()
    {
        return Math.sqrt(Math.pow((xB-xA),2)+Math.pow((yB-yA),2));
    }
    public double sideBC()
    {
        return Math.sqrt(Math.pow((xC-xB),2)+Math.pow((yC-yB),2));
    }
    public double sideCD()
    {
        return Math.sqrt(Math.pow((xD-xC),2)+Math.pow((yD-yC),2));
    }
    public double sideDA()
    {
        return Math.sqrt(Math.pow((xA-xD),2)+Math.pow((yA-yD),2));
    }


    //diagonals
    public double diagonalAC()
    {
         return Math.sqrt(Math.pow(sideAB(),2)+Math.pow(sideBC(),2));
    }
    public double diagonalBD(){
        return Math.sqrt(Math.pow(sideCD(),2)+Math.pow(sideBC(),2));
    }

    public double perimeter()
    {
        return 0;
    }

    public double area()
    {
        return 0;
    }

    @Override
    public String getinfos() {
        return "Area="+area()+" ,Perimeter="+perimeter();
    }
}