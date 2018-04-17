

![logo](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/src/main/resources/menu/VrTheAdventure.png)
# VR The Adventure
Peli on 2D scrolleri, jossa seikkaillaan asemilta toisille ja pyritään pääsemään lipuntarkastajia karkuun!
Asemat ovat turva-alueita ja kentät ovat junan sisällä. Tietyissä kentissä on bosseja ja tietysti final boss.

## Mitä olen tekemässä tällä hetkellä (Pelin koodaamiseen liittyen)
Lisätty Projekti hubbiin!
- Tällä hetkellä korjaan pom.xml:llää!

# HUOM 
Täytyy rajata pelin toiminnallisuutta ja karsia hieman tarpeettomia toiminnallisuuksia.
Tämä on päivitetty vaativuusmäärittelyyn.

## Dokumentaatio
Käyttöohje
[Vaativuusmäärittely](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/vaativuusm%C3%A4%C3%A4rittely.md)
Arkkitehtuuri
[Pakkausrakenne](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/Paukkausrakenne.PNG)
[Työaikakirjanpito](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/Ty%C3%B6aikakirjanpito.md)

## Komentorivin toiminnot
### Testaus
Testit:
 ```
 mvn test
 ```
Testikattavuuraportti:
 ``` 
 mvn jacoco:report
 ```
### Jar suoritus
Tässä oli ongelmia koska pom.xml on pielessä hieman. Mutta koodi on kummiskin...
```
mvn package
```
### JavaDoc
Generoidaan komennolla
```
mvn javadoc:javadoc
```
Heittää paljon virheitä!
### Checkstyle
```
mvn jxr:jxr checkstyle:checkstyle
```

#### Mistä peli kertoo?
Paljastan **vasta** pääsiäisloman jälkeiselle palautuskerralle (kun pitää palauttaa jotain koodattua).
Siinä vaiheessa pyrin jo saamaan tehtyä menun ja jotain pelitoiminnallisuuksia. + DAOT!

#### Mitä pelaaja pystyy tekemään pelissä?
Pelaaja voi rekisteröityä / kirjautua käyttäjälleen, josta hän löytää 
omat talletuksensa ja voi jatkaa siitä mihin jäi. (Siis jos pelaaja 
läpäisi tason 1 niin seuraava taso pysyy auki. Ei että pelaaja ilmestyy 
tasoon talletuksia).
Pelaaja voi mahdollisesti muokata asetuksia, kuten äänenvoimakkuutta, 
ohjaukseen liittyviä keybindejä (ja näytön kokoa, jos saan sen 
toimimaan).
Pelaaja sitten voi seikkailla pelissä ja yrittää voittaa sen!

#### Pelin toiminnallisuuksia.
Pelaaja pystyy liikkumaan juosten tai kävellen, hyppäämään. 
Myös tietyllä alueilla pelaaja voi vaihtaa tasoa ja hypätä liikkuvien tasojen kyytiin. Joiden avulla voi edetä pelissä!
Pelissä on myös vihollisia, jotka estävät pelaajan kulun tai yrittävät saada tämän kiinni.

#### Mitä peli sisältää?
Peli on pikselimäinen peli, mutta sisältää oman tyylinsä ja ennen 
näkemättömän tarinan. Peliin yritän luoda omat musiikit myös käyttäen 
musiikin luontiin hankkimiani sovelluksia. Mutta jos tämä ei onnistu 
niin käytän royalty free musiikkia sijaan. Peliin haluan toteuttaa 
tapahtumia (events), jotka ilmestyvät tiettyjen tapahtumien suoritettua, 
kuten: Pelaaja läpäisee ensimmäisen tason ja on löytänyt tasosta 
erikoisen esineen. Hän saapuu takaisin turva alueeseen (hub, ei pelin 
taso), sieltä hän kohtaa uuden NPC:n (no pc hahmon), joka kertoo jotain 
erikoista. *Huom* näin ei tapahtuisi, jos pelaaja ei löytänytkään 
esinettä!
Ja pelissä myös on cutscenejä (välikohtauksia).

#### Muita toiminnallisuuksia?
Peli käyttää tietokantoja(, koska se on vaatimuksena) 
tallentaakseen käyttäjien tiedot ja heidän tallennukset.

Pelin käynnistyksessä avautuu oma ikkuna, josta voi kirjautua 
käyttäjälle ja nämä sitten latautuu peliin kun painaa "Start" nappia.
Myös käyttäjän asetukset löytyvät tietokannasta.

Pelin ruudun kokoa voi muuttaa!

#### Kommentteja?
Tämä on siis projekti ja toivottavasti kaikki onnistuu ja itselläni on 
ainakin tähän asti ollut hauskaa suunnitella! Nyt eikun toteuttamaan 
suunnitelma! :D 
**Update Todennäköisesti en saa peliä 100% valmiiksi koska valitsin melko laajan alueen. 
Kuitenkin jatkoideoina voisin kokeilla luoda saman pelin esimerkiksi C# avulla!**


### Vaativuusmäärittely (tiivistetympi)
Pelaaja (normaali käyttäjä) voi: 
- Rekisteröityä / Kirjautua käyttäjälleen (luoda ja kirjautua)
- Vaihtaa käyttäjän asetuksia ja profiilia
- Käynnistää pelin
- Tallentaa ja ladata tallennukset (saves)
- Jatkaa peliä siitä mihin jäi
- Voittaa pelin
- Lopettaa pelin

Ylläpito (sisältäen normaalin käyttäjän toiminnot) voi:
- Skipata alku cutscenet
- Avata pelin testauskentän
- Käyttää huijauskoodeja (for testing purposes only)

Toimintaympäristön rajoitteet:
- Tulee toimia Linux koneissa
- Käyttää JavaFX:sää Swingin sijaan
- Pelaajien tallennukset ja profiilit (sis. asetukset) tallennetaan paikalliselle koneelle tietokantaan.

#### Tulevaisuus ideoita!
Nämä ovat siis ideoita, joita mahdollisesti en ehdi toteuttamaan, mutta varmaan jatkan projektia kurssin jälkeen!
- DLCs!!!
- Kenttäeditori
- Mahdollisesti kielen vaihtaminen Javan sijaan -> C tai C#.
- Lisää Bosseja, pyrin saamaan peliin yhden eeppisen bossi taistelun ainakin aikaan!
- Lisää täytettä!
- Myös mahdollisesti jos innostun jatkamaan pelin kehitystä niin ehkä myös pelin markkinointi. <- Siihen kuitenkin joku toinen kieli mahdollisesti ja enemmän sisältöä. Tämä projekti on enemmän harjoitus mielessä!

ELI periaatteessa [Insert Name Here] remastered versio!
