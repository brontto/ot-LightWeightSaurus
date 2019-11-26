# Arkkitehtuurikuvaus

## Sovelluslogiikka
Sovelluslogiikan keskeiset osat ovat [Engine](https://github.com/brontto/ot-LightWeightSaurus/blob/master/LightWeightSaurus/src/main/java/engine/Engine.java), 
[Input](https://github.com/brontto/ot-LightWeightSaurus/blob/master/LightWeightSaurus/src/main/java/engine/Input.java) ja [Window](https://github.com/brontto/ot-LightWeightSaurus/blob/master/LightWeightSaurus/src/main/java/engine/Window.java) luokat. 

Engine luokan tehtävä on käynnistää ja ylläpitää ns. renderlooppia sekä käsitellä käyttäjän antama syöte muiden luokkien avulla. Input luokka vastaa käyttäjän syötteestä ja tätä käytetään pelkästään staattisten metodien avulla. Window luokka taas ylläpitää ikkunan luontia ja sen sisällön piirtämistä. 

<img src="https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/Kuvat/alustavatluokat.png?raw=true" width="400">

