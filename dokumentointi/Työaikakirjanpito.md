# Työaikakirjanpito
Tähän on listattu päivät ja ajat (suurinpiirtein), mihin aikaan olen työstänyt projektia.
Huom kirjanpito on jaettu osiin suunnittelu, graaffinen suunnittelu ja musiikin työstämiseen. Ja myös itse sovelluksen koodaamis ajat!
Alku on hieman arvottu, koska ehdin jo aloittaa ennen tämän kirjanpidon tekoa.

# Käytetty aika suurinpiirtein = 250h!!!

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
5.4.2018 | 3h | Pelin ensimmäistä safezonea suunniteltu! Grafiikka tyylin vaihdon ja pelin nimi pitäisi vaihtaa 2.5D scrolleriksi!
15-17.4.2018 | 5h | Pelin grafiikoita edistetty ja luotu lisää nappeja ja myös gui korjattu!

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
5.4.2018 | 2h | Pelin fysiikkaa alettu pohtimaan. Tällä hetkellä pelaaja liikkuu melkein oikein. Täytyy saada vain sulavammaksi. Kuitenkaan hahmo ei voi mennä seinien läpi joka on täydellistä!
8.4.2018 | 10h | Pelin fysiikka mennyt pahasti pieleen... Ei ollut yhteensopiva metodin kanssa ja sain pähkäillä koko päivän missä ongelma on. Yritin saada sulavampaa liikkumista peliin mutta se vaikuttaa olevan mahdotonta tuolla algoritmilla.
9.4.2018 | 12h | Tuhlasin taas toisen päivän collisionin korjaamiseen ja ei tulosta. Päätin tehdä näin että jätin sulavuuden pois osittain. (Ehkä jopa parempi näin.) Kuitenkin putoaminen ja hyppääminen vaikuttaa sulavalta paitsi jostain syystä hieman palikkamainen liike (mutta ei haittaa). Täytyy vielä tehdä muutama kokeilu, koska bugeja löytyy liikkumisesta, jos oikein sitä vääntää! Näiden parin päivän aikana piti tehdä muita juttuja, mutta tämä oli sen verta kriittinen niin päätin nyt saada tämän edes kunnolla.
10.4.2018 | 1h | Jatkoa edelliseen. Täytyy tänään saada projekti lähetys kuntoon ja luoda grafiikoita ennen palautusta + saada menu kuntoon (graafikka taas). Ja lisätä siihen toiminnallisuus + lopputekstit. Pyrin myös luomaan samalla pelin yläreunaan guin! Se pitäisi olla hauskaa. Vihollis hahmot luon vasta seuraavalle kerralle ja myös ensimmäisen kentän valmiiksi! 
10.4.2018 | 10h | Tein guin ja menun napit ja myös kokeilin lisää elementtäjä kuten ajan siihen joka onnistuu! Täytyi myös siivota koodia paljon koska paljon turhia kommentteja ja yms! Liikkumista jouduin hiomaan vielä hieman ja myös pienen esimerkki kentän sain tehtyä, ei vielä mitään toiminnallisuutta paitsi eri tasossa layerit!
11.4.2018 | 16h | Kirjaimellisesti tuhlasin koko päivän kun yritin saada .jar tiedoston projektista! Tämä ei halua onnistua millään, koska ongelma ilmenee .tmx tiedoston <image **source="[INSERT TILESET HERE]"**... kohdassa! Joten ongelmana on, että ei halua avata jarria koska tätä ei löydy. Ja muutenkin on hankaluuksia tämän saamisessa kuntoon. Saatan tehdä näin että en toteuta jar tiedoston luontia, jos näinä parina päivänä se ei onnistu. Päivän päätteeksi lisäsin kaverini luomat musiikit peliin ja pelleilin sen toteutuksen kanssa hieman. Täytyy saada lopputekstit kuntoon jotta kaverin nimi näkyy siellä ennen palautusta.
13.4.2018 | 4h | Vielä kokeilin pom.xmllää korjata mutta ei edistynyt. Ongelmana on ettei libtiled josta sovellus on riippuuvainen ole korjannut omia bugejaan. Ja myös vaikuttaa, että se on hieman kesken eriäinenkin. Kuitenkin hyvä se on paitsi en saa luotua jarria jos se on käytössä. Ja aikaa ei ole paljoa.
14.4.2018 | 5h | Pom.xml pelleilyn jälkeen aloin korjaamaan toiminnallisuutta .tmx tiedoston kanssa ja tämä oli hauskaa! Peliin pystyy nyt lisäämään objekteja ja vihollisia ja tekstibokseja ja kaiken näköistä. Pelissä on kolikoita joita ei voi kerätä vielä, mutta niitä voi sijoittaa aivan vapaasti karttaan! Samoin myös voin vaihtaa pelaajan ja voittamis alueen sijainteja helposti! Enään puuttuu "switch areat" joista tasoja voi vaihtaa ja liikkuvat tasot ja viholliset joita aloitin hieman. (**Tämän voi huomata sitten tutoriaalissa** saattaa löytyä duplikaatti pelaaja jos en ehdi tehdä grafiikoita! XD**). 
15.4.2018 | 7h | Loin tietokannat sovellukseen ja ne ovat valmiina käyttöön ottoon. Täytyy vain tehdä sovelluslogiikka ja alku menu! Pelistä löytyy nyt käyttäjä luokka johon voi kirjautua ja rekisteröityä. Asetukset ovat vielä kesken.
16.4.2018 | 2h | Aloin tekemään alkumenuja joista kirjaudutaan käyttäjään ja asetetaan asetukset kuten vaativuusmäärittelyssä on! Käytin hyväkseni fxml scenebuilderia ja onnistui hyvin! Asetus valikkoa voi scrollata ja sieltä löytyy äänenvoimakkuus ja pelaajan nimen vaihto ja myös käyttäjän poistonappi! Saves kantaa ei vielä ole tietokannassa (siis käytettävissä, se on siellä siis). Tässä lähipäivinä, tai melkein heti seuraavan palautuksen jälkeen teen sen valmiiksi! <jatkuu>..
17.4.2018 | 19h | <Jatkuu> Kirjaimellisesti vedin allnighterin korjatessani asioita ja valmistellakseni tietokannan toiminnallisuutta toisiinsa. Checkstylesta jouduin karsimaan paljon pois, koska koko sovellus on suurin osin tekemisissä piirtoalustojen kanssa. Kuitenkin testattavaa löytyy ja testit on tehty tietokannoille ja myös korjasin KeyListener testin joka olikin väärin, jonka hoksasin vasta tänään! Checkstylesta jouduin myös karsimaan packagename checkkerin, koska se ei hyväksynyt mitään VR lyhennettä (ja meinasin poistaa koko projektini, joten siihen en koske enään). Koitan vielä tänään saada valmiiksi tuon menun joka on täysin valkoinen ja lopputekstit, kuvan vaihdot ja kentän nimen ilmestymisen ruudulle ennen palautusta. Aikaa noin 4h. (Voi jäädä kesken). Seuraavaan palautukseen pyrin saamaan kaikki vihollislogiikat ja pelilogiikat kuntoon ja ensimmäisen kentän ja grafiikat valmiiksi! + Saves tietokanta. Sen pitäisi olla helppoa ottamalla mallia muista tauluista jotka jo tein. Pelissä on myös musiikki lisätty jonka takia lopputeksti on tärkein!
20.4.2018 | 5h | Aloitin grafiikan tekemisellä ja pelaajan animoinnin suunnittelulla. Samalla myös pohdin kuinka saan pelaajan liikkeet oikein.
21.4.2018 | 6h | Muutin kaikkien luokkien x ja y kohdat integereistä -> doubleiksi! Parempi näin. Aloin myös korjaamaan musiikkia ja tekemään tälle parempaa luokkaa.
22.4.2018 | 16h | Vedin all nighterin kun aloin säätämään tuon kirotun pom.xml:län ja tmx kartta ongelman kanssa. (Koska huomasin että vaativuuksissa oli .jar release niin halusin saavuttaa tämän. En onnistunut kuitenkaan vielä toteuttamaan tätä .jar tiedostoa.
23.4.2018 | 18h | Taas allnighter, mutta sain ideaksi pakata .zip tiedoston .jar kansion sisälle! Tämä oli pahuksen mahtava idea! 
24.4.2018 | 14h | Koska valvoin nukuin melkein koko päivän. Tein projektia vielä klo 8 aamulla saakka ja sammuin klo 17 saakka. Nyt tähän saakka klo 0:47 yritin saada aikaan testauksia ja toiminnallisuutta peliin. Peliin sain kolikoiden keruun ja spurgun siirtelyn ja background luokan ja muita turhia koodeja siivottua pois!
30.4.2018 | 8h | Alkaa istuminen koneella rasittaa, Lisäsin peliin äänitehosteita ja pohdin kentän suunnittelua.Lisäsin peliin cutscenen testiksi ja ihan okei se toimii. Äänien kanssa voi havaita pientä ongelmia.
1.4.2018 | 2h | Illalla tein lisää grafiikoita peliin. Aloin suunnittelemaan erilaisia hahmoja, mutta näiden animoiminen vie ikuisuuden.
2.4.2018 | 20h | Koko päivän klo 3:sta saakka yritin saada ensimmäistä kenttää valmiiksi. Grafiikka muutoksen takia joudiin uusimaan kentän jo useampaan otteeseen (ja vieläkin ongelmia). Lisäsin hobon uuden skinin ja sisäisen cutscene testin alkuun (se ei toimi täydellisesti).
10.5.2018 | 5h | Aloitin viimeistelyä. Koska oli tenttiviikko en ehtinyt aikaisemmin jatkaa projektin edistystä. Mutta kummiskin sain ensimmäisen tason valmiiksi
11.5.2018 | 22h | Koko päivä koneella! Nyt on valmis!
