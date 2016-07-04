import java.io.*;
import java.util.Scanner;

public class TabConverter {

    public static void main(String[] args) {
	    Scanner scn = new Scanner(System.in);
        int capPosition;

        String inFileName;
        String outFileName;

        System.out.println("Enter the path of the file to convert...");
        inFileName = scn.next();

        System.out.println("Enter the desired path of the output...");
        outFileName = scn.next();

        System.out.println("Enter the fret number of the capo position...");
        capPosition = scn.nextInt();

        genNewFile(inFileName, outFileName, capPosition);
    }

    private static void genNewFile(String ifName, String ofName, int cp){
        boolean doubledigits;
        File inFile = new File(ifName);
        File outFile = new File(ofName);
        FileWriter fileWriter;
        BufferedWriter outWriter;
        try {
            fileWriter = new FileWriter(outFile);
            outWriter = new BufferedWriter(fileWriter);
            Scanner inScan = new Scanner(inFile);

            while (inScan.hasNextLine()){
                String line = inScan.nextLine();
                String cBuff = "";
                doubledigits = false;
                for (int i = 0; i < line.length(); ++i){
                    char checkChar = line.charAt(i);
                    if (Character.isDigit(checkChar)){
                        if ((i < line.length() - 1)) {
                            doubledigits = Character.isDigit(line.charAt(i + 1));
                        }

                        if (!doubledigits){
                            checkChar = convertFret(checkChar, cp);
                        }
                    }
                    cBuff = cBuff + checkChar;
                }
                outWriter.write(cBuff);
                outWriter.newLine();
            }

            outWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char convertFret(char origFret, int capPos){
        char convertChar;
        int fretNum = Character.getNumericValue(origFret) + capPos;
        convertChar = (char) (fretNum + 48);
        return  convertChar;
    }
}
