# Quiz_ProjectOnJavaSE
This project demonstrates my knowledge of JavaSE
Job requirements:

1. Implement the main form (Main Form), which is the main menu. The main menu should contain the following attributes:
1.1. Button object with Label "From Internet". When the button is pressed, the Loading Form should be launched (see point 2), which implements the download of the quiz from the Internet.
1.2. Button object with Label "From File". When the button is clicked, the quiz will need to be loaded from a .json or .csv file. To do this, you must use an object of the FileChooser.ExtensionFilter class. The quiz folder selection dialog should remember the previously selected folder path, and not open the default folder (you can use the Preferences class for this). After selecting the file, the form responsible for the game (Game Form) should be launched.
1.3. CheckBox object with Label “Show Correct Answers”.

2. Implement a Loading Form that loads a quiz from the Internet according to the user's choice. To be able to select in the Loading Form, you need to create the following objects:
2.1 A TextField object called “Number of questions” that takes a parameter as an integer from 1 to 10.
2.2 Object ComboBox with Label "Select Category", with which the user can select the topic of the questions. You need to manually select 4 categories, from which the user will then select one.
2.3 ComboBox object with Label “Select Difficulty”, with which the user can select the difficulty of questions (one of Easy, Medium, Hard).
2.4 Button object with Label “Save”, when clicked, an API request should be generated to the page https://opentdb.com/api_config.php, where a quiz with the parameters selected earlier will be created. Next, a dialog box (File Chooser) should open to save the generated quiz in one of two possible formats - .json or .csv. The dialog box should remember the previously selected folder path, and not open the default folder (you can use Preferences for this). Also, correct and incorrect answers must be encrypted before being written to the file (you can use for example, cyclic shift, Caesar cipher, XOR ...). When attempting to save a quiz with unselected or incorrectly selected options to a file, an AlertMessage should be displayed and the folder selection dialog should not open. The objects are first converted by the Jackson parser into Java objects, and then the questions and answers must be decoded using the URLDecoder. Do this in the constructor of the Repository class.
2.5 Button object with Label “Start”, when the button is pressed, a Game Form should open, which takes data from the API. The form should not open if any of the quiz options are incorrect or not selected. Instead, AlertMessage should be displayed.

3. Implement the form (Game Form), which is a game menu. It must contain the following attributes:
3.1. Panel TabPane with tabs - quiz questions. Each quiz question is a Label object and the answer options are a RadioButton.
3.2. The last tab of the TabPane is named “Results”. The panel should contain a Button object named “Check”, which, when clicked:
• All questions are checked for answers. If not all questions have been answered, then an AlertMessage should be displayed with the appropriate message.
• TabPane “Results” displays statistics (Label Statistics) for each question in the format Question i : (+/-), Correct/Incorrect (eg 3/2) and Correct Answer Rate (60%). If “Show Correct Answers” was selected in Form 1, the correct answers for each question are also shown.
