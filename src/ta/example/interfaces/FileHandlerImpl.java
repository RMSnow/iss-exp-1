package ta.example.interfaces;

import ta.example.vo.StockInfo;

import java.io.*;
import java.util.ArrayList;

public class FileHandlerImpl implements FileHandler {

    public StockInfo[] getStockInfoFromFile(String filePath) {
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String templine = "";
            String[] arrs = null;
            int line = 0;

            ArrayList<StockInfo> stockInfos = new ArrayList<>();

            while ((templine = bufferedReader.readLine()) != null) {
                line++;
                if (line == 1) continue;

                StockInfo tempInfo = new StockInfo();

                arrs = templine.split("\t");

                tempInfo.id = Integer.parseInt(arrs[0]);
                tempInfo.title = arrs[1];
                tempInfo.author = arrs[2];
                tempInfo.date = arrs[3];
                tempInfo.lastupdate = arrs[4];
                tempInfo.content = arrs[5];
                tempInfo.answerauthor = arrs[6];
                tempInfo.answer = arrs[7];

                stockInfos.add(tempInfo);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            return stockInfos.toArray(new StockInfo[stockInfos.size()]);

        } catch (FileNotFoundException e) {
            System.out.println("The file " + filePath + " doesn't exist.");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public int setStockInfo2File(String filePath, StockInfo[] stocks) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write("ID\tTITLE\tAUTHOR\tDATE\tLASTUPDATE" +
                    "\tCONTENT\tANSWERAUTHOR\tANSWER\n");

            int line = 1;

            for (StockInfo stock : stocks) {
                bufferedWriter.write(stock.id + "\t" + stock.title + "\t" + stock.author
                        + "\t" + stock.date + "\t" + stock.lastupdate + "\t" + stock.content
                        + "\t" + stock.answerauthor + "\t" + stock.answer + "\n");
                line++;
            }

            bufferedWriter.close();
            outputStreamWriter.close();
            fileOutputStream.close();

            return line;

        } catch (FileNotFoundException e) {
            System.out.println("The file " + filePath + " doesn't exist.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return -1;
    }
}
