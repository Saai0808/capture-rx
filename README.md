# Take Home Assignment
#Getting started (Running the application)
This is a Rest api application developed using Spring boot. It can be run as SpringBoot application and doesn't need any parameters. 

#APIs with in this application
This application consists of 3 different apis:
1. GET "/jobs/jobId?id=123" - Used to retrieve Job Info for ID = 123, and would listen to uncommited messages in kafka and would set their status to "In Progress" and would save to DB
2. POST "/jobs" - Would save a new job in database with status as "new"
3. PUT "/jobs/jobId/{id}" - Would retrieve job from DB with ID = "{id}" and status is updated to "done" in DB after 1-5 seconds