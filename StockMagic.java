// File name: StockMagic.java
import java.util.*;

public class StockMagic {
    // Crystal Ball Analytics
    public static float calculateAverage(float[] prices) {
        float sum = 0;
        for(float price : prices) sum += price;
        return sum / prices.length;
    }

    // Dragon's Treasure Finder
    public static float findMaximum(float[] prices) {
        float max = prices[0];
        for(float price : prices) if(price > max) max = price;
        return max;
    }

    // Magic Coin Counter
    public static int countPrices(float[] prices, float target) {
        int count = 0;
        for(float price : prices) if(price == target) count++;
        return count;
    }

    // Alchemist's Sum
    public static ArrayList<Float> cumulativeSum(ArrayList<Float> prices) {
        ArrayList<Float> result = new ArrayList<>();
        float sum = 0;
        for(float price : prices) {
            sum += price;
            result.add(sum);
        }
        return result;
    }

    public static void main(String[] args) {
        float[] prices = {45.8f, 43.2f, 44.9f, 48.3f, 47.1f, 49.7f, 46.4f, 50.2f, 49.9f, 48.6f};
        ArrayList<Float> priceList = new ArrayList<>();
        for(float p : prices) priceList.add(p);

        System.out.println("✨ Average: " + calculateAverage(prices));
        System.out.println("🐉 Maximum: " + findMaximum(prices));
        System.out.println("🔍 Count of 48.3: " + countPrices(prices, 48.3f));
        System.out.println("📈 Cumulative Sums: " + cumulativeSum(priceList));
    }
}