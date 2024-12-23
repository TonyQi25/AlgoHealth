# AlgoHealth
**CSC207 Final Project**
**Contributors:**
Matthew Simpson (Github username: matts1mpson),
Haoming (Tony) Qi (Github username: TonyQi25), 
Erin Chen (Github username: chenerin29),
Daniel Xie (Github username: DanielXie1795)

**Summary**
-

Algohealth is a program that supports food logging and nutrition calculations. In Algohealth, users can record their
daily consumption of food and view a summary of the macronutrients and calories in their daily consumptions, and
receive feedback on if they have reached the daily recommended amount of consumption.

**Features**
-

**Login**
- Log in to an existing AlgoHealth account with your username and password.
  - Logging in allows you to access information from a previous session.
  - This info is stored in your account automatically.

**Signup**
- Create a new AlgoHealth account to access the AlgoHealth Nutrition Tracker.
  - Requires:
    - Username
    - Password
    - Date of Birth
      - Default value is 1-1-(Current Year) (January 1st, Current Year)
    - Height
      - Empty fields are 0's
    - Weight
      - Empty fields are 0's
    - Diets
      - Default value is None
    - Dietary Restrictions
      - Default value is None
    - Main Nutrition Goal
      - Default value is Maintain Weight
- After signing up, you can use your new account username and password to log in for future uses.

**Logout**
- Logs out of a logged in AlgoHealth account from the main view and goes back to the login view

**History**
- view previously logged foods from user's past
- In this view you can remove or view one day's progress
- you can select food from the list provided to remove

**Remove Food**
- Select food from history to remove it from user's food log
- removal is saved into the local database

**One Day's Progress**
- view progress of calories, carbs, protein and fats of any day user has logged.
- shows progress bars and recommended values

**Food Search and Daily Value Recommendation**
- The user can search for a food and get back a wide variety of potential options.
- After selecting a food and portion amount, the user can perform a Daily Value calculation to see how much of the
  Daily Value the portion represents as a percentage.

**Installation**

First download Intellij then go to https://github.com/TonyQi25/AlgoHealth and copy the link in the code dropdown.
Create a new project by selecting "get from VCS" and copy the link in. Navigate to Main.java and run the file.


**Feedback**
-

If you have any feedback for the program, please fill out the google form at: https://forms.gle/1XztLeTQXUtwM2GRA
