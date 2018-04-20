# Peer to peer quote

This is the implementation of assignment "Zopa Technical Test"

It's been decided to use Java 8 and Maven as dependency manager and project builder.

36 months term was added as parameter to calculator and as a constant to the App class

Phrase "should be displayed" was interpreted as instruction to show approximate values, but use precise values for calculations, which might be considered as an issue by the users.

"quote.exe" - for the purpose of this test it was decided do not implement compilation of executable file/addition of a script, as it's unclear which target OS should be used.

Syntax is the following:

java -jar peer-to-peer-quote-1.0.0-SNAPSHOT-jar-with-dependencies.jar test-classes/Market_Data.csv 1000

For some reason data from the given example is not the same as the application output. I've verified the calculation using formula from https://www.thecalculatorsite.com/articles/finance/compound-interest-formula.php

Thank you. 