/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserverclient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author User
 */

public class UDPSunucu {
     public static void main(String args[]) throws Exception {
        DatagramSocket calcServerSocket = new DatagramSocket(5678);// UDP için DatagramSoket
        System.out.println("UDP sunucu 5678 portunda calismaya basladi");
        

        while (true) {
            byte[] gelenMesaj = new byte[65508];
            byte[] cevap;

            // Istemciden gelecek bilgiyi tutmak icin bir DatagramPacket hazirlama
            DatagramPacket receivePacket = new DatagramPacket(gelenMesaj, gelenMesaj.length);

            // Istemciden gelecek DatagramPacket'i beklemeye baslama
            calcServerSocket.receive(receivePacket);

            InetAddress istemciAdres = receivePacket.getAddress();
            int istemciPort = receivePacket.getPort();
            String mesaj = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println(istemciAdres.getHostAddress() + ": " +istemciPort + " adresinden mesaj alindi!");
            System.out.println("Mesaj icerigi: " + mesaj);

            if (mesaj.toLowerCase().contains("ping")) {
                cevap = "Pong".getBytes();

                //Cevabi iceren DatagramPacket'i hazirlama
                DatagramPacket sendPacket = new DatagramPacket(cevap, cevap.length, istemciAdres, istemciPort);

                //Cevabi iceren DatagramPacket'i gonderme
                calcServerSocket.send(sendPacket);
            } else {
                //ping mesaji harici mesajlari onemseme
            }
        } // while bitti
    }
}

