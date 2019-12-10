
# LWS - LightWeightSaurus 3d-moottori, ohjelmistotekniikan harjoitustyö

Ohjelman tarkoitus on mahdollistaa 3d-grafiikan piirtämisen tietokoneen ruudulle. 

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/kayttoohje.md)

[Testaus](https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/Testus.md)

## Releaset

[Viikko 5](https://github.com/brontto/ot-LightWeightSaurus/releases/tag/viikko5)

[Viikko 6](https://github.com/brontto/ot-LightWeightSaurus/releases/tag/0.2)


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

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_
