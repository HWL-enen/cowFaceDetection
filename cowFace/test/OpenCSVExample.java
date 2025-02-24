import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVExample {
    private static final String CSV_FILE_PATH = "csv/data.csv";

    // 创建 CSV 文件并添加表头和初始数据
    public static void createCSVFile() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
            // 定义表头
            String[] header = {"ID", "Name", "Age"};
            writer.writeNext(header);

            // 添加初始数据
            String[] row1 = {"1", "Alice", "25"};
            String[] row2 = {"2", "Bob", "30"};
            writer.writeNext(row1);
            writer.writeNext(row2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 向 CSV 文件中添加新数据
    public static void addDataToCSV(String[] data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.writeNext(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 修改 CSV 文件中的数据
    public static void modifyDataInCSV_index(int rowIndex,int cIndex, String num) {
        List<String[]> allData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            allData = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        if (rowIndex < allData.size()) {
            if (cIndex < allData.get(rowIndex).length) {
                String[] newData = allData.get(rowIndex);
                newData[cIndex] = num;
                allData.set(rowIndex, newData);
            }else {
                String[] newArray = new String[allData.get(rowIndex).length + 1];
                // 使用 System.arraycopy 方法将原数组的元素复制到新数组中
                System.arraycopy(allData.get(rowIndex), 0, newArray, 0, allData.get(rowIndex).length);
                // 将新元素添加到新数组的最后位置
                newArray[allData.get(rowIndex).length] = num;
                allData.set(rowIndex, newArray);
            }

        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH))) {
            writer.writeAll(allData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modifyDataInCSV_obj(String obj,String change, String num) {
        List<String[]> allData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            allData = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        int i = 0;
        for (String[] row : allData) {
            if (List.of(row).get(0).equals(obj)) {
                break;
            }
            i++;
        }

        int j = 0;
        if (!List.of(allData.get(0)).contains(change)){
            j = List.of(allData.get(0)).size();
            modifyDataInCSV_index(0,List.of(allData.get(0)).size(),change);
        }else {
            j = List.of(allData.get(0)).indexOf(change);
        }

        modifyDataInCSV_index(i,j,num);
    }

    // 删除 CSV 文件中的数据
    public static void deleteDataFromCSV(int rowIndex) {
        List<String[]> allData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            allData = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        if (rowIndex < allData.size()) {
            allData.remove(rowIndex);
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH))) {
            writer.writeAll(allData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String r,String c){

        List<String[]> allData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            allData = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        int i = 0;
        for (String[] row : allData) {
            if (List.of(row).get(0).equals(r)) {
                break;
            }
            i++;
        }
        if (i >= allData.size() || !List.of(allData.get(0)).contains(c)) {
            return "0";
        }

        return allData.get(i)[List.of(allData.get(0)).indexOf(c)];
    }

    public static void main(String[] args) {


//        // 添加新数据
//        String[] newData = {"2", "ggfd", "35"};
//        addDataToCSV(newData);
//        System.out.println("数据添加成功！");

//        // 修改数据
//        modifyDataInCSV_index(3, 2,"64");
        modifyDataInCSV_obj("2", "head","63");
        System.out.println("数据修改成功！"+getValue("2","head"));

//        // 删除数据
//        deleteDataFromCSV(1);
//        System.out.println("数据删除成功！");
    }
}