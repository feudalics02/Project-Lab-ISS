package lab.iss.repository;

import lab.iss.domain.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class RepoDoctors implements IRepoDB<Doctor, Integer> {

    private final UtilsDB utils;

    public RepoDoctors(UtilsDB utils) {
        this.utils = utils;
    }


    @Override
    public void add(Doctor doctor) {

    }

    @Override
    public void remove(Integer integer) {

    }

    public Doctor getByUsername(String username) {
        String SQL = "SELECT * FROM doctors WHERE username = ?";

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
                int departmentID = result.getInt(6);
                return new Doctor(ID, firstName, lastName, username, password, departmentID);
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
    public List<Doctor> getAll() {
        List<Doctor> doctors = new ArrayList<>();
        String SQL = "SELECT * FROM doctors";

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
                int departmentID = result.getInt(6);
                doctors.add(new Doctor(ID, firstName, lastName, username, password, departmentID));
            }

            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctors;
    }
}
