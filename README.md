# Autocomplete-function-on-trie-structure-dictionary

The trie-structure dictionary is further enhanced by implementing an auto-complete function. 
For any user-entered prefix, the program returns a list of candidate words sorted in lengthwise order. 
If there is more than one candidate word of the same length, those words will be sorted in alphabetical order instead. 

The function also automatically memorizes the returned word that the user previously selected for each prefix. 
If the user types in the same prefix again, the function will return the most recent candidate chosen word as the first item and so on.

After downloading and compiling all the java files, you can run the dictionary by:

```
java Dictionary (optional positive integer)
```

The programm accepts a positive integer after the Dictionary (default is 10).
This would represent the number of words you want the program to show in each run.
