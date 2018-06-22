Sample story

Narrative:
Als Laboringenieur möchte ich Messreihen inklusive Messungen lesen können,
um diese zu überprüfen
					 
Scenario:  Messreihen ansehen
Given eine Datenbank
When die Messreihen aus der Datenbank abgefragt werden
Then sollten 6 Messreihen in das Programm geladen werden

Scenario:  Messungen ansehen
Given eine Messreihe mit der ID <messreiheid>  
When die Messreihe an die Datenbankanbindung uebergeben wird
Then werden <anzahlMessungen> Messungen der Messreihe geladen

Examples:
|messreiheid|anzahlMessungen|
|3|7|
|8|0|

