# Työaikakirjanpito
Tähän on listattu päivät ja ajat (suurinpiirtein), mihin aikaan olen työstänyt projektia.
Huom kirjanpito on jaettu osiin suunnittelu, graaffinen suunnittelu ja musiikin työstämiseen. Ja myös itse sovelluksen koodaamis ajat!
Alku on hieman arvottu, koska ehdin jo aloittaa ennen tämän kirjanpidon tekoa.

## Suunnittelu (Sisältäen pelin kulun suunnittelua, yms)
Päivä|Aika|Kuvaus
-|-|--------------|
12.3.2018|10h++|Tähän hetkeen saakka : Hajanaista pelin ideointia ja suunnittelua veljien kanssa (olisi pitänyt kirjoittaa ylös) Periaatteessa 24:7 pelin suunnittelua, koska se on hauskaa!|
16.3.2018 | 4h | Pelin tarinan kulun ideointi
21.3.2018|4h|Intron suunnittelua.
25.3.2018|3h|Easter Egg:ien ideointia + pelin tasojen ideointi.


## Graaffinen suunnittelu
Päivä|Aika|Kuvaus
-|-|---------------------
12.3.2018 | 6h | Hahmojen suunnittelu
17.3.2018 | 9h | Hahmojen animaatio testejä
19.3.2018 | 5h | Pelin menun suunnittelua Graaffisesti + animointi
19.3.2018 | 2h | Pelin logon suunnittelu
24.3.2018 | 4h | Pelin menu nappien suunnittelu ja animointi
28.3.2018 | 1h | Lisää nappeja peliin!

## Musiikin suunnittelu
Päivä|Aika|Kuvaus
-|-|-
4.3.2018 - 17.3.2018 | 3h | Musiikin suunnittelua sovelluksilla ja Casion pianolla
31.3.2018 |  | Sovin kaverini kanssa että hän auttaa musiikin luomisen kanssa. Itse teen effektit hahmojen liikkeisiin.
2.4.2018 |  | Ensimmäiset pelin musiikit saapuivat kaveriltani! Ja ovat mahtavia! Laitan kaverini nimen ylös lopputeksteihin ja myös tänne sitten kun olen saanut lisättyä ne peliin (Tämän toteutan vasta kunnes kentät ovat tehty ja myös graaffisesti on valmiimman puolella, joten vasta loppua päin lisään musiikit).

## Ohjelmointi
Päivä|Aika|Kuvaus
-|-|-------------------------------------------
17.3.2018 - 18.3.2018 | 2h | Tietokantojen ja luokkien teko
18.3.2018 | 2h | Sovelluksen alku menu ikkuna JavaFX (puuttuu options ja profiilin muokkaus) (Pelissä on siis kaksi ikkunaa, menu ikkuna ja pelin ikkuna)
18.3.2018 | 4h | Pelin käynnistys (Lataa pelin ikkunan klikkaamaalla starttia)
18.3.2018 | 1h | Peli käynnistyksen jälkeen (Iso valkoinen ruutu)
21.3.2018 |7h|Uusi projekti kansio luotu koska oli sotkuinen, mutta animaatio saatu toimimaan! Pelin introa aloitettu hieman.
24.3.2018 |8h|Lisää luokkia ja enemmän kansioita luotu, introa tehty pidemmälle, näppäimistön lukija luotu ja toimii introssa, luokka luotu gif kuvia varten jota pystyy animoimaan.
25.3.2018 |7h| Jatkoinkin pidemmälle, ja intro on animoitu ja seuraavaksi menujen teko.
27.3.2018 |5h| Menu luotu ja eri menun osiot omissa luokissa, joita pystyy kutsumaan ja antaa suorituksen sinne! (puuttuu vain napit, mutta näitä pystyy vaihtamaan ja valitsemaan jo etukäteen! Periaatteessa menu on täysin toiminnallinen tällä hetkellä! Graaffinen puoli vain puuttuu).
28.3.2018 |4h| Tiles sovellus asennettu ja lisätty riippuvuus projektiin tähän ohjelmaan. --> Sovelluksen avulla voi tehdä kenttiä ja lukea niitä. (Samalla ohjelmalla tehty Shovel Knight:in kentät! Suosittelen tutustumaan!)
29.3.2018 |5h| Erittäin surkean näköinen tutorial kenttä luotu ja latautuu oikein projektissa! (Pitkä tappelu sen kanssa .-.). Kamera oliota toteutettu hieman ja myös pelaaja oliota. Jatkan tänään vielä lisää tämän parissa!
30.3.2018 |4h|KAMERA TOIMII HEUREKA! Tai miten se menikään. Enään pitää saada hahmoille fysiikat niin kentät voivat vaikuttaa pelaajan ja objektien liikkumiseen. Eli tavoitteena oli Surkean mapin tulostus ja sen liikuttaminen. Enään pitää estää kameran meneminen yli rajojen ja sitten voin alkaa tekemään niitä hahmoja ja parempia kenttiäkin!!!
30.3.2018 |6h| Tulikin takaisku ja kamera ei toimikkaan kuten halusin! Joten sinne meni yöunet turhaan... Kamera tulostaa kentän mutta kenttää liikuttaessa hahmot ja muut objektit jäävät silti kiinni eivätkä mene liikkeen mukana. Ratkaisuna muutan koko kamera luokan jotta se liikuttaa JavaFX:n graphicscontextia ja jotenkin sitten lataan myös vain näkyvät tilesit vaikka tämän olin saavuttanut jo.
31.3.2018|2h| Kamera korjattu ja seuraa pelaajaa. Kamera ei kuitenkaa mene kentän alueesta pidemmälle. (Jos pelaaja menee lähelle reunaa niin kamera ei seuraa enään pelaajaa vaan jää paikoilleen! (Tarvitsee kuitenkin hieman finetunea koska tätä pystyy myös zoomaata)!
2.4.2018|3h|Pelin collision detection saatu toimimaan! Päivän pohdinnan jälkeen sain collisio idean toimimaan Path2D:n avulla ja lisäämällä kaikki tilesit tähän. Tällä hetkellä jos pelaaja on tilesin päällä niin tämä ilmoittaa sen. LISÄKSI MapSuper luokkaan on lisätty kartan luku joka oli aikaisemmin Kamera luokan sisällä joka ei ollut järkevää. Nyt kamera ja mappi ovat erikseen!
