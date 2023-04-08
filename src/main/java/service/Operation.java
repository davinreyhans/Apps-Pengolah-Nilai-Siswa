package service;

import java.io.*;
import java.util.*;

public class Operation {
    public List<Integer> read(String path){
        try{
            File file = new File (path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            var line = " ";
            String[] tempArr;

            List<Integer> listInt = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                tempArr = line.split(";");

                for (int i=0; i<tempArr.length; i++){
                    if (i!=0){
                        var temp = tempArr[i];
                        var intTemp = Integer.parseInt(temp);
                        listInt.add(intTemp);
                    }
                }
            }
            reader.close();
            return listInt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String savePlace){
        MenuImpl menu = new MenuImpl();
        var read = read(menu.readCSV);

        try {
            File file = new File(savePlace);
            if (file.createNewFile()) {
                System.out.println("File Mean-Median-Modus tersimpan di -> " + savePlace);
            }
            FileWriter writer = new FileWriter(file);
            BufferedWriter bwr = new BufferedWriter(writer);

            bwr.write("Berikut Hasil Pengolahan Nilai:\n\nBerikut hasil sebaran data nilai\n");

            //rumus mean
            bwr.write("Mean   : " + String.format("%.2f", mean(read)));
            bwr.write("\n");

            bwr.write("Median : " + median(read));
            bwr.write("\n");

            bwr.write("Modus  : " + modus(read));
            bwr.write("\n");

            bwr.flush();
            bwr.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeModus(String saveMod) {

        MenuImpl menu = new MenuImpl();

        try {
            File file = new File(saveMod);
            if (file.createNewFile()) {
                System.out.println("File Modus Sekolah tersimpan di ->: " + saveMod);
            }
            FileWriter writer = new FileWriter(file);
            BufferedWriter bwr = new BufferedWriter(writer);
            var hMap = frekuensi(read(menu.readCSV));
            Set<Integer> key = hMap.keySet();

            bwr.write("Berikut Hasil Pengolahan Nilai:\n\n");
            bwr.write("Nilai\t\t\t\t" + "|\t\t" + "Frekuensi" + "\n");

            for (Integer nilai : key) {
                bwr.write(nilai + "\t\t\t\t\t" + "|\t\t" + hMap.get(nilai) + "\n");
            }

            bwr.flush();
            bwr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //operasi mean
    private double mean(List<Integer> list) {
        return list.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
    }

    //operasi median
    private double median(List<Integer> list){
        Arrays.sort(new List[]{list});
        double median;
        if (list.size() % 2 == 0) {
            median = ((double) list.get(list.size() / 2) + (double) list.get(list.size() / 2 - 1)) / 2;
        } else {
            median = (double) list.get(list.size() / 2);
        }
        return median;
    }


    private int modus(List<Integer> list) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int max = 1;
        int temp = 0;

        for (Integer integer : list) {
            if (hm.get(integer) != null){
                int count = hm.get(integer);
                count++;
                hm.put(integer, count);

                if (count > max) {
                    max = count;
                    temp = integer;
                }
            } else {
                hm.put(integer, 1);
            }
        }
        return temp;
    }

    //mapping modus
    private Map<Integer, Integer> frekuensi(List<Integer> array){
        Set<Integer> distinct = new HashSet<>(array);
        Map<Integer, Integer> mMap = new HashMap<>();

        for (Integer s : distinct) {
            mMap.put(s, Collections.frequency(array,s));
        }
        return mMap;
    }
}
