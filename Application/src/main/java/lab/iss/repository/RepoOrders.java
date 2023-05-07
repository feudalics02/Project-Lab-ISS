package lab.iss.repository;

import javafx.util.Pair;
import lab.iss.domain.Medicine;
import lab.iss.domain.Order;
import lab.iss.domain.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepoOrders implements IRepoDB<Order, Integer> {

    private final UtilsDB utils;

    public RepoOrders(UtilsDB utils) {
        this.utils = utils;
    }

    @Override
    public void add(Order order) {

    }

    @Override
    public void remove(Integer integer) {

    }

    public List<Pair<Medicine, Integer>> getMedicines(int orderID) {
        List<Pair<Medicine, Integer>> medicines = new ArrayList<>();
        String SQL = "SELECT medicines_order.quantity, medicines_order.id_medicine, medicines.name, medicines.description " +
                "FROM medicines_order INNER JOIN medicines ON medicines.id = medicines_order.id_medicine WHERE medicines_order.id_order = ?";

        try {
            Connection connection = utils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, orderID);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int quantity = result.getInt(1);
                int medicineID = result.getInt(2);
                String name = result.getString(3);
                String description = result.getString(4);

                medicines.add(new Pair<>(new Medicine(medicineID, name, description), quantity));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return medicines;
    }

    public List<Order> getByDepartment(int departmentID) {
        List<Order> orders = new ArrayList<>();
        String SQL = "SELECT * FROM orders WHERE id_doctor IN (SELECT id FROM doctors WHERE id_department = ?)";

        try {
            Connection connection = utils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, departmentID);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int ID = result.getInt(1);
                LocalDateTime dateTime = result.getTimestamp(2).toLocalDateTime();
                OrderStatus status = OrderStatus.valueOf(result.getString(3));
                orders.add(new Order(ID, dateTime, status));
            }

            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }
    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String SQL = "SELECT * FROM orders";

        try {
            Connection connection = utils.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int ID = result.getInt(1);
                LocalDateTime dateTime = result.getTimestamp(2).toLocalDateTime();
                OrderStatus status = OrderStatus.valueOf(result.getString(3));
                orders.add(new Order(ID, dateTime, status));
            }

            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }
}
