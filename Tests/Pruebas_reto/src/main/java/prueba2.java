/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author DAW129
 */
public class prueba2 {


    public static void main(String[] args) {

        Session session = null;

        try {

            /*
             * ==========================
             * CONEXION SSH
             * ==========================
             */

            JSch jsch = new JSch();

            session = jsch.getSession(
                    "equipo2",
                    "10.0.10.23",
                    22
            );

            session.setPassword("usuario@1");

            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            System.out.println("SSH conectado");

            /*
             * ==========================
             * TUNEL SSH
             * ==========================
             */

            int puertoLocal = 3307;

            session.setPortForwardingL(
                    puertoLocal,
                    "192.168.10.10",
                    3306
            );

            System.out.println("Tunel SSH creado");

            /*
             * ==========================
             * CONEXION MYSQL
             * ==========================
             */

            AccesoBase acceso = AccesoBase.getInstance();

            Connection conn = acceso.getConn();

            if (conn != null && !conn.isClosed()) {

                System.out.println("Conexion MySQL correcta");

            } else {

                System.out.println("No se pudo conectar a MySQL");

            }

            /*
             * ==========================
             * CERRAR
             * ==========================
             */

            acceso.cerrarConexion();

            session.disconnect();

            System.out.println("Conexion cerrada");

        } catch (Exception e) {

            System.out.println("ERROR GENERAL");

            e.printStackTrace();

            if (session != null &&
                    session.isConnected()) {

                session.disconnect();
            }
        }
    }
}
    

