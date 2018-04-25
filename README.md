
Ohjeet testaamiseen löytyy alta!

#### Viimeinen kommentti tästä sotkusta jonka github sai aikaan. Jostain syystä src/main/java sisältää toisen Vr kansion, tätä en saa poistettua millään. Seuraavaksi koska olin päivittänyt samaan aikaan dokumentteja ja yritin pushata projektia, tämä alkoi valittamaan Merge ongelmista! Lopputuloksena kaikki muutokset katosivat joita tein tänne ja meni .java tiedostot sekaisin. Joudun vielä korjailla yön läpi näitä ongelmia, joten committeja ilmenee vähitellen.
##### Pika update, nyt kaikki pitäisi olla täysin korjattu! Testaukset katosivat samassa sotkussa. Nyt ne on lisätty! klo 4.03.


![logo](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/src/main/resources/menu/VrTheAdventure.png)
# VR The Adventure
Peli on 2D scrolleri, jossa seikkaillaan asemilta toisille ja pyritään pääsemään lipuntarkastajia karkuun!
Asemat ovat turva-alueita ja kentät ovat junan sisällä. Tietyissä kentissä on bosseja ja tietysti final boss.


## Viimeisimmät päivitykset:
- Lisätty kolikoiden keruu toiminto.
- Saatu .jar toimimaan libtiled riippuvuuden kanssa, jonka kanssa olen tapellut jo yli kuukauden!
- Korjattu musiikin toiminto ja luotu tälle oma kunnon luokka, johon voi lisätä biisejä.
  - Biisit ovat HashMapissä ja voi hakea nimen perusteella!
- Lisätty BackGround canvas ja tälle oma luokka! (Ei enään valkoista taustaa!)
  - Tähän aijon tehdä erillaisen toiminnon pelin sisällä. Pelin sisällä Background luokka piirretään myös pelikentälle, jotta sekin liikkuu kun pelaaja liikkuu!
- Lisätty Spite luokka, joka käyttää Spritesheet muotoa pelaajista ja hahmoista!
  - Tämä täytyy lisätä kolikoille! (Koska näyttäisi paremmalta! :P).
- Lisätty pelaajan animoinnit yllä mainitulla Sprite luokalla.
  - Pelaajalta kuitenkin puuttuu putoamis animaatio ja juoksu animaatio!
    - Tämä näyttää huvittavalta lopputeksteissä!
    - Shift painiketta painamalla pelaajan animointi jää jumiin. (Koska juoksu animaatiota ei ole niin tämä jatkaa sen hetkistä animaatiota loputtomiin).
- Lisätty alkeellinen Spurgu
  - Tulen vaihtamaan tämän tekstuurit. mutta ainakin on eri näköinen kuin oma pelaaja hahmo!
  - Spurgun liikkuminen ei ole vielä oikein. Tähän lisää ensi viikolla.



## Mitä olen tekemässä tällä hetkellä (Pelin koodaamiseen liittyen)
- Nyt koska suurimman ongelman pelissä sain ratkottua, voin keskittyä 100% pelin Grafiikkaan ja mekaniikkaan!
- Teen seuraavaksi kenttien grafiikat, Konduktöörin, Spurgun ja veljien toivesta Lokkeja junan katolle (vihollisia).
=======
## Viimeisin suuri päivitys
- Lisätty kolikoiden keruu
  - Tähän pitää lisätä Sprite luokka! Näyttää paremmalta!
- Lisätty luokka zip tiedoston purkuun.
- Lisätty Spurgun tekstuuri (pitää kuitenkin parantaa).
  - TMX karttaan voi nyt sijoittaa vihollisia ja tämä käy kentän viholliset läpi ja luo ne. Kuten kolikotkin!
- Lisätty pelaajan animointi
  - Juoksua ja putoamis animaatiota ei ole, juoksussa pelaajan viimeisin animaatio jää ikuiseen looppiin jos pitää shiftiä pohjaan.
    - Lopputeksteissä huvittava, koska ei ole pudotus animaatiota.
- Korjattu .jar buildaus! Nyt se onnistuu ja kuukauden olen sen kanssa tapellut!
- Korjattu musiikki ja luotu tälle oma luokka parempaa musiikin käyttöä varten (Kappaleet Hashmapin sisällä ja niitä voi noukkia nimen avulla .wav).
- Luotu Sprite luokka animaatioita varten, joka käyttää spritesheet muotoa hahmojen animointia varten.

## Mitä olen tekemässä tällä hetkellä (Pelin koodaamiseen liittyen)
Nyt koska .jar on toiminnassa voin keskittyä 100% pelin logiikkaan ja grafiikkaan! Kun saan enemmän kenttiä lisään save tietokannan käyttöön ja tätäkin voi hyödyntää.


## Dokumentaatio
[Käyttöohje](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/edit/master/dokumentointi/kayttoohje.md)
[Vaativuusmäärittely](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/vaativuusm%C3%A4%C3%A4rittely.md)
[Arkkitehtuuri](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/Arkkitehtuuri.md)
[Työaikakirjanpito](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/Ty%C3%B6aikakirjanpito.md)

# Testaajille
#### HUOM, älä liikuta hiirtä tai näppäimistöä testatessasi tai buildatessa projektia! Muuten testit eivät mene läpi!

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
Pom.xml nyt korjattu! Ei ongelmia (pitäisi ainakaan olla)!
```
mvn package
```
### JavaDoc
Generoidaan komennolla
```
mvn javadoc:javadoc
```

### Checkstyle
```
mvn jxr:jxr checkstyle:checkstyle
```
# Pelin tarina ja toiminnot

#### Mistä peli kertoo? (Updated 24.4.2018)
Peli kertoo tarinan pelaajasta, kenellä ei ole tarpeeksi rahaa matkustaa asemien välillä! Kuitenkin hän päättää mennä pummilla!
Pelaaja, kohtaa matkallaan kohti Keravaa konduktöörejä ja spurguja, jotka estävät pelaajan etenemisen junassa.
Pelin tavoitteena on päästä junan päästä päähän ennen kuin konduktööri saa pelaajan kiinni.
HUOM tällä hetkellä pelissä ei ole kenttiä valmiina, mutta testaus kenttä löytyy. En pystynyt keskittyä niin paljoa kenttien tekoon, koska oli ongelmia .jar tiedoston teon kanssa.
=======
#### Mistä peli kertoo?
Peli kertoo pelaajan tarinan, jossa pelaajan tehtävänä on päästä Keravan asemalle Helsingistä! Kuitenkaan pelaajalla ei ole tarpeeksi rahaa ja hän päättää mennä pummilla! Pelaaja kohtaa matkallaan spurguja ja konduktöörejä ja veljien toiveesta pelaaja kohtaa myös lokkeja junan katolla (vihollisia), jotka estävät pelaajan pääsyn maaliin.
Pelin tavoitteena on päästä junan päästä päähän, ennen kuin konduktöörit saavat pelaajan kiinni! Muuten Game Over!
Pelaaja voi kerätä kolikoita kentästä ja näiden avulla pelaaja voi maksaa tarkastusmaksun (80€)
Kuitenkin pelaaja, joutuu lähimmäiselle checkpointille. Jos pelaajalla ei ole varaa maksaa maksua, hän joutuu takaisin viimeisimmälle asemalle! (Raha on kuin elämät!).

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
