Sample story

Narrative:
Als Laboringenieur möchte ich Messreihen inklusive Messungen lesen können,
um diese zu überprüfen
					 
Scenario:  Messreihen ansehen
Given eine Datenbank  
When die Messreihen aus der Datenbank abgefragt werden
Then sollen diese in das Programm geladen werden

Scenario:  Messungen ansehen
Given eine Messreihe mit der ID 1  
When die Messreihe an die Datenbankanbindung übergeben wird
Then werden 7 Messungen der Messreihe geladen



