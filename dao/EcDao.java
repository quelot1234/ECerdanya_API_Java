package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Database;
import model.House;
import model.User;
import controller.UserController;
import java.sql.PreparedStatement;
import model.Rent;

public class EcDao {

    private static EcDao daoInstance;
    private Connection dbCon;

    public EcDao() {
        try {
            this.dbCon = new Database().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(EcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static EcDao getInstance() {
        if (daoInstance == null) {
            daoInstance = new EcDao();
        }

        return daoInstance;
    }

    public int getUserId(User user) {

        if (user == null) {
            return 0;
        }

        int id = 0;

        final String sql = "SELECT id_usuario FROM usuario WHERE email = '" + user.getEmail() + "'";

        try {
            Statement stmt;
            ResultSet rs;

            stmt = this.dbCon.createStatement();

            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                id = rs.getInt("id_usuario");
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                this.dbCon.close();
            } catch (SQLException ex) {
                Logger.getLogger(EcDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }

    public String getUserToken(User user) {
        if (user == null) {
            return null;
        }

        String token = null;

        final String sql = "SELECT token FROM usuario "
                + "WHERE id_usuario = '" + user.getId() + "'";

        try {
            Statement stmt = this.dbCon.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            boolean hasResults = rs.next();

            if (hasResults) {
                token = rs.getString("token");
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                this.dbCon.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }

        return token;
    }

    public ArrayList<User> getAllUsers() {
        String sql = "SELECT * FROM usuario";

        ArrayList<User> users = new ArrayList<>();

        try {
            Statement stmt;
            ResultSet rs;

            stmt = this.dbCon.createStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String[] surnames = {rs.getString("apellido1"), rs.getString("apellido2")};

                users.add(new User(rs.getString("nombre"), surnames, rs.getString("email"),
                        rs.getString("telf"), rs.getString("c_postal"), rs.getString("direccion")));
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos: " + e.getMessage());
        } finally {
            try {
                this.dbCon.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión con la base de datos: " + e.getMessage());
            }
        }

        return users;
    }

    public ArrayList<House> getAllHouses() {
        ArrayList<House> houses = new ArrayList<>();

        final String sql = "SELECT * FROM vivienda";

        try {
            Statement stmt;
            ResultSet rs;

            stmt = this.dbCon.createStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                houses.add(new House(rs.getInt("id_vivienda"), rs.getString("nombre"), rs.getString("descripcion"),
                        rs.getString("direccion"), rs.getInt("ciudad"), rs.getString("c_postal"),
                        rs.getInt("num_max_habitantes"), rs.getInt("num_habitaciones"),
                        rs.getInt("num_wc"), rs.getInt("amueblada") == 1, rs.getDouble("precio_por_noche"),
                        rs.getInt("propietario"), rs.getInt("num_reservas")));
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos: " + e.getMessage());
        } finally {
            try {
                this.dbCon.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
        return houses;
    }

    public ArrayList<House> getHousesByInitialRentDate(Date initalRentDate) {
        ArrayList<House> houses = new ArrayList<>();

        final String DATE = initalRentDate.toString();

        final String SQL = "SELECT * FROM reserva INNER JOIN vivienda WHERE fecha_inicio = '" + DATE + "'";

        try {
            Statement stmt;
            ResultSet rs;

            stmt = this.dbCon.createStatement();

            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                houses.add(new House(rs.getInt("id_vivienda"), rs.getString("nombre"), rs.getString("descripcion"),
                        rs.getString("direccion"), rs.getInt("ciudad"), rs.getString("c_postal"),
                        rs.getInt("num_max_habitantes"), rs.getInt("num_habitaciones"),
                        rs.getInt("num_wc"), rs.getInt("amueblada") == 1, rs.getDouble("precio_por_noche"),
                        rs.getInt("propietario"), rs.getInt("num_reservas")));
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            try {
                this.dbCon.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }

        return houses;
    }

    public House getHouseById(int id) {
        String sql = "SELECT * FROM vivienda WHERE id_vivienda = '" + id + "'";

        House house = null;

        try {
            Statement stmt;
            ResultSet rs;

            stmt = this.dbCon.createStatement();

            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                house = new House(rs.getInt("id_vivienda"), rs.getString("nombre"), rs.getString("descripcion"),
                        rs.getString("direccion"), rs.getInt("ciudad"), rs.getString("c_postal"),
                        rs.getInt("num_max_habitantes"), rs.getInt("num_habitaciones"),
                        rs.getInt("num_wc"), rs.getInt("amueblada") == 1, rs.getDouble("precio_por_noche"),
                        rs.getInt("propietario"), rs.getInt("num_reservas"));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            try {
                this.dbCon.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }

        return house;
    }

    public boolean validateDates(Date initialDate, Date finalDate) {
        String sql = "SELECT * FROM reserva WHERE fecha_inicio BETWEEN '" + initialDate.toLocalDate() + "' "
                + "AND '" + finalDate.toLocaleString() + "' "
                + "OR fecha_fin BETWEEN '" + initialDate.toLocaleString() + "' "
                + "AND '" + finalDate.toLocaleString() + "'";

        boolean valid = false;

        try {
            Statement stmt;
            ResultSet rs;

            stmt = this.dbCon.createStatement();

            rs = stmt.executeQuery(sql);

            valid = (rs.next() != true);

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            try {
                this.dbCon.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }

        return valid;
    }

    public void saveNewRent(House rentHouse, User user, Date fechaInicio, Date fechaFin, int numHabitantes, int numNoches)
            throws Exception {
        if (rentHouse != null && user != null && fechaInicio != null && fechaFin != null) {
            if (UserController.getById(user.getId()) != null && this.getHouseById(rentHouse.getId()) != null) {
                if (fechaFin.before(fechaInicio)) {
                    throw new Exception("La fecha de fin no puede ser anterior a la fecha de inicio");
                }

                if (fechaFin.equals(fechaInicio)) {
                    throw new Exception("La fecha de inicio y la fecha de fin no pueden ser iguales");
                }

                if (!this.validateDates(fechaInicio, fechaFin)) {
                    throw new Exception("Fechas ocupadas");
                }

                final double precio = (rentHouse.getNightPrice() * numNoches);

                String sql = "INSERT INTO reserva (id_usuario, id_vivienda, fecha_inicio, "
                        + "fecha_fin, num_habitantes, num_noches, precio) "
                        + "VALUES (?, ?, ?, ?, ?)";

                try {
                    PreparedStatement pStmt;

                    pStmt = this.dbCon.prepareStatement(sql);

                    pStmt.setInt(1, user.getId());
                    pStmt.setInt(2, rentHouse.getId());
                    pStmt.setDate(3, fechaInicio);
                    pStmt.setDate(4, fechaFin);
                    pStmt.setDouble(5, precio);

                    pStmt.executeUpdate();

                    pStmt.close();
                } catch (SQLException e) {
                    throw new Exception("Error al guardar la reserva en la base de datos! " + e.getMessage());
                }
            } else {
                throw new Exception("Error al guardar la reserva");
            }
        } else {
            throw new Exception("Error al guardar la reserva");
        }
    }

    public ArrayList<House> getHouseReserves(House house) throws Exception {

        if (house == null) {
            return null;
        }

        final String SQL = "SELECT * FROM reserva WHERE id_vivienda = '" + house.getId() + "'";

        ArrayList<House> houses = new ArrayList<>();

        try {
            Statement stmt;
            ResultSet rs;

            stmt = this.dbCon.createStatement();

            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                houses.add(new House(rs.getInt("id_vivienda"), rs.getString("nombre"), rs.getString("descripcion"),
                        rs.getString("direccion"), rs.getInt("ciudad"), rs.getString("c_postal"),
                        rs.getInt("num_max_habitantes"), rs.getInt("num_habitaciones"),
                        rs.getInt("num_wc"), rs.getInt("amueblada") == 1, rs.getDouble("precio_por_noche"),
                        rs.getInt("propietario"), rs.getInt("num_reservas")));
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                this.dbCon.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }

        return houses;
    }

    public House getReservedHouse(int idReserva) throws Exception {

        House house = null;

        final String SQL = "SELECT * FROM reserva r "
                + "INNER JOIN vivienda v ON r.id_vivienda = v.id_vivienda "
                + "WHERE r.id_vivienda = '" + idReserva + "'";

        try {
            Statement stmt;
            ResultSet rs;

            stmt = this.dbCon.createStatement();

            rs = stmt.executeQuery(SQL);

            if (rs.next()) {
                house = new House(rs.getInt("id_vivienda"), rs.getString("nombre"), rs.getString("descripcion"),
                        rs.getString("direccion"), rs.getInt("ciudad"), rs.getString("c_postal"),
                        rs.getInt("num_max_habitantes"), rs.getInt("num_habitaciones"),
                        rs.getInt("num_wc"), rs.getInt("amueblada") == 1, rs.getDouble("precio_por_noche"),
                        rs.getInt("propietario"), rs.getInt("num_reservas"));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                this.dbCon.close();
            } catch (SQLException e) {
                throw new Exception(e.getMessage());
            }
        }

        return house;
    }

    public Rent getRentById(int id) throws Exception {

        String sql = "SELECT * FROM reserva WHERE id_reserva = '" + id + "'";

        Rent rent = null;

        try {
            Statement stmt;
            ResultSet rs;
            
            stmt = this.dbCon.createStatement();
            
            rs = stmt.executeQuery(sql);
            
            if(rs.next()){
                rent = new Rent(rs.getInt("id_usuario"), rs.getInt("id_vivienda"), rs.getDate("fecha_inicio"), rs.getDate("fecha_fin"), rs.getInt("num_noches"), rs.getInt("num_habitantes"), rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }

        return rent;
    }

}
