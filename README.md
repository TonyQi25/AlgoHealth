# AlgoHealth
**CSC207 Final Project
Group Members:**
Matthew Simpson (Github username: matts1mpson),
Haoming (Tony) Qi (Github username: TonyQi25), 
Erin Chen (Github username: chenerin29),
Daniel Xie (Github username: DanielXie1795)

## **Use Cases:**

**User logs in:**

    User enters username and password. The system shows the user is logged in if the username exists and the password
    matches the username. If the username does not exist or the password does not match the username, the system shows
    the log in failed.

**User logs food:**

    User enters the type and amount of food they consumed. The system shows the nutrients and calories the user
    consumed and stores the information if the entered food is valid. If the food entered is invalid, the system 
    alerts the user that no such food exists.

**User logs out:**

    User logs out and the system shows that the user successfully logged out.

**Get General Recommendations:**

User presses on the “get recommendation” button to trigger a window with recommendations made based on the user’s 
previous input of food consumption.

**Looking back at history**:

The user selects a date to view their record of food intake on that day. If the date is in the past, the system shows
the user’s record of food intake on that day. If the date is in the future, the system alerts the user that there is
no record.

**Recipe creation:**

The user inputs a list of ingredients for their own recipe. The system calculates the total macronutrients if the food
inputted are valid. If the food entered is invalid, the system alerts the user that no such food exists.

**Edit food log:**

The user selects a date to revise the recorded daily consumption. The system records the new food intake the user 
inputs if the food is valid. If the food is not valid, the system alerts the user.

## ** Links for Sketches and Video: **
Link to view sketches:https://www.dropbox.com/scl/fi/yfsiq2puil3mbfrjzsddm/AlgoHealth-App-View-Sketches.pdf?rlkey=bt4sihznpxc7ekzqoys62ng9f&st=d0rvugd5&dl=0
Link to two demo videos: https://www.dropbox.com/scl/fo/9cvvaf0iet1wnstenxwlr/AF8dcD3_V9sccfPR6rwzTpM?rlkey=7qxdvztpsy3pzw4r7cvh56hry&st=tcbgcdhe&dl=0
Link to login demo video: https://www.dropbox.com/scl/fi/rvain97y9o0096qgan28n/10.11.2024_22.34.44_REC.mp4?rlkey=9wa8exbhwadcjdzsj2e84oqtp&st=qbmqszdx&dl=0

## ** Entities: **

Entities Food, DayInfo, and AccountInfo will be used in our project. Code for them has been written
and can be found in our repo in package "data". If we end up integrating recipe related features, we
will write and use a Recipe entity.

## ** Calling API: **

In package "api", a method in class callUsdaApi calls the USDA FoodData Central API and searches the database by
keyword. It returns the first result of the search. A method in populateFromUsda successfully creates a food entity
from this data under certain conditions relating to uniformity of data retrieved from the API. 