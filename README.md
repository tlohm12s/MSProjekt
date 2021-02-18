# MSProjekt
Prüfungsleistung für das Fach Microservices im WS 2020
Autor: Tobias Lohmüller  
Matrikelnummer: 9033365

[Hier](/Release) finden Sie alle ausführbaren Dateien, inklusive einer Postman  
Kollektion mit sämtlichen möglich Request-Möglichkeiten.

Die Service Infrastruktur besteht aus folgenden Services:
* [Register/Discovery Service](/src/main/java/de/hbrs/tlohm12s/MSProjekt/DiscoveryRegisterServiceApplication.java)
* [Dynamisch generierter Service](helloworldservice/src/main/java/de/hbrs/tlohm12s/helloworldservice/HelloworldServiceApplication.java)
* [Service Factory](servicefactory/src/main/java/de/hbrs/tlohm12s/servicefactory/ServiceFactoryApplication.java)
* [Gateway Service](gatewayservice/src/main/java/de/hbrs/tlohm12s/gatewayservice/GatewayServiceApplication.java)

## API:  

* Discovery / Register Service (Port 8761):  
_Läuft über Spring Cloud Netflix – Eureka. Zusätzliche Api, welche Eureka anbietet findet man [hier](https://github.com/Netflix/eureka/wiki/Eureka-REST-operations) zu finden. Bei dem starten eines Service, registriert sich dieser automatisch bei diesem Service._  

| Verb | Pfad | Status | Aktion | Beispiel |
| ------- | ---- | ----- | ------ | ------ |
|  GET   | /service/{instanceID}  | OK (200), NOT FOUND (404) | Gibt den Host (Format http://localhost:1234/) zu der jeweiligen instanceID wieder. | /service/123abc |

* Gateway Service (Port 8081):   
_Zugangspunkt für Klienten, leitet Anfragen an die unterschiedlichen Services (generierten Instanzen, Register/Discovery, Service Factory) weiter_

| Verb | Pfad | Status | Aktion | Beispiel |
| ---- | ---- | ------ | ------ | -------- |
|  GET     | /stop/{instanceID}  | OK (200), NOT FOUND (404) | Stoppt den Service mit der gegebenen instanceID. | /service/123abc/stop |
|  GET    | /service/{instanceID}/**  | * | Leitet die Anfrage (wo ** ist) an den jeweiligen Service mit der Instance ID weiter | /service/123abc/helloworld (Falls vom Typ HELLO_WORLD_SERVICE) |
|  POST   | /start/{type}  | OK (200), NOT FOUND (404) | Startet einen Service mit dem gegebenen Service Typs (Derzeitig Möglich: HELLO_WORLD_SERVICE) und gibt ein JSONObject mit der instanceID wieder. | /service/start/HELLO_WORLD_SERVICE |

* Service Factory Service (Port 8080):   
_Für das starten von dynamisch generierten Services zuständig_

| Verb | Pfad | Status | Aktion | Beispiel |
| ------- | ---- | ----- | ------ | ------ |
|  POST   | /service/{type}  | OK (200), NOT FOUND (404) | Startet einen Service mit dem gegebenen Service Typs (Derzeitig Möglich: HELLO_WORLD_SERVICE) und gibt ein JSONObject mit der instanceID wieder. | /service/HELLO_WORLD_SERVICE |

* Dynamisch generierter Service [Hello world Service] (Port dynamisch):  
_Läuft auf einem freien Port, begrüßt die Welt und gibt seine instance id wieder:_

| Verb | Pfad | Status | Aktion |
| ------- | ---- | ----- | ------ |
|  GET     | /helloworld  | OK (200) | Gibt ein Hello World wieder und seine instanceID
|  POST    | /actuator/shutdown  | OK (200), UNSUPPORTED MEDIA TYPE (415), METHOD NOT ALLOWED (405) | Stoppt den Service (Mithilfe des spring-boot-starter-actuator) |

_Weiterer Hinweis: Ein 404 Status wird wiedergegeben, falls ein Service mit der instanceID nicht existiert oder der angegebene Service Typ nicht existiert._

## Service Infrastruktur  
Hier wird das Zusammenspiel unterschiedlicher Service Instanzen verwendet, um für einen Klienten bei Bedarf  
einen Service zu erstellen, worauf er dann zugreifen kann, ohne wissen zu müssen, wo dieser wirklich liegt und  
ihn nachher wieder beendet. Dabei muss dem Klienten nur die REST-Api des Gateway Services interessieren, da dieser  
Anfragen an jeden weiteren Service weiterleitet.
 
Alle anderen Services sind über den Gateway Service versteckt, sodass der Gateway Service für die Klienten  
zum Access-Punkt der Infrastruktur wird.  
  
Jede Aufgabe des Systems wird in einen Service unterteilt, die ihren eigene API (Schnittstelle) anbietet, damit diese untereinander kommunizieren können (orientiert sich nach REST).  
Somit entesteht eine Microservice Struktur.

Mithilfe des Netflix Eureka Projektes, wird durch die @EnableEurekaServer und @EnableEurekaClient  
Annotation die Registrierung und Discovery automatisiert.
