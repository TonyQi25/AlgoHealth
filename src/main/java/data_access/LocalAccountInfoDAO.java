package data_access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import data.AccountInfo;
import use_case.login.LoginDataAccessInterface;
import use_case.logout.LogoutDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;

/**
 * DAO for user data implemented using a File to persist the data.
 */
public class LocalAccountInfoDAO implements SignupDataAccessInterface, LoginDataAccessInterface,
        LogoutDataAccessInterface {

    private static final String HEADER = "username,password";

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, AccountInfo> accounts = new HashMap<>();
    private String currentUsername;

    public LocalAccountInfoDAO(String csvPath) throws IOException {

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("dateOfBirth", 2);
        headers.put("height", 3);
        headers.put("weight", 4);
        headers.put("diet", 5);
        headers.put("dietaryRestriction", 6);
        headers.put("goal", 7);

        if (csvFile.length() == 0) {
            save();
        }
        else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                final String header = reader.readLine();

                if (!header.equals(HEADER)) {
                    throw new RuntimeException(String.format("header should be%n: %s%but was:%n%s", HEADER, header));
                }

                String row;
                while ((row = reader.readLine()) != null) {
                    final String[] col = row.split(",");
                    final String username = String.valueOf(col[headers.get("username")]);
                    final String password = String.valueOf(col[headers.get("password")]);
                    final String[] dateOfBirthString =  String.valueOf(col[headers.get("dateOfBirth")]).split("/");
                    final LocalDate dateOfBirth = LocalDate.of(Integer.valueOf(dateOfBirthString[2]),
                            Integer.valueOf(dateOfBirthString[1]), Integer.valueOf(dateOfBirthString[0]));
                    final Integer height = Integer.valueOf(col[headers.get("height")]);
                    final Integer weight = Integer.valueOf(col[headers.get("weight")]);
                    final String[] diet = new String[1];
                    diet[0] = String.valueOf(col[headers.get("diet")]);
                    final List<String> dietaryRestrictions = new ArrayList<>();
                    dietaryRestrictions.add(String.valueOf(col[headers.get("dietaryRestrictions")]));
                    final String goal = String.valueOf(col[headers.get("goal")]);
                    final AccountInfo account = new AccountInfo(dateOfBirth, height, weight, diet, goal, username, password,
                            dietaryRestrictions);
                    accounts.put(username, account);
                }
            }
        }
    }

    private void save() {
        final BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (AccountInfo account : accounts.values()) {
                final String line = String.format("{name: %s, password: %s, dateOfBirth: %s, height: %s, weight: %s," +
                                "diet: %s, goal: %s, dietaryRestrictions: %s}",
                        account.getUsername(), account.getPassword(), account.getDateOfBirth(), account.getHeight(), account.getWeight(),
                        account.getDiet(), account.getDietaryRestrictions());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void createAccount(String username, String password) {

    }

    @Override
    public AccountInfo get(String username) {
        return accounts.get(username);
    }

    @Override
    public void put(String username, String password, AccountInfo accountInfo) {

    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void save(AccountInfo account) {
        accounts.put(account.getUsername(), account);
        this.save();
    }
}