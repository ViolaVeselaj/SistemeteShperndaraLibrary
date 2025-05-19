# Sisteme_te_Shperndara_Library
Sisteme_te_Shperndara_Library është një aplikacion modern dhe i shpërndarë për menaxhimin e një sistemi librarie, i zhvilluar në kuadër të lëndës “Sistemet e Shpërndara”. Ky sistem është ndërtuar duke ndjekur parimet e arkitekturës klient-server, duke përdorur një backend të fuqishëm me Spring Boot dhe një frontend interaktiv me React.js, ndërsa të dhënat menaxhohen në mënyrë të sigurt përmes MySQL.

Qëllimi i këtij projekti është të simulojë një skenar të botës reale për menaxhimin e bibliotekave të ndara në mënyrë multitenant (ku çdo bibliotekë mund të ketë administratorët dhe përdoruesit e vet), duke mundësuar kështu një sistem të centralizuar dhe të shkallëzueshëm për menaxhimin e burimeve bibliotekare. Sistemi mbështet regjistrimin dhe autentifikimin e përdoruesve, ndarjen e roleve (ADMIN, USER), kontrollin e aksesit bazuar në autorizime, si dhe funksionalitete të avancuara si rezervimi i librave, menaxhimi i kërkesave për huazim, rekomandimet për libra trending dhe shumë të tjera.

Ky projekt shërben jo vetëm si një zgjidhje teknike funksionale, por edhe si një shembull praktik për konceptet kyçe të sistemeve të shpërndara, si ndarja e burimeve, siguria, komunikimi përmes REST API-ve dhe përdorimi i mekanizmave të mirëfilltë për ruajtjen e integritetit dhe konsistencës së të dhënave.

Përmes këtij sistemi, përdoruesit mund të:

Kërkojnë, rezervojnë dhe huazojnë libra në mënyrë të thjeshtuar

Ndjekin statusin e huazimeve të tyre

Ndajnë librat më të pëlqyer

Shkruajnë reviews për librat që kanë lexuar

Shikojnë statistika të përdorimit dhe lista të librave më të preferuar

Marrin rekomandime të personalizuara

Shikojnë evente të ndryshme të organizuar nga bibloteka

Administratorët mund të:

Menaxhojnë librat dhe informacionin përkatës

Verifikojnë dhe aprovojnë kërkesat për huazim

Shtojne autorë të rinj

Shtojnë libra të rinj në koleksion

Shtojnë evente në lidhje me punen e biblotekes

Monitorojnë përdorimin e sistemit në nivel tenant-i

Ky sistem është krijuar me qëllim që të jetë i lehtë për t’u zgjeruar, modular në ndërtim, dhe me një ndërfaqe përdoruesi të këndshme që rrit përvojën e përdorimit. Në thelb, ai përfaqëson një zbatim praktik të parimeve të shpërndarjes, me vlera të larta për aplikime akademike dhe profesionale.


## Struktura e Projektit

- **Backend:** Java + Spring Boot  
- **Frontend:** React.js  
- **Database:** MySQL  
- **Security:** JWT + Role-Based Access Control

## Funksionalitetet Kryesore

- Regjistrimi dhe autentikimi i përdoruesve
- Menaxhimi i librave (shtim, përditësim, fshirje)
- Huazimi dhe kthimi i librave
- Kërkesa për huazim (me aprovim nga admini)
- Rekomandime për libra trending
- Mbështetje për role: ADMIN dhe USER

## Teknologjitë e Përdorura

| Teknologji     | Përshkrim                         |
|----------------|-------------------------------    |
| Spring Boot    | REST API dhe logjika e backend-it |
| React.js       | UI moderne dhe interaktive        |
| MySQL          | Bazë të dhënash relacional        |
| JWT            | Autentifikim dhe autorizim        |


