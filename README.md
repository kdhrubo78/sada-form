# Introduction

This is a Spring Boot Application that uses Camunda 8 tasklist API to render 

- Inbox (list of tasks)
- Dynamic Forms (custom form based on the form key in the task object and also another with form-io.js using schema)


This application makes use of :

 - Tasklist V2 API (branch : *main*)
 - Tasklist v1 API (deprecated and will be removed in Camunda 8.10) (branch: *tasklist_v1*)

Example Scenario:

1. Employee wants to go on a long term leave (e.g - paternity, family emergency).
2. Discusses with HR.
3. HR starts long term leave process. 
4. User receives leave application form. (This is a custom form rendered based on user task form key)
5. User fills out leave application form and submits form. 
6. Workflow then creates a leave review user task.
7. Manager views leave review application in his task Inbox (This is a form rendered based on form schema)
8. Manager approves or rejects the application.