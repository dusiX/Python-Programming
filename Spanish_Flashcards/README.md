Language Flashcards App (Polish ↔ Spanish)
This is a desktop application built with Python and PyQt6, designed to help users practice and memorize Spanish vocabulary using a flashcard-style interface. The app integrates with an Excel file where words and their learning status are stored.

📸 Screenshots
- Main Menu
- Translate Prompt
- Result Dialog
- Add Word Dialog

🧠 Features
- Translate from Polish to Spanish and vice versa.

- Learn words based on their current learning status:
    - DO NAUCZENIA (To Learn)
    - DO POWTÓRKI (To Review)
    - NAUCZONE (Learned)
- Add new vocabulary pairs to the Excel sheet.
- Smart accent character buttons for easy Spanish input (á, é, í, ó, ú, ñ).

📁 File Structure
Your vocabulary is stored in an Excel workbook located at:
    ~/Desktop/Language_App/Words.xlsx

The app expects a worksheet named Wszystkie, with the following column layout:
Column | Data
A | Spanish Word
B | Polish Translation
C | Polish → Spanish learning status
D | Spanish → Polish learning status

🚀 How to Run
1. Make sure you have Python 3 installed.
2. Install dependencies:
    pip install openpyxl PyQt6
3. Ensure the Excel file is placed at the following location:
    ~/Desktop/Language_App/Words.xlsx
4. Run the app:
    python your_script_name.py

🧩 Components Overview
- Main Menu
    - "Translate Polish to Spanish"
    - "Translate Spanish to Polish"
    - "Add New Word"
- Translate Prompt
    - Randomly chooses a word based on progress status.
    - Shows result and correct answer feedback.
- Add New Word Dialog
    - Easily add new Spanish-Polish word pairs.
    - Uses quick-insert buttons for Spanish accents.

🔄 Learning Logic
The word selection is randomized but weighted:
- 60% chance to select from “DO NAUCZENIA”
- 30% from “DO POWTÓRKI”
- 10% from “NAUCZONE”
Each correct or incorrect answer updates the word’s status, helping you track your progress over time.

🛠️ Customization
- Want to change categories or add more languages?Modify the Excel structure and update the code accordingly.
- Want to switch UI language? Change dialog texts in InputDialog, ResultDialog, and WordEntryDialog.

✅ To-Do / Improvements
- Add sound/pronunciation support.
- Option to import/export vocabulary sets.
- Statistics or progress tracking.
- Dark mode.

📃 License
This project is provided as-is for personal use and educational purposes.
