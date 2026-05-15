/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author DAW129
 */
public class pruebaGPT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        

        SSHConnector ssh = new SSHConnector();

        try {

            ssh.connect(
                    "equipo2",
                    "usuario@1",
                    "10.0.10.23",
                    22
            );
            
            

            System.out.println("SSH conectado");

            String salida = ssh.executeCommand("whoami");

            System.out.println(salida);

            ssh.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
