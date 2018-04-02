
# 2D Scrolleri peli
Peli on tyypillinen 2D tasohyppely peli, mutta sisältää oman tyylinsä ja 
custom animaatioita.

## Mitä olen tekemässä tällä hetkellä (Pelin koodaamiseen liittyen)
//Päivitän tähän tällä hetkellä kesken olevat ominaisuudet joita olen tekemässä.
Klo 22.52: Collisio saatu toimimaan, seuraavaksi kokeilen luoda peliin fysiikat pelaaja hahmolle.
Fysiikan toimimisen jälkeen siirrän fysiikan käsittelyn omaan luokkaan, josta pelin objektit ottavat tämän käyttöön (Super class).
Jos ei super luokkaa niin aiheuttaa copypastea.

## Dokumentaatio
Käyttöohje

[Vaativuusmäärittely](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/vaativuusm%C3%A4%C3%A4rittely.md)

Arkkitehtuuri

[Työaikakirjanpito](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/Ty%C3%B6aikakirjanpito.md)

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
Peliin voi kirjautua ylläpito tunnuksella, joka mahdollistaa komentojen 
syöttämisen ja testikentän, joka on täysin muokattavissa. Testikentässä 
voi kokeilla syötteitä, tapahtumia, hahmojen spawnaamista, animaatioita 
ja vihollisia, yms!
Ylläpito käyttäjää käytetään pääosin JUnit testaamista varten. Koska 
tämä mahdollistaa nopeamman tilanteisiin pääsyn. Eikä tarvitse katsoa 
kun ylläpito pelaa pelin puolestasi!

Peli myös käyttää tietokantoja(, koska se on vaatimuksena) 
tallentaakseen käyttäjien tiedot ja heidän tallennukset.

Pelin käynnistyksessä avautuu oma ikkuna, josta voi kirjautua 
käyttäjälle ja nämä sitten latautuu peliin kun painaa "Start" nappia.
Myös käyttäjän asetukset löytyvät tietokannasta.

#### Kommentteja?
Tämä on siis projekti ja toivottavasti kaikki onnistuu ja itselläni on 
ainakin tähän asti ollut hauskaa suunnitella! Nyt eikun toteuttamaan 
suunnitelma! :D 

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
