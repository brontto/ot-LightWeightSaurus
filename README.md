
# LWS - LightWeightSaurus 3d-moottori, ohjelmistotekniikan harjoitustyö

Ohjelman tarkoitus on mahdollistaa 3d-grafiikan piirtämisen tietokoneen ruudulle. 
Tämän hetken versio luo ikkunan harmaalla taustalla ja tulostaa hiiren kursorin sijainnin komentoriville hiiren vasenta painiketta painettaessa.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/tuntikirjanpito.md)


## Komentorivitoiminnot

### Suoritus 

Projektin voi suorittaa komenolla

```
mvn compile exec:java -Dexec.mainClass=main.Main
```

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn test jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _LightWeightSaurus-1.0-SNAPSHOT.jar_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/brontto/ot-LightWeightSaurus/blob/master/LightWeightSaurus/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_