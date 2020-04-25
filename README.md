# Projekt Bored

## Alkalmazás célja

Egy Androidos alkalmazást készítünk, ami segítséget nyújt a hétköznapok során, ha programötletekre van szükségünk. Ha unatkozol indítsd el ezt az alkalmazást, add meg a releváns információkat, és az program automatikusan megtalálja a neked legmegfelelőbb tevékenységet, akár egyedül vagy, akár barátaiddal unatkoztok.

## Terv

- Az applikációt az Android Studio használatával fogjuk elkészíteni
- Java programozási nyelven fogunk fejleszteni
- támogatjuk az Android 4.0-s verziójától kezdve minden telefont.
- Build rendszernek a Gradle-t fogjuk használni

## Prototípus

Ezen a linken megtekinthető az alkalmazás prototípusa: <https://www.figma.com/proto/faqImwXPhWMtNNAki6mWiR/ProjectBored?node-id=4%3A2&scaling=scale-down>

## Letöltés, tesztek eredményei, lint eredményei, dokumentáció

A repo Github Actions oldalára kattintva meg lehet tekinteni az applikáció legújabb verzióit. Bármelyikre rákattintva az artifact listából le lehet tölteni az applikációt, a unit tesztek eredményeit, a lint eredményeket illetve a dokumentációt.

## Telepítés

Az app.zip letöltése után (lásd fentebb) tömörítse ki az állományt, majd az így kapott .apk fájlt helyezze át az android telefonjára. A telefonján egy kattintással ez az .apk fájl telepítődni fog, és használatra készen fog állni.

## Használat

Az alkalmazás előre fel van töltve tevékenységekkel amikből véletlenszerűen választ egyet a "Give me an idea" gomb megnyomása után. Ha szeretné jobban szabályozni, hogy milyen tevékenységeket ajánljon a program, akkor navigáljon el az "Options" menübe. Ha megtetszett egy ötlet amit az applikáció adott Önnek, és a kiválasztott tevékenység támogatja ezt, akkor megjelenik a "Show in Map" gomb. Erre kattintva megnyílik a Google Maps applikáció és az alkalmazásunk megpróbál rákeresni arra, hogy az adott tevékenységet hol lehet Önhöz legközelebb végezni.

Ha saját tevékenységeket szeretne hozzáadni a programhoz, vagy törölni/módosítani szeretne egy már meglévő tevékenységet akkor kattintson az "All Ideas" gombra. Az applikáció színvilágát a "Settings" menüpontban állíthatja át Light illetve Dark témára. Ugyanitt bekapcsolhatja a "Power User" módot, amivel nagyobb szabadságot nyerhet a saját tevékenységek hozzáadásakor.

## Készítette

Ez az applikáció 2020 első felében készült az ELTE IK Projekt eszközök tantárgy elvégzése közben, projekt feladat gyanánt.

Készítette: Hock Gergely, Osbáth Gergely, Rusznyák Anna, Szabó Bence
