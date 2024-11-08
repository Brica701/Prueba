package org.example;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    private final List<Credencial> credencialesRegistradas = new ArrayList<>();

    // Método que carga el contexto y lee las anotaciones
    public static Login cargarContexto() {
        Login login = new Login();
        Field[] fields = Login.class.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Credencial.class)) {
                Credencial credencial = field.getAnnotation(Credencial.class);
                login.credencialesRegistradas.add(credencial);
            }
        }
        return login;
    }

    // Método para realizar el login
    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();

        try {
            String hashedInputPassword = hashPassword(password);

            for (Credencial credencial : credencialesRegistradas) {
                if (credencial.usuario().equals(username) && credencial.hashPasswd().equals(hashedInputPassword)) {
                    System.out.println("ACCESO CONCEDIDO");
                    return true;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error al calcular el hash de la contraseña.");
            e.printStackTrace();
        }

        System.out.println("ACCESO DENEGADO");
        return false;
    }

    // Método que obtiene el hash de la contraseña
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    private static String bytesToHex(byte[] byteHash) {
        StringBuilder hexString = new StringBuilder(2 * byteHash.length);
        for (byte b : byteHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Credenciales de ejemplo
    @Credencial(usuario = "usuario1", hashPasswd = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918") //contraseña:admin
    private static String credencial1;

    @Credencial(usuario = "usuario2", hashPasswd = "ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270") //contraseña: admin1234
    private static String credencial2;

    @Credencial(usuario = "josemaria", hashPasswd = "5994471abb01112afcc18159f6cc74b4f511b99806c6c8a450f9a6437a7a5b38") // Contraseña: letmein
    private static String credencial3;
}


