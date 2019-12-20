# Arkkitehtuurikuvaus

Ohjelmisto käyttää pohjanaan Light Weight Java Game Libraryn osia jotka tarjoavat rajapinnan OpenGL:än käyttöön javalla. OpenGL on taas rajapinta grafiikan piirtämiseen näytöonhjaimen avulla. 

## Pakkausrakenne

Koodi on jaettu useampaan pakkaukseen. Keskeiset näistä ovat engine ja graphics. Engine sisältää moottorin keskeisen toimintalogiikan. Graphics taas sisältää kaikki grafiikan piirtämiseen tarvittavat komponentit.
Game pakkauksesta löytyy kaikki pelkästään yhteen "peliin" liittyvät asiat.

## Sovelluslogiikka

Sovelluslogiikan keskeiset osat ovat [Engine](https://github.com/brontto/ot-LightWeightSaurus/blob/master/LightWeightSaurus/src/main/java/engine/Engine.java), 
[Input](https://github.com/brontto/ot-LightWeightSaurus/blob/master/LightWeightSaurus/src/main/java/engine/Input.java) ja [Window](https://github.com/brontto/ot-LightWeightSaurus/blob/master/LightWeightSaurus/src/main/java/engine/Window.java) luokat. 

Engine luokan tehtävä on käynnistää ja ylläpitää ns. renderlooppia sekä käsitellä käyttäjän antama syöte muiden luokkien avulla. Input luokka vastaa käyttäjän syötteestä ja tätä käytetään pelkästään staattisten metodien avulla. Window luokka taas ylläpitää ikkunan luontia ja sen sisällön piirtämistä. 

<img src="https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/Kuvat/alustavatluokat.png?raw=true" width="400">

## Päätoiminnallisuus

#### Käynnistys

Suoritus lähtee siitä että luodaan IGameLogic rajapinnan toteuttava olio ja annetaan se Enginen luonnin yhteydessä sille. 
Enginen sisällä taas alustetaan kaikki tarvittavat isot komponentit kuten ikkuna ja inputit ja näiden jälkeen kaikki itse IGameLogicin sisältämä kama eli mm. meshit ja renderer.  

Seuraava kaavio kuvaa käynnistyksen toimintaa. 

<img src="https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/Kuvat/kaynnistyskaavio.png?raw=true" width="500">

#### Suoritus

Suorituksen aikana moottori päivittää eri komponentteja peräkkäin tietyssä järjestyksessä. Ensimmäisenä päiviteään pelilogiikka jonka sisällä otetaan vastaan käyttäjältä tuleva input ja muokataan pelin sisältöä sen mukaan. Tämän jälkeen tehdään uusi kuva muutosten pohjalta ja lopuksi annetaan se ikkunan näytettäväksi. Tätä looppia jatketaan kunnes moottori sammutetaan. 

<img src="https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/Kuvat/suorituskaavio.png?raw=true" width="500">

## IGameLogic

IGameLogic on ehkä lopulta moottorin mielekiintoisin osuus. Sillä määritellään kulloinenki "scene" mikä moottorin pitää suorittaa. "Scenen" vaihto ei ole mahdollista suorituksen aikana. 
IGameLogic rajapinnan toteuttavassa luokassa määritellään mitä erilaisia komponentteja haluamme moottorin sisällä mahdollisesti käyttää. Yleisimmät ovat Camera, Renderer ja lista GameItemeitä jotka sisältävä tiedot esim. 3d mallista ja textuuresit sekä näiden lokaation 3d tilassa.
Siinä myös määritellään millaisia käyttäjän syötteitä ohjelma tarkkailee jokaisella ruudunpäivityksellä ja mitä näille syötteillä tehdään. Esim. RollingCubeDemossa kuunnellaan Q, A, Z, X ja nuolinäppäimiä ja niiden pohjalta sitten liikutetaan kuutiota edestakaisin.   

## Oheistoiminnot

Ohjelma lukee useampaa erilaista tiedostoa joiden lukeminen tapahtuu utils pakkauksen luokilla. Yleisin tiedostyyppi moottorissa on shader joka sisältää näytönohjaimella ajettavan ohjelman. Tämän lisäksi ohjelmasta löytyy tuki sekä PNG tiedostojen käyttämiselle tekstuurina. 
