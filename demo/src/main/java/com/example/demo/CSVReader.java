package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

        private final UserRepository userRepository;
        private static final String COMMA_DELIMITER = ",";
        private String fileName;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public CSVReader(UserRepository userRepository, String fileName) {
            this.userRepository = userRepository;
            this.fileName = fileName;
        }

        private List<List<String>> readLines() {
            List<List<String>> records = new ArrayList<>();
            try (Scanner scanner = new Scanner(new File(fileName));) {
                while (scanner.hasNextLine()) {
                    records.add(getRecordFromLine(scanner.nextLine()));
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            return records;
        }

        private List<String> getRecordFromLine(String line) {
            List<String> values = new ArrayList<String>();
            try (Scanner rowScanner = new Scanner(line)) {
                rowScanner.useDelimiter(COMMA_DELIMITER);
                while (rowScanner.hasNext()) {
                    values.add(rowScanner.next());
                }
            }
            return values;
        }

        //salvataggio su db di una lista di user, letta da un file csv
        public void saveUsersFromCsvFile() throws InvalidObjectException {
            List<List<String>> records = readLines();

            for(List<String> list : records){
                if(records.size() != 4)
                    throw new InvalidObjectException("Errore nella costruzione dell'oggetto User");
                User userDto = new User(list.get(0), list.get(1), list.get(2), list.get(3));

                userRepository.save(userDto);
            }
        }
}


