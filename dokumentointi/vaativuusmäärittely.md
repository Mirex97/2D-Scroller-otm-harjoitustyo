# Vaativuusmäärittely
## Pelin tarkoitus
Läpäistä läpi ja käyttää vapaa-aikaa! Peliin voi kirjautua käyttäjällä ja jatkaa mihin jäi!

## Käyttäjät
Käyttäjät ovat normaaleja (default) roolillisia, mutta peliin voi kirjautua salaisella ylläpidon käyttäjällä ja salasanalla.
Tämä mahdollistaa testikenttien käytön ja myös metodien kokeiluja. (JUnit)

## Käyttöliittymäluonnos
Sovellus avaa pienen ikkunan jonka avulla voi kirjautua / rekisteröityä käyttäjälle.
Samalla voi myös määrittää asetuksia profiilille ennen itse pelin käynnistämistä.
Tämän jälkeen peli aukeaa painamalla Start nappia!
![Login]()
![Ingame]()

## Alpha version toiminnallisuus
### Alussa
- Käyttäjä voi luoda käyttiksen (Uniikki ja pituus vaikka 16) nimi + salasana.
  - Käyttäjälle ladataan perusasetukset alussa.
- Käyttäjä voi kirjautua luomaansa käyttäjään, antamalla nimen ja salasanan.
  - Käyttäjä voi muokata asetuksiaan.
  - Käyttäjä voi käynnistää pelin tai poistua.

### Pelissä
- Käyttäjä näkee alku intron, jonka jälkeen pelaaja siirtyy valikkoon.
  - Valikosta voi valita "New Game, Continue, Load, Credits ja Quit"
    - Valikon vaihtoehdot Load ja Credits avaa uuden näkymän (Load tallennukset ja Credits lopputekstit).
    - Näkymä siis tulee menun päälle, periaatteessa saman näkymä... eri luokka vain!
  - New Game ja Continue vievät peliin kuten Load näkymäkin kun valitsee tallennuksen.

### Pelin sisällä
- Pelin alussa pienehkö tutorial! Tutoriaalissa käydään läpi mahdollisia toimintoja. (Eräänlainen showcase).
- Käyttäjä voi nyt seikkailla kentissä ja edetä kohti final bossia vastaan (No Spoilers)!
  - Aluksi kuitenkin on vain simppeli kenttä käytössä. (Lisään sitten jatkossa).

## Jatkokehitysideoita
### Tavoitteet.
- Lisäkenttien lisääminen.
- Lisää hahmoja joille puhua turva-alueilla.
- Peli ikkunan koon vaihto + fullscreen (tällä hetkelle hankalaa ja ei halua toimia kunnolla)
  - Taidan jättää sen ehkä väliin, koska se ei ole oleellinen osa toimintoja. Mukava lisä vain. (Voi lisätä myöhemmin).
- Easter eggs!
- Lisää bosseja ja täytettä peliin.

### Myöhemmin
- MMO <-- just kidding...
- Minipelit
- Moninpeli!
