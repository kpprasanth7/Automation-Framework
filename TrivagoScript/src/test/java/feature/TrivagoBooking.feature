Feature: Trivago Account Creation, Login and Hotel Booking 

Scenario: Booking Esence King Bed Room in Azaya Hotel Goa
Given Navigate to Trivago Web Application
When User searches hotels in "Goa" with the checkin, checkout dates and Number of adults "1"
When The user sort hotels list with guest rating more than "8"
When User should be able to find Azaya hotel and click on view deal button
Then The user should be navigated to Bookingdotcom website
When The user click search availability button
When Select "Essence Room King Bed" 
Then Provide all the information
Then The user should be navigated to the address details page