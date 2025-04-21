package Security;
import javax.swing.*;
import java.util.*;

public class ColumnarCipher {
    private int getNumberOfColumns(String plainText,String ciphertext){
        HashMap<Integer,Integer> lengthFrequency= new HashMap<>();
        for(int i=0;i<ciphertext.length()-1;i++){
            int idx1=plainText.indexOf(ciphertext.charAt(i));
            int idx2=plainText.indexOf(ciphertext.charAt(i+1));
            lengthFrequency.put((idx2-idx1),lengthFrequency.getOrDefault(idx2-idx1,0)+1);
        }
        int max = lengthFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1);
        return max;
    }
    public List<Integer> analyse(String plainText, String cipherText) {

        int columns=getNumberOfColumns(plainText,cipherText);
        int rows=(int)Math.ceil((double) plainText.length()/columns);
        Integer[] key=new Integer[columns];
        char[][] wordMatrix=new char[rows][columns];
        int index=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                wordMatrix[i][j]=plainText.charAt(index);
                index++;
            }
        }
        int upperBound=(int)Math.ceil((double) cipherText.length()/rows);
        for(int i=0;i<upperBound;i++){
            int start=i*rows;
            int end=start+rows;
            String str=cipherText.substring(start,end);
            for(int j=0;j<columns;j++){
                boolean flag=true;
                for(int k=0;k<rows;k++){
                    char c1=str.charAt(k);
                    char c2=wordMatrix[k][j];
                    if(c1!=c2){

                        flag=false;
                        break;
                    }
                }
                if (flag) {
                    key[j]=i+1;
                    break;
                }
            }

        }
        return Arrays.asList(key) ; // Placeholder return
    }

    public String decrypt(String cipherText, List<Integer> key) {
        int cipherSize = cipherText.length();
        int rows = (int) Math.ceil((double) cipherSize / key.size());
        char[][] grid = new char[rows][key.size()];
        int count = 0;

        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            keyMap.put(key.get(i) - 1, i);
        }

        int remainingCols = cipherSize % key.size();
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < rows; j++) {
                if (remainingCols != 0 && j == rows - 1 && keyMap.get(i) >= remainingCols) continue;
                grid[j][keyMap.get(i)] = cipherText.charAt(count++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.size(); j++) {
                result.append(grid[i][j]);
            }
        }
        return result.toString().toUpperCase().trim();
    }

    public String encrypt(String plainText, List<Integer> key) {
        int ptSize = plainText.length();
        int rows = (int) Math.ceil((double) ptSize / key.size());
        char[][] grid = new char[rows][key.size()];
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.size(); j++) {
                if (count >= ptSize) {
                    grid[i][j] = 'x';
                } else {
                    grid[i][j] = plainText.charAt(count++);
                }
            }
        }

        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            keyMap.put(key.get(i) - 1, i);
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < rows; j++) {
                cipherText.append(Character.toUpperCase(grid[j][keyMap.get(i)]));
            }
        }
        return cipherText.toString();
    }
}
