import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class CaesarCipher {
    String file_name;
    String outputFileName;

    private String in;
    private String out;
    private int n;

    private char[] tab;
    private Vector<String> inputs=new Vector<String>();
    private Vector<String> outputs=new Vector<String>();

    CaesarCipher() {
        this.in = "";
        this.out = "";
        this.n = 3;
    }

    CaesarCipher(String file_name, int n) throws IOException {
        this.file_name = file_name;
        this.n = n;
        tab = new char[36];
        this.tab[0] = '0';
        this.tab[1] = '1';
        this.tab[2] = '2';
        this.tab[3] = '3';
        this.tab[4] = '4';
        this.tab[5] = '5';
        this.tab[6] = '6';
        this.tab[7] = '7';
        this.tab[8] = '8';
        this.tab[9] = '9';
        this.tab[10] = 'A';
        this.tab[11] = 'B';
        this.tab[12] = 'C';
        this.tab[13] = 'D';
        this.tab[14] = 'E';
        this.tab[15] = 'F';
        this.tab[16] = 'G';
        this.tab[17] = 'H';
        this.tab[18] = 'I';
        this.tab[19] = 'J';
        this.tab[20] = 'K';
        this.tab[21] = 'L';
        this.tab[22] = 'M';
        this.tab[23] = 'N';
        this.tab[24] = 'O';
        this.tab[25] = 'P';
        this.tab[26] = 'Q';
        this.tab[27] = 'R';
        this.tab[28] = 'S';
        this.tab[29] = 'T';
        this.tab[30] = 'U';
        this.tab[31] = 'V';
        this.tab[32] = 'W';
        this.tab[33] = 'X';
        this.tab[34] = 'Y';
        this.tab[35] = 'Z';

        readFile();
    }

    private void readFile() throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;

        try {
            inputStream = new FileInputStream(file_name);
            sc = new Scanner(inputStream, "UTF-8");

            // read all the lines and put them into vector inputs:
            while (sc.hasNextLine()) {
                // read next line:
                String line = sc.nextLine();
                // tmp string to convert signs to uppercase:
                String tmp = "";

                // convert all signs to uppercase:
                for (int i = 0; i < line.length(); i++) {
                    tmp = line.toUpperCase();
                }

                System.out.println(tmp);

                // add converted string to vector inputs:
                inputs.add(tmp);
            }

            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void encode() {
        String output = "";
        int x;
        char tmp_x;
        int En;

        for (int i = 0; i < inputs.size(); i++) {
            output = "";

            for (int j = 0; j < inputs.elementAt(i).length(); j++) {
                // catch current letter from input:
                tmp_x = inputs.elementAt(i).charAt(j);

                // if table does NOT contain special character:
                if (findInTable(tmp_x) == true) {
                    // get the number of this letter:
                    x = searchInTable(tmp_x);

                    // calculate the index of the encoded letter:
                    En = (x + n) % tab.length; // mod 36

                    // add this encoded letter to output String
                    output += tab[En];
                } else {
                    output += tmp_x;
                }
            }

            // add line to vector outputs:
            outputs.add(output);
        }
    }

    public void decode() {
        String output = "";
        int y;
        char tmp_y;
        int Dn;

        for (int i = 0; i < inputs.size(); i++) {
            output = "";

            for (int j = 0; j < inputs.elementAt(i).length(); j++) {
                // catch current letter from input:
                tmp_y = inputs.elementAt(i).charAt(j);

                // if table does NOT contain special character:
                if (findInTable(tmp_y) == true) {
                    // get the number of this letter:
                    y = searchInTable(tmp_y);

                    // protect from going out of range (if y-n is negative, then add tab.lenght to y)! (wikipedia)
                    while ((y - n) < 0 ) {
                        y += tab.length;
                    }

                    // calculate the index of the decoded letter:
                    Dn = (y - n) % tab.length; // mod 36

                    // add this decoded letter to output String
                    output += tab[Dn];
                } else {
                    output += tmp_y;
                }
            }

            // add line to vector outputs:
            outputs.add(output);
        }
    }

    private int searchInTable(char letter) {
        int position = 0;

        // iterate through the tab to find the position of the current letter:
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == letter) {
                break;
            } else {
                position++;
            }
        }

        return position;
    }

    private boolean findInTable(char letter) {
        for (int i = 0; i < tab.length; i++) {
            // if there is this letter in tab return true:
            if (tab[i] == letter) {
                return true;
            }
        }
        // if not return false:
        return false;
    }

    public void showOutputs() {
        for (String linie : outputs) {
            System.out.println(linie);
        }
    }

    public void writeToFile() throws IOException {
        File fout = new File(outputFileName);
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < outputs.size(); i++) {
            bw.write(outputs.elementAt(i));
            bw.newLine();
        }

        bw.close();
    }
}
