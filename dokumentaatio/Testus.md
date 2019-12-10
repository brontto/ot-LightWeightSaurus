# Testaus

Ohejelmaa testataan automatisoiduin testein JUnitilla sekä manuaaliseti testaamalla. 

### Sovelluslogiikka

Sovelluslogiikkaa on testattu automaattisesti mahdollisimman laajasti. 

Tästä on kuitenkin irrotettu paketit game ja main koska game vastaa osaltaan käytännössä käyttöliittymän luontia. 

<img src="https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/Kuvat/testikattavuus.png" width="800">

### Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti ja se on testattu sekä windows että linux ympäristössä. 
Siitä on testattu kaikki käyttöohjeen mukaiset ominaisuudet.


### Testauksen ongelmakohtia

Testeissä ongelmana on monessa kohtaa se että käytössä on todella laajalti LWJGL-kirjasto, jonka testaaminen ei 
ole kovin mielekästä koska se on jo testattu. Myöskin poikkeuksien heitto kohdat eivät sisälly testeihin koska en
löytänyt keinoa näiden testaamiseen ilman että olisin rikkonu koodini totaalisesti. 

Samoin update loopin aikainen toiminta tuntui olevan erittäin vaikeaa testata keskeisistä komponenteista kuten luokista engine ja window.
  
