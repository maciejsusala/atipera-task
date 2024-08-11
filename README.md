# GitHub Repositories API

This application provides an API to list all non-fork GitHub repositories for a given user, along with branch information and the last commit SHA. It also handles cases where the GitHub user does not exist or there is to much number of request to API.

# Prerequisites
Java 21
Maven
Git

# Instalation
Clone the repository:  
git clone https://github.com/maciejsusala/atipera-task

Build the project using Maven:  
mvn clean install

# Usage
Run the application:  
mvn spring-boot:run

The application will start on http://localhost:8080


# API Documentation
List Non-Fork Repositories:
POST http://localhost:8080/api/v1/github/users/repos
Headers: 
Accept: application/json
Request Body:
{
    "username": "github_username"
}

Response:
[
    {
        "name": "repository_name",
        "ownerLogin": "owner_login",
        "branches": [
            {
                "name": "branch_name",
                "lastCommitSha": "commit_sha"
            }
        ]
    }
]

Handle Non-Existing GitHub User
Response:
{
    "status": 404,
    "message": "User not found"
}

# Notes
This application uses the GitHub API v3 as the backing API.

# Feign for JSON Processing
This application uses Feign as a declarative web service client to interact with the GitHub API. 
Feign simplifies the process of making HTTP requests and handling JSON responses by providing a clean and easy-to-use interface.
It automatically converts JSON responses into Java objects, making it easier to work with the data returned from the GitHub API.

# Usage of DTOs
DTOs (Data Transfer Objects) are used to encapsulate data and transfer it between different layers of the application.

# Testing
This application includes unit tests to ensure the correctness and reliability of the code. The following testing frameworks and tools are used:  
JUnit: For writing and running unit tests.
Mockito: For mocking dependencies in unit tests.

Running Tests:
To run the tests, use the following Maven command:
mvn: test

# Greetings
Hello Mr. Adam! 
If you've made it this far, I hope you enjoyed reading through the README.md. May your day be as bug-free as this code aims to be! 😄
