package ta.example.interfaces;

import ta.example.vo.StockInfo;

public class StockSorterImpl implements StockSorter {
    public StockInfo[] sort(StockInfo[] info) {
        return quickSort(info, 0, info.length - 1);
    }

    public StockInfo[] sort(StockInfo[] info, boolean order) {
        StockInfo[] stockInfos = quickSort(info, 0, info.length - 1);

        //升序
        if (order) {
            return stockInfos;
        }

        //转换为降序
        int n = stockInfos.length;
        for (int i = 0; i < n / 2; i++) {
            StockInfo tempStock = stockInfos[i];
            stockInfos[i] = stockInfos[n - i - 1];
            stockInfos[n - i - 1] = tempStock;

            tempStock = null;
        }
        return stockInfos;

    }

    //快速排序算法：升序排列时，start=0，end=length-1
    public StockInfo[] quickSort(StockInfo[] infos, int start, int end) {
        if (start >= end) {
            return infos;
        }

        int i = start;
        int j = end;
        int value = infos[i].answer.length();
        boolean flag = true;

        while (i != j) {
            if (flag) {
                if (value > infos[j].answer.length()) {
                    StockInfo tempStock = infos[i];
                    infos[i] = infos[j];
                    infos[j] = tempStock;

                    tempStock = null;

                    flag = false;

                } else {
                    j--;
                }
            } else {
                if (value < infos[i].answer.length()) {
                    StockInfo tempStock = infos[i];
                    infos[i] = infos[j];
                    infos[j] = tempStock;

                    tempStock = null;

                    flag = true;
                } else {
                    i++;
                }
            }
        }

        quickSort(infos, start, j - 1);
        quickSort(infos, i + 1, end);

        return infos;
    }
}
