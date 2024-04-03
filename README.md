# This is technical challenge for a selection proccess for a software development intern position.
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# Description and rules of the challenge

In this challenge I had to create to create a HTML analyzer that searches for the deepest text in a nested HTML structure and analyzes if the HTML structure is formed properly, using only JDK17,
the use of any external library or native JDK packages and classes related to HTML,XML or DOM are prohibited.

There are some rules to decrease the complexity of the challenge :
    1. The HTML code is divided in lines.
    2. Each line can only be one of the following kinds of elements:
        a) Opening tag 
        b) Closing tag
        c) A piece of text
    3. Only HTML elements that have a pair of opening tag and closing tag are going to be utilized.
    4. Opening tags don't have attributes.
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# Instructions to use the program
To compile the program use on the command line: javac HtmlAnalyzer.java
To execute the program use on the command line: java HtmlAnalyzer insert-the-URL-here
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# Expected outputs from the program
  # If the HTML is valid:
    $ Piece of text
  # If the URL is invalid or there is a connection error:
    $ URL connection error
  # If the HTML structure is malformed:
    $ malformed HTML
