import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {
    private static int M, N;
    private static FileStorage storage = new FileStorage();

    public static final String MAIN_FILE_NAME = "last_save.lab";
    public static final String FILES_EXTENSION = ".lab";

    public static void Main(String[] args) {

        //search for existing files
        System.out.println("Available files:");
        File[] saves = FileStorage.getSavesList(".", FILES_EXTENSION);
        int counter = 0;
        for (File f : saves)
        {
            ++counter;
            System.out.println(counter + ". " + f.getName());
        }

        //do last save backup
        storage.doFileBackup(MAIN_FILE_NAME);

        boolean flag = counter > 0;
        if(!flag)
        {
            flag = true;
            System.out.println("No suitable files found\n");
        }
        else
        {
            System.out.println("\nEnter a number of file to load. Enter '0' to skip.");
            int f = readInteger(0, counter);
            if (f != 0)
            {
                //load file
                storage.load(saves[f - 1].getName());
                flag = false;
            }
            else flag = true;
        }

        if(flag)
        {
            //handle user input
            System.out.println("Enter N value");
            N = readInteger();
            System.out.println("Enter M value");
            M = readInteger();

            //fill storage with figures
            storage.fillListRandomly(FileStorage.ListType.Quadrillateral, N);
            storage.fillListRandomly(FileStorage.ListType.IsoscelesTrapezoid, M);
        }

        //print figures info
        System.out.println("Quadrillateral list:");
        storage.printList(FileStorage.ListType.Quadrillateral);
        System.out.println("Isosceles Quadrillateral list:");
        storage.printList(FileStorage.ListType.IsoscelesTrapezoid);

        //do tasks
        doTask(FileStorage.ListType.Quadrillateral);
        doTask(FileStorage.ListType.IsoscelesTrapezoid);

        //save data to file
        /*storage.save(MAIN_FILE_NAME);
        System.out.println("Data saved to file '" + MAIN_FILE_NAME + "'");*/
        if(flag) storage.serialize(MAIN_FILE_NAME);
        System.out.println("Data saved to file '" + MAIN_FILE_NAME + "'");

        System.out.println("\nJSON serialization:");
        storage.serializeFastJSON("json-" +MAIN_FILE_NAME);
        storage.deserializeFastJSON("json-" +MAIN_FILE_NAME);
        System.out.println("Quadrillateral list:");
        storage.printList(FileStorage.ListType.Quadrillateral);
        System.out.println("Isosceles Trapezoid list:");
        storage.printList(FileStorage.ListType.IsoscelesTrapezoid);
    }

    public static void doTask(FileStorage.ListType type)
    {
        if(type== FileStorage.ListType.Quadrillateral)
        {//printing quadrilaterals with max Area
        System.out.println("Quadrilaterals with Maximum area :");
        for (var item:Functions.quadrilateralsWithMaxArea(storage.getFigureList(type))) {
            System.out.println(item.toString());
        }
        System.out.println("Quadrilaterals with minimum area :");
        for (var item:Functions.quadrilateralsWithMinArea(storage.getFigureList(type))) {
            System.out.println(item.toString());
        }

        System.out.println("number of Quadrilaterals with maximum area :");
        System.out.println(Functions.numberOfQuadrilateralWithMaxArea(storage.getFigureList(type)));
        }
        else if(type== FileStorage.ListType.IsoscelesTrapezoid){
            //trapezoid with the smallest diagonal.
            System.out.println("trapezoid with the smallest diagonal.:");
            System.out.println(Functions.trapezoidWithSmallestDiagonal(storage.getFigureList(type)));
        }
    }

    public static int readInteger()
    {
        return readInteger(1, 9999);
    }

    public static int readInteger(int minValue, int maxValue)
    {
        Scanner input = new Scanner(System.in);
        while(true)
        {
            try
            {
                int result = Integer.parseInt(input.next());
                if(result >= minValue)
                {
                    if(result <= maxValue) return result;
                    else
                    {
                        System.out.println("Value must be less than " + (maxValue + 1));
                    }
                }
                else
                {
                    System.out.println("Value must be greater than " + (minValue - 1));
                }
            }
            catch (Exception e)
            {
                System.out.println("Enter a number, please");
            }
        }
    }
}
