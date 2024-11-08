package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainLogin {
    public static void main(String[] args) {
        Login login = Login.cargarContexto();
        login.login();
    }
}

