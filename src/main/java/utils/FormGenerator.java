package utils;

import model.User;

public class FormGenerator {
    public static String getUpdateForm(User user) {
        return  "<form id=\"inline\" action=\"/edituser\" method=\"GET\">" +
                "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                "<input type=\"hidden\" name=\"login\" value=\"" + user.getLogin() + "\">" +
                "<input type=\"hidden\" name=\"password\" value=\"" + user.getPassword() + "\">" +
                "<input type=\"hidden\" name=\"email\" value=\"" + user.getEmail() + "\">" +
                "<input id=\"sub\" type=\"submit\" value=\"Edit User\"></form>";
    }
    public static String getDeleteForm(User user) {
        return  "<form id=\"inline\" action=\"/deleteuser\" method=\"POST\">" +
                "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                "<input type=\"submit\" value=\"Delete User\"></form>";
    }
}
