gs-gatling-testing-service
=========================

### Students App
1. **Login with dashboard**
   - **Definition**: this is the first page that will run once the user is logged into the website, to test it out, simply execute the following commands
   - **Main Page**: mvn gatling:test -Dgatling.simulationClass=computerdatabase.StudentsFrontend.LoginWithDashboard.MainPage
   - **Lesson Page** mvn gatling:test -Dgatling.simulationClass=computerdatabase.StudentsFrontend.LoginWithDashboard.SeeLessonPage
2. **Assignments Section**:
   - **Definition**: Second section of the students, it loads all the pending and completed homework
   - **Main Page**: mvn gatling:test -Dgatling.simulationClass=computerdatabase.StudentsFrontend.HomeWork.MainPage
