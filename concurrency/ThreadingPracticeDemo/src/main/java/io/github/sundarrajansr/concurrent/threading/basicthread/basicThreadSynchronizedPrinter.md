## Basic Threading Example


####Scenario

    Develop a print spooler which awaits print tasks and give CPU
    to print Tasks in Asychnronous manner though messages are intact.
    Here the 'printing' refers to standard output.
    
    Note : Each character should be printed per PrintTask and allow a context switch


####Problem

When a character is printed per Print Task and if the context switch is allowed i.e current
Task goes to Sleep , other Tasks will interfere to print their own character. This async 
behavior breaks the text and makes it no longer meaningful. To avoid we must make right usage of synchronized.






