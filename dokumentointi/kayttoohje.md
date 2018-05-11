# Käyttöohje
Voit ladata Pelin tiedoston joko releasien kautta, tai tutustumalla [VR The Adventure - Forumiin!](https://mirex-pelifoorumi.herokuapp.com).
Lataus linkki löytyy sivun pohjalta, mutta se vie myös githubin release osioon.
[Releases](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/releases)
Jos lataat koko zip tiedoston tai kloonaat gitillä, niin git etusivulla on ohjeet testaajille!

## Konfiguraatio
Varmista, että ladattu .jar tiedosto on omassa kansiossaan!
Pelin käynnistyessä, tämä purkaa levelsandstuff.zip tiedoston kansioon, jossa peli sijaitsee ja samalla luo tietokannan samaan paikkaan.

(Linuxilla db tiedosto ilmestyykin home kansion alle! Kannattaa tuplacheckata, ettei ole vanhaa tietokantaa siellä! Päivitysten varalta.)

Peli myös olettaa, että käyttäjällä on toimiva äänentoisto! (Muuten ei pysty jatkaa, huomasin tämän yliopiston koneella, jossa ei ollut ääniajuria).

# Ohjelman käynnistys
Tämän voi joko suorittaa komentoriviltä tai suoraan tuplaklikkaamalla tiedosto auki.
```
// X.X = versio
java -jar VR_The_Adventure-X.X-launcher.jar
```
Linuxilla, kannattaa odottaa hetki jos ei käynnisty heti ja sitten kokeilla uudelleen.

# Alkumenu
### Kirjautuminen ja Rekisteröinti
![A-1](https://raw.githubusercontent.com/Mirex97/2D-Scroller-otm-harjoitustyo/master/dokumentointi/kuvat/A-1.PNG)

Kirjoita käyttäjänimesi ja salasanasi ja klikkaa "Register" nappia. 

Tämä automaattisesti klikkaa myös login nappia ja kirjautuu sisälle!

Tyhjä salasana on myös hyväksytty, mutta turvallisuus syistä kannattaa heittää jokin salasana.

Jos on jo käyttäjä niin klikkaa suoraan login syötettyäsi tiedot!

Jos käyttäjä on jo varattu niin menu ilmoittaa siitä.

### Mini-menu
Tämä on viimeinen vaihe ennen pelin käynnistystä.
Kannattaa kuitenkin ensiksi poiketa "Options" sivun kautta heti aluksi!
![A-2](https://raw.githubusercontent.com/Mirex97/2D-Scroller-otm-harjoitustyo/master/dokumentointi/kuvat/A-2.PNG)

### Options
![A-3](https://raw.githubusercontent.com/Mirex97/2D-Scroller-otm-harjoitustyo/master/dokumentointi/kuvat/A-3.PNG)

Täältä löytyy paljon hyödyllisiä asetuksia pelaamisen mukauttamiseen ja myös uusia toiminnallisuuksia!

(NEW) Resoluutio ja Fullscreen mode!

Peli sisältää kokoruututilan, mutta oletuksena se käynnistyy windowed moodissa ja 1024x764 resoluutiolla.
### HUOM Linuxilla ei toimi kokoruututila. Tähän en tiedä ratkaisua.

Resoluutioksi kannaa valita lähin sopiva koko. Kannattaa ennen kaikkea pitää silmällä viimeistä lukua! Eli korkeus resoluutio vaikuttaa peliruudun kokoon eniten!

Äänenvoimakkuus joko tehostaa tai mykistää äänet! (Suosittelen pitämään keskellä. Ellei ole liian kovalla.) 

Pelaajan nimen vaihdolla voit vaikuttaa pelin sisäisen hahmon nimeen! Kätevää!

Remove Account: Vältetään tällä kertaa.

Kun kaikki on valmista niin klikkaa save ja palaa äsköiselle sivulle ja lyö Start Game nappia!

### Pelin sisällä
![A-4](https://raw.githubusercontent.com/Mirex97/2D-Scroller-otm-harjoitustyo/master/dokumentointi/kuvat/A-4.PNG)
Intro pitäisi käynnistyä ja pääset pelin sisäiseen menuun!

Oli tarkoituksena tehdä vaihdettavat painikkeet, mutta keskityin nämä päivät pelin viimeistelyyn.
Painikkeiden vaihtaminen on kuitenkin täysin mahdollista.

Pelin näppäimet:
### Näppäimet
#### Pelin sisällä
Näppäin | Toiminto
---|---
W | Hyppää
AD | Liikkuminen
SHIFT | Juoksu
PLUS | Lähennä kuvaa
MIINUS | Loitonna kuvaa
ENTER | Hyväksy / Seuraava teksti.
#### Pelin sisäinen menu ja pause.
Näppäin | Toiminto
---|---
ENTER | Valitse
W Tai Nuoli ylös | Valinta ylös
S Tai Nuoli alas | Valinta alas


