
package udpserverclient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Date;

/**
 *
 * @author User
 */
public class UDPIstemci {
        public static void main(String args[]) throws Exception {
        InetAddress address = InetAddress.getLocalHost(); 
        int port = 5678;
        double ortalama = 0;
        String mesaj = "ping";
        if (args.length == 2) {
            try {
                address = InetAddress.getByName(args[0]);
                port = Integer.parseInt(args[1]);
            } catch (Exception e) {
                //Gerekli olmamakla beraber guvenli
                address = InetAddress.getLocalHost();
                port = 5678;
            }
        }

        byte[] sendData = mesaj.getBytes();

        //paket oluşturuldu
        DatagramPacket gonderilecekPaket = new DatagramPacket(sendData, sendData.length, address, port);
        DatagramSocket istemciSocket = new DatagramSocket();
        //timeout belirlendi(rastgele bir değer)
        istemciSocket.setSoTimeout(10000);

        double toplamSure = 0;
        int toplamBasariliMesaj = 0;
        for(int i = 0; i <5; i++) {
            try {
                byte[] gelenMesaj = new byte[9999];
                DatagramPacket gelenPaket = new DatagramPacket(gelenMesaj, gelenMesaj.length);
                Date gondermedenOncekiTarih = new Date();
                istemciSocket.send(gonderilecekPaket);
                istemciSocket.receive(gelenPaket);
                String cevap = new String(gelenPaket.getData(), 0, gelenPaket.getLength());
                if(cevap.toLowerCase().contains("pong")) {
                    // geçen süre hesplanıyor
                    double gecenZaman = new Date().getTime() - gondermedenOncekiTarih.getTime();
                    System.out.println("Sunucudan pong mesaji basariyla alindi. RTT: " + gecenZaman + " ms");
                    toplamSure += gecenZaman;
                    toplamBasariliMesaj++;
                    
                } else {
                    System.err.println("Beklenmedik bir cevap alindi: " + cevap);
                }

            } catch (SocketTimeoutException e) {
                System.err.println("Istek zaman asimina ugradi!");
            } catch (Exception e) {
                System.err.println("Bilinmeyen hata: " + e.getMessage());
            }
        }

        System.out.println("Sunucuya gonderilen 5 mesaj icerisinden " + toplamBasariliMesaj + " tanesi basarili oldu!");
        if (toplamBasariliMesaj != 0 ){
            ortalama = toplamSure/toplamBasariliMesaj ;
            System.out.println("Ortalama RTT: " + ortalama+ " ms");
        }
        else System.out.println("Basarili mesaj olmadigi icin ortalama hesaplanamadi!");

    }
}
