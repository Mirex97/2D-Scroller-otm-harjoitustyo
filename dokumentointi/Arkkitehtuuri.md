# Arkkitehtuuri
##### Huom, jos kuvat ovat liian pienet niin oikea klikkaa kuvaa ja klikkaa "Avaa kuva uudessa välilehdessä" (Ainakin chromesta löytyy)!
## Pakkausrakenne
Ohjelmassa on hyvin paljon pakkauksia, jotka jokainen toteuttavat single responsebility sääntöä! Kaikki pakkaukset siis tärkeitä!
Pyrin toteuttamaan pelin mahdollisimman yksinkertaisesti ja helposti seurattavasti ainakin logiikan osalta, että kaikille olisi oma pakkaus ja vain pakkaukseen liittyviä luokkia saa lisätä.

Esimerkiksi:
´´´
VR.handlers -> TMX kartan sisäisten objektien käsittelyä varten + Näppäimistönkuuntelija.
VR.controllers -> Tämä on alkumenun käsittelyä varten
VR.levels -> Vain kentät
´´´

## Maven riippuvuudet
Lisäsin paljon riippuvuuksia projektiin, mutta kaikista tärkein täytyy olla libtiled! Lisäksi löytyy myös riippuvuuksia ääniä varten, jotta sain musiikit .wav tiedostoina. Kuullema .mp3 formaati voi aihettaa korvauksia peliyhtiöille ja sen takia päätin vaihtaa mp3 tiedostot toiseen formaattiin. Ogg ei toiminut linuxilla joten wav oli ainoa vaihtoehto.
Joka tapauksessa suosittelen tutustumaan Tiled nimiseen ohjelmaan! (Pahuksen hyödyllinen kenttäeditori).
  - Itseasiassa 'Shovel Knight' peli inspiroi minua tekemään tämän tyyppisen pelin ja tutkin miten he saivat toteutettua kentät ja törmäsin heidän kauttaan tähän sovellukseen ja oli pakko kokeilla miten kenttien teko onnistuu!
  - Vaikka java:n libtiled ohjelmassa on puitteita niin sain ne toimimaan!

## Käyttöliittymä
Mini-Menu on toteutettu FXML määrittelyllä, jonka ansiosta on hyvin eriytetty ohjaimet ja käyttöliittymä toisistaan.

Pelin sisällä käyttöliittymä on hieman hankalempaa, koska Frame pohjainen päivittäminen määrittää paljon mitä pelissä tapahtuu.

Se myös vaikuttaa logiikkaankin hieman (Delta-time).

## Sovelluslogiikka
Kaikista eniten vastaava luokka on **Main**! Main luokka käynnistää ja luo kaikki tärkeät pelin käsittelijät.
Kuitenkaan Main luokka ei toteuta mitään näihin käynnistettyihin oliohin se vain herättää ne.
Main luokasta on tullut siis portti jokaiseen pelin osaseen varsinkin staattisuuden ansiota.

Main luokka siirtää suorituksen VR.login osion luokille (Mini-Menu). Ja startatessa pelin nämä välittää käynnistyskäskyn Mainiin, että peli voi käynnistää.

Peli itseasiassa käyttää vain yhtä Stage oliota läpi koko prosessin! Esim windowed moodissa voi havaita, että ruutu jää jumiin hetkeksi.
(Ei siis vakavaa lainkaan).

Täältä sitten suoritus siirtyy takaisin Mainiin ja sitten VR.sections luokkaan! Nyt voidaan sanoa että ollaan varsinaisesti pelin sisällä.

## Tietojen pysyväistallennus
Pelistä löytyy pakkaus VR.database, joka sisältää tietokantoihin liittyvät käsittelijät. 
Kaikki käyttäjien tiedot löytyy tietokannasta saves.db.
Peli toteuttaa myös Data Access Object mallia ja tietokanta toiminnot on eristetty Daojen taakse.

## Tiedostot
Jar tiedoston sisältä löytyy levelsandstuff.zip kansio, jonka sovellus purkaa itse käynnistyksessä ulos. Sitten tämä purkaa sen vielä uudelleen kun se on ulkopuolella ja työstettävään sijaintiin ilmestyy 'levels' kansio.
(Tämä oli osa suurta taistelua TMX karttojen kanssa ja näin sain sen ratkaistua.)

Peli siis luo kolme tiedostoa: saves.db, levelsandstuff.zip ja levels kansio.



![rakenne](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/PakkausRakenne%20(improved).PNG)
## Pelin Sisäistä Logiikkaa (puuttuu canvakset)
### Canvaksia olisi neljä kappaletta Gui, Pause ja Main.Canvas + Background!
  - Main.Canvas on tärkein näistä neljästä!
  
## Rakenteeseen jääneet heikkoudet
- Käyttäjien salasanoja ei encryptoida ja ne ovat selkokielisenä tietokannassa.
- Ääni ajurin puuttuminen estää pelin käynnistymisen (tätä varten pitäisi luoda tarkastus löytyykö ajuria, mutta nyt en enään ehdi sitä tehdä. (Kuitenkin huomioitu).

- Main luokkaa voisi eriyttää enemmän, mutta se saattaa myös hankaloittaa asioita.

- Vasta loppupuoliskolla, hoksasin paremman tavan toteuttaa kentät Tiled sovelluksella ja pyrin korjaamaan sitä.
  - Aluksi loin kentät palikka kerrallaan ja se vaikutti toimivan hyvin (pieniä kenttiä siinä vaiheessa).
  - Vasta tehdessäni suurempaa kenttää totesin, että peli hidastui ja oli melkein täysin pelattamattomissa. Tästä hoksasin, että voin tiled ohjelman avulla tulostaa kentän kuvaksi ja sitten käyttää tätä kenttänä. Tästä johtuen metodi ei ole täysin yhteensopiva.
  (Jos ehdin vielä tänään palautuspäivänä korjaamaan sen niin yritän tehdä lisäkentän pikaisesti.)

  
![logiikka](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/kuvat/Sis%C3%A4ist%C3%A4Logiikkaa(Updated).PNG)

## Sekvenssikaavioita (Vain peliä edeltävä menu sekvenssikaaviona.):
##### Logiikkaa en tee sekvenssikaaviona, koska siinä menisi ikuisuus! Kuitenkin tämän logiikka löytyy yllä olevasta kuvasta!

### Ohjelman käynnistys
![Initiate](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/kuvat/Initiate.PNG)
![LoginStart](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/kuvat/LoginStart.PNG)
### Kirjautuminen ja rekisteröityminen
![LogOrReg](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/kuvat/LoginORRegister.PNG)
### Menu 
![Menu](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/kuvat/Menu.PNG)
### Options
![Options](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/kuvat/Optionss.PNG)

