package service;

import java.util.Scanner;

public class MenuImpl implements Menu{
    Scanner input = new Scanner(System.in);

    Operation operationObj = new Operation();

    String dir = "C:\\Users\\...\\IdeaProjects\\AplikasiPengolahNilaiSiswa\\src\\main\\resources";

    String readCSV = "src/main/resources/data_sekolah.csv"; //path csv
    String writeModus = "src/main/resources/Frekuensi.txt"; //path modus
    String writeMMM = "src/main/resources/MeanMedianModus.txt"; //path mean modus median

    public void header(){
        System.out.println("-------------------------------");
        System.out.println("Aplikasi Pengolah Nilai Siswa");
        System.out.println("-------------------------------");
    }

    public void menu2(){
        System.out.println("0. Exit\n1. Kembali ke menu utama");
        for (int i=0; i<1; i++) {
            System.out.print("===> ");
            int pilihMenu2 = input.nextInt();

            if (pilihMenu2 == 0) {
                System.out.println("Sampai jumpa kembali!");
                loading();
                System.exit(0);
            } else if (pilihMenu2 == 1) {
                loading();
                MainMenu();
            } else {
                System.out.println("Pilihan tidak ada.\n");
                i--;
                loading();
            }
        }
    }

    public void loading(){
        try {
            Thread.sleep(1000); // mengatur program delay selama 1 detik
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void MainMenu(){
        header();
        System.out.println("Letakan file csv dengan nama file 'data_sekolah' di direktori berikut '" + dir + "'");
        System.out.println("\nPilih menu:");
        System.out.println("1. Generate txt untuk menampilkan modus\n2. Generate txt untuk menampilkan nilai rata-rata, median\n3. Generate kedua file\n0. Exit");

        System.out.print("===> ");
        int pilihMenu = input.nextInt();
        System.out.println();
        switch (pilihMenu){
            case 0:
                System.out.println("\nSampai jumpa kembali!");
                System.exit(0);
                break;
            case 1:             // bikin file modus aja
                operationObj.writeModus(writeModus);
                header();
                System.out.println(("File telah di generate di '" + writeModus + "'"));
                menu2();
                break;
            case 2:             // bikin file modus median mean
                operationObj.write(writeMMM);
                header();
                System.out.println(("File telah di generate di '" + writeMMM + "'"));
                menu2();
                break;
            case 3:             // bikin 2 file ( modus & modus median mean )
                operationObj.write(writeMMM);
                operationObj.writeModus(writeModus);
                header();
                System.out.println(("File telah di generate di '" + writeMMM + "' dan '" + writeModus + "'"));
                menu2();
            default:
                System.out.println("Pilihan tidak ada.\n");
                loading();
                MainMenu();
                break;
        }
    }
}
