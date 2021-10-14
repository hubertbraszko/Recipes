# Recipes Multi-User REST API

## Table of Contents
1. [Description](#description)
2. [How to build and run](#build)
3. [Available endpoints](#endpoints)
4. [Important notes](#notes)

<div id="description">

## Description 

The Recipes project is a multi-user web service build with Spring Boot and Spring Security that allows storing, retrieving, updating, and deleting recipes.
It supports user registration and basic auth or form login.

<div id="build">

## How to build and run 

1. Clone the repository
2. Inside the repository run ```./gradlew build``` and then ```./gradlew run```

<div id="endpoints">

## Available endpoints 

- ```POST /api/register```-Recives user data in JSON format for registration


    **Example**


    *POST /api/register*
    ```json
    {
        "email": "yourEmail@somewhere.com",
        "password": "yourPassword"
    }
    ```

    *Response:*
    
    ```
        User registered successfully
    ```

<br />
<br />

- ```POST /api/recipe/new``` -Receives a recipe as a JSON object and returns a JSON object with one id field. Provide credentials for basic auth to successfully post a recipe.


    **Example**
    
    *POST /api/recipe/new*
    ```json
    {
        "name": "Mint Tea",
        "category": "beverage",
        "description": "Light, aromatic and refreshing beverage, ...",
        "ingredients": ["boiled water", "honey", "fresh mint leaves"],
        "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
    }
    ```
    *Response:*
    
    ```json 
    {
        "id": 1
    }   
    ```
<br />
<br />



- ```GET /api/recipe/{id}```-Gets a recipe with specified id

    **Example** 
  

    *GET /api/recipe/1*

    *Response:*
    ```json
    {
        "name": "Mint Tea",
        "category": "beverage",
        "date": "13.10.2021 19:10:20.296985",
        "description": "Light, aromatic and refreshing beverage, ...",
        "ingredients": [
            "boiled water",
            "honey",
            "fresh mint leaves"
        ],
        "directions": [
            "Boil water",
            "Pour boiling hot water into a mug",
            "Add fresh mint leaves",
            "Mix and let the mint leaves seep for 3-5 minutes",
            "Add honey and mix again"
        ]
    }
    ```

<br />
<br />

- ```DELETE /api/recipe/{id}```-Deletes recipe with specified id. Ony the owner user of the recipe can delete it.

    **Example**

    *DELETE /api/recipe/1*

    *Responds with 204 No Content*

    Then if we try to get it:

    *GET /api/recipe/1*

    ```json
    {
        "timestamp": "2021-10-13T19:04:07.920+00:00",
        "status": 404,
        "error": "Not Found",
        "message": "Recipe not found for id = 1",
        "path": "/api/recipe/1"
    }
    ```

<br />
<br />

- ```PUT /api/recipe/{id}```-Updates recipe with specified id. Ony the owner user of the recipe can update it.

    **Example**

    *PUT /api/recipe/1*
    ```json
    {
        "name": "Mint Tea UPDATED",
        "category": "beverage",
        "description": "Light, aromatic and refreshing beverage, ...",
        "ingredients": [
            "boiled water",
            "honey",
            "fresh mint leaves"
        ],
        "directions": [
            "Boil water",
            "Pour boiling hot water into a mug",
            "Add fresh mint leaves",
            "Mix and let the mint leaves seep for 3-5 minutes",
            "Add honey and mix again"
        ]
    }
    ```

    *Responds with 204 No Content*

    Then if we try to get it:

    *GET /api/recipe/1*

    ```json
    {
        "name": "Mint Tea UPDATED",
        "category": "beverage",
        "date": "13.10.2021 20:11:35.925484",
        "description": "Light, aromatic and refreshing beverage, ...",
        "ingredients": [
            "boiled water",
            "honey",
            "fresh mint leaves"
        ],
        "directions": [
            "Boil water",
            "Pour boiling hot water into a mug",
            "Add fresh mint leaves",
            "Mix and let the mint leaves seep for 3-5 minutes",
            "Add honey and mix again"
        ]
    }
    ```
<br />
<br />

- ```GET /api/recipe/search```- takes one of the two mutually exclusive parameters: category or name. Retruns a JSON array of all recipes with the specified category or recipies that contain specified name.

    **Example**

    *GET /api/recipe/search/?name=tea*

    *Resposne:*
    ```json
    [
        {
            "name": "Fresh Mint Tea",
            "category": "beverage",
            "date": "13.10.2021 20:12:35.925484",
            ....
        },
        {
            "name": "warming ginger tea",
            "category": "beverage",
            "date": "13.10.2021 20:13:35.925484",
            ....
        },
        {
            "name": "Iced Tea Without Sugar",
            "category": "beverage",
            "date": "13.10.2021 20:14:35.925484",
            ....
        },
        {
            "name": "Mint Tea UPDATED",
            "category": "beverage",
            "date": "13.10.2021 20:11:35.925484",
            ....
        },
    ]
    ```
<br/>

<div id="notes">

## Important notes 

- **NOTE 1:** If you are getting 403 Forbidden for some endpoints, remember to provide your credentials for basic auth. <br/>
- **NOTE 2:** If you want to acces the database use ```/h2``` endpiont and login with credentials ```Login:admin```, ```Password:admin```.<br/>
- **NOTE 3:** Admin credentials are not supported for other endpionts, and are only there to access the database console.<br/> 
- **NOTE 4:** Application work on ```http://localhost:8881``` by default. You can change the port in application.properties file.