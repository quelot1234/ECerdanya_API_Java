package controller;

import dao.EcDao;
import exception.InvalidArgumentException;
import exception.UserException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import model.User;

public class UserController {

    private static final ArrayList<User> allUsers = EcDao.getInstance().getAllUsers();

    private static User loginUser;

    public static User getById(int id) {
        for (User u : allUsers) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public static User getByEmail(String email) {
        for (User u : allUsers) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public static User getLoginUser() {
        return loginUser;
    }

    public static void registerUser(String username, String firstname, String lastname,
            String email, String password, String passwordConfirm, String telf, String c_postal, String direccion)
            throws UserException, IOException, InvalidArgumentException {

        final String ENDPOINT = "http://localhost/EnjoyCerdanya/app/action/sign-in-action.php";

        final String POST_PARAMS = "username=" + username + "&firstname=" + firstname + "&surname=" + lastname
                + "&email=" + email + "&pass=" + password + "&pass_confirm=" + passwordConfirm + "&telf=" + telf
                + "&postal_code=" + c_postal + "&direction=" + direccion + "&app=desktop";

        String response = RequestController.postRequest(ENDPOINT, POST_PARAMS);
        
        if(response == null) {
        	throw new UserException("Null POST request response");
        }

        if (!response.equals("RegistroOk")) {
            throw new UserException(response);
        }
    }

    public static void login(String email, String password) throws UserException, MalformedURLException, IOException, InvalidArgumentException {

        final String ENDPOINT = "http://localhost/EnjoyCerdanya/app/action/login-action.php";

        final String POST_PARAMS = "email=" + email + "&pass=" + password + "&app=desktop";

        String response = RequestController.postRequest(ENDPOINT, POST_PARAMS);

        if (!response.equals("loginOk")) {
            throw new UserException("Login incorrecto");
        }

    }

    public static void logout() {
        if (loginUser != null) {
            loginUser = null;
        }
    }

    public static void setLoginUser(User loginUser) {
        UserController.loginUser = loginUser;
    }
}
