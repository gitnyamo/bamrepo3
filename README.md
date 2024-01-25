Welcome to BAM User Application
The Index HTML document view page serves as the landing page for the BAM User Application. It features a welcoming message, dynamic content for the current date, and a button to manage users.

Structure
The document starts with the usual HTML5 doctype declaration and sets the language to English.
Thymeleaf attributes are used for server-side processing.
The document contains a <head> section where metadata and stylesheets are defined.
The page uses Bootstrap CSS for styling.
There's also custom CSS for a celebratory splash animation.
The <body> section includes the main content of the page.
A container with centered text holds the welcome message, dynamic date display, and the "Manage Users" button.
The welcome message is styled to have a purple color and is animated with a celebratory splash effect.
The current date is dynamically displayed using Thymeleaf expressions.
The "Manage Users" button is linked to a user management page and triggers the start of the splash animation when clicked.
A <script> block at the end of the document contains JavaScript functions to control the splash animation.
The startSplash() function adds a CSS class to initiate the splash animation.
The stopSplash() function removes the CSS class to stop the splash animation.
Usage
To use this web page:

Open the HTML file in a web browser.
You'll see the welcome message along with the current date and a "Manage Users" button.
Click the "Manage Users" button to trigger the celebratory splash animation.
To stop the splash animation, simply reload the page.
