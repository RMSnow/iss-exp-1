package ta.example;

import ta.example.interfaces.FileHandler;
import ta.example.interfaces.FileHandlerImpl;
import ta.example.interfaces.StockSorter;
import ta.example.interfaces.StockSorterImpl;
import ta.example.vo.StockInfo;

public class Main {

    private static FileHandler fileHandler;

    private static StockSorter stockSorter;

    static {
        fileHandler = new FileHandlerImpl();
        stockSorter = new StockSorterImpl();
    }

    public static void main(String[] args) {

        //从data.txt获取StockInfo
        StockInfo[] stockInfos = fileHandler.getStockInfoFromFile("data.txt");
        System.out.println("读取成功");

        //升序排列，并输出
        fileHandler.setStockInfo2File("output-asc.txt", stockSorter.sort(stockInfos));
        System.out.println("升序排列成功");

        //降序排列，并输出
        fileHandler.setStockInfo2File("output-desc.txt", stockSorter.sort(stockInfos, false));
        System.out.println("降序排列成功");
    }

}
