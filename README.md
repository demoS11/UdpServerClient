**1) YÖNTEM**

**UDP:**

**UDP (User Datagram Protocol - Kullanıcı Veribloğu İletişim Kuralları),** UDP güvenilir olmayan bir aktarım protokolüdür. Ağ üzerinden paketi gönderir ama gidip gitmediğini takip etmez ve paketin yerine ulaşıp ulaşmayacağına onay verme yetkisi yoktur. UDP üzerinden güvenilir şekilde veri göndermek isteyen bir uygulama bunu kendi yöntemleriyle yapmak zorundadır.

 

**2) UYGULAMA**

* UDP tabanlı çalışan sunucu ve istemci kullandık. İstemci sunucuya bir ping mesajı gönderir buna karşılık sunucu pong mesajı döndürür.

*  Ping ile pong arasında ki gecikmeyi hesaplayarak RTT(Run Trip Time) bulacaktır. Program 5 kez ping mesajı gönderir ve buna karşılık cevap mesajlarını ve RTT sürelerini konsola yazdırır. 

*  RTT sürelerinin ortalamasını hesaplar ve başarılı cevapları konsola yazdırır, timeout olursa SoketTimeOutException(İstek Zaman Aşımına Uğradı) verir.

 

 **3) SONUÇ**

![1555966757396](C:\Users\User\AppData\Roaming\Typora\typora-user-images\1555966757396.png)



![1555966770883](C:\Users\User\AppData\Roaming\Typora\typora-user-images\1555966770883.png)



![1555966792847](C:\Users\User\AppData\Roaming\Typora\typora-user-images\1555966792847.png)