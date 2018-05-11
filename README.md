
Ohjeet testaamiseen löytyy alta! Osiosssa "Testaajille". <-- Nämä ovat tärkeitä ohjeita!

![logo](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/src/main/resources/menu/VrTheAdventure.png)
# VR The Adventure
Peli on 2D scrolleri, jossa seikkaillaan asemilta toisille ja pyritään pääsemään lipuntarkastajia karkuun!
Asemat ovat turva-alueita ja kentät ovat junan sisällä. Tietyissä kentissä on bosseja ja tietysti final boss.

Tällä hetkellä peli on vain Demo.

Projektin tärkeimpänä haasteena itselle oli tutustua pelinkehittäjän työhön. Ja mielellään loisin lisää pelejä!
Pelin tarkoituksena myös oli demonstroida omaa kykyäni luoda pelejä.

Pelistä on myös Foorumi sivu käytössä, joka on osa TietokantaSovellus kurssia. (Eli toinen projekti jota olen tehnyt ohella.


## Tämän hetken puuttuvat toiminnot:
- Bossi
- Lokit
- Resiina
- Lisä Kentät (Vain Level 1 Demonstraationa).
- Kenttien puute vaikuttaa suoraan saves kantaan.
(Nämä ovat kaikki pääosin Grafiikasta kiinni.)

## Foorumi
[VR The Adventure Forums - TSOHA](https://mirex-pelifoorumi.herokuapp.com/)

## Dokumentaatio

[Releases Viikko7-Final](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/releases/tag/Viikko7-Final)

[Käyttöohje](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

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
