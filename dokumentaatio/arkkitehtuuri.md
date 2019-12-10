# Arkkitehtuurikuvaus

## Pakkausrakenne

Koodi on jaettu useampaan pakkaukseen. Keskeiset näistä ovat engine ja graphics. Engine sisältää moottorin keskeisen toimintalogiikan. Graphics taas sisältää kaikki grafiikan piirtämiseen tarvittavat komponentit.
Game pakkauksesta löytyy kaikki pelkästään yhteen "peliin" liittyvät asiat.

## Sovelluslogiikka

Sovelluslogiikan keskeiset osat ovat [Engine](https://github.com/brontto/ot-LightWeightSaurus/blob/master/LightWeightSaurus/src/main/java/engine/Engine.java), 
[Input](https://github.com/brontto/ot-LightWeightSaurus/blob/master/LightWeightSaurus/src/main/java/engine/Input.java) ja [Window](https://github.com/brontto/ot-LightWeightSaurus/blob/master/LightWeightSaurus/src/main/java/engine/Window.java) luokat. 

Engine luokan tehtävä on käynnistää ja ylläpitää ns. renderlooppia sekä käsitellä käyttäjän antama syöte muiden luokkien avulla. Input luokka vastaa käyttäjän syötteestä ja tätä käytetään pelkästään staattisten metodien avulla. Window luokka taas ylläpitää ikkunan luontia ja sen sisällön piirtämistä. 

<img src="https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/Kuvat/alustavatluokat.png?raw=true" width="400">

Seuraava kaavio kuvaa käynnistyksen ja suorituksen aikaista toimintaa. 

<img src="https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/Kuvat/suorituskaavio.png?raw=true" width="400">
