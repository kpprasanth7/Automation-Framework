Feature: Trivago Account Creation, Login and Hotel Booking

Scenario: User Creation, Login and report an error
Given Navigate to Trivago Web Application
When User clicks on Login Button
Then User clicks on Create an account button
And User enters email address, password 
When User clicks Register Button
Then User should reach Home Page and clicks Account settings option
And User select "I found an error while using trivago" as Issue topic
When User provides all the necessary Details
And Click Send Message Button
Then The Issue should be selected and the Success message is captured