
Ohjeet testaamiseen löytyy alta! Osiosssa "Testaajille". <-- Nämä ovat tärkeitä ohjeita!

![logo](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/src/main/resources/menu/VrTheAdventure.png)
# VR The Adventure
Peli on 2D scrolleri, jossa seikkaillaan asemilta toisille ja pyritään pääsemään lipuntarkastajia karkuun!
Asemat ovat turva-alueita ja kentät ovat junan sisällä. Tietyissä kentissä on bosseja ja tietysti final boss.


## Puuttuvat toiminnot:
- Pelaajan häviäminen (jos menee yli kartan.)
- Lokit
- Resiina
- Kentät (Vain Level 1 Demonstraationa).
(Nämä ovat kaikki pääosin Grafiikasta kiinni.)

## Foorumi 
#### (Toinen projektini, jota olen tehnyt viikottain pelin ohella. Ovat tavallaan yhteydessä toisiinsa.)
[VR The Adventure Forums - TSOHA](https://mirex-pelifoorumi.herokuapp.com/)

## Dokumentaatio

[Releases](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/releases)

[Käyttöohje](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/edit/master/dokumentointi/kayttoohje.md)

[Vaativuusmäärittely](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/vaativuusm%C3%A4%C3%A4rittely.md)

[Arkkitehtuuri](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/Arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/Ty%C3%B6aikakirjanpito.md)

[Tarina](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/Pelin%20tarina.md)


# Testaajille <-- TÄSSÄ
#### HUOM, älä liikuta hiirtä tai näppäimistöä testatessasi tai buildatessa projektia! Muuten testit eivät mene läpi!
#### Lisäksi, sijoita ladattu .jar tiedosto tyhjään kansioon! Tiedosto luo tietokannat ja zip tiedoston ulkopuolelle!

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
tai
```
mvn install
```
Jos käytät generoitua .jar tiedostoa:
Jar pitäisi generoitua **target/VR_The_Adventure-1.0-launcher.jar** alle!
Älä käytä toista jar tiedostoa joka on target kansiossa! 
Se ei ole oikea eikä toimi. (Puuttuu riippuvuudet sisältä).

#### Jar tiedoston suoritus.
```
# Komentoriville
java -jar <nimi>
# Tai sitten vain suoraan tuplaklikkaa .jar tiedostoa!
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
