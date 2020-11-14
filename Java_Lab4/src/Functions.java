import java.util.ArrayList;
import java.util.List;

public class Functions {

    public static List<Quadrillateral> quadrilateralsWithMaxArea(List<Quadrillateral> list)
    {
       ArrayList<Quadrillateral> newlist=new ArrayList<>();
        for (var item:list
             ) {
            if(item.area()>=maximumArea(list))
                newlist.add(item);
        }

        return newlist;
    }

    public static List<Quadrillateral> quadrilateralsWithMinArea(List<Quadrillateral> list)
    {
        List<Quadrillateral> newlist=new ArrayList<>();
        for (var item:list
        ) {
            if(item.area()<=minimumArea(list))
                newlist.add(item);
        }
        return newlist;
    }

    public  static int numberOfQuadrilateralWithMaxArea(List<Quadrillateral> list)
    {
        var result=quadrilateralsWithMaxArea(list);
        return  result.size();

    }
    public static Quadrillateral trapezoidWithSmallestDiagonal(List<Quadrillateral> list){
        //find smallest diagonal
        double smallestD=list.get(0).area();
        int index=-1;
        for (var item:list) {
            if(item.isIsoscelesTrapezoid())
            {
                if(item.diagonalAC()<smallestD)
                { smallestD=item.diagonalAC();
                index= list.indexOf(item);}
            }

        }

        if(index>=0)
        return list.get(index);
        else
            return  null;
    }

    public static double maximumArea(List<Quadrillateral> list){
        double max=list.get(0).area();
        for (var x:list
             ){
            if(x.area()>max)
                max=x.area();
              }
        return max;
    }

    public static double minimumArea(List<Quadrillateral> list){
        double min=list.get(0).area();
        for (var x:list
        ){
            if(x.area()<min)
                min=x.area();
        }
        return min;
    }
}
