# Vaativuusmäärittely

## Pelin tarkoitus
Läpäistä läpi ja käyttää vapaa-aikaa! Peliin voi kirjautua käyttäjällä ja jatkaa seikkailua!

## Käyttäjät
Käyttäjät normaaleita käyttäjiä, joihin voi kirjautua.

## Käyttöliittymäluonnos (Ensimmäinen)
Sovellus avaa pienen ikkunan jonka avulla voi kirjautua / rekisteröityä käyttäjälle.
Samalla voi myös määrittää asetuksia profiilille ennen itse pelin käynnistämistä.
Tämän jälkeen peli aukeaa painamalla Start nappia!
![Login](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/kuvat/Login.png.png)
![Ingame](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/kuvat/Ingame.png)

## Tämänhetkisen Version toiminnallisuus
## Tietokannoista
Pelillä on kolme (käytössä nyt kaksi) tietokanta taulua.
Options, Users ja Saves.
Options ja Users on nyt vain toiminnassa. Saves taulun voi ottaa käyttöön kun pelin kentät on tehty.

### Alussa
- Käyttäjä voi luoda käyttiksen (nimi + salasana).
  - Käyttäjälle ladataan perusasetukset alussa (Näitä voi muokata options valikosta).
- Käyttäjä voi kirjautua luomaansa käyttäjään, antamalla nimen ja salasanan.
  - Käyttäjä voi muokata asetuksiaan.
  - Käyttäjä voi käynnistää pelin tai poistua.

### Pelissä
- Käyttäjä näkee alku intron, jonka jälkeen pelaaja siirtyy valikkoon.
  - Valikosta on näkyvissä "New Game, Continue, Load, Credits ja Quit"
    - Vain New Game, Credits ja Quit toimivat tällä hetkellä.
    

### Pelin sisällä
- Peli alkaa suoraan tarinalla ja siirtyy ensimmäiseen kenttään.
- Kenttiä on vain tällä hetkellä yksi (pois lukien lopputekstit).
  - En halunnut luoda uusia kenttä jos edes ensimmäinen ei toimi, mutta tällä hetkellä pitäisi kaikki pelittää.
  - Pelin voi pysäyttää painamalla 'ESCAPE' nappia ja tämän kautta voi sitten poistua valikkoon.
- Pelissä voi nyt seikkailla.

## Jatkokehitysideoita
### Tavoitteet.
- Lisäkenttien lisääminen ja turva-alueet, joista voi käydä kauppaa kerätyillä kolikoilla.
- Hahmoja joille puhua turva-alueilla.
- Peli ikkunan koon vaihto + fullscreen  (DONE)
- Bosseja ja täytettä peliin!

### Myöhemmin
- MMO <-- just kidding...
- Minipelit
- Moninpeli!

## Mitä opin ja mitä päätän tehdä!
- Tämän tekeminen on ollut erittäin hauskaa, mutta näen miksi pelien teossa kestää niin kauan.
- Olen siis päättänyt luoda uuden pelin ja mahdollisesta jopa kunnon työnäkin. Tähän haluan siis opetella C++:ssaa ja mahdollisesti käyttää jotain valmista pelimoottoria (Unity). Johdatus Peliohjelmointiin kurssikin käyty!
- Tätä varten aijon suunnitella ainakin rauhallisemmin pelin, koska jos tekee liikaa kaikkea niin asiat kasaantuvat
  - Lisäksi oli muita kursseja samaan aikaan.
- Kummiskin pyrin käyttämään samoja sovelluksia ja tyyliä pyrin parantamaan.
- Ja myös tulen pyytämään kavereiltani jeesiä ja mahdollisesti hekin sitten tienaavat jotain.
