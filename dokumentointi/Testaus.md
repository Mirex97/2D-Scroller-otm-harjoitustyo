# Testausdokkari


## Yksikkö- ja integraatiotestaus
Ohjelmassa automaattinen testaus JUnitilla, lisäksi JUnitin ohella on JLayer käytössä näppäimistönkuuntelijaa varten.
(Valkoinen ruutu, jolloin ei saa liikkua, muuten menee testi pieleen).

Testit on pääosin tehty daoja varten, mutta myös löytyy testaus normaaleja luokkia varten, kuten Timer luokkaa joka laskee pelin sisäistä kelloa alaspäin. 

Myös Scorea varten tehty, koska scoren laskija toimii erikoisella tavalla. Sisältää kaksi arvoa, oikea arvo ja laiskempi arvo joka juoksee oikean arvon perässä.

DAO testiluokat luovat oman tietokantansa ja tekevät testit tänne. Päätyttyä tämä fake testikanta poistetaan koneelta!
Daojen testauksen toteuttaa DatabaseTest luokka.

## Testauskattavuus
Testauksesta on jätetty pois monta luokkaa, koska suurimmalla osasta luokista on paljon tekemistä grafiikan kanssa.

Varsinkin suurin osa pelin olioista on tekemisissä libtiled riippuvuuden map tiedoston kanssa. Tämä tarkoittaa että testejä varten pitäisi tehdä oma kenttä ja käydä läpi yksitellen toimiiko kaikki. (Tähän ei kuitenkaan ollut aikaa), joten keskityin vain oleellisiin luokkiin.

Joten projektissa ei ollut mahdollisuutta luoda testausta kenttiä varten tai mitään pelin oliota varten, koska kaikkien toiminnot määräytyvät minkä kanssa ne kohtaavat (intersect). 
(Lisäksi testauksiin menee ikuisuus, jos katsotaan testin pelaavan peliä puolesta). 

![Jacoco kattavuus](https://github.com/Mirex97/2D-Scroller-otm-harjoitustyo/blob/master/dokumentointi/kuvat/Jacoco.PNG)


## Järjestelmätestaus
Järjestelmätestaus suoritettu manuaalisti!

## Asennus ja konffaus
Kokeilin monella koneella pelin toimimista ja windowsilla tämä toimi jokaisella (paitsi Äitini koneella joka on surkea)!
Peli toimii Windows 7, 8, 10 ja Linuxilla. Mac:stä en tiedä, koska konetta en omista.
Yhteensä 8:salla eri koneella testattu pelin toimintaa.
Yhdessä meni peli sekaisin mutta java päivityksellä se lähti toimimaan (siksi linkki viimeisimpään javaan foorumilla).

Pelin tmx kartan lukeminen on testattu kaikin mahdollisin keinoin! Siinä meni yli kuukausi kun tappelin saman tiedosto ongelman kanssa!
Myös zip tiedoston tallennus ja purku kokeiltu useaan otteeseen.

Ainoastaan tuo ääniajuri ongelma on vielä avoin, mutta tästä on kerrottu lisää dokumentaatiossa.

## Laatuongelmat
Ääniajuri ongelma (ainakin linuxilla).
Linuxilla peli välkkyy hieman (Delta-time ongelma), mutta kuitenkin se on pelattavissa!
Löyty uusi ongelma! Full Screen moodi ei toimi linuxilla. Se jää nyt laatuongelmaksi.
Windowsilla kaikki pelittää kuin rasvattu.
