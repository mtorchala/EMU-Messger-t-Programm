Sample story

Narrative:
Als Laboringenieur m�chte ich Messreihen inklusive Messungen lesen k�nnen,
um diese zu �berpr�fen
					 
Scenario:  Messreihen ansehen
Given eine Datenbank  
When die Messreihen aus der Datenbank abgefragt werden
Then sollen diese in das Programm geladen werden

Scenario:  Messungen ansehen
Given eine Messreihe mit der ID 1  
When die Messreihe an die Datenbankanbindung �bergeben wird
Then werden 7 Messungen der Messreihe geladen



