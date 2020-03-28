Welcome in the Trending Repositories API:
Developped By Badr Benbiga,
28-03-2020

1- About the API:

this API was developped in order to expose the last 30 repositories created in github
which was extracted from the url : github_url = https://api.github.com/search/repositories?q=created:>{date}&sort=stars&order=desc
when {date} should be replaced by any valid format date that respect the following one : yyy-dd-MM

2- How it works ?

1.1. Run the spring boot project in your local or in any other compatible environnement that include a JDK and a Maven repository
1.2. first we need to exporte the trending repositories in order to store them in our embedded DB
    For tht we use :
           POST ---> http://localhost:8080/repos/create
    while we passe in the body (as form-data via postman for example) that should fill in the place of {date} in the github_url

1.3. we get the Laguage repositories count by :

           GET ---> http://localhost:8080/repos/languages/count

1.4. we get the repositories by Language by :

           GET ---> http://localhost:8080/repos/languages

1.5. if you want to get Both 1.3 and 1.4 combined :

           GET ---> http://localhost:8080/repos/languages-and-count


Best regards,

Badr Benbiga