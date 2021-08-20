package com.kirilo.sqlite.jdbc;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Start {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new FileInputStream("src/com/kirilo/sqlite/files/execute.txt"), "UTF-8");
            String path = scanner.nextLine();
            System.out.println("DataBase: " + path);
            if (!new File(path).exists()) {
                System.out.println("DataBase file not found!");
                return;
            }

            DBUtils.openConnection(path);

            StringBuilder builder = new StringBuilder();

            try {
                while (scanner.hasNextLine()) {
                    builder.append(scanner.nextLine());
                }
            } finally {
                scanner.close();
            }

            String[] sqlRequests = builder.toString().split(";");

            builder = new StringBuilder();

            for (String request : sqlRequests) {
                builder.append("Request: ").append(request).append("\n").
                        append("Result:\n");
                for (DirObject dirObject : DBUtils.getResultList(request)) {
                    builder.append(
                            dirObject.getId()).
                            append(dirObject.getName_en()).
                            append(dirObject.getName_ua()).
                            append("\n");
                }
                builder.append("\n");
            }
            String response = builder.toString();
            Logger.getLogger(Start.class.getName()).log(Level.INFO, response);
            writeTextToFile(response);
            DBUtils.showPreparedStatement();
            DBUtils.closeConnection();
        } catch (FileNotFoundException e) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static void writeTextToFile(String response) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("result.txt");
            fileWriter.write(response);
        } catch (IOException e) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, response, e);
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, response, e);
            }
        }
    }
}
