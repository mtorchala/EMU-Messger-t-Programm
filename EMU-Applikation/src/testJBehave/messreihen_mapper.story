Sample story

Narrative:
Als Laboringenieur m�chte ich Messreihen inklusive Messungen lesen k�nnen,
um diese zu �berpr�fen
					 
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

