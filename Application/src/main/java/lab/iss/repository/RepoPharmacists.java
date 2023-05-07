package lab.iss.repository;

import lab.iss.domain.Pharmacist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoPharmacists implements IRepoDB<Pharmacist, Integer> {

    private final UtilsDB utils;

    public RepoPharmacists(UtilsDB utils) {
        this.utils = utils;
    }

    @Override
    public void add(Pharmacist pharmacist) {

    }

    @Override
    public void remove(Integer integer) {

    }

    public Pharmacist getByUsername(String username) {
        String SQL = "SELECT * FROM pharmacists WHERE username = ?";

        try {
            Connection connection = utils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int ID = result.getInt(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                String password = result.getString(5);
                int pharmacyID = result.getInt(6);
                return new Pharmacist(ID, firstName, lastName, username, password, pharmacyID);
            }

            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Pharmacist> getAll() {
        List<Pharmacist> pharmacists = new ArrayList<>();
        String SQL = "SELECT * FROM pharmacists";

        try {
            Connection connection = utils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int ID = result.getInt(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                String username = result.getString(4);
                String password = result.getString(5);
                int pharmacyID = result.getInt(6);
                pharmacists.add(new Pharmacist(ID, firstName, lastName, username, password, pharmacyID));
            }

            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pharmacists;
    }
}
