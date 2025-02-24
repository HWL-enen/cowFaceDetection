import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvMng {
    private String CSV_FILE_PATH ;
    CsvMng(String CSV_FILE_PATH) {
        this.CSV_FILE_PATH = String.format("csv/%s.csv",CSV_FILE_PATH);
    }
    // 创建 CSV 文件并添加表头和初始数据
    public void createCSVFile() {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(CSV_FILE_PATH), StandardCharsets.UTF_8);
             CSVWriter csvWriter = new CSVWriter(writer)) {
            // 定义表头
            String[] header = {"Name"};
            csvWriter.writeNext(header);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 向 CSV 文件中添加新数据
    public void addDataToCSV(String[] data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.writeNext(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 修改 CSV 文件中的数据
    public void modifyDataInCSV_index(int rowIndex,int cIndex, String num) {
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

    public void modifyDataInCSV_obj(String obj,String change, String num) {
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
        if (i >= allData.size() ){
            String[] newData = new String[allData.get(0).length];
            for (int index = 0; index < allData.get(0).length; index++) {
                newData[index] = "0";
            }
            newData[0] = obj;
            addDataToCSV(newData);
        }

        int j = 0;
        if (!List.of(allData.get(0)).contains(change)){
            j = List.of(allData.get(0)).size();
            modifyDataInCSV_index(0,List.of(allData.get(0)).size(),change);
            for (int index = 1; index < allData.size(); index++) {
                modifyDataInCSV_index(index,List.of(allData.get(index)).size(),"0");
            }
        }else {
            j = List.of(allData.get(0)).indexOf(change);
        }

        modifyDataInCSV_index(i,j,num);
    }

    // 删除 CSV 文件中的数据
    public void deleteDataFromCSV(int rowIndex) {
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

    public String getValue(String r,String c){

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

}