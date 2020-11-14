import com.sun.tools.javac.Main;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileStorage
{
    private List<Quadrillateral> listQ = new ArrayList<>();
    private List<Quadrillateral> listI = new ArrayList<>();


    enum ListType
    {
        Quadrillateral,
        IsoscelesTrapezoid
    }
    private static Random random = new Random(20);

    public void addFigureQuadri(Quadrillateral quadri)
    {
        listQ.add(quadri);
    }

    public void addFigureIsco(Quadrillateral isosceles)
    {
        listI.add(isosceles);
    }

    public List getFigureList(ListType type)
    {
        if(type==ListType.Quadrillateral)
        {
            return listQ;
        }
        else if(type == ListType.IsoscelesTrapezoid) {
            return listI;
        }
        return null;
    }

    public void fillListRandomly(ListType listType, int count)
    {
        if(listType == ListType.Quadrillateral)
        {
            for(int i = 0; i < count; i++)
            {
                Quadrillateral tr = getRandomQuadrillateral();
                addFigureQuadri(tr);
            }
        }
        else if(listType == ListType.IsoscelesTrapezoid)
        {
            for(int i = 0; i < count; i++)
            {
                Quadrillateral pr = getRandomIsco();
                addFigureIsco(pr);
            }
        }
    }

    public void printList(ListType type)
    {
        List list = getFigureList(type);
        if(list == null || list.isEmpty() || !(list.get(0) instanceof IFigure))
        {
            System.out.println("Error! Can't print a figures list!");
        }
        else
        {
            int counter = 0;
            for (Object f : list)
            {
                counter++;
                System.out.println((counter) + ".\n" + ((IFigure)f).getinfos() + "\n");
            }
        }
    }

    public void clear()
    {
        listQ.clear();
        listI.clear();
    }

    public static Quadrillateral getRandomQuadrillateral()
    {
        return new Quadrillateral((double) random.nextDouble() + 1,(double) random.nextDouble() + 1,
                (double) random.nextDouble() + 1,(double) random.nextDouble() + 1,(double) random.nextDouble() + 1,
                (double) random.nextDouble() + 1,(double) random.nextDouble() + 1,(double) random.nextDouble() + 1);
    }

    public static Quadrillateral getRandomIsco()
    {
        while(true)
        {
            var figure=new Quadrillateral((double) random.nextDouble() + 1,(double) random.nextDouble() + 1,
                    (double) random.nextDouble() + 1,(double) random.nextDouble() + 1,(double) random.nextDouble() + 1,
                    (double) random.nextDouble() + 1,(double) random.nextDouble() + 1,(double) random.nextDouble() + 1);
            if(figure.isIsoscelesTrapezoid())
                return figure;
        }
    }

    public void save(String fileName)
    {
        try(BufferedWriter out = new BufferedWriter(new FileWriter(fileName)))
        {
            if (listQ != null)
            {
                for (Quadrillateral qua : listQ)
                {
                    out.write(String.valueOf(qua.getxA()));
                    out.newLine();
                    out.write(String.valueOf(qua.getyA()));
                    out.newLine();
                    out.write(String.valueOf(qua.getxB()));
                    out.newLine();
                    out.write(String.valueOf(qua.getyB()));
                    out.newLine();
                    out.write(String.valueOf(qua.getxC()));
                    out.newLine();
                    out.write(String.valueOf(qua.getyC()));
                    out.newLine();
                    out.write(String.valueOf(qua.getxD()));
                    out.newLine();
                    out.write(String.valueOf(qua.getyD()));
                    out.newLine();

                }
                out.write(";");
                out.newLine();
            }
            if(listI != null)
            {
                for (Quadrillateral isco : listI)
                {
                    out.write(String.valueOf(isco.getxA()));
                    out.newLine();
                    out.write(String.valueOf(isco.getyA()));
                    out.newLine();
                    out.write(String.valueOf(isco.getxB()));
                    out.newLine();
                    out.write(String.valueOf(isco.getyB()));
                    out.newLine();
                    out.write(String.valueOf(isco.getxC()));
                    out.newLine();
                    out.write(String.valueOf(isco.getyC()));
                    out.newLine();
                    out.write(String.valueOf(isco.getxD()));
                    out.newLine();
                    out.write(String.valueOf(isco.getyD()));
                    out.newLine();
                }
                out.write(";");
                out.newLine();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void load(String fileName)
    {
        this.clear();

        try(Scanner scanner = new Scanner(new FileReader(fileName)))
        {
            while(scanner.hasNextLine())
            {
                try
                {
                    double xA = Double.parseDouble(scanner.nextLine());
                    double yA = Double.parseDouble((scanner.nextLine()));
                    double xB =Double.parseDouble((scanner.nextLine()));
                    double yB = Double.parseDouble((scanner.nextLine()));
                    double xC = Double.parseDouble((scanner.nextLine()));
                    double yC = Double.parseDouble((scanner.nextLine()));
                    double xD = Double.parseDouble((scanner.nextLine()));
                    double yD = Double.parseDouble((scanner.nextLine()));
                    listQ.add(new Quadrillateral(xA,yA,xB,yB,xC,yC,xD,yD));
                }
                catch (NumberFormatException e)
                {
                    break;
                }
            }
            while(scanner.hasNextLine())
            {
                try
                {
                    double xA = Double.parseDouble(scanner.nextLine());
                    double yA = Double.parseDouble((scanner.nextLine()));
                    double xB = Double.parseDouble((scanner.nextLine()));
                    double yB = Double.parseDouble((scanner.nextLine()));
                    double xC = Double.parseDouble((scanner.nextLine()));
                    double yC = Double.parseDouble((scanner.nextLine()));
                    double xD = Double.parseDouble((scanner.nextLine()));
                    double yD = Double.parseDouble((scanner.nextLine()));
                    listI.add(new Quadrillateral(xA,yA,xB,yB,xC,yC,xD,yD));
                }
                catch (NumberFormatException e)
                {
                    break;
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void doFileBackup(String mainFileName)
    {
        File f = new File(mainFileName);
        if(!f.exists())
        {
            return;
        }

        String backupName = "save-";
        backupName += new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(new Date());
        backupName += main.FILES_EXTENSION;

        /*load(mainFileName);
        save(backupName);
        clear();*/
        deserialize(mainFileName);
        serialize(backupName);
        System.out.println("Created last save backup file '" + backupName + "'");
        this.clear();
    }
    public void serialize(String fileName)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fos);

            out.writeObject(listN);
            out.writeChars(";");
            out.writeObject(listM);

            out.close();
            fos.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deserialize(String fileName)
    {
        try
        {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(fis);

            this.listN = (ArrayList<TriangleEquilateral>)input.readObject();
            input.readChar();
            this.listM = (ArrayList<TrianglePrismEquilateral>)input.readObject();

            input.close();
            fis.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void serializeFastJSON(String fileName)
    {
        try
        {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(JSON.toJSONString(this.listN) + "\n");
            bw.write(JSON.toJSONString(this.listM));
            bw.close();
            fw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deserializeFastJSON(String fileName)
    {
        try
        {
            Scanner scanner = new Scanner(new FileReader(fileName));
            this.clear();
            ArrayList<JSONObject> JSONlist = JSON.parseObject(scanner.nextLine(), ArrayList.class);
            for (JSONObject obj : JSONlist)
            {
                this.listN.add(new TriangleEquilateral(obj.getIntValue("sideLength")));
            }
            JSONlist = JSON.parseObject(scanner.nextLine(), ArrayList.class);
            for (JSONObject obj : JSONlist)
            {
                this.listM.add(new TrianglePrismEquilateral(obj.getIntValue("sideLength"), obj.getIntValue("height")));
            }
            scanner.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static File[] getSavesList(String directory, String extension)
    {
        File dir = new File(directory);
        File [] files = dir.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(extension);
            }
        });
        return files;
    }
}
