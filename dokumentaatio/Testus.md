#Testaus

Ohejelmaa testataan automatisoiduin testein JUnitilla sekä manuaaliseti testaamalla. 

### Sovelluslogiikka

Sovelluslogiikkaa on testattu automaattisesti mahdollisimman laajasti. 

Tästä on kuitenkin irrotettu paketit game ja main koska game vastaa osaltaan käytännössä käyttöliittymän luontia. 

<img src="https://github.com/brontto/ot-LightWeightSaurus/blob/master/dokumentaatio/Kuvat/testikattavuus.pngraw=true" width="500">

### Testauksen ongelmakohtia

Testeissä ongelmana on monessa kohtaa se että käytössä on todella laajalti LWJGL-kirjasto, jonka testaaminen ei 
ole kovin mielekästä koska se on jo testattu. Myöskin poikkeuksien heitto kohdat eivät sisälly testeihin koska en
löytänyt keinoa näiden testaamiseen ilman että olisin rikkonu koodini totaalisesti. 
  
