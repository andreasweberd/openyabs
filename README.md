# YaBS – Yet another Business Software

**Angebote, Aufträge, Rechnungen, E-Rechnungen, Kunden, Artikel und Buchhaltung für Selbstständige und kleine Unternehmen – auf dem eigenen Rechner, mit den Daten in den eigenen Händen.**

Frei und Open Source (GPL v3) · Windows, Linux und macOS · Keine Cloud, kein Account, kein Abonnement

**[⬇ Neueste Version herunterladen](https://github.com/anti43/openyabs/releases)** · [Hinweise und Neuerungen](docs/Hinweise.md) · [E-Rechnung (XRechnung / ZUGFeRD)](docs/E-Rechnung.md)

![Ein Auftrag in YaBS: Kunde, Belegdaten und Termine in Kacheln, darunter die Positionen mit Netto, MwSt. und Gesamt](docs/images/invoice.png)

---

## Inhaltsverzeichnis

- [Warum YaBS](#warum-yabs)
- [Ihre Daten bleiben bei Ihnen](#ihre-daten-bleiben-bei-ihnen)
- [Funktionen auf einen Blick](#funktionen-auf-einen-blick)
- [Kunden, Lieferanten und Hersteller](#kunden-lieferanten-und-hersteller)
- [Angebote, Aufträge, Rechnungen und mehr](#angebote-aufträge-rechnungen-und-mehr)
- [Centgenaue Beträge](#centgenaue-beträge)
- [E-Rechnung: XRechnung und ZUGFeRD](#e-rechnung-xrechnung-und-zugferd)
- [Druck, PDF, Vorlagen und E-Mail](#druck-pdf-vorlagen-und-e-mail)
- [Zahlungseingang: Fälligkeiten, Skonto, Mahnwesen](#zahlungseingang-fälligkeiten-skonto-mahnwesen)
- [Artikel, Dienstleistungen und Lager](#artikel-dienstleistungen-und-lager)
- [Buchhaltung: Journal, Einnahmen und Ausgaben](#buchhaltung-journal-einnahmen-und-ausgaben)
- [Terminplanung und wiederkehrende Rechnungen](#terminplanung-und-wiederkehrende-rechnungen)
- [Eine moderne, schnelle Benutzeroberfläche](#eine-moderne-schnelle-benutzeroberfläche)
- [Kasse für die Ladentheke (erster Entwurf)](#kasse-für-die-ladentheke-erster-entwurf)
- [Teams: Benutzer, Gruppen und Rechte](#teams-benutzer-gruppen-und-rechte)
- [Import, Export und Umstieg von anderer Software](#import-export-und-umstieg-von-anderer-software)
- [Für Power-User: Skripte, Plugins und SQL](#für-power-user-skripte-plugins-und-sql)
- [Installation](#installation)
- [Aktualisierung und bestehende Daten](#aktualisierung-und-bestehende-daten)
- [Aus dem Quellcode bauen](#aus-dem-quellcode-bauen)
- [Hilfe, Community und Lizenz](#hilfe-community-und-lizenz)

---

## Warum YaBS

- **Alles an einem Ort**: Kontakte, Angebote, Aufträge, Rechnungen, Abschlagszahlungen, Gutschriften, Mahnungen, Artikel, Lager, Ausgaben und ein Journal – nahtlos miteinander verknüpft.
- **Ihre Daten bleiben auf Ihrem Rechner**: Keine Cloud, keine Registrierung, kein Tracking, funktioniert 100 % offline. Siehe [unten](#ihre-daten-bleiben-bei-ihnen).
- **Vorbereitet auf die gesetzliche E-Rechnungspflicht**: XRechnung und ZUGFeRD nach EN 16931, vor dem Versand mit dem offiziellen Validator auf Gültigkeit geprüft.
- **Eigenes Design & Layout**: Rechnungen und Anschreiben aus eigenen LibreOffice / OpenOffice-Vorlagen (ODT) oder interaktiven PDF-Formularen.
- **Dauerhaft frei & kostenlos**: Open Source unter der GPL v3. Keine Lizenzkosten, keine Beschränkung der Arbeitsplätze, kein Lock-in.
- **Läuft überall**: Windows, Linux und macOS, einzeln oder im Team mit einer gemeinsamen Netzwerkdatenbank.

## Ihre Daten bleiben bei Ihnen

Das unterscheidet YaBS von webbasierten Cloud-Rechnungsdiensten:

- **Keine Cloud, kein Account.** YaBS ist eine echte Desktop-Anwendung. Niemand kann Ihr Konto sperren, Preise erhöhen oder Ihre betriebswirtschaftlichen Zahlen einsehen.
- **Alle Daten bleiben lokal.** Kunden, Rechnungen, Preise und Buchhaltung lagern in einer Datenbank auf Ihrem eigenen PC (eingebettetes Apache Derby) – oder auf Wunsch auf Ihrem eigenen Büroserver (MySQL / MariaDB).
- **Funktioniert komplett offline.** Angebote verfassen, Rechnungen schreiben und Buchhaltung benötigen keine Internetverbindung. YaBS geht nur dann online, wenn *Sie* eine entsprechende Aktion auslösen, wie das Senden einer E-Mail über Ihren eigenen SMTP-Server.
- **Keine Telemetrie, kein Tracking, keine Werbung.**
- **Normale Dateien auf Ihrer Festplatte.** PDFs, E-Rechnungen und Exporte landen in Verzeichnissen Ihrer Wahl. Die Datenbank ist ein normaler Ordner, den Sie einfach sichern oder auf einen USB-Stick kopieren können.
- **Einfach für die DSGVO.** Kundendaten werden keinem fremden Auftragsverarbeiter übergeben – sie verlassen zu keinem Zeitpunkt Ihren Computer.
- **Daten jederzeit exportierbar.** Kontakte und Dokumente lassen sich jederzeit nach CSV, XML, vCard oder HTML exportieren. Der Quellcode ist offen, das Dateiformat niemals ein Betriebsgeheimnis.
- **Software, die dauerhaft funktioniert.** Lokale Software verschwindet nicht plötzlich, wenn ein Startup aufgekauft oder ein Dienst eingestellt wird.

## Funktionen auf einen Blick

| Bereich | Was enthalten ist |
|---|---|
| **Kontakte** | Kunden, Lieferanten, Hersteller und Firmen; mehrere Anschriften; Bankverbindungen; Telefonnotizen, Dateianhänge und lückenlose Historie |
| **Dokumente** | Angebote, Aufträge, Rechnungen, Abschlagszahlungen, Teilrechnungen, Gutschriften, Lieferscheine, Auftragsbestätigungen, Mahnungen |
| **E-Rechnung** | XRechnung (XML), ZUGFeRD (PDF mit eingebettetem XML), validiert nach EN 16931, Ausgabeformat pro Kunde konfigurierbar |
| **Ausgabe** | Druck, PDF, ODT aus eigenen Vorlagen, E-Mail mit Anhängen, Serien-Mails, Sammeldruck, EPC-QR-Code (GiroCode) |
| **Artikel & Lager** | Artikel und Dienstleistungen, EK/VK-Preise, Margenkalkulation, Staffelpreise, Bildvorschau, EAN-Barcodes, Warengruppen und Stücklisten |
| **Lagerbestand** | Bestandsführung, Meldeschwellen, Bestellungen an Lieferanten, integrierter Bestandsmanager |
| **Finanzen** | Exakte Dezimalrechnung, separate MwSt.-Sätze, Rabatte, Skonto (Prozente & Tage), Fälligkeiten, Mahnspesen, Abschlagsverrechnung |
| **Buchhaltung** | Journal mit Einnahmen, Ausgaben, Gewinn und MwSt., Kontenrahmen, Barauslagen, SEPA-Lastschriften (DTAUS/XML) |
| **Planung** | Kalender, Fälligkeitsradar, wiederkehrende Dauerrechnungen, Übersichts-Dashboard |
| **Arbeiten** | Moderne Kachelansicht, Tabs, Vor/Zurück wie im Browser, Favoriten, Volltextsuche, Tastenkürzel, Papierkorb, Rückgängig |
| **Teams** | Mehrere Benutzer, Mandanten/Gruppen, Datensatz-Sperren gegen gleichzeitiges Überschreiben, Rechteverwaltung |
| **Daten** | Lokale Derby-Datenbank oder MySQL/MariaDB-Server, CSV/XML/vCard/HTML-Import & Export, Migrationsassistenten |
| **Erweiterungen** | Groovy-Skripte und Konsole, Live-Plugin-Manager, SQL-Abfragefenster, Shop-Schnittstelle |

## Kunden, Lieferanten und Hersteller

![Ein Kontakt mit Name, Rollen, Adresse, Kommunikation, Geschäfts- und Zahlungsdaten sowie allen Dokumenten des Kontakts](docs/images/contact.png)

- **Ein Kontakt, mehrere Rollen**: Ein Kontakt kann gleichzeitig Kunde, Lieferant, Hersteller und/oder Partnerfirma sein – dargestellt als Chips neben dem Namen und mit einem Klick umschaltbar.
- **Alles auf einer Seite**: Name, Anschriften (Haupt-, Rechnungs- und Lieferadresse), Telefon, Mail, Webseite, USt-IdNr., Bankverbindung, Zahlungsbedingungen und Notizen.
- **Alle Dokumente des Kontakts**: Angebote, Aufträge, Rechnungen, Gutschriften und Artikel direkt unter den Stammdaten – jeweils mit einzeiliger Positionsübersicht, Schnellfilter und Gesamtsumme.
- **Dokumente direkt aus dem Kontakt anlegen**: Neues Angebot, Auftrag, Rechnung, Gutschrift oder Artikel mit einem Klick erzeugen.
- **Individuelle Zahlungsbedingungen**: Zahlungsziel in Tagen oder „sofort fällig“, Skonto (% und Tage), das Ziel-Bankkonto und das E-Rechnungs-Format inklusive behördlicher Leitweg-ID.
- **Kommunikation**: Gesprächsnotizen, angehängte Dateien, E-Mail direkt versenden, Webseite im Browser öffnen.
- **Historie & Prüfung**: Jede Änderung wird protokolliert (wer hat was wann geändert). USt-IdNr. und IBAN werden bei der Eingabe live validiert.

## Angebote, Aufträge, Rechnungen und mehr

- **Der vollständige Verkaufsprozess**: Angebot → Auftrag → Anzahlung / Teilzahlung → Schlussrechnung → Gutschrift, jeweils mit einem Klick aus dem Vorgängerbeleg erstellt. Der Vorgänger wird als erledigt markiert und sauber referenziert.
- **Schlussrechnungen mit Abschlagsabzug**: Die Schlussrechnung übernimmt alle Positionen des Auftrags und zieht alle geleisteten Abschlags- und Teilzahlungen automatisch mit dem korrekten Steuersatz ab.
- **Flexible Positionen**: Menge, Einheit, Artikel, formatierter Freitext, Nettopreis, Steuersatz, Positionsrabatt, optionale Zusatzspalten oder reine Textzeilen; Zeilen verschieben, Artikel oder Belege einfügen.
- **Live-Summen**: Netto, Rabatt, Mehrwertsteuer und Gesamtbetrag aktualisieren sich sofort bei jeder Eingabe.
- **Statusverfolgung**: In Warteschlange, In Bearbeitung, Pausiert, Abgeschlossen, Bezahlt – mit automatischer Verbuchung des Zahldatums.
- **Eigene Nummernkreise**: Frei definierbare Belegnummern (z. B. `R-2026-001`) pro Belegart.
- **Lieferscheine & Auftragsbestätigungen**: Aus demselben Vorgang erstellbar, auf Wunsch zusammen ausgedruckt.
- **Sammelrechnungen**: Mehrere Rechnungen aus der Belegsuche zu einer Sammelrechnung zusammenfassen.
- **Soll-/Ist-Vergleich**: Rechnung direkt mit dem Auftrag vergleichen, um gelieferte und abgerechnete Mengen zu prüfen.

## Centgenaue Beträge

Eine kaufmännische Software darf nicht runden wie ein Taschenrechner. YaBS rechnet mit exakter Dezimalarithmetik (BigDecimal), niemals mit binärem Gleitkomma:

- Jede Zeile wird kaufmännisch auf den Cent gerundet, die Mehrwertsteuer wird **einmal pro Steuersatz** auf die Zwischensumme berechnet – exakt wie es der E-Rechnungsstandard EN 16931 vorschreibt. PDF und E-Rechnung weisen immer identische Beträge aus.
- Mehrere Steuersätze auf einer Rechnung (z. B. 19 % und 7 %) werden sauber aufgeschlüsselt.
- Zeilenrabatte, Staffelpreise, Skontobeträge und Mahngebühren werden einheitlich ermittelt – auf dem Bildschirm, im PDF, in der E-Rechnung und im Buchungsjournal.
- Zahlen werden im deutschen Format dargestellt (`1.234,56 €`).

## E-Rechnung: XRechnung und ZUGFeRD

- **XRechnung** (XML), **ZUGFeRD** (PDF/A-3 mit eingebettetem XML) oder beides nach **EN 16931**.
- **Amtlicher Prüfvalidator integriert**: Automatische Schematron-Validierung vor dem Speichern oder Versenden – fehlerhafte E-Rechnungen verlassen nicht das Haus, YaBS nennt den genauen Grund.
- **Pro Kunde einstellbar**: Ausgabeformat wählen (normales PDF, ZUGFeRD, XRechnung oder PDF + XRechnung), Leitweg-ID für Behörden, Zahlungsziele und Skonto.
- **Versand per E-Mail & Serien-E-Mail**: Jeder Empfänger erhält automatisch das für ihn hinterlegte Format.
- **Mehrere Bankverbindungen**: Je Kunde festlegen, welches Geschäftskonto auf der Rechnung ausgewiesen wird.
- Ausführliche Anleitung und rechtliche Hintergründe: **[docs/E-Rechnung.md](docs/E-Rechnung.md)**.

## Druck, PDF, Vorlagen und E-Mail

- **Freies Layout**: Rechnungen, Angebote, Mahnungen und Briefe in LibreOffice / OpenOffice Writer (ODT) oder als PDF-Formular gestalten – mit Platzhaltern für Positionstabellen, MwSt.-Gruppen, Zahlungsbedingungen und **EPC-GiroCode (QR-Code)**.
- **Vorlagen nach Belegart, Benutzer und Mandant**: Unterschiedliche Briefköpfe für verschiedene Firmenzweige oder Abteilungen.
- **PDF- und ODT-Export** mit frei wählbaren Dateinamenmustern (z. B. nach Jahr und Kunde).
- **Direkter Mailversand**: Über Ihren eigenen SMTP-Server mit angehängter PDF- und/oder XML-Rechnung basierend auf Textvorlagen.
- **Serienbriefe & Sammeldruck**: Aus dem Journal heraus alle offenen Rechnungen eines Monats auf einmal drucken oder versenden.
- **Automatisches Archiv**: Jede gesendete E-Mail kann automatisch als Blindkopie (Bcc) an ein Archivpostfach gehen.

## Zahlungseingang: Fälligkeiten, Skonto, Mahnwesen

- **Fälligkeiten**: Automatisch aus den Kundeneinstellungen, der Vorlage oder den Firmenvorgaben.
- **Skonto**: Skontosatz in Prozent und Skontofrist in Tagen je Kunde mit automatischer Berechnung des Abzugsbetrags auf der Rechnung.
- **Überfällige Rechnungen im Blick**: Schnellübersicht auf dem Dashboard.
- **Mehrstufiges Mahnwesen**: Zahlungserinnerung, 1. Mahnung, 2. Mahnung mit eigenen Fristen, Texten und Mahngebühren.
- **SEPA-Lastschrift**: Export von DTAUS-/SEPA-Dateien zum automatischen Einzug von Rechnungsbeträgen bei Ihrer Bank.

## Artikel, Dienstleistungen und Lager

![Ein Artikel mit Preisen, Warengruppe, Beschreibung, Hersteller und Lieferanten, Lagerbestand und Belegen, in denen er verwendet wurde](docs/images/product.png)

- **Artikel & Dienstleistungen**: Mit Artikelnummer, Einheit, EAN, Hersteller-Teilenummer, Beschreibung, Web-Link und **großem Artikelbild** im Kopfbereich für sofortige Erkennbarkeit.
- **Datenblatt-Verknüpfung**: Webadresse oder lokale Datei (z. B. auf dem Server `\\nas\docs\artikel.pdf`), mit einem Klick geöffnet.
- **Automatische Preiskalkulation**: EK und Aufschlag eingeben, Netto- oder Bruttopreis wählen – alle abhängigen Werte passen sich live an.
- **Staffelpreise**: Preisstaffeln nach Mindestmenge; der Beleg wählt automatisch den passenden Preis.
- **Stücklisten & Sets**: Vordefinierte Leistungspakete mit einem Klick in Angebote übernehmen.
- **Lieferantenvergleich**: Mehrere Lieferanten je Artikel mit direktem Shop-Link und aktuellem Einkaufspreis; der günstigste Lieferant wird hervorgehoben.
- **Verkaufshistorie**: Welcher Kunde hat den Artikel wann und zu welchem Preis bestellt?
- **Lagerverwaltung**: Mindestbestand, automatische Bestandsabbuchung bei Rechnungsstellung, Inventur und Lieferantenbestellungen.

## Buchhaltung: Journal, Einnahmen und Ausgaben

![Das Journal mit allen Belegen eines Zeitraums, Filtern und Gesamtsummen für Umsatz, Ausgaben, Gewinn und MwSt.](docs/images/journal.png)

- **Journal**: Alle Belege, Einnahmen und Ausgaben eines Zeitraums mit Filtern nach Belegart, Status, Mandant, Bearbeiter und Sachkonto.
- **Einnahmen-Überschuss-Rechnung (EÜR)**: Einnahmen und Ausgaben werden erst mit tatsächlicher Zahlung gewertet (Zufluss-/Abflussprinzip).
- **Belege & Barausgaben**: Manuelle Erfassung von Ausgabenbelegen (z. B. Tankquittungen, Porto) mit Steuersatz und Kontierung.
- **Freie Kontenrahmen**: SKR03, SKR04 oder eigene Nummernkonten zuweisbar.
- **Dashboard**: Schnellansicht offener Angebote, unbezahlter Rechnungen und anstehender Termine.
- **Export für den Steuerberater**: Journal als PDF, CSV oder formatierte Auswertung exportieren.

## Terminplanung und wiederkehrende Rechnungen

- **Kalender**: Integrierte Terminübersicht verknüpft mit Belegen und Kundenkontakten.
- **Wiederkehrende Rechnungen (Abo-Rechnungen)**: Regelmäßige Rechnungsstellung (z. B. monatlich oder quartalsweise) automatisieren – mit einem Klick aus dem Dashboard erzeugt.
- **Fälligkeitswarnungen**: Automatische Hinweise auf überschrittene Zahlungsziele.

## Eine moderne, schnelle Benutzeroberfläche

- **Modernes Kacheldesign**: Übersichtliche Karten, runde Bedienelemente, aufgeräumte Tabs – die **klassische Ansicht bleibt mit einem Klick erreichbar** (*Ansicht › Layout › Modernes Aussehen*).
- **Responsives Layout**: Kacheln ordnen sich je nach Fensterbreite automatisch ein- bis dreispaltig an; optimiert für hochauflösende 4K- und HiDPI-Bildschirme.
- **Universalsuche (`Shift Shift` oder `Strg+F`)**: Wie in modernen IDEs findet ein einziges Suchfeld Kontakte, Rechnungen, Artikel, Termine, Menübefehle und Plugin-Funktionen.
- **Aktivitätsleiste unten rechts**: Alle Aktionen der Sitzung im Überblick – erstellte PDFs (sofort öffnen oder im Ordner anzeigen), geänderte Datensätze und Statusmeldungen.
- **Papierkorb & Historie**: Versehentlich gelöschte Einträge landen im Papierkorb und können jederzeit wiederhergestellt werden.
- **Vollständige Tastaturbedienung**: Schnelles Arbeiten ohne Maus über standardisierte Shortcuts (`Strg+S` Speichern, `Strg+P` Drucken, `Strg+Shift+P` PDF, `Strg+E` Mail).
- **Datumsfelder mit Schnelltippen**: Kurzeingaben wie `1.2.` (dieses Jahr), `010226`, `5` (5. des Monats), `+7` (in einer Woche) oder `h` / `heute`.
- **Kopie erstellen (`Strg+Shift+S`)**: Öffnet eine ungespeicherte Kopie eines Belegs oder Artikels in einem neuen Tab zum schnellen Weiterbearbeiten.
- **Zoom wie im Browser**: Mit `Strg +`, `Strg −` und `Strg 0` die gesamte Oberfläche stufenlos von 80 % bis 200 % vergrößern.

## Kasse für die Ladentheke (erster Entwurf)

YaBS stellt auf Wunsch eine **Touch-Kasse als Weboberfläche** für Tablets, Touchscreens oder Smartphones im lokalen Netzwerk bereit:

- Aufruf über *Einstellungen › Kasse*. YaBS zeigt die **lokale Netzwerk-IP** (z. B. `http://192.168.1.20:8090/`) als Link und Kopiervorlage für das Touchgerät an.
- **Warengruppen auswählen**: Warengruppen für den Schnellverkauf an der Theke festlegen.
- **Verkauf per Fingertipp**: Kassierer loggen sich per PIN ein, tippen auf Artikelkacheln, wählen Barzahlung und erhalten das Wechselgeld centgenau berechnet.
- **Automatische Verbuchung**: Jeder Bon wird als bezahlte Rechnung für den „Barverkauf“ angelegt und fließt direkt in Umsatz und Journal ein.
- **Bon-Druck auf 80mm-Thermodrucker**: Vorlage *Kassenbon (Kasse)* für Bondrucker inklusive Ausdruck oder PDF-Anzeige.
- **Rechtlicher Hinweis (Deutschland):** Kassen im gewerblichen Barverkauf unterliegen der KassenSichV (§ 146a AO) und benötigen eine zertifizierte technische Sicherheitseinrichtung (TSE). Dieser erste Entwurf enthält **noch keine TSE** und dient zu Testzwecken oder für Bereiche ohne TSE-Pflicht.

## Teams: Benutzer, Gruppen und Rechte

- **Mehrbenutzerbetrieb**: Jeder Benutzer hat eigene Einstellungen, Vorlagen und Vorgabewerte.
- **Gruppen & Mandanten**: Getrennte Firmen, Filialen oder Abteilungen mit eigenen Nummernkreisen und Dokumentvorlagen.
- **Detaillierte Rechte**: Getrennt nach Ansehen, Exportieren, Bearbeiten, Anlegen/Löschen und Administrieren.
- **Datensatz-Sperre**: Wenn ein Mitarbeiter einen Beleg bearbeitet, können andere ihn nicht versehentlich überschreiben.
- **Gemeinsame Datenbank**: Mehrere Arbeitsplätze greifen im lokalen Netzwerk gemeinsam auf einen MariaDB- oder MySQL-Server zu – ganz ohne fremde Cloud.

## Import, Export und Umstieg von anderer Software

- **Import**: Kontakte und Artikel aus CSV, vCard und XML importieren.
- **Export**: Stammdaten, Belege und Auswertungen nach CSV, XML, vCard, HTML und PDF exportieren.
- **Migrations-Assistent für Fakturama 2**: Unter *Datei › Import › Fakturama 2* die `Database.script`-Datei Ihres Fakturama-Arbeitsbereichs auswählen. Steuersätze, Warengruppen, Kontakte mit Bankdaten, Artikel mit Staffelpreisen, Belege und Ausgabenbelege werden mit Belegnummern und Datum übernommen.
- **Webshop-Schnittstelle**: Datenaustausch mit Online-Shops zur automatischen Auftragsübernahme.

## Für Power-User: Skripte, Plugins und SQL

- **Groovy-Plugins**: Einfache `.groovy`-Dateien (ohne Build-Schritt) in den `plugins`-Ordner legen. Neue Menüpunkte, Felder, Validierungen und Reaktionen auf Belege hinzufügen.
- **Integrierter Editor**: Groovy-Editor mit Syntax-Highlighting, Zeilennummern, Vorlagen und Sofort-Kompilierung bei `Strg+S`. [Plugin-Anleitung](docs/Plugins.md).
- **Groovy-4-Konsole**: Automatisierte Datenbereinigung oder Sonderberechnungen.
- **SQL-Abfragefenster**: Direkte SELECT- und Auswertungsabfragen auf die lokale oder zentrale Datenbank.

## Installation

YaBS läuft auf **Windows, Linux und macOS**.

**Installationspakete** (enthalten eine vorkonfigurierte Java-Laufzeitumgebung – keine manuelle Java-Installation nötig):

| System | Paket | Installation |
|---|---|---|
| **Windows** | `.msi` | Per Doppelklick installieren |
| **Debian, Ubuntu, Linux Mint** | `.deb` | `sudo apt install ./yabs_*.deb` |
| **Fedora, openSUSE, RHEL** | `.rpm` | `sudo dnf install ./yabs-*.rpm` |
| **macOS (Apple Silicon `arm64`, Intel `x86_64`)** | `.dmg` | Öffnen und in *Programme* ziehen (beim ersten Start Rechtsklick › *Öffnen*) |

**Plattformunabhängiges ZIP** (benötigt **Java 21** oder neuer, z. B. von [adoptium.net](https://adoptium.net)): Entpacken und `yabs.bat` (Windows) oder `yabs.sh` (Linux/macOS) ausführen – oder `java -jar openyabs.jar`.

Alle Downloads: **[Releases auf GitHub](https://github.com/anti43/openyabs/releases)**

Beim ersten Programmstart fragt ein Assistent nach dem Speicherort: **Auf diesem Computer** (ein Klick, Standard) oder auf einem **Datenbankserver** (MySQL / MariaDB). Die Einrichtung erfolgt automatisch, deutsche Systeme starten auf Deutsch und Sie sind als `admin` (Passwort `password` – bitte unter *Einstellungen › Benutzer* ändern) angemeldet.

## Aktualisierung und bestehende Daten

- Bestehende Datenbanken werden beim ersten Start nach einem Update automatisch auf das neue Schema aktualisiert.
- Die deutsche Sprachdatei wird nach Updates automatisch auf den neuesten Stand gebracht.
- Vor jedem Update empfiehlt sich eine Sicherheitskopie des Datenbankordners.
- Weitere Hinweise: [docs/Hinweise.md](docs/Hinweise.md).

## Aus dem Quellcode bauen

```bash
./gradlew test distributionZip
```

Das ZIP-Paket wird im Ordner `build/distributions/` erstellt. Voraussetzung ist Java 21. Nach `./gradlew build` kopiert Gradle alle Abhängigkeiten nach `build/libs/lib/`, sodass `java -jar build/libs/openyabs.jar` direkt startet.

## Hilfe, Community und Lizenz

- Plugins erstellen: [docs/Plugins.md](docs/Plugins.md)
- Fragen und Support (Deutsch & Englisch): Mailingliste [openyabs@googlegroups.com](mailto:openyabs@googlegroups.com) · [Archiv](https://groups.google.com/forum/#!forum/openyabs)
- Dokumentation: [Handbuch](https://docs.google.com/document/d/1VhUi0wS-EJgfV-o89Hl-2wux810mJnqUV1wPxpgVIs4/pub) · [Hinweise und Neuerungen](docs/Hinweise.md) · [E-Rechnung](docs/E-Rechnung.md)
- Ursprüngliches Projekt: [anti43/openyabs](https://github.com/anti43/openyabs) · [Heise Download](http://www.heise.de/download/yabs-yet-another-business-software-1149250.html) · [Ubuntuusers Wiki](http://wiki.ubuntuusers.de/Yabs)

YaBS ist freie Software unter der **GNU General Public License v3**. Verwendete Bibliotheken und Lizenzen: [licenses-list.txt](licenses-list.txt).

----
# YaBS – Yet another Business Software

**Quotes, orders, invoices, e-invoices, customers, products and bookkeeping for freelancers and small businesses – on your own computer, with your data in your own hands.**

Free and open source (GPL v3) · Windows, Linux and macOS · No cloud, no account, no subscription

**[⬇ Download the latest version](https://github.com/anti43/openyabs/releases)** · [Deutsche Hinweise](docs/Hinweise.md) · [E-Rechnung (XRechnung / ZUGFeRD)](docs/E-Rechnung.md)

![An order in YaBS: customer, document data and dates in cards, the positions with net, VAT and total below](docs/images/invoice.png)

---

## Contents

- [Why YaBS](#why-yabs)
- [Your data stays with you](#your-data-stays-with-you)
- [Features at a glance](#features-at-a-glance)
- [Customers, suppliers and manufacturers](#customers-suppliers-and-manufacturers)
- [Quotes, orders, invoices and more](#quotes-orders-invoices-and-more)
- [Correct amounts, to the cent](#correct-amounts-to-the-cent)
- [E-invoicing: XRechnung and ZUGFeRD](#e-invoicing-xrechnung-and-zugferd)
- [Printing, PDF, templates and e-mail](#printing-pdf-templates-and-e-mail)
- [Getting paid: due dates, cash discount, reminders](#getting-paid-due-dates-cash-discount-reminders)
- [Products, services and stock](#products-services-and-stock)
- [Bookkeeping: journal, expenses and revenues](#bookkeeping-journal-expenses-and-revenues)
- [Scheduling and recurring invoices](#scheduling-and-recurring-invoices)
- [A modern, fast user interface](#a-modern-fast-user-interface)
- [Cash register for the shop counter (first draft)](#cash-register-for-the-shop-counter-first-draft)
- [Teams: users, groups and rights](#teams-users-groups-and-rights)
- [Import, export and moving from other software](#import-export-and-moving-from-other-software)
- [For power users: scripts, plugins and SQL](#for-power-users-scripts-plugins-and-sql)
- [Installation](#installation)
- [Updating and your existing data](#updating-and-your-existing-data)
- [Building from source](#building-from-source)
- [Help, community and license](#help-community-and-license)

---

## Why YaBS

- **Everything in one place**: contacts, quotes, orders, invoices, deposits, credit notes, reminders, products, stock, expenses and a journal – connected to each other.
- **Your data stays on your computer**: no cloud, no login, no tracking, works offline. See [below](#your-data-stays-with-you).
- **Ready for the e-invoice obligation**: XRechnung and ZUGFeRD according to EN 16931, checked with the official validator before they leave the house.
- **Your own layout**: invoices and letters come from your own LibreOffice / OpenOffice templates (ODT) or PDF forms.
- **Free forever**: open source under the GPL v3. No license fees, no per-user prices, no lock-in.
- **Runs everywhere**: Windows, Linux and macOS, alone or with several users on a shared database.

## Your data stays with you

This is what sets YaBS apart from the many online invoicing services:

- **No cloud, no account.** YaBS is a desktop application. There is nothing to sign up for, and nobody but you can switch it off, raise the price or read your figures.
- **All data is local.** Customers, invoices, prices and your bookkeeping are stored in a database on your own computer (embedded Apache Derby) – or, if you want, on your own server in your office (MySQL / MariaDB).
- **Works offline.** Writing quotes, invoicing and bookkeeping need no internet connection. YaBS only goes online when *you* do something that needs it, such as sending an e-mail through *your* mail server.
- **No telemetry, no tracking, no ads.**
- **Your files are normal files.** PDFs, e-invoices and exports go to folders you choose. The database is a plain folder you can back up, copy to a USB stick or put into your existing backup.
- **Easy for data protection (GDPR).** Customer data is not handed to a third-party processor – it never leaves your machine.
- **You can always get your data out.** Export contacts and documents to CSV, XML, vCard or HTML at any time – and the source code is open, so the format is never a secret.
- **It keeps working.** Software on your own computer doesn't disappear when a start-up is bought or a service is discontinued.

## Features at a glance

| Area | What you get |
|---|---|
| Contacts | Customers, suppliers, manufacturers and companies; several addresses; bank accounts; notes, phone calls, files and a full history |
| Documents | Quotes, orders, invoices, deposits, part payments, credit notes, delivery notes, order confirmations, reminders |
| E-invoice | XRechnung (XML), ZUGFeRD (PDF with embedded XML), validated per EN 16931, delivery format per customer |
| Output | Print, PDF, ODT from your own templates, e-mail with attachments, serial e-mails, mass printing, EPC QR code (GiroCode) |
| Products | Products and services, purchase/sale prices, markup, staggered prices, images, EAN, product groups and lists, suppliers |
| Stock | Inventory, thresholds, purchase orders to suppliers, stock manager |
| Money | Exact decimal arithmetic, VAT per rate, discounts, cash discount (Skonto), due dates, reminder fees, deposits deducted in the final invoice |
| Bookkeeping | Journal with revenue, expenses, profit and VAT, accounts, expenses and revenues, direct debit export (DTAUS) |
| Planning | Calendar, due date overview, recurring invoices, dashboard with open amounts |
| Working | Modern card layout, tabs, back/forward, favourites, search, keyboard shortcuts, trash bin, undo of unsaved changes |
| Teams | Several users, groups, rights, record locking, own settings per user |
| Data | Local Derby database or MySQL server, CSV / XML / vCard / HTML import and export, migration tools |
| Extend | Groovy scripts and console, plugins, SQL query window, web shop interface |

## Customers, suppliers and manufacturers

![A contact with name, roles, address, communication, business and payment data and all documents of the contact](docs/images/contact.png)

- **One contact, several roles**: a contact can be customer, supplier, manufacturer and/or company at the same time – shown as chips next to the name and switched with one click.
- **Everything about a contact on one page**: name, addresses (main, billing and delivery addresses), phone, mail, web, VAT ID, bank account, payment terms and notes.
- **All documents of a contact** below the data: quotes, orders, invoices, credit notes and products, each with a one-line summary of its positions, a filter and the total.
- **Create documents from the contact**: new quote, order, invoice, credit note or product with one click.
- **Payment terms per customer**: due days or "payable immediately", cash discount (% and days), the bank account the customer pays to, and the e-invoice delivery format with Leitweg-ID.
- **Communication**: phone call / conversation notes, files attached to the contact, mail directly from the contact, website opens in the browser.
- **History**: every change is recorded – who changed what and when.
- **Checks while typing**: VAT IDs and IBANs are validated.

## Quotes, orders, invoices and more

- **The whole sales process**: quote → order → deposit / part payment → invoice → credit note, each created from the previous one with one click. The previous document is marked as finished and referenced.
- **Final invoices with deposits**: the final invoice contains the order's positions and deducts all deposits and part payments – automatically and with the right VAT.
- **Positions as you need them**: quantity, unit, product, free text (multi-line), net price, VAT rate, discount per line, optional extra column, text-only lines; move lines up and down, paste products, product lists or whole documents.
- **Live totals**: net, discount, VAT and total update while you type.
- **Status tracking**: queued, in progress, paused, finished, paid – with the payment date booked automatically.
- **Document numbers** from your own number formats (e.g. `R-2026-001`), per document type.
- **Delivery notes and order confirmations** from the same document, optionally printed together.
- **Combine several invoices** into one collective invoice from the document search.
- **Compare an invoice with its order** to see what has been delivered and billed.
- **Find documents** by number, customer, date, status or text.

## Correct amounts, to the cent

Invoicing software has to calculate correctly. YaBS calculates with exact decimal numbers, never with binary floating point:

- Every line is rounded to the cent, the VAT is calculated **once per VAT rate** on the sum of the lines – exactly as EN 16931 (the e-invoice standard) requires. The PDF and the e-invoice always show the same amounts.
- Several VAT rates on one invoice are listed separately, also rates like 7.7 %.
- Line discounts, staggered prices, cash discount and reminder fees are calculated the same way everywhere – on screen, in the PDF, in the e-invoice, in the journal and in the direct debit file.
- Numbers are read and shown in your language's format (e.g. `1.234,56` in German).

## E-invoicing: XRechnung and ZUGFeRD

- **XRechnung** (XML), **ZUGFeRD** (PDF/A-3 with embedded XML) or both, according to **EN 16931**.
- **Validated with the official validator** before saving or sending – invalid e-invoices are not sent, and YaBS tells you why.
- **Per customer**: choose the delivery format (normal PDF, ZUGFeRD, XRechnung or PDF + XRechnung), the Leitweg-ID for public authorities, due days and cash discount.
- **Works with e-mail and serial e-mail**: each customer receives the format they need.
- **Several bank accounts**: choose per customer which account appears on the invoice.
- All details, the legal background and a checklist: **[docs/E-Rechnung.md](docs/E-Rechnung.md)** (German).

## Printing, PDF, templates and e-mail

- **Your own layout**: design invoices, quotes, reminders and letters in LibreOffice / OpenOffice Writer (ODT) or as PDF forms, with fields for all data, position tables, VAT groups, payment terms and an **EPC QR code (GiroCode)** for instant bank transfers.
- **Templates per document type, user and group** – for example different layouts for different companies or departments.
- **PDF and ODT export** with file names and folders from your own patterns (e.g. by year and customer). File dialogs always open in your configured folder or the last one you used.
- **E-mail directly from YaBS** through your own mail server (SMTP), with the PDF and/or e-invoice attached, using message templates.
- **Serial e-mails and mass printing** from the journal – for example all open invoices of a month.
- **Automatic archive**: every sent e-mail can also go to your own archive mailbox (Bcc).
- **Auto-PDF**: optionally save a PDF every time a document is saved.

## Getting paid: due dates, cash discount, reminders

- **Due dates** from the customer, the template or your company defaults.
- **Cash discount (Skonto)**: percent and days per customer, with discount amount and the amount to pay in the template.
- **Overdue invoices at a glance** on the dashboard.
- **Reminders in stages** (e.g. payment reminder, 1st and 2nd reminder) with your own texts and fees per stage, printed or mailed.
- **Direct debit**: export a DTAUS file for collecting invoice amounts.

## Products, services and stock

![A product with prices, classification, description, manufacturer and suppliers, stock and the documents it was used in](docs/images/product.png)

- **Products and services** with number, unit, EAN, manufacturer reference, description, URL and a **large product image** at the top of the page (so you see at once that it is the right part), plus a **datasheet link**: a web address or a file, also on the network (e.g. `\\nas\docs\part.pdf`), opened with one click.
- **Prices that calculate themselves**: enter cost and markup, sale price or gross price – the other values follow while you type.
- **Staggered prices**: price per minimum quantity; in a document the price follows the quantity automatically (unless you typed a price yourself).
- **Product groups and product lists** (e.g. a standard package you add to a quote with one click, optionally with a percentage surcharge).
- **Manufacturers and several suppliers** per product, each with a **link to the product in the supplier's shop** and its **purchase price**; the cheapest supplier is marked.
- **Where was it sold?** Every product shows the quotes, orders, invoices and credit notes it appears in, with quantities and amounts.
- **Stock**: stock level and threshold, automatic reduction when invoicing, stock manager, purchase orders to suppliers.

## Bookkeeping: journal, expenses and revenues

![The journal with all documents of a period, filters and totals for revenue, expenses, profit and VAT](docs/images/journal.png)

- **Journal**: all documents, expenses and revenues of a period, filtered by type, status, group, user and account – with totals for volume, revenue, expenses, profit and VAT.
- **Cash basis**: revenue and expenses count once they are paid.
- **Expenses and revenues** outside of invoices (e.g. receipts), with VAT and account.
- **Accounts** for your chart of accounts; documents and receipts are assigned to them.
- **Dashboard**: open quotes, orders and invoices, overdue invoices, upcoming events.
- **Export** the journal to PDF, CSV or your own template – e.g. for your tax advisor.

## Scheduling and recurring invoices

- **Calendar** with events for documents and contacts.
- **Recurring invoices**: schedule an invoice to be created again every *n* months until an end date – YaBS creates them from the dashboard with one confirmation.
- **Due date warnings** for invoices.

## A modern, fast user interface

- **Modern look by default**: clean cards, rounded controls, clear tabs – and the **classic views are one click away** (*View › Layout › Modern look*).
- **Responsive layout**: the data cards arrange themselves in one to three columns depending on the window size; it scales with large fonts and HiDPI screens.
- **Search anything** (`Shift Shift`, like in IntelliJ, or `Ctrl+F`): one field finds contacts, quotes, invoices (also by number or type, e.g. "Rechnung"), products, positions, appointments, settings, menu commands and plugin results – `Enter` opens the hit.
- **Activity at the bottom right**: everything of the session in one list – created PDFs and documents (open them, show them in their folder), created, changed and deleted records (open them) and the messages; searchable and filterable.
- **History and trash**: the history of all records grouped by day, searchable and filterable, opens the records; the trash restores records or deletes them for good.
- **Settings in one place**: a searchable list with an overview of all settings, each page in clear cards.
- **Tabs and navigation**: several records open side by side, back and forward like a browser, favourites, a clipboard for records.
- **Fully usable by keyboard**: `Tab` / `Shift+Tab` walk through all fields, also past notes, file and document lists (in the positions of a document `Tab` moves through the cells and leaves at the last one, `Ctrl+Tab` leaves at once).
- **Date fields you can type into** in the format of your region (German: `dd.MM.yyyy`). Short forms work: `1.2.` (this year), `1.2.26`, `010226`, `5` (5th of this month), `+7` / `-1` (days from today), `h` / `heute` / `t` / `today`; arrow keys change the day, month or year under the cursor, `Alt+↓` or `F4` opens the calendar (`Enter` takes the date, `Esc` closes it).
- **Create copy** (*More › Create copy*, `Ctrl+Shift+S`): opens a copy of the contact, product or document **in a new tab** (the original stays open) as a new record, **not saved yet** (a yellow note says so, *Discard copy* goes back to the original). Only saving creates it.
- **Bank details explained on the pages**: *Settings › Company info* holds **your own** account (printed on your invoices) and the default payment term and Skonto for all contacts. At a contact, *Payment terms* overrides these defaults for this contact only (also which of your accounts the customer pays to), while *Bank account of this contact* is the contact's **own** account (direct debits, refunds, paying a supplier).
- **Keyboard shortcuts** for everything frequent: `Ctrl+S` save, `Ctrl+P` print, `Ctrl+Shift+P` PDF, `Ctrl+E` mail, `Shift Shift` / `Ctrl+F` search anything, `Ctrl+C` / `Ctrl+V` copy and paste records, `Ctrl+F1` shows all shortcuts.
- **Safe deleting**: before deleting, YaBS lists everything that would be deleted with it; deleted records go to a trash bin first.
- **Your language**: English and German included – on a German system (or with a German locale) YaBS switches to German by itself, and *View › Language* switches at any time (YaBS restarts itself); further translations can be made inside the application.
- **Zoom like in a browser**: `Ctrl +` / `Ctrl −` / `Ctrl 0` make the whole application – menus, texts, tables, date pickers and drop-downs – larger or smaller (80 – 200 %), remembered on each computer.

## Cash register for the shop counter (first draft)

YaBS can serve a **cash register as a web page** to a touch screen device at the counter (tablet, all-in-one PC). The device only needs a browser.

- Start it in *Settings › Cash register* (or *Plugins › Open cash register*). The settings page lists the **addresses of this computer with its IP** (e.g. `http://192.168.1.20:8090/`) as links: a click starts the register and opens it, a button copies the address for the touch device. Optionally the register starts together with YaBS.
- **Choose the product groups** offered at the register (nothing chosen: all products); changes apply immediately, even while the register runs.
- Sellers **sign in with their YaBS user**, tap product tiles (with groups and search), see net, VAT and total, and pay **cash** with quick amounts and a keypad; the **change** is shown.
- Every sale is booked as a **paid invoice for the cash customer** ("Barverkauf", created automatically), so it shows up in journals, revenue and reports like every other invoice.
- On first enable YaBS creates the **register user `kasse`** with a random 6-digit PIN; user name and PIN are shown on the settings page (a new PIN can be generated there).
- **Receipts from a template**: a demo template *Kassenbon (Kasse)* (80 mm receipt paper) is imported for the register. After a sale the receipt can be shown as PDF or printed on the template's printer (or the default printer), optionally automatically. Edit the template like every other YaBS template; extra fields are `pos.time` and `pos.seller`.
- Texts of the register and its settings are translated (German texts are also used when an older German language file is installed).
- **Legal note (Germany):** a cash register used for real cash sales must have a certified technical security device (TSE, KassenSichV / § 146a AO) and print its data (transaction number, signature counter, check value, serial numbers) on every receipt. This draft has **no TSE yet**; the demo receipt says so. Use it only for testing or where no TSE is required.
- First draft: cash only, no cash book yet. The server runs in your network without encryption, so use it only in a trusted network.

## Teams: users, groups and rights

- **Several users** with their own settings, signatures, templates and default values.
- **Groups** (e.g. companies, branches or departments) with their own documents, number formats and templates.
- **Rights** per user: view, export, edit, create/delete, administrate.
- **Record locking**: while someone edits a record, others can't overwrite it.
- **Shared database**: run YaBS on several computers against one MySQL / MariaDB server in your network – still without any cloud.

## Import, export and moving from other software

- **Import** contacts and products from CSV, vCard and XML.
- **Export** contacts, documents and lists to CSV, XML, vCard, HTML and PDF.
- **Migration tools**: from **Fakturama 2** (*File › Import › Fakturama 2*: choose `Database.script` of your Fakturama workspace; tax rates, product categories, contacts with address and bank account, products with block prices, invoices/offers/orders/delivery notes/credit notes with positions and shipping, and expense vouchers are taken over with their numbers and dates. Fakturama's files are not changed, and records already imported are skipped, so the import can be repeated), from older YaBS versions and MP 4.5, and from the local Derby database to a MySQL server.
- **Web shop interface** to exchange contacts and orders with a shop.

## For power users: scripts, plugins and SQL

- **Plugins** in **Groovy** (one file, no build) or **Java** (a jar): add commands, results in Search anything, settings pages and reactions to new invoices, created PDFs and more. Drop them into the `plugins` folder or install them in *Settings › Plugin Manager* – enable, disable, reload and edit them without restarting. Groovy plugins are written in the **built-in editor** (Groovy highlighting, line numbers, working examples to insert, syntax check with the line of the error, `Ctrl+S` saves and starts the plugin at once). **[How to write a plugin](docs/Plugins.md)**.
- **Groovy console and script manager** (Groovy 4): automate recurring tasks or add your own calculations and document labels.
- **SQL query window** for your own evaluations – your data is a normal SQL database.
- **Add your own actions** to the toolbar.

## Installation

YaBS runs on **Windows, Linux and macOS**.

**Installer packages** (bring their own Java – nothing else to install):

| System | Package | Install |
|---|---|---|
| Windows | `.msi` | double-click |
| Debian, Ubuntu, Linux Mint | `.deb` | `sudo apt install ./yabs_*.deb` |
| Fedora, openSUSE, RHEL | `.rpm` | `sudo dnf install ./yabs-*.rpm` |
| macOS (Apple silicon: `arm64`, Intel: `x86_64`) | `.dmg` | open, drag YaBS into *Applications*; the first time right-click › *Open* (the package is not signed) |

**ZIP for all systems** (needs **Java 21** or newer, e.g. from [adoptium.net](https://adoptium.net)): unpack and start `yabs.bat` (Windows) or `yabs.sh` (Linux, macOS) – or `java -jar openyabs.jar`.

All downloads: **[Releases](https://github.com/anti43/openyabs/releases)** – `latest-master` is the current development build.

On the first start one window asks where your data should be stored: **on this computer** (one click) or on a **database server** (MySQL / MariaDB, Derby). The setup runs in the background; languages, countries and an invoice template are set up automatically, German systems start in German, and you are signed in as `admin` (password `password` – change it under *Settings › Users*). Your company data goes to *Settings › Company Info*. No account, no internet connection needed.

## Updating and your existing data

- Older YaBS databases are opened and updated automatically on the first start; an interrupted update continues where it stopped.
- The German language file is brought up to date after an update without asking (a short note in the status bar), so new texts appear in German too.
- If a database is damaged, YaBS offers to copy all data into a newly created database and keeps the old one as a backup next to it.
- Tip: make a copy of the database folder before every update – it's just a folder.

Details (German): [docs/Hinweise.md](docs/Hinweise.md).

## Building from source

```bash
./gradlew test distributionZip
```

The ZIP is created in `build/distributions/`. Java 21 is required. After `./gradlew build` (or `jar`), the libraries are copied to `build/libs/lib/`, so `java -jar build/libs/openyabs.jar` starts right away (a `-cp` option is ignored by `java -jar`). Pushes to `master` and version tags build the ZIP and the installer packages on GitHub Actions.

Versions: builds of `master` are **2.0.&lt;build number&gt;** (shown on the splash screen and in *Help › About*), releases carry the version of their tag (`v2.1.0` → 2.1.0), local builds are `2.0-dev` (`./gradlew distributionZip -PreleaseVersion=2.0.5` sets one).

## Help, community and license

- Plugins: [docs/Plugins.md](docs/Plugins.md)
- Questions (English and German): mailing list [openyabs@googlegroups.com](mailto:openyabs@googlegroups.com) · [archive](https://groups.google.com/forum/#!forum/openyabs)
- German documentation: [Handbuch](https://docs.google.com/document/d/1VhUi0wS-EJgfV-o89Hl-2wux810mJnqUV1wPxpgVIs4/pub) · [Hinweise und Neuerungen](docs/Hinweise.md) · [E-Rechnung](docs/E-Rechnung.md)
- Original project: [anti43/openyabs](https://github.com/anti43/openyabs) · [heise download](http://www.heise.de/download/yabs-yet-another-business-software-1149250.html) · [ubuntuusers wiki](http://wiki.ubuntuusers.de/Yabs)

YaBS is free software under the **GNU General Public License v3**. Libraries and their licenses: [licenses-list.txt](licenses-list.txt).

![](https://raw.githubusercontent.com/anti43/openyabs/master/src/mpv5/resources/images/icon2_128.png)
